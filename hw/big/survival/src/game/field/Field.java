package game.field;

import game.figures.Figure;
import java.util.Random;

public class Field {

  private static final String COORDINATES_ARE_NOT_VALID = "Coordinates are not valid";

  private final Random random;

  private final Integer rows;
  private final Integer cols;

  private Figure[][] field;

  public Field(Integer rows, Integer cols) throws IllegalArgumentException {
    if (rows < 2 && cols < 2) {
      throw new IllegalArgumentException(
        "Rows and cols must be greater than 1"
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
      this.field[figure.getX()][figure.getY()] = figure;
      return;
    }

    if (!areValidCoordinates(figure.getX(), figure.getY())) {
      throw new IllegalArgumentException(COORDINATES_ARE_NOT_VALID);
    }

    if (!isPositionEmpty(figure.getX(), figure.getY())) {
      throw new IllegalArgumentException(
        "There is already a figure in position " + figure.getPosition()
      );
    }

    this.field[figure.getX()][figure.getY()] = figure;
  }

  public void moveFigure(Figure figure, Integer newX, Integer newY) {
    if (figure == null) {
      throw new IllegalArgumentException("Figure cannot be null");
    }

    if (!areValidCoordinates(newX, newY)) {
      throw new IllegalArgumentException(COORDINATES_ARE_NOT_VALID);
    }

    this.field[figure.getX()][figure.getY()] = null;
    this.field[newX][newY] = figure;

    figure.setX(newX);
    figure.setY(newY);
  }

  public void removeFigure(Figure figure) {
    if (figure == null) {
      throw new IllegalArgumentException("Figure cannot be null");
    }

    if (!areValidCoordinates(figure.getX(), figure.getY())) {
      throw new IllegalArgumentException(COORDINATES_ARE_NOT_VALID);
    }

    if (this.field[figure.getX()][figure.getY()] == null) {
      throw new IllegalArgumentException(
        "There is no figure in position " + figure.getPosition()
      );
    }

    this.field[figure.getX()][figure.getY()] = null;
  }

  // Hero Methods

  // Validation Methods

  public boolean areValidCoordinates(Integer x, Integer y) {
    return x >= 0 && x < this.rows && y >= 0 && y < this.cols;
  }

  public boolean isPositionEmpty(Integer x, Integer y) {
    return this.field[x][y] == null;
  }

  // Getters

  public Integer getRows() {
    return rows;
  }

  public Integer getCols() {
    return cols;
  }

  public Figure getFigure(Integer x, Integer y) {
    return this.field[x][y];
  }

  // Helpers

  private void setFigurePosition(Figure figure) {
    Integer x;
    Integer y;

    do {
      x = getRandom(this.cols);
      y = getRandom(this.rows);
    } while (!isPositionEmpty(x, y));

    this.field[x][y] = figure;

    figure.setX(x);
    figure.setY(y);
  }

  public void setFigurePosition(Figure figure, Integer x, Integer y) {
    if (!areValidCoordinates(x, y)) {
      throw new IllegalArgumentException(COORDINATES_ARE_NOT_VALID);
    }

    if (figure.getX() != null && figure.getY() != null) {
      this.field[figure.getX()][figure.getY()] = null;
    }

    this.field[x][y] = figure;

    figure.setX(x);
    figure.setY(y);
  }

  private Integer getRandom(Integer max) {
    return random.nextInt(max);
  }
}
