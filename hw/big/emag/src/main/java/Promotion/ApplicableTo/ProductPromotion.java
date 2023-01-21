package Promotion.ApplicableTo;

import Product.Product;
import java.util.Map;


public interface ProductPromotion {
    Map<Long, Product> getApplicableProducts();
}
