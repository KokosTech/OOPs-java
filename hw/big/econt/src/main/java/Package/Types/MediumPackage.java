package Package.Types;

import Package.Package;

public class MediumPackage extends Package {

    public MediumPackage(Long id, Integer size) {
        super(id);
        if (size < 4 || size > 6) {
            throw new IllegalArgumentException("Size must be between 4 and 6");
        }
        this.size = size;
    }

    public MediumPackage(int size) {
        super();
        if (size < 4 || size > 6) {
            throw new IllegalArgumentException("Size must be between 4 and 6");
        }
        this.size = size;
    }

    @Override
    public Double getDeliveryPrice() {
        return size * 4.0;
    }
}
