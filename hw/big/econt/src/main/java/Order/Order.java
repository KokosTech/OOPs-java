package Order;

import Package.Package;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class Order {
    private final Long id;
    Set<Package> packages;
    private Long addressId;
    private Status status;


    public Order(Long id, Set<Package> packages, Long addressId, Status status) {
        this.id = id;

        if (packages.isEmpty()) {
            throw new IllegalArgumentException("Cannot create an order with no packages!");
        }

        this.packages = packages;
        this.addressId = addressId;
        this.status = status;
    }

    public Order(Set<Package> packages, Long addressId, Status status) {
        this.id = System.currentTimeMillis();

        if (packages.isEmpty()) {
            throw new IllegalArgumentException("Cannot create an order with no packages!");
        }

        this.packages = packages;
        this.addressId = addressId;
        this.status = status;
    }

    // Methods
    public Double getDeliveryPrice() {
        Double sum = 0.0;

        for (Package pack : packages) {
            sum += pack.getDeliveryPrice();
        }

        return sum;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public Set<Package> getPackages() {
        return packages;
    }

    public Integer getPackagesCount() {
        return packages.size();
    }

    public void setPackages(@NotNull Set<Package> packages) {
        if (packages.isEmpty()) {
            throw new IllegalArgumentException("Cannot set an order with no packages!");
        }

        this.packages = packages;
    }

    public void addPackage(@NotNull Package pack) {
        packages.add(pack);
    }

    public Long getAddressId() {
        return addressId;
    }

    public Long setAddressId(Long addressId) {
        return this.addressId = addressId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", packages=" + packages +
                ", addressId=" + addressId +
                ", status=" + status +
                '}';
    }
}
