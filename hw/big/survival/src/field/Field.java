package field;

import figures.Consumable;
import figures.Figure;
import figures.Hero;
import java.util.Arrays;
import java.util.Random;

public class Field {

  final Random random;

  private final Integer rows, cols;
  private Figure[][] field;

  public Field(Integer rows, Integer cols) throws IllegalArgumentException {
    if (rows < 2 && cols < 2) {
      throw new IllegalArgumentException(
        "Rows and cols must be greater than 0"
      );
    }

    random = new Random();

    this.rows = rows;
    this.cols = cols;

    this.field = new Figure[rows][cols];

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        this.field[i][j] = null;
      }
    }
  }

  // General Methods

  public void print() {
    // fancy print with |

    System.out.println(" ---".repeat(cols));
    for (int i = 0; i < rows; ++i) {
      for (int j = 0; j < cols; ++j) {
        if (field[i][j] != null) {
          System.out.print("| " + field[i][j].getInitial() + " ");
        } else if (field[i][j] == null) {
          System.out.print("|   ");
        }
      }
      System.out.println("|");
      System.out.println(" ---".repeat(cols));
    }
  }

  // Figure Methods

  public void addFigure(Figure figure) {
    if (figure == null) {
      throw new IllegalArgumentException("Figure cannot be null");
    }

    if (figure.getX() == null || figure.getY() == null) {
      this.setFigurePosition(figure);
      this.field[figure.getY()][figure.getX()] = figure;
      return;
    }

    if (!areValidCoordinates(figure.getX(), figure.getY())) {
      throw new IllegalArgumentException("Coordinates are not valid");
    }

    if (!isPositionEmpty(figure.getX(), figure.getY())) {
      throw new IllegalArgumentException(
        "There is already a figure in position " + figure.getPosition()
      );
    }

    this.field[figure.getY()][figure.getX()] = figure;
  }

  public void removeFigure(Figure figure) {
    if (figure == null) {
      throw new IllegalArgumentException("Figure cannot be null");
    }

    if (!areValidCoordinates(figure.getX(), figure.getY())) {
      throw new IllegalArgumentException("Coordinates are not valid");
    }

    if (this.field[figure.getY()][figure.getX()] == null) {
      throw new IllegalArgumentException(
        "There is no figure in position " + figure.getPosition()
      );
    }

    this.field[figure.getY()][figure.getX()] = null;
  }

  // Hero Methods

  public Hero moveHero(Hero hero, Integer x, Integer y) {
    if (hero == null) {
      throw new IllegalArgumentException("Hero cannot be null");
    }

    if (!areValidCoordinates(x, y)) {
      throw new IllegalArgumentException("Coordinates are not valid");
    }

    Hero dead = null;

    if (!isPositionEmpty(x, y)) {
      if (this.field[y][x] instanceof Hero) {
        System.out.println(
          "Hero " +
          hero.getName() +
          " fights hero " +
          this.field[y][x].getName()
        );

        dead = hero.fight((Hero) this.field[y][x]);
        if(dead != null) {
          this.removeFigure(dead);
        } else {
          this.removeFigure(this.field[y][x]);
          this.removeFigure(hero);
        }
      } else if (this.field[y][x] instanceof Consumable) {
        System.out.println(
          "Hero " + hero.getName() + " ate a " + this.field[y][x].getName()
        );
        ((Consumable) this.field[y][x]).apply(hero);
      }
    }

    this.field[hero.getY()][hero.getX()] = null;
    this.field[y][x] = hero;

    hero.setX(x);
    hero.setY(y);

    return dead;
  }

  // Validation Methods

  public boolean areValidCoordinates(Integer x, Integer y) {
    return x >= 0 && x < this.rows && y >= 0 && y < this.cols;
  }

  public boolean isPositionEmpty(Integer x, Integer y) {
    return this.field[y][x] == null;
  }

  // Getters

  public Integer getRows() {
    return rows;
  }

  public Integer getCols() {
    return cols;
  }

  public Figure[][] getField() {
    return field;
  }

  // Helpers

  private void setFigurePosition(Figure figure) {
    Integer x, y;

    do {
      x = getRandom(this.cols);
      y = getRandom(this.rows);
    } while (!isPositionEmpty(x, y));

    figure.setX(x);
    figure.setY(y);
  }

  private Integer getRandom(Integer max) {
    return random.nextInt(max);
  }
}
