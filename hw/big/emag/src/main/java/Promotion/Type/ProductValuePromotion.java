package Promotion.Type;

import Product.Product;
import Promotion.ApplicableTo.ProductPromotion;
import Promotion.Kind.ValueDiscount;
import Promotion.Promotion;

import java.util.Map;

public class ProductValuePromotion extends Promotion implements ProductPromotion, ValueDiscount {
    private Map<Long, Product> products;
    private Double valueDiscount;

    public ProductValuePromotion(String name, String description, Map<Long, Product> products, Double valueDiscount) {
        super(name, description);
        this.products = products;
        this.valueDiscount = valueDiscount;
    }

    @Override
    public boolean isApplicable(Product product) {
        return products.containsKey(product.getId());
    }

    @Override
    public Double apply(Product product) {
        Double discount = valueDiscount;

        if (product.getPrice() - discount <= 0) {
            return 0.0;
        }

        product.setPrice(product.getPrice() - discount);

        return discount;
    }

    @Override
    public Double getValueDiscount() {
        return valueDiscount;
    }

    @Override
    public Map<Long, Product> getApplicableProducts() {
        return products;
    }
}

