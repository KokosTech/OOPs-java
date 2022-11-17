package game;

import game.directions.Directions;
import game.field.Field;
import game.figures.Consumable;
import game.figures.Figure;
import game.figures.Hero;
import java.util.ArrayList;
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

  private boolean isOver() {
    if (this.heroes.size() > 1) return false;

    System.out.println("Game over!");
    System.out.println("Winner: " + this.heroes.get(0).getName());

    this.print();
    return true;
  }

  public void start() throws IllegalArgumentException {
    if (this.heroes.size() < 2) throw new IllegalArgumentException(
      "Not enough heroes"
    );

    System.out.println("Game started!");

    while (!isOver()) {
      for (Integer i = 0; i < this.heroes.size(); ++i) {
        this.print();
        this.moveHero(this.heroes.get(i));
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

    System.out.println(hero + " is moving...");

    switch (this.getDirection()) {
      case UP:
        if (this.isValidMove(oldX, oldY - 1)) {
          oldY -= 1;
          System.out.print(hero + " moved up");
        }
        break;
      case DOWN:
        if (this.isValidMove(oldX, oldY + 1)) {
          oldY += 1;
          System.out.print(hero + " moved down");
        }
        break;
      case LEFT:
        if (this.isValidMove(oldX - 1, oldY)) {
          oldX -= 1;
          System.out.print(hero + " moved left");
        }
        break;
      case RIGHT:
        if (this.isValidMove(oldX + 1, oldY)) {
          oldX += 1;
          System.out.print(hero + " moved right");
        }
        break;
    }

    if (oldX != hero.getX() || oldY != hero.getY()) {
      System.out.println(
        " from " + hero.getX() + " " + hero.getY() + " to " + oldX + " " + oldY
      );
    }

    moveHeroHelper(hero, oldX, oldY);
  }

  public void moveHeroHelper(Hero hero, Integer x, Integer y) {
    if (hero == null) {
      throw new IllegalArgumentException("Hero cannot be null");
    }

    if (!isValidMove(x, y)) {
      throw new IllegalArgumentException("Coordinates are not valid");
    }

    if (!this.field.isPositionEmpty(x, y)) {
      if (this.field.getFigure(x, y) instanceof Hero oponent) {
        if (hero == this.field.getFigure(x, y)) {
          return;
        }
        System.out.println(
          "Hero " + hero.getName() + " fights hero " + oponent.getName()
        );

        battle(hero, oponent);
      } else if (this.field.getFigure(x, y) instanceof Consumable) {
        System.out.println(
          "Hero " +
          hero.getName() +
          " consumed a " +
          this.field.getFigure(x, y).getName()
        );
        ((Consumable) this.field.getFigure(x, y)).apply(hero);
        this.removeFigure(this.field.getFigure(x, y));

        if (!hero.isAlive()) {
          System.out.println("Hero " + hero.getName() + " died from poison");
          this.heroes.remove(hero);
        }
      }
    }

    if (heroes.contains(hero)) {
      this.field.moveFigure(hero, x, y);
    }
  }

  private void battle(Hero hero1, Hero hero2) {
    if (hero1 == null || hero2 == null) {
      throw new IllegalArgumentException("Heroes cannot be null");
    }

    Hero deadHero = hero1.fight(hero2);

    if (deadHero != null) {
      this.removeFigure(deadHero);
    } else {
      this.removeFigure(hero1);
      this.removeFigure(hero2);
    }
  }

  // Validate Methods

  private boolean isValidMove(Integer x, Integer y) {
    return this.field.areValidCoordinates(x, y);
  }

  // Figure Methods

  public Game addFigure(Figure figure) {
    this.field.addFigure(figure);

    if (figure instanceof Hero) {
      this.heroes.add((Hero) figure);
      System.out.print("Hero added ");
    } else {
      System.out.print("Consumable added ");
    }

    System.out.println(figure.toString() + "at" + figure.getPosition());

    return this;
  }

  public Game removeFigure(Figure figure) {
    this.field.removeFigure(figure);

    if (figure instanceof Hero) {
      this.heroes.remove(figure);
      System.out.print("Hero removed ");
    } else {
      System.out.print("Consumable removed ");
    }

    System.out.println(figure.toString() + " at " + figure.getPosition());

    return this;
  }

  // Field Methods

  public void print() {
    this.field.print();
  }
}
