import directions.Directions;
import field.Field;
import figures.Consumable;
import figures.Figure;
import figures.Hero;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Game {

  private ArrayList<Hero> heroes;

  final Field field;
  final Random random = new Random();

  public Game(Integer rows, Integer cols) throws IllegalArgumentException {
    if (rows < 1 || cols < 1) throw new IllegalArgumentException(
      "Rows and columns must be positive"
    );

    this.heroes = new ArrayList<>();
    this.field = new Field(rows, cols);
  }

  // Start

  public void start() throws IllegalArgumentException {
    if (this.heroes.size() < 2) throw new IllegalArgumentException(
      "Not enough heroes"
    );

    System.out.println("Game started!");

    while (true) {
      for (Hero hero : this.heroes) {
        this.print();
        this.moveHero(hero);
      }

      if (this.heroes.size() < 2) {
        System.out.println("Game over!");
        System.out.println("Winner: " + this.heroes.get(0).getName());

        this.print();
        break;
      }
    }
  }

  // Move Methods

  private Directions getDirection() {
    return Directions.values()[this.random.nextInt(Directions.values().length)];
  }

  private void moveHero(Hero hero) {
    Integer oldX = hero.getX();
    Integer oldY = hero.getY();

    hero.applyActiveConsumables();

    if (!hero.isAlive()) {
      this.heroes.remove(hero);
      return;
    }

    System.out.println(hero.getName() + " is moving...");

    switch (this.getDirection()) {
      case UP:
        if (this.isValidMove(hero, oldX, oldY - 1)) {
          hero.setY(oldY - 1);
        }
        break;
      case DOWN:
        if (this.isValidMove(hero, x, y + 1)) {
          hero.setY(oldY + 1);
        }
        break;
      case LEFT:
        if (this.isValidMove(hero, x - 1, y)) {
          hero.setX(oldX - 1);
        }
        break;
      case RIGHT:
        if (this.isValidMove(hero, x + 1, y)) {
          hero.setX(oldX + 1);
        }
        break;
    }
    field.moveHero(hero, hero.getX(), hero.getY());
  }

  // Validate Methods

  private boolean isValidMove(Hero hero, Integer x, Integer y) {
    return this.field.areValidCoordinates(x, y);
  }

  // Figure Methods

  public Game addFigure(Figure figure) {
    this.field.addFigure(figure);

    if (figure instanceof Hero) {
      this.heroes.add((Hero) figure);
      System.out.print("Hero added");
    } else {
      System.out.print("Consumable added");
    }

    System.out.println(figure.toString() + "at" + figure.getPosition());

    return this;
  }

  public Game removeFigure(Figure figure) {
    this.field.removeFigure(figure);

    if (figure instanceof Hero) {
      this.heroes.remove(figure);
      System.out.print("Hero removed");
    } else {
      System.out.print("Consumable removed");
    }

    System.out.println(figure.toString() + "at" + figure.getPosition());

    return this;
  }

  // Field Methods

  public void print() {
    this.field.print();
  }
}
