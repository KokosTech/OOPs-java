package DeliverySystemTests;

import DeliverySystem.DeliverySystem;
import FileManager.CSV;
import FileManager.FileManager;
import Order.Address;
import Order.Order;
import User.Roles;
import User.User;
import jdk.jfr.Name;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class DeliverySystemTest {
    ArrayList<User> users;
    HashMap<Long, Address> addresses;
    HashMap<Long, Order> orders;
    FileManager fm;
    DeliverySystem deliverySystem;

    @BeforeEach
    void setup() {
        fm = new CSV("/Users/kaloyandoychinov/projects/OOPs-java/hw/big/econt/src/test/java/TestData");
        deliverySystem = new DeliverySystem(fm);
    }

    @Test
    void testLogin() {
        deliverySystem.login("admin", "admin123");
        assertEquals(deliverySystem.getCurrentUser().getUsername(), "admin");
        assertEquals(deliverySystem.getCurrentUser().getPassword(), "admin123");
        assertEquals(deliverySystem.getCurrentUser().getRole(), Roles.ADMINISTRATOR);
    }

    @Test
    @Name("with invalid credentials")
    void testLoginWithInvalidCredentials() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> deliverySystem.login("admin", "admin1234"));
        assertEquals("User with username=" + "admin" + " and password=" + "admin1234" + " does not exists", exception.getMessage());
    }

    @Test
    void testLogout() {
        deliverySystem.login("admin", "admin123");
        deliverySystem.logout();
        assertNull(deliverySystem.getCurrentUser());
    }

    @Test
    @Name("with no logged in user")
    void testLogoutWithNoLoggedInUser() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> deliverySystem.logout());
        assertEquals("No user logged in!", exception.getMessage());
    }

    @Test
    void testRegisterUser() {
        deliverySystem.login("admin", "admin123");
        User usr = deliverySystem.register("test", "test123", Roles.CUSTOMER);
        assertEquals(usr.getUsername(), "test");
        assertEquals(usr.getPassword(), "test123");
        assertEquals(usr.getRole(), Roles.CUSTOMER);

        deliverySystem.login("test", "test123");
        assertEquals(deliverySystem.getCurrentUser().getUsername(), "test");
        assertEquals(deliverySystem.getCurrentUser().getPassword(), "test123");
    }

    @Test
    void testAddAddress() {
        deliverySystem.login("admin", "admin123");
        User usr = deliverySystem.register("test", "test123", Roles.CUSTOMER);
        deliverySystem.login("test", "test123");

        // Long id, String country, String city, String street, Long userId
        Address address = deliverySystem.addAddress(new Address(6L, "Bulgaria", "Sofia", "Vitosha", usr.getId()));
        assertEquals(address.getCountry(), "Bulgaria");
        assertEquals(address.getCity(), "Sofia");
        assertEquals(address.getStreet(), "Vitosha");
        assertEquals(address.getUserId(), usr.getId());
        assertEquals(address.getId(), 6L);
    }

    @Test
    void testAddAddressExisting() {
        deliverySystem.login("admin", "admin123");
        User usr = deliverySystem.register("test", "test123", Roles.CUSTOMER);
        deliverySystem.login("test", "test123");

        // Long id, String country, String city, String street, Long userId
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> deliverySystem.addAddress(new Address(1L, "Bulgaria", "Sofia", "Vitosha", usr.getId())));
        assertEquals("Address already exists", exception.getMessage());
    }

    @Test
    void register_validAdminUser_addsNewUserToUsers() {
        User admin = deliverySystem.login("admin", "admin123");
        User newUser = deliverySystem.register("newuser", "newpassword", Roles.CUSTOMER);

        assertNotNull(newUser);
        assertEquals("newuser", newUser.getUsername());
        assertEquals("newpassword", newUser.getPassword());
        assertEquals(Roles.CUSTOMER, newUser.getRole());
        assertTrue(deliverySystem.getUsers().contains(newUser));
    }

    @Test
    void register_nonAdminUser_throwsRuntimeException() {
        deliverySystem.login("admin", "admin123");
        User user = deliverySystem.register("user", "user123", Roles.CUSTOMER);
        deliverySystem.login("user", "user123");
        assertThrows(RuntimeException.class, () -> deliverySystem.register("newuser", "newpassword", Roles.CUSTOMER));
    }

    @Test
    void isValidUser_validUser_doesNotThrowException() {
        User user = new User(7L, "user", "password", Roles.CUSTOMER);

        assertDoesNotThrow(() -> deliverySystem.isValidUser(user));
    }

    @Test
    void isValidUser_duplicateUser_throwsIllegalArgumentException() {
        User user = new User(1L, "user", "password", Roles.CUSTOMER);
        deliverySystem.getUsers().add(user);

        assertThrows(IllegalArgumentException.class, () -> deliverySystem.isValidUser(user));
    }


}


