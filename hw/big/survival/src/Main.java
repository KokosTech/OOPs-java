import field.Field;
import figures.Hero;
import figures.consumables.Broccoli;
import figures.consumables.Mushroom;
import figures.consumables.Rice;

public class Main {

  public static void main(String[] args) {
    try {
      //Game game = new Game(2, 3);
      // Test

      /*       Field field = new Field(2, 3);
      field.print();
      field.addFigure(new Hero("Hero", 100, 10));
      field.print();
      field.addFigure(new Mushroom(2));
      field.print(); */


      //game.start();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.out.println(e.getStackTrace());
      System.out.println("Goodbye!");
    }
  }
}
