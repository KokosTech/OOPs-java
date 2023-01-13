package PackageTests;

import Package.Types.MediumPackage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MediumPackageTest extends PackageTest {
    @ParameterizedTest
    @DisplayName("Test for creating a MediumPackage")
    @ValueSource(ints = {4, 5, 6})
    @Override
    public void createPackageTest(int value) {
        MediumPackage orderPackage = new MediumPackage(value);
        assertEquals(value, orderPackage.getSize());
    }

    @ParameterizedTest
    @DisplayName("Test for creating a MediumPackage with wrong size")
    @ValueSource(ints = {3, 7, 8, 9})
    @Override
    public void createWronglySizedPackageTestException(int value) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new MediumPackage(value));
        assertEquals("Size must be between 4 and 6", exception.getMessage());
    }

    @ParameterizedTest
    @DisplayName("Test for getting the delivery price of a MediumPackage")
    @ValueSource(ints = {4, 5, 6})
    @Override
    public void getDeliveryPriceTest(int value) {
        MediumPackage orderPackage = new MediumPackage(value);
        assertEquals(value * 4, orderPackage.getDeliveryPrice());
    }
}
