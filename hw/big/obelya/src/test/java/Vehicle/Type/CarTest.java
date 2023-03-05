package Vehicle.Type;

import Vehicle.Type.CarEquipment.EquipmentLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {
    Car car;

    // test only constructor

    @Test
    void testConstructorWithValidValues() {
        car = new Car(
                "Toyota", "Corolla", (short)2020, "Whitish", 20000.0,
                (short) 4, (short) 4, EquipmentLevel.BASIC
        );

        assertAll(
                () -> assertEquals("Toyota", car.getMaker()),
                () -> assertEquals("Corolla", car.getModel()),
                () -> assertEquals((short) 2020, car.getYear()),
                () -> assertEquals("Whitish", car.getColor()),
                () -> assertEquals(20000.0, car.getPrice()),
                () -> assertEquals((short) 4, car.getDoorsNumber()),
                () -> assertEquals((short) 4, car.getSeatNumber()),
                () -> assertEquals(EquipmentLevel.BASIC, car.getEquipmentLevel())
        );
    }

    @Test
    void testConstructorWithInvalidValues() {
        assertThrows(IllegalArgumentException.class, () -> {
            car = new Car(
                    "Toyota", "Corolla", (short)2020, "Whitish", 20000.0,
                    (short) 4, (short) 4, null
            );
        });

        assertThrows(IllegalArgumentException.class, () -> {
            car = new Car(
                    "Toyota", "Corolla", (short)2025, "Whitish", 20000.0,
                    (short) 4, (short) 4, EquipmentLevel.SPORT
            );
        });

        assertThrows(IllegalArgumentException.class, () -> {
            car = new Car(
                    "Toyota", "Corolla", (short)2020, "Whitish", 20000.0,
                    (short) 444, (short) 232, EquipmentLevel.SPORT
            );
        });
    }
}