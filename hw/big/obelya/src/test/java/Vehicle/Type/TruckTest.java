package Vehicle.Type;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TruckTest {

        private Truck truck;

        @Test
        void testConstructorWithValidValues() {
            truck = new Truck(
                    "Hyundai", "i20 something", (short) 2012, "Grayish", 9750.0,
                    1000, 10.0
            );

            assertAll(
                    () -> assertEquals("Hyundai", truck.getMaker()),
                    () -> assertEquals("i20 something", truck.getModel()),
                    () -> assertEquals((short) 2012, truck.getYear()),
                    () -> assertEquals("Grayish", truck.getColor()),
                    () -> assertEquals(9750.0, truck.getPrice()),
                    () -> assertEquals(1000, truck.getMaxLoadWeight()),
                    () -> assertEquals(10.0, truck.getMaxLoadVolume())
            );
        }

        @Test
        void testConstructorWithInvalidValues() {
            assertThrows(IllegalArgumentException.class, () -> {
                truck = new Truck(
                        "Hyundai", "i20 something", (short) 2012, "Grayish", 9750.0,
                        -4, 10.0
                );
            });

            assertThrows(IllegalArgumentException.class, () -> {
                truck = new Truck(
                        "Hyundai", "i20 something", (short) 2012, "Grayish", 9750.0,
                        1000, -1111.0
                );
            });

            assertThrows(IllegalArgumentException.class, () -> {
                truck = new Truck(
                        "", "i20 something", (short) 2012, "Grayish", 9750.0,
                        1000, 10.0
                );
            });
        }
}