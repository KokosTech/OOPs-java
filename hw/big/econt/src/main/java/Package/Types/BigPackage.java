package Package.Types;

import Package.Package;

public class BigPackage extends Package {

    public BigPackage(Long id, Integer size) {
        super(id);
        if (size < 7 || size > 9) {
            throw new IllegalArgumentException("Size must be between 7 and 9");
        }
        this.size = size;
    }

    public BigPackage(int size) {
        super();
        if (size < 7 || size > 9) {
            throw new IllegalArgumentException("Size must be between 7 and 9");
        }
        this.size = size;
    }

    @Override
    public Double getDeliveryPrice() {
        return size * 5.0;
    }
}
