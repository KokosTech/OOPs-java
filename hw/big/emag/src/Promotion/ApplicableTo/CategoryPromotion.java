package Promotion.ApplicableTo;

import Product.Category;

import java.util.Set;

public interface CategoryPromotion {
    Set<Category> getApplicableCategories();
}
