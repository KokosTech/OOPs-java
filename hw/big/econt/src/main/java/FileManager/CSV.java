package FileManager;

import Order.Address;
import Order.Order;
import Order.Status;
import Package.Package;
import Package.Types.BigPackage;
import Package.Types.MediumPackage;
import Package.Types.SalablePackage;
import Package.Types.SmallPackage;
import User.Roles;
import User.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CSV extends FileManager {
    public CSV(String dirPath) {
        super(dirPath, ".csv");
    }

    private File openFile(String fileName) {
        return new File(this.dirPath + fileName + this.fileExtension);
    }

    public ArrayList<User> getUsers() {
        try (Scanner scanner = new Scanner(openFile("users"))) {
            ArrayList<User> users = new ArrayList<User>();

            while (scanner.hasNextLine()) {
                String[] values = scanner.nextLine().split(DELIMITER);

                Long id = Long.parseLong(values[0]);
                String username = values[1];
                String password = values[2];
                Roles role = Roles.valueOf(values[3]);

                users.add(new User(id, username, password, role));
            }
            scanner.close();
            return users;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File 'users' not found");
        }
    }

    public HashMap<Long, Address> getAddresses() {
        try (Scanner scanner = new Scanner(openFile("addresses"))) {
            HashMap<Long, Address> addresses = new HashMap<>();

            while (scanner.hasNextLine()) {
                String[] values = scanner.nextLine().split(DELIMITER);

                Long id = Long.parseLong(values[0]);
                String country = values[1];
                String city = values[2];
                String street = values[3];
                Long userId = Long.parseLong(values[4]);

                addresses.put(id, new Address(id, country, city, street, userId));
            }

            scanner.close();
            return addresses;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File 'addresses' not found");
        }
    }

    public Set<Package> getPackages(Long orderId) {
        try (Scanner scanner = new Scanner(openFile("packages"))) {
            Set<Package> packages = new HashSet<>();

            while (scanner.hasNextLine()) {
                String[] values = scanner.nextLine().split(DELIMITER);

                Long id = Long.parseLong(values[0]);
                Long csvOrderId = Long.parseLong(values[1]);

                if (!csvOrderId.equals(orderId)) {
                    continue;
                }
                Integer size = Integer.parseInt(values[2]);
//                if(values[2] != "null") {
//                     size = Integer.parseInt(values[2]);
//                }
                Double price;

                if (values.length == 4) {
                    price = Double.parseDouble(values[3]);
                    if (size > 6 && size < 10) {
                        packages.add(new SalablePackage(new BigPackage(id, size), price));
                    } else if (size > 3 && size < 6) {
                        packages.add(new SalablePackage(new MediumPackage(id, size), price));
                    } else if (size > 0 && size < 3) {
                        packages.add(new SalablePackage(new SmallPackage(id, size), price));
                    }
                } else {
                    if (size > 6 && size < 10) {
                        packages.add(new BigPackage(id, size));
                    } else if (size > 3 && size < 6) {
                        packages.add(new MediumPackage(id, size));
                    } else if (size > 0 && size < 3) {
                        packages.add(new SmallPackage(id, size));
                    }
                }
            }

            scanner.close();
            return packages;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File 'packages' not found");
        }
    }

    public HashMap<Long, Order> getOrders() {
        try (Scanner scanner = new Scanner(openFile("orders"))) {
            HashMap<Long, Order> orders = new HashMap<>();

            while (scanner.hasNextLine()) {
                String[] values = scanner.nextLine().split(DELIMITER);

                Long id = Long.parseLong(values[0]);
                Set<Package> packages = getPackages(id);
                Long addressId = Long.parseLong(values[1]);
                Status status = Status.valueOf(values[2]);

                orders.put(id, new Order(id, packages, addressId, status));
            }

            scanner.close();
            return orders;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File 'orders' not found");
        }
    }

    public void writeUser(User user) throws IOException {
        try (FileWriter fileWriter = new FileWriter(openFile("users"), true)) {
            String row = String.format(
                    "%d, %s, %s, %s\n",
                    user.getId(), user.getUsername(), user.getPassword(), user.getRole().toString()
            );

            fileWriter.write(row);
        }
    }

    public void writeAddress(Address address) throws IOException {
        try (FileWriter fileWriter = new FileWriter(openFile("addresses"), true)) {
            String row = String.format(
                    "%d, %s, %s, %s, %d\n",
                    address.getId(), address.getCountry(), address.getCity(), address.getStreet(), address.getUserId()
            );

            fileWriter.write(row);
        }
    }

    public void writePackage(Package deliveryPackage, Long orderId) throws IOException {
        try (FileWriter fileWriter = new FileWriter(openFile("packages"), true)) {
            String row;

            if (deliveryPackage instanceof SalablePackage) {
                row = String.format(
                        "%d, %d, %d, %f\n",
                        deliveryPackage.getId(), orderId, deliveryPackage.getSize(), ((SalablePackage) deliveryPackage).getPrice()
                );
            } else {
                row = String.format(
                        "%d, %d, %d\n",
                        deliveryPackage.getId(), orderId, deliveryPackage.getSize()
                );
            }

            fileWriter.write(row);
        }
    }

    public void writeOrder(Order order) throws IOException {
        try (FileWriter fileWriter = new FileWriter(openFile("orders"), true)) {
            String row = String.format(
                    "%d, %d, %s\n",
                    order.getId(), order.getAddressId(), order.getStatus().toString()
            );

            fileWriter.write(row);

            for (Package deliveryPackage : order.getPackages()) {
                writePackage(deliveryPackage, order.getId());
            }
        }
    }

    public void updateOrders(HashMap<Long, Order> orders) throws IOException {
        try (FileWriter fileWriter = new FileWriter(openFile("orders"))) {
            for (Order order : orders.values()) {
                String row = String.format(
                        "%d, %d, %s\n",
                        order.getId(), order.getAddressId(), order.getStatus().toString()
                );

                fileWriter.write(row);
            }
        }
    }
}

