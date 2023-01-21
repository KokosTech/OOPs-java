import Product.Category;
import Product.Product;
import Promotion.Type.CategoryPercentPromotion;
import Promotion.Type.CategoryValuePromotion;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        OnlineShop onlineShop = new OnlineShop();
        onlineShop.register("user", "password");
        onlineShop.login("user", "password");

        Category electronicsCat = new Category("Electronics");
        Category clothesCat = new Category("Clothes");
        Category booksCat = new Category("Books");
        Category foodCat = new Category("Food");

        onlineShop.addCategory(electronicsCat);
        onlineShop.addCategory(clothesCat);
        onlineShop.addCategory(booksCat);
        onlineShop.addCategory(foodCat);

        onlineShop.listCategories();

        Product product1 = new Product("iPhone 12", "New iPhone", 1000.0, electronicsCat);
        Product product2 = new Product("iPhone 12 Pro", "New iPhone", 1200.0, electronicsCat);
        Product product3 = new Product("iPhone 12 Pro Max", "New iPhone", 1400.0, electronicsCat);
        Product product4 = new Product("iPhone 12 Mini", "New iPhone", 800.0, electronicsCat);
        Product product5 = new Product("iPhone 11", "Old iPhone", 700.0, electronicsCat);

        onlineShop.addProduct(product1, 10);
        onlineShop.addProduct(product2, 10);
        onlineShop.addProduct(product3, 10);
        onlineShop.addProduct(product4, 10);
        onlineShop.addProduct(product5, 10);

        onlineShop.listProducts(null);
        onlineShop.listProducts(electronicsCat);

        onlineShop.buyProduct(product1.getId(), 1);

        Set<Category> gayPromoCategories = new HashSet<>();
        gayPromoCategories.add(electronicsCat);
        CategoryValuePromotion promotion1 = new CategoryValuePromotion("Gay Day", "2023", gayPromoCategories, 100.0);
        CategoryPercentPromotion promotion2 = new CategoryPercentPromotion("Even gayer day", "2024", gayPromoCategories, 10.0);

        onlineShop.addPromotion(promotion1);
        onlineShop.addPromotion(promotion2);

        onlineShop.buyProduct(product2.getId(), 10);

        onlineShop.checkout();

        onlineShop.listProducts(null);

        onlineShop.logout();
    }
}