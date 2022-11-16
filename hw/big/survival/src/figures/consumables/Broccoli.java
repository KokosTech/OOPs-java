package figures.consumables;

import figures.Consumable;
import figures.Hero;

public class Broccoli extends Consumable {

  public Broccoli(Integer level) throws IllegalArgumentException {
    super('B', "Broccoli", level);
  }

  @Override
  public void apply(Hero hero) {
    hero.upHealth(this.level * 2);
  }
}
