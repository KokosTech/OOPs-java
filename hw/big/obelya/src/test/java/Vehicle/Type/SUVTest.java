package Vehicle.Type;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SUVTest {

    private SUV suv;

    @Test
    void testConstructorWithValidValues() {
        suv = new SUV(
                "Hyundai", "i20 something", (short) 2012, "Grayish", 9750.0,
                true, 10
        );

        assertAll(
                () -> assertEquals("Hyundai", suv.getMaker()),
                () -> assertEquals("i20 something", suv.getModel()),
                () -> assertEquals((short) 2012, suv.getYear()),
                () -> assertEquals("Grayish", suv.getColor()),
                () -> assertEquals(9750.0, suv.getPrice()),
                () -> assertTrue(suv.getHas4by4()),
                () -> assertEquals(10, suv.getClearance())
        );
    }

    @Test
    void testConstructorWithInvalidValues() {
        assertThrows(IllegalArgumentException.class, () -> {
            suv = new SUV(
                    "Hyundai", "i20 something", (short) 2012, "Grayish", 9750.0,
                    true, 0
            );
        });

        assertThrows(IllegalArgumentException.class, () -> {
            suv = new SUV(
                    "Hyundai", "i20 something", (short) 2012, "Grayish", 9750.0,
                    null, 1
            );
        });

        assertThrows(IllegalArgumentException.class, () -> {
            suv = new SUV(
                    "", "i20 something", (short) 2012, "Grayish", 9750.0,
                    true, 3
            );
        });
    }
}