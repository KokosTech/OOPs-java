package styles;

import ITechnique.ICanBox;
import ITechnique.ICanKick;
import participant.Participant;

public class Karate extends Participant implements ICanBox, ICanKick {

  public Karate(String name, Short power, Double health) {
    super(name, power, health);
  }

  @Override
  public void punch(Participant participant) {
    participant.takeDamage((double) this.power);
    System.out.println(this.getName() + " (" + getHealth() + ") punches " + participant.getName() + " (" + participant.getHealth() + ")");
  }

  @Override
  public void kick(Participant participant) {
    participant.takeDamage(this.power * 1.5);
    System.out.println(this.getName() + " (" + getHealth() + ") kicks " + participant.getName() + " (" + participant.getHealth() + ")");
  }

  @Override
  public void attack(Participant participant) {
    punch(participant);
    kick(participant);
  }
}
