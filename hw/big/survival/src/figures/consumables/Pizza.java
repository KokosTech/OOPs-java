package figures.consumables;

import figures.Consumable;
import figures.Hero;

public class Pizza extends Consumable {

  public Pizza(Integer level) throws IllegalArgumentException {
    super('P', "Pizza", level);
  }

  @Override
  public void apply(Hero hero) {
    hero.upPower(13);
  }
}
