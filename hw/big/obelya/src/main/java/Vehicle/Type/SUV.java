package Vehicle.Type;

import Vehicle.Vehicle;

public class SUV extends Vehicle {
    public final Boolean has4by4;
    public final Integer clearance;

    public SUV(String maker, String model, Short year, Boolean has4by4, Integer clearance) {
        super(maker, model, year);

        if (has4by4 == null)
            throw new IllegalArgumentException("4x4 cannot be null");

        if (clearance < 0)
            throw new IllegalArgumentException("Clearance must be greater than 0");

        this.has4by4 = has4by4;
        this.clearance = clearance;
    }

    public SUV(String maker, String model, Short year, String color, Double price, Boolean has4by4, Integer clearance) {
        super(maker, model, year, color, price);

        if (has4by4 == null)
            throw new IllegalArgumentException("4x4 cannot be null");

        if (clearance < 1)
            throw new IllegalArgumentException("Clearance must be greater than 0");

        this.has4by4 = has4by4;
        this.clearance = clearance;
    }

    public SUV(Long id, String maker, String model, Short year, Boolean has4by4, Integer clearance) {
        super(id, maker, model, year);

        if (has4by4 == null)
            throw new IllegalArgumentException("4x4 cannot be null");

        if (clearance < 0)
            throw new IllegalArgumentException("Clearance must be greater than 0");

        this.has4by4 = has4by4;
        this.clearance = clearance;
    }

    public SUV(Long id, String maker, String model, Short year, String color, Double price, Boolean has4by4, Integer clearance) {
        super(id, maker, model, year, color, price);

        if (has4by4 == null)
            throw new IllegalArgumentException("4x4 cannot be null");

        if (clearance < 0)
            throw new IllegalArgumentException("Clearance must be greater than 0");

        this.has4by4 = has4by4;
        this.clearance = clearance;
    }

    public Boolean getHas4by4() {
        return has4by4;
    }

    public Integer getClearance() {
        return clearance;
    }

}
