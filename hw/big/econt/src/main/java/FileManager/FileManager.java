package FileManager;

import Order.Address;
import Order.Order;
import Package.Package;
import Package.Types.BigPackage;
import Package.Types.MediumPackage;
import Package.Types.SmallPackage;
import User.User;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public abstract class FileManager {
    protected static final String DELIMITER = ", ";
    protected static final String NEW_LINE = "\n";
    protected final String fileExtension; // .csv or .json

    protected final String dirPath;

    public FileManager(String dirPath, String fileExtension) {
        if (dirPath == null || dirPath.isEmpty()) {
            throw new IllegalArgumentException("Path to directory cannot be empty!");
        }

        if (dirPath.charAt(dirPath.length() - 1) != '/') {
            dirPath += "/";
        }

        this.dirPath = dirPath;


        if (fileExtension == null || fileExtension.isEmpty()) {
            throw new IllegalArgumentException("File extension cannot be empty!");
        }

        if (fileExtension.charAt(0) != '.') {
            fileExtension = "." + fileExtension;
        }

        this.fileExtension = fileExtension;
    }

    // Initialize files if they don't exist

    protected void initFiles() {
        try {
            File usersFile = new File(dirPath + "users" + fileExtension);
            if (!usersFile.exists()) {
                usersFile.createNewFile();
            }

            File addressesFile = new File(dirPath + "addresses" + fileExtension);
            if (!addressesFile.exists()) {
                addressesFile.createNewFile();
            }

            File packagesFile = new File(dirPath + "packages" + fileExtension);
            if (!packagesFile.exists()) {
                packagesFile.createNewFile();
            }

            File ordersFile = new File(dirPath + "orders" + fileExtension);
            if (!ordersFile.exists()) {
                ordersFile.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while initializing files");
        }
    }

    // Helper Methods

    protected Package getPackageType(Long id, Integer size) {
        if (size > 0 && size < 4) {
            return new SmallPackage(id, size);
        } else if (size > 3 && size < 7) {
            return new MediumPackage(id, size);
        } else if (size > 6 && size < 10) {
            return new BigPackage(id, size);
        } else {
            throw new IllegalArgumentException("Package size must be between 1 and 9 (not inclusive)");
        }
    }

    protected Boolean userAlreadyExists(User user, @NotNull ArrayList<User> users) {
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername())) {
                return true;
            }
        }
        return false;
    }

    // Reading Methods

    public abstract ArrayList<User> getUsers();

    public abstract HashMap<Long, Address> getAddresses();

    public abstract Set<Package> getPackages(Long orderId);

    public abstract HashMap<Long, Order> getOrders();

    // Writing Methods

    public abstract void writeUser(User user) throws IOException;

    public abstract void writeAddress(Address address) throws IOException;

    public abstract void writePackage(Package deliveryPackage, Long orderId) throws IOException;

    public abstract void writeOrder(Order order) throws IOException;

    // Updating Methods

    public abstract void updateOrders(HashMap<Long, Order> orders) throws IOException;
}
