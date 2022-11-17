package game.figures;

public abstract class Consumable extends Figure {

  protected Integer level;
  protected Integer usesLeft;

  protected Consumable(Character initial, String name, Integer level)
    throws IllegalArgumentException {
    super(initial, name);
    if (level < 1) {
      throw new IllegalArgumentException("Level must be greater than 0");
    }

    if (level > 5) {
      throw new IllegalArgumentException("Level must be less or equal to 5");
    }

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

  public void decreaseUsesLeft() {
    --this.usesLeft;
  }
}
