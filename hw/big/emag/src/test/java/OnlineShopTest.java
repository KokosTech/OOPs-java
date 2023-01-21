import Product.Category;
import Product.Product;
import Promotion.Type.CategoryPercentPromotion;
import Promotion.Type.CategoryValuePromotion;
import Promotion.Type.ProductPercentPromotion;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OnlineShopTest {
    OnlineShop shop;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        shop = new OnlineShop();
    }

    @org.junit.jupiter.api.Test
    void register() {
        shop.register("username", "password");
        assertTrue(shop.users.containsKey("username"));

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            shop.register("username", "password");
        });
        assertEquals("User already exists", exception.getMessage());

        Throwable exception2 = assertThrows(IllegalArgumentException.class, () -> {
            shop.register("", "");
        });
        assertEquals("Username cannot be null or empty", exception2.getMessage());

        Throwable exception3 = assertThrows(IllegalArgumentException.class, () -> {
            shop.register("username2", "");
        });
        assertEquals("Password cannot be null or empty", exception3.getMessage());
    }

    @org.junit.jupiter.api.Test
    void login() {
        shop.register("username", "password");
        shop.login("username", "password");

        assertEquals(shop.users.get("username"), shop.activeUser);

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            shop.login("username", "password2");
        });
        assertEquals("Wrong password", exception.getMessage());

        Throwable exception2 = assertThrows(IllegalArgumentException.class, () -> {
            shop.login("username2", "password");
        });
        assertEquals("User does not exist", exception2.getMessage());
    }

    @org.junit.jupiter.api.Test
    void logout() {
        shop.register("username", "password");
        shop.login("username", "password");
        shop.logout();
        assertNull(shop.activeUser);
    }

    @org.junit.jupiter.api.Test
    void addCategory() {
        shop.register("username", "password");
        shop.login("username", "password");

        Category category = new Category("category");
        shop.addCategory(category);
        assertTrue(shop.categories.contains(category));

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            shop.addCategory(category);
        });
        assertEquals("Category already exists", exception.getMessage());

        shop.logout();
        Throwable exception2 = assertThrows(IllegalArgumentException.class, () -> {
            shop.addCategory(category);
        });
        assertEquals("You must be logged in to add a category", exception2.getMessage());
    }

    @org.junit.jupiter.api.Test
    void addProduct() {
        shop.register("username", "password");
        shop.login("username", "password");

        Category category = new Category("category");
        shop.addCategory(category);
        Product product = new Product("product", "description", 10.0, category);

        Throwable exception2 = assertThrows(IllegalArgumentException.class, () -> {
            shop.addProduct(product, -10);
        });
        assertEquals("Quantity must be positive", exception2.getMessage());

        shop.addProduct(product, 10);
        assertTrue(shop.products.containsKey(product));

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            shop.addProduct(product, 10);
        });
        assertEquals("Product already exists", exception.getMessage());

        shop.logout();
        Throwable exception3 = assertThrows(IllegalArgumentException.class, () -> {
            shop.addProduct(product, 10);
        });
        assertEquals("You must be logged in to add a product", exception3.getMessage());
    }

    @org.junit.jupiter.api.Test
    void addPromotion() {
        shop.register("username", "password");
        shop.login("username", "password");

        Category category = new Category("category");
        shop.addCategory(category);
        Product product = new Product("product", "description", 10.0, category);
        shop.addProduct(product, 10);

        Map<Long, Product> productsForProm = new HashMap<Long, Product>();
        productsForProm.put(product.getId(), product);

        shop.addPromotion(new ProductPercentPromotion("prodictPercentPromotion", "desct", productsForProm, 10.0));

        assertTrue(shop.promotions.contains(new ProductPercentPromotion("prodictPercentPromotion", "desct", productsForProm, 10.0)));

        shop.logout();
        Throwable exception2 = assertThrows(IllegalArgumentException.class, () -> {
            shop.addPromotion(new ProductPercentPromotion("prodictPercentPromotion", "desct", productsForProm, 10.0));
        });
        assertEquals("No active user", exception2.getMessage());
    }

    @org.junit.jupiter.api.Test
    void listProducts() {
        shop.register("username", "password");
        shop.login("username", "password");

        Category category = new Category("category");
        shop.addCategory(category);
        Product product = new Product("product", "description", 10.0, category);
        shop.addProduct(product, 10);

        shop.listProducts(category);
        assertTrue(shop.products.containsKey(product));

        shop.logout();
        Throwable exception2 = assertThrows(IllegalArgumentException.class, () -> {
            shop.listProducts(null);
        });
        assertEquals("No active user", exception2.getMessage());
    }

    @org.junit.jupiter.api.Test
    void listCategories() {

    }

    @org.junit.jupiter.api.Test
    void buyProduct() {
        shop.register("username", "password");
        shop.login("username", "password");

        Category category = new Category("category");
        shop.addCategory(category);
        Product product = new Product("product", "description", 10.0, category);
        shop.addProduct(product, 10);

        shop.buyProduct(product.getId(), 5);
        assertEquals(5, shop.activeShoppingCart.getProducts().get(product));

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            shop.buyProduct(product.getId(), 11);
        });
        assertEquals("Not enough products in stock", exception.getMessage());

        shop.logout();
        Throwable exception2 = assertThrows(IllegalArgumentException.class, () -> {
            shop.buyProduct(product.getId(), 5);
        });
        assertEquals("No active user", exception2.getMessage());
    }

    @org.junit.jupiter.api.Test
    void checkout() {
        shop.register("user", "password");
        shop.login("user", "password");

        Category electronicsCat = new Category("Electronics");
        Category clothesCat = new Category("Clothes");
        Category booksCat = new Category("Books");
        Category foodCat = new Category("Food");

        shop.addCategory(electronicsCat);
        shop.addCategory(clothesCat);
        shop.addCategory(booksCat);
        shop.addCategory(foodCat);

        shop.listCategories();

        Product product1 = new Product("iPhone 12", "New iPhone", 1000.0, electronicsCat);
        Product product2 = new Product("iPhone 12 Pro", "New iPhone", 1200.0, electronicsCat);
        Product product3 = new Product("iPhone 12 Pro Max", "New iPhone", 1400.0, electronicsCat);
        Product product4 = new Product("iPhone 12 Mini", "New iPhone", 800.0, electronicsCat);
        Product product5 = new Product("iPhone 11", "Old iPhone", 700.0, electronicsCat);

        shop.addProduct(product1, 10);
        shop.addProduct(product2, 10);
        shop.addProduct(product3, 10);
        shop.addProduct(product4, 10);
        shop.addProduct(product5, 10);

        shop.listProducts(null);
        shop.listProducts(electronicsCat);

        shop.buyProduct(product1.getId(), 1);

        Set<Category> gayPromoCategories = new HashSet<>();
        gayPromoCategories.add(electronicsCat);
        CategoryValuePromotion promotion1 = new CategoryValuePromotion("Gay Day", "2023", gayPromoCategories, 100.0);
        CategoryPercentPromotion promotion2 = new CategoryPercentPromotion("Even gayer day", "2024", gayPromoCategories, 10.0);

        shop.addPromotion(promotion1);
        shop.addPromotion(promotion2);

        shop.buyProduct(product2.getId(), 10);

        assertEquals(10710.0, shop.checkout());

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            shop.checkout();
        });
        assertEquals("Shopping cart is empty", exception.getMessage());

        shop.listProducts(null);

        shop.activeShoppingCart = null;
        Throwable exception3 = assertThrows(IllegalArgumentException.class, () -> {
            shop.checkout();
        });
        assertEquals("No active shopping cart" , exception3.getMessage());

        shop.logout();

        Throwable exception2 = assertThrows(IllegalArgumentException.class, () -> {
            shop.checkout();
        });
        assertEquals("No active user", exception2.getMessage());
    }
}