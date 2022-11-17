package game.figures;

import java.util.ArrayList;

public class Hero extends Figure {

  Integer health, power;
  ArrayList<Consumable> activeConsumables;

  public Hero(String name, Integer health, Integer power)
    throws IllegalArgumentException {
    super(name.charAt(0), name);
    if (health < 0 || power < 0) throw new IllegalArgumentException(
      "Health and power must be positive"
    );

    this.health = health;
    this.power = power;

    this.activeConsumables = new ArrayList<>();
  }

  @Override
  public String toString() {
    return (
      "Hero [" +
      this.initial +
      "] " +
      this.name +
      " [health=" +
      this.health +
      ", power=" +
      this.power +
      ", activeConsumables=" +
      this.activeConsumables +
      "]"
    );
  }

  public boolean isAlive() {
    return this.health > 0;
  }

  // Manage Active Consumables

  public void addActiveConsumable(Consumable consumable) {
    this.activeConsumables.add(consumable);
  }

  public void removeActiveConsumable(Consumable consumable) {
    this.activeConsumables.remove(consumable);
  }

  public void applyActiveConsumables() {
    for (Consumable consumable : this.activeConsumables) {
      consumable.apply(this);
      consumable.decreaseUsesLeft();

      if (consumable.getUsesLeft() == 0) this.removeActiveConsumable(
          consumable
        );
    }
  }

  // Getters

  public Integer getHealth() {
    return health;
  }

  public Integer getPower() {
    return power;
  }

  // Setters

  public void setHealth(Integer health) {
    this.health = health;
  }

  public void setPower(Integer power) {
    this.power = power;
  }

  // Uppers

  public void upHealth(Integer health) {
    this.health += health;
  }

  public void upPower(Integer power) {
    this.power += power;
  }

  // Lowers

  public void lowerHealth(Integer health) {
    if (this.health - health < 0) this.health = 0; else this.health -= health;
  }

  public void lowerPower(Integer power) {
    if (this.power - power < 0) this.power = 0; else this.power -= power;
  }

  // Actions

  public Hero fight(Hero rhs) {
    if (
      this.power > rhs.power ||
      (this.power == rhs.getPower() && this.health > rhs.health)
    ) {
      rhs.setHealth(0);
      return rhs;
    }

    if (
      this.power < rhs.power ||
      (this.power == rhs.getPower() && this.health < rhs.health)
    ) {
      this.health = 0;
      return this;
    }

    this.health = 0;
    rhs.setHealth(0);
    return null;
  }
}
