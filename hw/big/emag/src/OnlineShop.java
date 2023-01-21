import Product.Category;
import Product.Product;
import Promotion.Promotion;
import ShoppingCart.ShoppingCart;
import User.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
    OnlineShop(Онлайн магазин)
    users - колекция с потребители на магазина
    categories - колекция с категории в магазина
    products - контейнер с продукти в магазина
    promotions - колекция с налични промоции
    activeUser - активен потребител на магазина
    activeShoppingCart - активна потребителска количка
    register(String username, String password) - регистрира се нов потребител в магазина. Ако вече има потребител с такъв username, да се хвърли грешка.
    login(String username, String password) - задава се активен потребител.
    Създава се нова количка за потребителя.
    Ако не съществува потребител с такъв username и password да се хвърли грешка.
    logout() - активния потребител и активната потребителска кошница стават null
    addCategory(Category category) - добавя категория в магазина. Ако вече има такава категория, хвърля грешка
    addProduct(Product product, int quantity) - добавя продукт и наличност към магазина. Ако вече има такъв продукт се увеличава неговата наличност в магазина. Ако продукта е с категория която не съществува за магазина, да се хвърля грешка
    addPromotion(Promotion promotion) - добавя промоция към колекцията с налични промоции
    listProducts(String category) - принтира в конзолата всички продукти, които имат подадената категория и тяхната наличност в магазина. Ако се подаде null да се принтират абсолютно всички продукти в магазина и тяхната наличност.
    buyProduct(int id, int amount) - добавя в активната кошница продукт с посоченото id и количество. Ако няма продукт с посоченото id или неговата наличност е изчерпана или недостатъчна, да се хвърли грешка
    chekout() - върху текущата кошница се прилагат всички възможни промоции. Ако след някоя промоция цената ще стане <= 0, тогава тя не се прилага. След прилагането на всички промоции, от наличните количества в магазина се изваждат количествата в кошницата. След това в конзолата се принтира детайлна информация на какво е било закупено и какви промоции са приложени. Накрая се създава нова количка за активния потребител.
    Стил на информацията в конзолата:


    —---------------------------------------------------------------------------
    <product name> <quantity> <price>
    Applied promotions:
    <promotion name>
    <promotion description>
    Discount: <total discount applied from that promotion>
    <promotion name>
    <promotion description>
    Discount: <total discount applied from that promotion>
    End price: <end price for that product and amount>
    —---------------------------------------------------------------------------
    <product name> <quantity> <price>
    Applied promotions:
    <promotion name>
    <promotion description>
    Discount: <total discount applied from that promotion>
    End price <end price for that product and amount>
    —---------------------------------------------------------------------------
    Total: <the total amount for all products after discounts>
    —---------------------------------------------------------------------------
 */

public class OnlineShop {
    Map<String, User> users;
    Set<Category> categories;
    Map<Product, Integer> products; // Product - Quantity
    Set<Promotion> promotions;
    User activeUser;
    ShoppingCart activeShoppingCart;

    public OnlineShop() {
        users = new HashMap<String, User>();
        categories = new HashSet<Category>();
        products = new HashMap<Product, Integer>();
        promotions = new HashSet<Promotion>();

        activeUser = null;
        activeShoppingCart = null;
    }

    public void register(String username, String password) {
        if (users.containsKey(username)) {
            throw new IllegalArgumentException("User already exists");
        }

        User user = new User(username, password);
        users.put(username, user);
    }

