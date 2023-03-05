package Vehicle.Type;

import Vehicle.Vehicle;
import Vehicle.Type.CarEquipment.EquipmentLevel;

public class Car extends Vehicle {
    private final Short seatNumber;
    private final Short doorsNumber;
    private final EquipmentLevel equipmentLevel;

    public Car(String maker, String model, Short year, Short seatNumber, Short doorsNumber, EquipmentLevel equipmentLevel) {
        super(maker, model, year);

        if (seatNumber < 2 || seatNumber > 5)
            throw new IllegalArgumentException("Seat number must be between 2 and 5");

        if (doorsNumber < 2 || doorsNumber > 5)
            throw new IllegalArgumentException("Doors number must be between 2 and 5");

        if (equipmentLevel == null)
            throw new IllegalArgumentException("Equipment level cannot be null");

        this.seatNumber = seatNumber;
        this.doorsNumber = doorsNumber;
        this.equipmentLevel = equipmentLevel;
    }

    public Car(Long id, String maker, String model, Short year, Short seatNumber, Short doorsNumber, EquipmentLevel equipmentLevel) {
        super(id, maker, model, year);

        if (seatNumber < 2 || seatNumber > 5)
            throw new IllegalArgumentException("Seat number must be between 2 and 5");

        if (doorsNumber < 2 || doorsNumber > 5)
            throw new IllegalArgumentException("Doors number must be between 2 and 5");

        if (equipmentLevel == null)
            throw new IllegalArgumentException("Equipment level cannot be null");

        this.seatNumber = seatNumber;
        this.doorsNumber = doorsNumber;
        this.equipmentLevel = equipmentLevel;
    }

    public Car(String maker, String model, Short year, String color, Double price, Short seatNumber, Short doorsNumber, EquipmentLevel equipmentLevel) {
        super(maker, model, year, color, price);

        if (seatNumber < 2 || seatNumber > 5)
            throw new IllegalArgumentException("Seat number must be between 2 and 5");

        if (doorsNumber < 2 || doorsNumber > 5)
            throw new IllegalArgumentException("Doors number must be between 2 and 5");

        if (equipmentLevel == null)
            throw new IllegalArgumentException("Equipment level cannot be null");

        this.seatNumber = seatNumber;
        this.doorsNumber = doorsNumber;
        this.equipmentLevel = equipmentLevel;
    }

    public Car(Long id, String maker, String model, Short year, String color, Double price, Short seatNumber, Short doorsNumber, EquipmentLevel equipmentLevel) {
        super(id, maker, model, year, color, price);

        if (seatNumber < 2 || seatNumber > 5)
            throw new IllegalArgumentException("Seat number must be between 2 and 5");

        if (doorsNumber < 2 || doorsNumber > 5)
            throw new IllegalArgumentException("Doors number must be between 2 and 5");

        if (equipmentLevel == null)
            throw new IllegalArgumentException("Equipment level cannot be null");

        this.seatNumber = seatNumber;
        this.doorsNumber = doorsNumber;
        this.equipmentLevel = equipmentLevel;
    }

    public Short getSeatNumber() {
        return seatNumber;
    }

    public Short getDoorsNumber() {
        return doorsNumber;
    }

    public EquipmentLevel getEquipmentLevel() {
        return equipmentLevel;
    }

    @Override
    public String toString() {
        return "Car [id=" + getId() + ", maker=" + getMaker() + ", model=" + getModel() + ", year=" + getYear() + ", color=" + getColor() + ", price=" + getPrice() + ", seatNumber=" + seatNumber + ", doorsNumber=" + doorsNumber + ", equipmentLevel=" + equipmentLevel + "]";
    }
}
