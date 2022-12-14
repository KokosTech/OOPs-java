package game.figures.consumables;

import game.figures.Consumable;
import game.figures.Hero;

public class Mushroom extends Consumable {

  public Mushroom(Integer level) throws IllegalArgumentException {
    super('M', "Mushroom", level);
    this.usesLeft = level;
  }

  @Override
  public void apply(Hero hero) {
    hero.lowerHealth(20);

    if (this.usesLeft == this.level) {
      --this.usesLeft;
      hero.addActiveConsumable(this);
    }
  }
}
