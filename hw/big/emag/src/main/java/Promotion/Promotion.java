package Promotion;

import Product.Product;

import java.util.Objects;

public abstract class Promotion {
    protected String name;
    protected String description;

    public Promotion(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract boolean isApplicable(Product product);

    public abstract Double apply(Product product);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Promotion promotion)) return false;
        return Objects.equals(getName(), promotion.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
