package WinterResort;

import Lift.Type.ChairLift;
import Lift.Type.GondolaLift;
import Lift.Type.TBarLift;
import jdk.jfr.Name;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WinterResortTest {
    WinterResort winterResort;

    @BeforeEach
    void setup() {
        winterResort = new WinterResort();
    }

    @Test
    @Name("Test Constructor with right values")
    void testConstructorWithRightValues() {
        WinterResort testWinterResort = new WinterResort("Name");
        assertEquals("Name", testWinterResort.getName());
    }

    @Test
    @Name("Test Constructor with wrong values")
    void testConstructorWithWrongValues() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new WinterResort(""));
        assertEquals("Name cannot be null or empty", exception.getMessage());
    }

    @Test
    @Name("Test setName")
    void testSetName() {
        winterResort.setName("Borovets");
        assertEquals(winterResort.getName(), "Borovets");
    }

    @Test
    @Name("Test addLift with non-existing lift")
    void testAddLift() {
        winterResort.addLift(new GondolaLift("Gondola", (short) 10, (short) 2010, (short) 10));
        assertEquals(winterResort.getLifts().size(), 1);
        assertEquals(winterResort.getLifts().get(0).getName(), "Gondola");
    }

    @Test
    @Name("Test addLift with existing lift")
    void testAddLiftWithExistingLift() {
        GondolaLift gondolaLift = new GondolaLift("Gondola", (short) 10, (short) 2010, (short) 10);
        winterResort.addLift(gondolaLift);
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> winterResort.addLift(gondolaLift));
        assertEquals("Lift already exists", exception.getMessage());
    }

    @Test
    @Name("Test getResortRating with no lifts")
    void testGetResortRatingWithNoLifts() {
        assertEquals(winterResort.getResortRating(), 0);
    }

    @Test
    @Name("Test getResortRating with one lift")
    void testGetResortRatingWithOneLift() {
        GondolaLift gondolaLift = new GondolaLift("Gondola", (short) 10, (short) 2010, (short) 10);
        winterResort.addLift(gondolaLift);
        assertEquals(winterResort.getResortRating(), gondolaLift.getRaiting());
    }

    @Test
    @Name("Test getResortRating with multiple lifts")
    void testGetResortRatingWithMultipleLifts() {
        GondolaLift gondolaLift = new GondolaLift("Gondola", (short) 10, (short) 2010, (short) 10);
        ChairLift chairLift = new ChairLift("Chair", (short) 5, (short) 2010, (short) 10);
        TBarLift tBarLift = new TBarLift("TBar", (short) 2, (short) 2010, (short) 10);

        double expectedRating = (gondolaLift.getRaiting() + chairLift.getRaiting() + tBarLift.getRaiting()) / 3.0;

        winterResort.addLift(gondolaLift);
        winterResort.addLift(chairLift);
        winterResort.addLift(tBarLift);

        assertEquals(winterResort.getResortRating(), expectedRating);
    }

    @Test
    @Name("Test getName")
    void testGetName() {
        WinterResort testWinterResort = new WinterResort("Name");
        assertEquals(testWinterResort.getName(), "Name");
    }

    @Test
    @Name("Test getLifts")
    void testGetLifts() {
        GondolaLift gondolaLift = new GondolaLift("Gondola", (short) 10, (short) 2010, (short) 10);
        winterResort.addLift(gondolaLift);
        assertEquals(winterResort.getLifts().size(), 1);
        assertEquals(winterResort.getLifts().get(0).getName(), "Gondola");
    }

    @Test
    @Name("Test getLifts with no lifts")
    void testGetLiftsWithNoLifts() {
        assertEquals(winterResort.getLifts().size(), 0);
    }

    @Test
    @Name("Test getLifts with multiple lifts")
    void testGetLiftsWithMultipleLifts() {
        GondolaLift gondolaLift = new GondolaLift("Gondola", (short) 10, (short) 2010, (short) 10);
        ChairLift chairLift = new ChairLift("Chair", (short) 5, (short) 2010, (short) 10);
        TBarLift tBarLift = new TBarLift("TBar", (short) 2, (short) 2010, (short) 10);

        winterResort.addLift(gondolaLift);
        winterResort.addLift(chairLift);
        winterResort.addLift(tBarLift);

        assertEquals(winterResort.getLifts().size(), 3);
        assertEquals(winterResort.getLifts().get(0).getName(), "Gondola");
        assertEquals(winterResort.getLifts().get(1).getName(), "Chair");
        assertEquals(winterResort.getLifts().get(2).getName(), "TBar");
    }
}