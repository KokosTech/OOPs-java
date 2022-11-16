package figures;

public abstract class Consumable extends Figure {

  protected Integer level;
  protected Integer usesLeft;

  public Consumable(Character initial, String name, Integer level)
    throws IllegalArgumentException {
    super(initial, name);
    this.level = level;
  }

  // Abstract methods

  public abstract void apply(Hero hero);

  // Getters

  @Override
  public String toString() {
    return (
      "Consumable [" +
      this.initial +
      "] " +
      this.name +
      " [level=" +
      this.level +
      ", usesLeft=" +
      this.usesLeft +
      "]"
    );
  }

  public Integer getLevel() {
    return this.level;
  }

  public Integer getUsesLeft() {
    return this.usesLeft;
  }

  // Setters

  public void setLevel(Integer level) {
    this.level = level;
  }

  public void decreaseUsesLeft() {
    --this.usesLeft;
  }
}
