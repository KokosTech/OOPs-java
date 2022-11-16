package figures.consumables;

import figures.Consumable;
import figures.Hero;

public class Orange extends Consumable {

  public Orange(Integer level) throws IllegalArgumentException {
    super('O', "Orange", level);
  }

  @Override
  public void apply(Hero hero) {
    hero.upHealth(level);
    hero.upPower(level);
  }
}
