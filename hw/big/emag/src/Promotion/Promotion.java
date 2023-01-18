package Promotion;

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

    public abstract boolean isApplicable(Product.Product product);
    public abstract void apply(Product.Product product);
}
