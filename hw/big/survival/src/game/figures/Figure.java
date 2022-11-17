package game.figures;

public abstract class Figure {

  private Integer x;
  private Integer y;

  protected final Character initial;
  protected final String name;

  public Figure(Character initial, String name)
    throws IllegalArgumentException {
    if (initial == null || initial == ' ' || name.isEmpty()) {
      throw new IllegalArgumentException("Initial and name cannot be null");
    }

    this.initial = initial;
    this.name = name;
  }

  // Abstract methods

  @Override
  public abstract String toString();

  // Getters

  public Integer getX() {
    return x;
  }

  public Integer getY() {
    return y;
  }

  public Character getInitial() {
    return initial;
  }

  public String getName() {
    return name;
  }

  // Setters

  public void setX(Integer x) {
    this.x = x;
  }

  public void setY(Integer y) {
    this.y = y;
  }ƒ
}
