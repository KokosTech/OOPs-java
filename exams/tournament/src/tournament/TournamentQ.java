package tournament;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import participant.Participant;

public class TournamentQ {

  final Date date;
  private String name;

  private Queue<Participant> group1, group2;
  private ArrayList<Participant> participants;

  public TournamentQ(String name) {
    date = new Date();

    this.name = name;
    this.participants = new ArrayList<Participant>();
  }

  private void generateId(Participant participant) {
    participant.setId(date.getTime());
  }

  public TournamentQ addParticipant(Participant participant) {
    generateId(participant);
    this.participants.add(participant);
    return this;
  }

  private void createGroups() {
    this.group1 = new LinkedList<Participant>();
    this.group2 = new LinkedList<Participant>();

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

    while (group1.size() > 0 && group2.size() > 0) {
      Participant participant1 = group1.remove();
      Participant participant2 = group2.remove();

      System.out.printf(
        "%s fight %s\n",
        participant1.getName(),
        participant2.getName()
      );

      Participant winner = fight(participant1, participant2);

      System.out.printf("%s moves on\n", winner.getName());

      if (winner.getId() == participant1.getId()) {
        group1.add(participant1);
      } else {
        group2.add(participant2);
      }
    }

    System.out.println("Tournament is over");
    if (0 == group1.size()) {
      System.out.println("Winner is " + group2.peek().getName());
    } else {
      System.out.println("Winner is " + group1.peek().getName());
    }
  }
}
