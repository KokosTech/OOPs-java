package Promotion.Type;

import Product.Category;
import Product.Product;
import Promotion.ApplicableTo.CategoryPromotion;
import Promotion.Kind.ValueDiscount;
import Promotion.Promotion;

import java.util.Set;

public class CategoryValuePromotion extends Promotion implements CategoryPromotion, ValueDiscount {
    private Set<Category> categories;
    private Double discount;

    public CategoryValuePromotion(String name, String description, Set<Category> categories, Double discount) {
        super(name, description);
        this.categories = categories;
        this.discount = discount;
    }

    @Override
    public boolean isApplicable(Product product) {
        return categories.contains(product.getCategory());
    }

    @Override
    public Double apply(Product product) {
        if (product.getPrice() - discount <= 0) {
            return 0.0;
        }

        product.setPrice(product.getPrice() - discount);

        return discount;
    }

    @Override
    public Double getValueDiscount() {
        return discount;
    }

    @Override
    public Set<Category> getApplicableCategories() {
        return categories;
    }
}

