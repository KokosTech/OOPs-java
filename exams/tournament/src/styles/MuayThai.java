package styles;

import ITechnique.ICanBox;
import ITechnique.ICanKick;
import ITechnique.ICanSpin;
import participant.Participant;

public class MuayThai
  extends Participant
  implements ICanBox, ICanKick, ICanSpin {

  public MuayThai(String name, Short power, Double health) {
    super(name, power, health);
  }

  @Override
  public double spin(double damage) {
    return damage * 2.0;
  }

  @Override
  public void punch(Participant participant) {
    participant.takeDamage(spin((double) this.power));
    System.out.println(this.getName() + " (" + getHealth() + ") punches " + participant.getName() + " (" + participant.getHealth() + ")");
  }

  @Override
  public void kick(Participant participant) {
    participant.takeDamage(spin(this.power * 1.5));
    System.out.println(this.getName() + " (" + getHealth() + ") kicks " + participant.getName() + " (" + participant.getHealth() + ")");
  }

  @Override
  public void attack(Participant participant) {
    punch(participant);
    kick(participant);
  }
}
