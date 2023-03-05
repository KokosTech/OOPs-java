package Vehicle;

public abstract class Vehicle {
    private final Long id;
    private final String maker;
    private final String model;
    private final Short year;

    private String color;
    private Double price;

    public Vehicle(String maker, String model, Short year) {
        this(System.currentTimeMillis(), maker, model, year);
    }

    public Vehicle(Long id, String maker, String model, Short year) {
        if (id == null || maker == null || model == null || year == null)
            throw new IllegalArgumentException("Arguments cannot be null");

        if (maker.isEmpty() || model.isEmpty() || maker.isBlank() || model.isBlank())
            throw new IllegalArgumentException("Arguments cannot be empty");

        if (year < 1885 || year > 2023)
            throw new IllegalArgumentException("Year must be between 1885 and 2023");

        if (id < 0)
            throw new IllegalArgumentException("ID must be greater than 0");

        this.id = id;
        this.maker = maker;
        this.model = model;
        this.year = year;
    }

    public Vehicle(String maker, String model, Short year, String color, Double price) {
        this(System.currentTimeMillis(), maker, model, year, color, price);

        if (color == null || color.isEmpty() || color.isBlank())
            throw new IllegalArgumentException("Color cannot be null");
    }

    public Vehicle(Long id, String maker, String model, Short year, String color, Double price) {
        this(id, maker, model, year);

        if (color == null || color.isEmpty() || color.isBlank())
            throw new IllegalArgumentException("Color cannot be null");

        if (price < 0)
            throw new IllegalArgumentException("Price must be greater than 0");

        this.color = color;
        this.price = price;
    }

    // Getters

    public Long getId() {
        return id;
    }

    public String getMaker() {
        return maker;
    }

    public String getModel() {
        return model;
    }

    public Short getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    // Setters

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
