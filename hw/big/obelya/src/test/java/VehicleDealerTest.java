import Vehicle.Type.Car;
import Vehicle.Type.CarEquipment.EquipmentLevel;
import Vehicle.Type.SUV;
import Vehicle.Type.Truck;
import Vehicle.Vehicle;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class VehicleDealerTest {
    VehicleDealer vehicleDealer;
    Car car;
    SUV suv;
    Truck truck;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        vehicleDealer = new VehicleDealer();

        car = new Car("BMW", "X5", (short) 2008, "LGBTQ", 10000.0, (short) 4, (short) 4, EquipmentLevel.BASIC);
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        suv = new SUV("BMW", "X5", (short)2020, "White", 15000.0 , true, 10);
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        truck = new Truck("BMW", "X5", (short)1999, "White", 20000.0, 5000, 50000.0);

        vehicleDealer.addVehicle(car);
        vehicleDealer.addVehicle(suv);
        vehicleDealer.addVehicle(truck);
    }

    @org.junit.jupiter.api.Test
    void addVehicleWithValidId() {
        int expectedSize = 1;
        int actualSize = vehicleDealer.getCars().size();
        assertAll(
                () -> assertEquals(expectedSize, actualSize),
                () -> assertTrue(vehicleDealer.getCars().contains(car))
        );
    }

    @org.junit.jupiter.api.Test
    void addVehicleWithInvalidId() {
        assertThrows(IllegalArgumentException.class, () -> vehicleDealer.addVehicle(car));
    }

    @org.junit.jupiter.api.Test
    void getAllVehiclesByIdByMaker() {
        Map<String, Map<Long, Vehicle>> expectedMap = new HashMap<>();

        expectedMap.put("BMW", new HashMap<>());
        expectedMap.get("BMW").put(car.getId(), car);
        expectedMap.get("BMW").put(suv.getId(), suv);
        expectedMap.get("BMW").put(truck.getId(), truck);

        Map<String, Map<Long, Vehicle>> actualMap = vehicleDealer.getAllVehiclesByIdByMaker();

        assertEquals(expectedMap, actualMap);
    }

    @org.junit.jupiter.api.Test
    void getAllSUVWith4by4() {
        SUV suvy = new SUV("BMW", "X5", (short)2020, "White", 15000.0 , true, 10);
        vehicleDealer.addVehicle(suvy);

        Set<SUV> expectedSet = Set.of(suvy, suv);
        Set<SUV> actualSet = vehicleDealer.getAllSUVWith4by4();

        assertEquals(expectedSet, actualSet);
    }

    @org.junit.jupiter.api.Test
    void getAllCarsWithEquipmentLevel() {
        Car car2 = new Car("BMW", "X5", (short) 2008, "LGBTQ", 10000.0, (short) 4, (short) 4, EquipmentLevel.BASIC);
        vehicleDealer.addVehicle(car2);

        Set<Car> expectedSet = Set.of(car, car2);
        Set<Car> actualSet = vehicleDealer.getAllCarsWithEquipmentLevel(EquipmentLevel.BASIC);

        assertEquals(expectedSet, actualSet);
    }

    @org.junit.jupiter.api.Test
    void getCheapestVehicle() {
        Vehicle expectedVehicle = car;
        Vehicle actualVehicle = vehicleDealer.getCheapestVehicle(List.of(car, suv, truck));

        assertEquals(expectedVehicle, actualVehicle);
    }

    @org.junit.jupiter.api.Test
    void getCheapestVehicleWithEmptyList() {
        assertThrows(
                NoSuchElementException.class,
                () -> vehicleDealer.getCheapestVehicle(new ArrayList<>())
        );
    }

    @org.junit.jupiter.api.Test
    void getTotalNumberOfModels() {
        int expectedNumberOfModels = 3;
        Long actualNumberOfModels = vehicleDealer.getTotalNumberOfModels("BMW");

        assertEquals(expectedNumberOfModels, actualNumberOfModels);
    }

    @org.junit.jupiter.api.Test
    void sellVehicle() {
        vehicleDealer.sellVehicle(car.getId());
        assertFalse(vehicleDealer.getCars().contains(car));
    }

    @org.junit.jupiter.api.Test
    void sellVehicleWithInvalidId() {
        assertThrows(
                IllegalArgumentException.class,
                () -> vehicleDealer.sellVehicle(100L)
        );
    }
}