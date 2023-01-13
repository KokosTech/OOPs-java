package Package.Types;

import Package.Package;

public class SmallPackage extends Package {

    public SmallPackage(Long id, Integer size) {
        super(id);
        if (size < 1 || size > 3) {
            throw new IllegalArgumentException("Size must be between 7 and 10");
        }
        this.size = size;
    }

    public SmallPackage(int size) {
        super();
        if (size < 1 || size > 3) {
            throw new IllegalArgumentException("Size must be between 7 and 10");
        }
        this.size = size;
    }

    @Override
    public Double getDeliveryPrice() {
        return size * 3.0;
    }
}
