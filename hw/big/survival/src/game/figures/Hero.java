package game.figures;

import java.util.ArrayList;

public class Hero extends Figure {

  Integer health;
  Integer power;
  ArrayList<Consumable> activeConsumables;

  public Hero(String name, Integer health, Integer power)
    throws IllegalArgumentException {
    super(name.charAt(0), name);
    if (health < 0 || power < 0) throw new IllegalArgumentException(
      "Health or/and power must be positive"
    );

    this.health = health;
    this.power = power;

    this.activeConsumables = new ArrayList<>();
  }

  // Manage Active Consumables

  public void addActiveConsumable(Consumable consumable) {
    this.activeConsumables.add(consumable);
  }

  public void removeActiveConsumable(Consumable consumable) {
    this.activeConsumables.remove(consumable);
  }

  public void applyActiveConsumables() {
    for (Integer i = 0; i < this.activeConsumables.size(); ++i) {
      this.activeConsumables.get(i).apply(this);
      this.activeConsumables.get(i).decreaseUsesLeft();

      if (
        this.activeConsumables.get(i).getUsesLeft() == 0
      ) this.activeConsumables.remove(i);
    }
  }

  // Getters

  public Integer getHealth() {
    return health;
  }

  public Integer getPower() {
    return power;
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
      this.activeConsumables.size() +
      "]"
    );
  }

  public boolean isAlive() {
    return this.health > 0;
  }

  // Setters

  public void setHealth(Integer health) {
    if (health < 0) throw new IllegalArgumentException(
      "Health must be positive"
    );
    this.health = health;
  }

  public void setPower(Integer power) {
    if (power < 0) throw new IllegalArgumentException("Power must be positive");
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

  // null - both die
  // this - this dies
  // rhs - oponent dies
  public Hero fight(Hero rhs) {
    if (this.power > rhs.power) {
      System.out.println(this + " killed " + rhs);
      rhs.setHealth(0);
      return rhs;
    } else if (this.power < rhs.power) {
      System.out.println(rhs + " killed " + this);
      this.health = 0;
      return this;
    } else {
      if (this.health > rhs.health) {
        System.out.println(this + " killed " + rhs);
        rhs.setHealth(0);
        return rhs;
      } else if (this.health < rhs.health) {
        System.out.println(rhs + " killed " + this);
        this.health = 0;
        return this;
      } else {
        System.out.println(this + " and " + rhs + " killed each other");
        this.health = 0;
        rhs.setHealth(0);
        return null;
      }
    }
  }
}
