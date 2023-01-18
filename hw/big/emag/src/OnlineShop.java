import User.User;
import Product.Product;
import Product.Category;
import Promotion.Promotion;
import ShoppingCart.ShoppingCart;

import java.util.HashMap;
import java.util.HashSet;

public class OnlineShop {
    HashMap<String, User> users;
    HashSet<Category> categories;
    HashMap<Product, Long> products; // Product - Quantity
    HashSet<Promotion> promotions;
    User activeUser;
    ShoppingCart activeShoppingCart;

    public OnlineShop() {
        users = new HashMap<>();
        categories = new HashMap<>();
        products = new HashMap<>();
        promotions = new HashMap<>();
        activeUser = null;
        activeShoppingCart = null;
    }

    public void register(String username, String password) {
        User user = new User(username, password);
        users.put(username, user);
    }

    public void login(String username, String password) {
        User user = users.get(username);

        if(user == null) {
           throw new IllegalArgumentException("User does not exist");
        }

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Wrong password");
        }

        activeUser = user;
        activeShoppingCart = new ShoppingCart();
    }

    public void logout() {
        activeUser = null;
        activeShoppingCart = null;
    }

public void addCategory(String name) {
        Category category = new Category(name);
        categories.put(name, category);
    }

    public void addProduct(String name, String description, Double price, String categoryName) {
        Category category = categories.get(categoryName);
        Product product = new Product(name, description, price, category);
        products.put(product.getId(), product);
    }

    public void addPromotion(String name, String description) {
        Promotion promotion = new Promotion(name, description);
        promotions.put(promotion.getId(), promotion);
    }

    public void listProducts(Category category) {
        if(category == null) {
            for (Product product : products.values()) {
                System.out.println(product);
            }
        } else {
            for (Product product : products.values()) {
                if(product.getCategory().equals(category)) {
                    System.out.println(product);
                }
            }
        }
    }

    public void buyProduct(Long productId, Integer quantity) {
        Product product = products.get(productId);
        activeShoppingCart.addProduct(product, quantity);
    }

    public void checkout() {
        activeShoppingCart.checkout();
    }
}
