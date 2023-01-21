package Promotion.Type;

import Product.Category;
import Product.Product;
import Promotion.ApplicableTo.CategoryPromotion;
import Promotion.Kind.PercentDiscount;
import Promotion.Promotion;

import java.util.Set;

public class CategoryPercentPromotion extends Promotion implements CategoryPromotion, PercentDiscount {
    private Set<Category> categories;
    private Double percentDiscount;

    public CategoryPercentPromotion(String name, String description, Set<Category> categories, Double percentDiscount) {
        super(name, description);
        this.categories = categories;
        this.percentDiscount = percentDiscount;
    }

    @Override
    public boolean isApplicable(Product product) {
        return categories.contains(product.getCategory());
    }

    @Override
    public Double apply(Product product) {
        Double discount = product.getPrice() * percentDiscount / 100;
        //System.out.println("Price before discount: " + product.getPrice());
        //System.out.println("Discount: " + discount + " (" + percentDiscount + "%)");

        if (product.getPrice() - discount <= 0) {
            return 0.0;
        }

        product.setPrice(product.getPrice() - discount);

        return discount;
    }

    @Override
    public Double getPercentDiscount() {
        return percentDiscount;
    }

    @Override
    public Set<Category> getApplicableCategories() {
        return categories;
    }
}

