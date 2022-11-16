package tournament;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import participant.Participant;

public class Tournament {

  final Date date;
  private String name;

  private ArrayList<Participant> group1, group2;
  private ArrayList<Participant> participants;

  public Tournament(String name) {
    date = new Date();

    this.name = name;
    this.participants = new ArrayList<Participant>();
  }

  private void generateId(Participant participant) {
    participant.setId(date.getTime());
  }

  public Tournament addParticipant(Participant participant) {
    generateId(participant);
    this.participants.add(participant);
    return this;
  }

  private void createGroups() {
    this.group1 = new ArrayList<Participant>();
    this.group2 = new ArrayList<Participant>();

    for (int i = 0; i < participants.size(); i++) {
      if (i % 2 == 0) {
        this.group1.add(this.participants.get(i));
      } else {
        this.group2.add(this.participants.get(i));
      }
    }
  }

  private Participant fight(Participant p1, Participant p2) {
    Double health1 = p1.getHealth();
    Double health2 = p1.getHealth();

    while (!p1.isKnockOut() && !p2.isKnockOut()) {
      p1.attack(p2);
      p2.attack(p1);
    }

    if (p1.isKnockOut()) {
      p2.setHealth(health2);
      return p2;
    } else {
      p1.setHealth(health1);
      return p1;
    }
  }

  public void start() throws Exception {
    if (participants.size() < 2) {
      throw new Exception("Not enough participants");
    }
    // devides participants into two groups
    // and starts the tournament
    createGroups();

    while (!group1.isEmpty() && !group2.isEmpty()) {
      Iterator<Participant> it1 = group1.iterator();
      Iterator<Participant> it2 = group2.iterator();

      while (it1.hasNext() && it2.hasNext()) {
        Participant p1 = it1.next();
        Participant p2 = it2.next();

        Participant winner = fight(p1, p2);

        System.out.println(winner.getName() + " wins the fight!");
        System.out.println("=====================================");

        if (winner.getId() == p1.getId()) {
          group2.remove(p2);
          it2 = group2.iterator();
        } else {
          group1.remove(p1);
          it1 = group1.iterator();
        }
      }
    }

    System.out.println("Tournament is over");
    if (group1.isEmpty()) {
      System.out.println("Winner is " + group2.get(0).getName());
    } else {
      System.out.println("Winner is " + group1.get(0).getName());
    }
  }
}
