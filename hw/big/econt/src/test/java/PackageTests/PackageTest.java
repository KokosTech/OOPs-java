package PackageTests;

import Package.Package;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public abstract class PackageTest {
    Package orderPackage;

    public abstract void createPackageTest(int value);
    public abstract void createWronglySizedPackageTestException(int value);

    public abstract void getDeliveryPriceTest(int value);
}
