import game.Game;
import game.figures.Hero;
import game.figures.consumables.Beer;
import game.figures.consumables.Broccoli;
import game.figures.consumables.Mushroom;
import game.figures.consumables.Orange;
import game.figures.consumables.Pizza;
import game.figures.consumables.Rice;

public class Main {

  public static void main(String[] args) {
    try {
      Game game = new Game(3, 3);
      game.addFigure(new Hero("Omori", 2000, 300));
      game.addFigure(new Hero("Basil", 1500, 200));
      game.addFigure(new Hero("Hero", 2500, 400));

      game.addFigure(new Broccoli(3));
      game.addFigure(new Rice(1));
      game.addFigure(new Mushroom(5));

      game.addFigure(new Beer(2));
      game.addFigure(new Orange(4));
      game.addFigure(new Pizza(5));

      game.start();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.out.println(e.getStackTrace());
      System.out.println("Goodbye!");
    }
  }
}
