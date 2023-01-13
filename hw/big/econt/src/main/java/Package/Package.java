package Package;

import java.util.Objects;

public abstract class Package {
    protected final Long id;
    protected Integer size;

    public Package(Long id) {
        this.id = id;
    }

    public Package() {
        this.id = System.currentTimeMillis();
    }

    public Long getId() {
        return id;
    }

    public Integer getSize() {
        return this.size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public abstract Double getDeliveryPrice();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Package aPackage)) return false;
        return getId().equals(aPackage.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Package{" + "id=" + id + '\'' + ", size=" + size + '}';
    }
}