    public void login(String username, String password) {
        if (!users.containsKey(username)) {
            throw new IllegalArgumentException("User does not exist");
        }

        User user = users.get(username);
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

    public void addCategory(Category category) {
        if (activeUser == null) {
            throw new IllegalArgumentException("You must be logged in to add a category");
        }

        if (categories.contains(category)) {
            throw new IllegalArgumentException("Category already exists");
        }

        categories.add(category);
    }

    public void addProduct(Product product, int quantity) {
        if (activeUser == null) {
            throw new IllegalArgumentException("You must be logged in to add a product");
        }

        if (products.containsKey(product)) {
            throw new IllegalArgumentException("Product already exists");
        }

        products.put(product, quantity);
    }

    public void addProduct(String name, String description, Double price, String categoryName) {
        if (activeUser == null) {
            throw new IllegalArgumentException("No active user");
        }

        //System.out.println("Adding product " + name + " to category " + categoryName);

        Category category = null;
        for (Category c : categories) {
            if (c.getName().equals(categoryName)) {
                category = c;
                break;
            }
        }

        if (category == null) {
            throw new IllegalArgumentException("Category does not exist");
        }

        //System.out.println("Category found");

        Product product = new Product(name, description, price, category);
        if (products.containsKey(product)) {
            throw new IllegalArgumentException("Product already exists");
        }
        products.put(product, 1);
    }

    public void addPromotion(Promotion promotion) {
        if (activeUser == null) {
            throw new IllegalArgumentException("No active user");
        }

        if (promotions.contains(promotion)) {
            throw new IllegalArgumentException("Promotion already exists");
        }

        promotions.add(promotion);
    }

    public void listProducts(Category category) {
        if (activeUser == null) {
            throw new IllegalArgumentException("No active user");
        }

        if (category == null) {
            System.out.println("Listing all products");
        } else if (!categories.contains(category)) {
            throw new IllegalArgumentException("Category does not exist");
        } else {
            System.out.println("Listing products in category " + category.getName());
        }

        for (Product product : products.keySet()) {
            if (product.getCategory().equals(category) || category == null) {
                System.out.println(product + " - " + products.get(product));
            }
        }
    }

    public void listCategories() {
        if (activeUser == null) {
            throw new IllegalArgumentException("No active user");
        }

        for (Category category : categories) {
            System.out.println(category);
        }
    }

    public void buyProduct(Long productId, Integer quantity) {
        if (activeUser == null) {
            throw new IllegalArgumentException("No active user");
        }

        Product product = null;
        for (Product p : products.keySet()) {
            if (p.getId().equals(productId)) {
                product = p;
                break;
            }
        }

        if (product == null) {
            throw new IllegalArgumentException("Product does not exist");
        }

        if (products.get(product) < quantity) {
            throw new IllegalArgumentException("Not enough products in stock");
        }

        activeShoppingCart.addProduct(product, quantity);
    }

    private void lowerProductQuantity(Long productId, int quantity) {
        Product product = null;

        for (Product p : products.keySet()) {
            if (p.getId().equals(productId)) {
                product = p;
                break;
            }
        }

        if (product == null) {
            throw new IllegalArgumentException("Product does not exist");
        }

        if (products.get(product) < quantity) {
            throw new IllegalArgumentException("Not enough products in stock");
        }

        products.put(product, products.get(product) - quantity);
    }

    public void checkout() {
        if (activeUser == null) {
            throw new IllegalArgumentException("No active user");
        }

        if (activeShoppingCart.getProducts().isEmpty()) {
            throw new IllegalArgumentException("Shopping cart is empty");
        }

        System.out.println("—---------------------------------------------------------------------------");
        System.out.println("Checking out...");
        System.out.println("Products:");
        for (Product product : activeShoppingCart.getProducts().keySet()) {
            System.out.println("\t" + product.getName() + " " + activeShoppingCart.getProducts().get(product) + "x" + product.getPrice());
        }

        for (Product product : activeShoppingCart.getProducts().keySet()) {
            Integer quantity = activeShoppingCart.getProducts().get(product);

            System.out.println("Applied promotions for " + product.getName() + " (" + quantity + "):");
            for (Promotion promotion : promotions) {
                if (promotion.isApplicable(product)) {
                    System.out.println("\t" + promotion.getName());
                    System.out.println("\t\t" + promotion.getDescription());

                    Double discount = promotion.apply(product);

                    if (discount > 0) {
                        System.out.println("\t\tDiscount: " + discount * quantity);
                    } else {
                        System.out.println("\t\tDiscount: does not apply, price is 0 or less");
                    }
                }
            }
            System.out.println("End price: " + (product.getPrice() * quantity));
        }

        Double totalPrice = 0.0;

        for (Product product : activeShoppingCart.getProducts().keySet()) {
            Integer quantity = activeShoppingCart.getProducts().get(product);

            totalPrice += product.getPrice() * quantity;
        }

        System.out.println("Total price: " + totalPrice);
        System.out.println("—---------------------------------------------------------------------------");
        activeShoppingCart = new ShoppingCart();
    }
}
