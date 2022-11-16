package styles;

import ITechnique.ICanBox;
import participant.Participant;

public class Box extends Participant implements ICanBox {

  public Box(String name, Short power, Double health) {
    super(name, power, health);
  }

  @Override
  public void punch(Participant participant) {
    participant.takeDamage((double) this.power);
    System.out.println(this.getName() + " (" + getHealth() + ") punches " + participant.getName() + " (" + participant.getHealth() + ")");
  }

  @Override
  public void attack(Participant participant) {
    punch(participant);
  }
}
