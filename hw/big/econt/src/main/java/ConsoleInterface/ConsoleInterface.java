package ConsoleInterface;

import DeliverySystem.DeliverySystem;
import FileManager.CSV;
import FileManager.FileManager;
import Order.Address;
import Order.Order;
import Package.Package;
import Package.Types.BigPackage;
import Package.Types.MediumPackage;
import Package.Types.SalablePackage;
import Package.Types.SmallPackage;
import User.Roles;
import User.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static Order.Status.CREATED;
import static User.Roles.CUSTOMER;
import static User.Roles.DRIVER;

public class ConsoleInterface {
    private final Scanner sc = new Scanner(System.in);
    private final DeliverySystem ds;
    private final FileManager fm;

    public ConsoleInterface(String dirPath) throws FileNotFoundException {
        this.fm = new CSV(dirPath);
        this.ds = new DeliverySystem(fm);
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // menu options

    private void printMenu() {
        clearScreen();
        System.out.print("""
                   === Econt Delivery System (Java Edition) === 
                   1. Login (if not logged in)
                   2. Logout (if logged in)
                   3. Register (if not logged in)
                   4. Add an address (if logged in as customer)
                   5. Add an order (if logged in as customer)
                   6. Add a package (if logged in as customer)
                   7. Get an order to deliver (if logged in as driver)
                   8. Deliver an order (if logged in as driver)
                   9. Exit
                   
                   Enter your choice (1-9):
                """);
    }

    private void printPackageMenu() {
        System.out.print("""
                   === Add a package ===
                   1. Add a normal package
                   2. Add a salable package
                   
                   Enter your choice (1-2):
                """);
    }

    private void printPackageTypeMenu() {
        clearScreen();
        System.out.print("""
                   === Choose package size ===
                        1. Small (size: 1-3)
                        2. Middle (size: 4-6)
                        3. Big (size: 7-9)
                   
                   Enter your choice (1-3):
                """);
    }

    private void printRegisterMenu() {
        clearScreen();
        System.out.print("""
                   === Register as ===
                        1. Customer
                        2. Driver
                   
                   Enter your choice (1-2):
                """);
    }

    private void printDeliveryMenu() {
        clearScreen();
        System.out.print("""
                   === Deliver an order ===
                        1. Deliver random order
                        2. Deliver specific order (by ID)
                   
                   Enter your choice (1-2):
                """);
    }

    public void start() {
        // main loop
        while (true) {
            // print menu
            printMenu();
            // get user input
            Integer choice = sc.nextInt();
            sc.nextLine();
            // handle user input
            switch (choice) {
                case 1 -> {
                    // login
                    login();
                }
                case 2 -> {
                    // register
                    logout();
                }
                case 3 -> {
                    // logout
                    register();
                }
                case 4 -> {
                    // add an address
                    addAddress();
                }
                case 5 -> {
                    // add an order
                    addOrder();
                }
                case 6 -> {
                    // add a package
                    addPackage();
                }
                case 7 -> {
                    // get an order to deliver
                    getOrderToDeliver();
                }
                case 8 -> {
                    // deliver an order
                    deliverAnOrder();
                }
                case 9 -> {
                    // exit
                    sc.close();
                    return;
                }
            }
        }
    }

    private void login() {
        HashMap<String, String> input = getUsernamePassword("login");

        try {
            ds.login(input.get("username"), input.get("password"));
            System.out.println("Successfully logged in! -> " + ds.getCurrentUser() + " <-" + input);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void register() {
        HashMap<String, String> input = getUsernamePassword("register");

        printRegisterMenu();
        Roles role = sc.nextInt() == 1 ? CUSTOMER : DRIVER;
        sc.nextLine();

        String username = input.get("username");
        String password = input.get("password");

        try {
            switch (role) {
                case CUSTOMER -> {
                    User user = ds.register(username, password, CUSTOMER);
                    fm.writeUser(user);
                }
                case DRIVER -> {
                    User user = ds.register(username, password, DRIVER);
                    fm.writeUser(user);
                }
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void logout() {
        try {
            ds.logout();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addAddress() {
        try {
            Address addr = ds.addAddress(getAddress());
            fm.writeAddress(addr);
        } catch (RuntimeException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addOrder() {
        try {
            Order order = ds.addOrder(getOrder());
            fm.writeOrder(order);

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addPackage() {
        try {
            System.out.print("Enter order id: ");
            Long orderId = sc.nextLong();

            Package newDeliveryPackage = ds.addPackage(getPackage(), orderId);
            fm.writePackage(newDeliveryPackage, orderId);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void getOrderToDeliver() {
        try {
            printDeliveryMenu();
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    ds.getOrderToDeliver();
                    fm.updateOrders(ds.getOrders());
                }
                case 2 -> {
                    System.out.print("Enter order id: ");
                    Long orderId = sc.nextLong();
                    ds.getOrderToDeliver(orderId);
                    fm.updateOrders(ds.getOrders());
                }
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deliverAnOrder() {
        try {
            System.out.print("Enter order id: ");
            Long orderId = sc.nextLong();
            ds.deliverOrder(orderId);
            fm.updateOrders(ds.getOrders());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Input Methods

    HashMap<String, String> getUsernamePassword(String template) {
        HashMap<String, String> usernamePassword = new HashMap<>();

        clearScreen();
        System.out.printf("=== %s ===\n", template);

        sc.nextLine();
        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        usernamePassword.put("username", username);
        usernamePassword.put("password", password);

        return usernamePassword;
    }

    Address getAddress() {
        clearScreen();
        System.out.println("=== add an address ===");

        System.out.print("Enter country: ");
        String country = sc.nextLine();

        System.out.print("Enter city: ");
        String city = sc.nextLine();

        System.out.print("Enter street: ");
        String street = sc.nextLine();

        System.out.print("Enter user id: ");
        Long userId = sc.nextLong();

        return new Address(country, city, street, userId);
    }

    public Order getOrder() {
        clearScreen();
        System.out.println("=== add an order ===");

        System.out.print("Enter address id: ");
        Long addressId = sc.nextLong();

        Set<Package> packages = new HashSet<>();

        System.out.print("Enter number of packages: ");
        int packagesCount = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < packagesCount; ++i) {
            packages.add(getPackage());
        }

        return new Order(packages, addressId, CREATED);
    }

    public Package getNormalPackage() {
        printPackageTypeMenu();
        Integer choice = sc.nextInt();
        sc.nextLine();

        if (choice < 1 || choice > 3) {
            throw new RuntimeException("Invalid choice in package type selection");
        }

        System.out.print("Enter size: ");
        Integer size = sc.nextInt();
        sc.nextLine();

        return switch (choice) {
            case 1 -> new SmallPackage(size);
            case 2 -> new MediumPackage(size);
            case 3 -> new BigPackage(size);
            default -> throw new RuntimeException("Invalid choice in package type selection");
        };
    }

    public Package getSalablePackage() {
        printPackageTypeMenu();
        Integer choice = sc.nextInt();
        sc.nextLine();

        if (choice < 1 || choice > 3) {
            throw new RuntimeException("Invalid choice in package type selection");
        }

        System.out.print("Enter size: ");
        Integer size = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter price: ");
        Double price = sc.nextDouble();
        sc.nextLine();

        return switch (choice) {
            case 1 -> new SalablePackage(new SmallPackage(size), price);
            case 2 -> new SalablePackage(new MediumPackage(size), price);
            case 3 -> new SalablePackage(new BigPackage(size), price);
            default -> throw new RuntimeException("Invalid choice in package type selection");
        };
    }

    public Package getPackage() {
        printPackageMenu();
        Integer choice = sc.nextInt();
        sc.nextLine();

        return switch (choice) {
            case 1 -> getNormalPackage();
            case 2 -> getSalablePackage();
            default -> throw new RuntimeException("Invalid choice in package selection");
        };
    }
}
