package Promotion.Type;

import Product.Product;
import Promotion.ApplicableTo.ProductPromotion;
import Promotion.Kind.PercentDiscount;
import Promotion.Promotion;

import java.util.Map;

public class ProductPercentPromotion extends Promotion implements ProductPromotion, PercentDiscount {
    private Map<Long, Product> products;
    private Double percentDiscount;

    public ProductPercentPromotion(String name, String description, Map<Long, Product> products, Double percentDiscount) {
        super(name, description);
        this.products = products;
        this.percentDiscount = percentDiscount;
    }

    @Override
    public boolean isApplicable(Product product) {
        return products.containsKey(product.getId());
    }

    @Override
    public Double apply(Product product) {
        Double discount = product.getPrice() * percentDiscount / 100;

        if (product.getPrice() - discount <= 0) {
            return 0.0;
        }

        product.setPrice(product.getPrice() - discount);

        return product.getPrice() - discount;
    }

    @Override
    public Double getPercentDiscount() {
        return percentDiscount;
    }

    @Override
    public Map<Long, Product> getApplicableProducts() {
        return products;
    }
}
