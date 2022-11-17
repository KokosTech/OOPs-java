package game.figures.consumables;

import game.figures.Consumable;
import game.figures.Hero;

public class Broccoli extends Consumable {

  public Broccoli(Integer level) throws IllegalArgumentException {
    super('B', "Broccoli", level);
  }

  @Override
  public void apply(Hero hero) {
    hero.upHealth(this.level * 2);
  }
}
