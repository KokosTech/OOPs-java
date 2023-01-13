package Package.Types;

import Package.Package;

// wrapper class for the package type
public class SalablePackage extends Package {
    private Double price;
    private Package pac;

    public SalablePackage(Long id, Package pac, Double price) {
        super(id);

        this.pac = pac;
        this.size = pac.getSize();

        if (price <= 0) {
            throw new IllegalArgumentException("Price must be positive number");
        }

        this.price = price;
    }

    public SalablePackage(Package pac, Double price) {
        super();

        this.pac = pac;

        if (price <= 0) {
            throw new IllegalArgumentException("Price must be positive number");
        }

        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDeliveryPrice() {
        return pac.getDeliveryPrice() + price * 0.1;
    }

    @Override
    public String toString() {
        return "SalablePackage{" +
                "price=" + price +
                ", pac=" + pac +
                '}';
    }
}
