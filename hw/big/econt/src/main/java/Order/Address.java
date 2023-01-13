package Order;

public class Address {
    private final Long id;
    private final Long userId;
    private String country;
    private String city;
    private String street;

    public Address(Long id, String country, String city, String street, Long userId) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.street = street;
        this.userId = userId;
    }

    public Address(String country, String city, String street, Long userId) {
        this.id = System.currentTimeMillis();
        this.country = country;
        this.city = city;
        this.street = street;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", userId=" + userId +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
