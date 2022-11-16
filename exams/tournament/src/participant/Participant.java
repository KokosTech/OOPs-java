package participant;

public abstract class Participant {

  protected Long id;
  protected String name;

  protected Short power;
  Double health;

  public Participant(String name, Short power, Double health)
    throws IllegalArgumentException {
    if (name.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be empty");
    }

    this.name = name;
    this.power = power;
    this.health = health;
  }

  // Methods

  public void takeDamage(Double damage) {
    this.health -= damage;
  }

  public Boolean isKnockOut() {
    return this.health <= 0;
  }

  public abstract void attack(Participant participant);

  // Setters

  public void setId(Long id) {
    this.id = id;
  }

  public void setHealth(Double health) {
    this.health = health;
  }

  // Getters

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public Short getPower() {
    return this.power;
  }

  public Double getHealth() {
    return this.health;
  }
}
