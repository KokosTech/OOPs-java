package Products;

/*
    Sting barcode - съдържа 13 цифри, задължително поле, може да започва с 0. Уникален идентификатор. Не може да се променя.
    String name - име на артикула, незадължително
    double weight - тежест на артикула в килограми, незадължително.
    double price - цена на артикула, задължително, по-голямо от 0.
*/
public class Other extends Product {
    final String barcode;
    String name;
    Double weight;

    public Other(String barcode, Double price) {
        super(price);

        if(barcode.length() != 13)
            throw new IllegalArgumentException("Barcode must be 13 digits long");

        if(!barcode.chars().allMatch(Character::isDigit))
            throw new IllegalArgumentException("Barcode must contain only digits");

        this.barcode = barcode;
    }

    public Other(String barcode, String name, Double weight, Double price) {
        this(barcode, price);

        if(name.isEmpty())
            throw new IllegalArgumentException("Name cannot be empty");
        this.name = name;

        if(weight < 0)
            throw new IllegalArgumentException("Weight cannot be negative");
        this.weight = weight;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Other && ((Other) obj).barcode.equals(this.barcode);
    }

    @Override
    public String toString() {
        return "Other{" +
                "barcode='" + barcode + '\'' +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                '}';
    }
}
