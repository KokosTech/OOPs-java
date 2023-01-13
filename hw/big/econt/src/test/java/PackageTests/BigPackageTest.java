package PackageTests;

import Package.Types.BigPackage;

import Package.Types.MediumPackage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BigPackageTest extends PackageTest {
    @ParameterizedTest
    @DisplayName("Test for creating a BigPackage")
    @ValueSource(ints = {7, 8, 9})
    @Override
    public void createPackageTest(int value) {
        orderPackage = new BigPackage(value);
        assertEquals(value, orderPackage.getSize());
    }

    @ParameterizedTest
    @DisplayName("Test for creating a BigPackage with wrong size")
    @ValueSource(ints = {4, 6, 10, 12})
    @Override
    public void createWronglySizedPackageTestException(int value) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new BigPackage(value));
        assertEquals("Size must be between 7 and 9", exception.getMessage());
    }


    @ParameterizedTest
    @DisplayName("Test for getting delivery price of a BigPackage")
    @ValueSource(ints = {7, 8, 9})
    @Override
    public void getDeliveryPriceTest(int value) {
        orderPackage = new BigPackage(value);
        assertEquals(value * 5, orderPackage.getDeliveryPrice());
    }
}
