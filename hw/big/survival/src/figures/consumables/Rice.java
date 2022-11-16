package figures.consumables;

import figures.Consumable;
import figures.Hero;

public class Rice extends Consumable {

  public Rice(Integer level) throws IllegalArgumentException {
    super('R', "Rice", level);
  }

  @Override
  public void apply(Hero hero) {
    hero.upPower(this.level * 2);
  }
}
