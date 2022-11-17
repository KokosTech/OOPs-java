package game.figures.consumables;

import game.figures.Consumable;
import game.figures.Hero;

public class Rice extends Consumable {

  public Rice(Integer level) throws IllegalArgumentException {
    super('R', "Rice", level);
  }

  @Override
  public void apply(Hero hero) {
    hero.upPower(this.level * 2);
  }
}
