package Vehicle.Type;

import Vehicle.Vehicle;

public class Truck extends Vehicle {
    private final Integer maxLoadWeight;
    private final Double maxLoadVolume;

    public Truck(String maker, String model, Short year, Integer maxLoadWeight, Double maxLoadVolume) {
        super(maker, model, year);

        if (maxLoadWeight < 0)
            throw new IllegalArgumentException("Max load weight must be greater than 0");

        if (maxLoadVolume < 0)
            throw new IllegalArgumentException("Max load volume must be greater than 0");

        this.maxLoadWeight = maxLoadWeight;
        this.maxLoadVolume = maxLoadVolume;
    }

    public Truck(String maker, String model, Short year, String color, Double price, Integer maxLoadWeight, Double maxLoadVolume) {
        super(maker, model, year, color, price);

        if (maxLoadWeight < 0)
            throw new IllegalArgumentException("Max load weight must be greater than 0");

        if (maxLoadVolume < 0)
            throw new IllegalArgumentException("Max load volume must be greater than 0");

        this.maxLoadWeight = maxLoadWeight;
        this.maxLoadVolume = maxLoadVolume;
    }

    public Truck(Long id, String maker, String model, Short year, Integer maxLoadWeight, Double maxLoadVolume) {
        super(id, maker, model, year);

        if (maxLoadWeight < 0)
            throw new IllegalArgumentException("Max load weight must be greater than 0");

        if (maxLoadVolume < 0)
            throw new IllegalArgumentException("Max load volume must be greater than 0");

        this.maxLoadWeight = maxLoadWeight;
        this.maxLoadVolume = maxLoadVolume;
    }

    public Truck(Long id, String maker, String model, Short year, String color, Double price, Integer maxLoadWeight, Double maxLoadVolume) {
        super(id, maker, model, year, color, price);

        if (maxLoadWeight < 0)
            throw new IllegalArgumentException("Max load weight must be greater than 0");

        if (maxLoadVolume < 0)
            throw new IllegalArgumentException("Max load volume must be greater than 0");

        this.maxLoadWeight = maxLoadWeight;
        this.maxLoadVolume = maxLoadVolume;
    }

    public Integer getMaxLoadWeight() {
        return maxLoadWeight;
    }

    public Double getMaxLoadVolume() {
        return maxLoadVolume;
    }

    @Override
    public String toString() {
        return "Truck [id=" + getId() + ", maker=" + getMaker() + ", model=" + getModel() + ", year=" + getYear() + ", color=" + getColor() + ", price=" + getPrice() + ", maxLoadWeight=" + maxLoadWeight + ", maxLoadVolume=" + maxLoadVolume + "]";
    }
}
