package User;

public class UserDetails {
    private final Long id;
    private final Long userId;
    private String firstName;
    private String lastName;
    private String telephone;

    public UserDetails(Long id, String firstName, String lastName, String telephone, Long userId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.userId = userId;
    }

    public UserDetails(String firstName, String lastName, String telephone, Long userId) {
        this.id = System.currentTimeMillis();
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", telephone='" + telephone + '\'' +
                ", userId=" + userId +
                '}';
    }
}
