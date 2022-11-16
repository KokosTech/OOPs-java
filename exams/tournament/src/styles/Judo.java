package styles;

import ITechnique.ICanBox;
import ITechnique.ICanWrestle;
import participant.Participant;

public class Judo extends Participant implements ICanWrestle, ICanBox {

  public Judo(String name, Short power, Double health) {
    super(name, power, health);
  }

  @Override
  public void toss(Participant participant) {
    participant.takeDamage((double) (this.power * 2));
    System.out.println(this.getName() + " (" + getHealth() + ") tosses " + participant.getName() + " (" + participant.getHealth() + ")");
  }

  @Override
  public void punch(Participant participant) {
    participant.takeDamage((double) this.power);
    System.out.println(this.getName() + " (" + getHealth() + ") punches " + participant.getName() + " (" + participant.getHealth() + ")");
  }

  @Override
  public void attack(Participant participant) {
    punch(participant);
    toss(participant);
  }
}
