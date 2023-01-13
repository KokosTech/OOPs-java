package DeliverySystem;

import FileManager.FileManager;
import Order.Address;
import Order.Order;
import Order.Status;
import Package.Package;
import User.Roles;
import User.User;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class DeliverySystem {
    private HashMap<Long, User> users;
    private HashMap<Long, Order> orders;
    private HashMap<Long, Address> addresses;
    private User currentUser;

    public DeliverySystem(@NotNull FileManager fileManager) {
        users = new HashMap<>();
        addresses = new HashMap<>();
        orders = new HashMap<>();

        for (User user : fileManager.getUsers()) {
            isValidUser(user);
            users.put(user.getId(), user);
        }

        for (Map.Entry<Long, Address> entry : fileManager.getAddresses().entrySet()) {
            isValidAddress(entry.getValue());
            addresses.put(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<Long, Order> entry : fileManager.getOrders().entrySet()) {
            isOrderValid(entry.getValue());
            orders.put(entry.getKey(), entry.getValue());
        }

        this.currentUser = createInitAdminUser();
        users.put(currentUser.getId(), currentUser);
    }

    @Contract(value = " -> new", pure = true)
    private @NotNull User createInitAdminUser() {
        return new User(0L, "admin", "admin123", Roles.ADMINISTRATOR); // id of 0, because, it's an admin account
    }

    public void isValidUser(@NotNull User user) {
        if (users.containsKey(user.getId())) {
            throw new IllegalArgumentException("User already exists");
        }
    }

    private void isValidAddress(Address address) {
        if (addresses.containsKey(address.getId())) {
            throw new IllegalArgumentException("Address already exists");
        }
    }

    private void isOrderValid(Order order) {
        if (orders.containsKey(order.getId())) {
            throw new IllegalArgumentException("Order already exists");
        }

        if (order.getPackagesCount() == 0) {
            throw new IllegalArgumentException(
                    String.format("No packages in order id=%d", order.getId())
            );
        }

        if (!addresses.containsKey(order.getAddressId())) {
            throw new IllegalArgumentException(
                    String.format("Address with id=%d does not exists", order.getAddressId())
            );
        }
    }

    private void auth(Roles role) {
        if (currentUser == null) {
            throw new RuntimeException("There is no active current user");
        }

        if (currentUser.getRole() != role) {
            throw new RuntimeException("Current user is not authorized to perform this operation");
        }
    }

    public User login(String username, String password) {
        for (User user : users.values()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                return user;
            }
        }

        throw new IllegalArgumentException(
                String.format("User with username=%s and password=%s does not exists", username, password)
        );
    }

    public void logout() {
        if (currentUser == null) {
            throw new RuntimeException("No user logged in!");
        }

        this.currentUser = null;
    }

    public User register(String username, String password, Roles role) {
        auth(Roles.ADMINISTRATOR);

        for (User user : users.values()) {
            if (user.getUsername().equals(username)) {
                throw new IllegalArgumentException(String.format("User with username=%s already exists", username));
            }
        }

        User newUser = new User(username, password, role);

        users.put(newUser.getId(), newUser);

        return newUser;
    }

    public Address addAddress(Address address) {
        auth(Roles.CUSTOMER);
        isValidAddress(address);

        if (!Objects.equals(currentUser.getId(), address.getUserId())) {
            throw new IllegalArgumentException(
                    String.format(
                            "Current user id=%d is different from address user id=%d",
                            currentUser.getId(), address.getUserId()
                    )
            );
        }

        addresses.put(address.getId(), address);

        return address;
    }

    public Order addOrder(Order order) {
        auth(Roles.CUSTOMER);
        isOrderValid(order);

        Address address = addresses.get(order.getAddressId());

        if (!Objects.equals(address.getUserId(), currentUser.getId())) {
            throw new IllegalArgumentException(
                    String.format(
                            "Current user id=%d does not match with address user id=%d",
                            currentUser.getId(), address.getUserId()
                    )
            );
        }

        order.setStatus(Status.CREATED);
        orders.put(order.getId(), order);

        return order;
    }

    public Package addPackage(Package deliveryPackage, Long orderId) {
        auth(Roles.CUSTOMER);

        if (!orders.containsKey(orderId)) {
            throw new IllegalArgumentException(String.format("Order with id=%d does not exists", orderId));
        }

        Order order = orders.get(orderId);
        Address address = addresses.get(order.getAddressId());

        if (!Objects.equals(address.getUserId(), currentUser.getId())) {
            throw new IllegalArgumentException(
                    String.format(
                            "Current user id=%d does not match with address user id=%d",
                            currentUser.getId(), address.getUserId()
                    )
            );
        }

        order.addPackage(deliveryPackage);

        return deliveryPackage;
    }

    private Order getRandomOrderToDeliver() {
        ArrayList<Order> createdOrders = new ArrayList<>();

        for (Order order : orders.values()) {
            if (order.getStatus() == Status.CREATED) {
                createdOrders.add(order);
            }
        }

        if (createdOrders.size() == 0) {
            throw new RuntimeException("There are no available orders to deliver");
        }

        int index = new Random().nextInt(createdOrders.size());
        return createdOrders.get(index);
    }

    public Long getOrderToDeliver() {
        auth(Roles.DRIVER);

        Order order = getRandomOrderToDeliver();
        order.setStatus(Status.DELIVERING);

        return order.getId();
    }

    public Long getOrderToDeliver(Long id) {
        auth(Roles.DRIVER);

        if (!orders.containsKey(id)) {
            throw new IllegalArgumentException(String.format("Order with id=%d does not exists", id));
        }

        Order order = orders.get(id);

        if (order.getStatus() != Status.CREATED) {
            throw new RuntimeException(String.format("Order with id=%d has no status CREATED", id));
        }

        order.setStatus(Status.DELIVERING);
        return order.getId();
    }

    public void deliverOrder(Long id) {
        auth(Roles.DRIVER);

        if (!orders.containsKey(id)) {
            throw new IllegalArgumentException(String.format("Order with id=%d does not exists", id));
        }

        Order order = orders.get(id);

        if (order.getStatus() != Status.DELIVERING) {
            throw new RuntimeException(String.format("Order with id=%d has no status DELIVERING", id));
        }

        order.setStatus(Status.DELIVERED);
    }

    public int getUsersCount() {
        return users.size();
    }

    public int getAddressesCount() {
        return addresses.size();
    }

    public HashMap<Long, Order> getOrders() {
        return orders;
    }

    public int getOrdersCount() {
        return orders.size();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public ArrayList<User> getUsers() {
        return new ArrayList<>(users.values());
    }

}
