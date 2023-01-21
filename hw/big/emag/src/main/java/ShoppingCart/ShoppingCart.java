package ShoppingCart;

import Product.Product;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private final Long id;
    private Map<Product, Integer> products;

    public ShoppingCart(Long id, Map<Product, Integer> products) {
        this.id = id;

        if (products == null) {
            this.products = new HashMap<>();
        } else {
            this.products = products;
        }
    }

    public ShoppingCart(Long id) {
        this.id = System.currentTimeMillis();
        this.products = new HashMap<Product, Integer>();
    }

    public ShoppingCart(Map<Product, Integer> products) {
        this.id = System.currentTimeMillis();
        this.products = products;
    }

    public ShoppingCart() {
        this.id = System.currentTimeMillis();
        this.products = new HashMap<Product, Integer>();
    }

    public Long getId() {
        return id;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void addProduct(Product product, Integer quantity) {
        if (products.containsKey(product)) {
            products.put(new Product(product), products.get(product) + quantity);
        } else {
            products.put(new Product(product), quantity);
        }
    }

    public void removeProduct(Product product, Integer quantity) {
        if (products.containsKey(product)) {
            if (products.get(product) > quantity) {
                products.put(product, products.get(product) - quantity);
            } else {
                products.remove(product);
            }
        } else {
            System.out.println("Product(s) not found in cart");
        }
    }

    public void removeProduct(Product product) {
        if (products.containsKey(product)) {
            products.remove(product);
        } else {
            throw new IllegalArgumentException("Product(s) not found in cart");
        }
    }

    public void checkout() {

    }
}
