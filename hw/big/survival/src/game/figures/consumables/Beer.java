package game.figures.consumables;

import game.figures.Consumable;
import game.figures.Hero;

public class Beer extends Consumable {

  public Beer(Integer level) throws IllegalArgumentException {
    super('B', "Beer", level);
    this.usesLeft = level;
  }

  @Override
  public void apply(Hero hero) {
    hero.lowerPower(15);

    if (this.usesLeft == this.level) {
      --this.usesLeft;
      hero.addActiveConsumable(this);
    }
  }
}
