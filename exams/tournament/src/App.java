import styles.Box;
import styles.Judo;
import styles.Karate;
import styles.MuayThai;
import tournament.Tournament;

public class App {

  public static void main(String[] args) throws Exception {
    try {
      Tournament tournament = new Tournament("Tournament");
      tournament.addParticipant(new Box("Boxer 1", (short) 10, 100.0));
      tournament.addParticipant(new Judo("Judo 1", (short) 20, 400.0));
      tournament.addParticipant(new Karate("Karate 1", (short) 24, 150.0));
      tournament.addParticipant(new MuayThai("MuayThai 1", (short) 15, 350.0));

      tournament.addParticipant(new Box("Boxer 2", (short) 10, 100.0));
      tournament.addParticipant(new Judo("Judo 2", (short) 20, 400.0));
      tournament.addParticipant(new Karate("Karate 2", (short) 24, 150.0));
      tournament.addParticipant(new MuayThai("MuayThai 2", (short) 15, 350.0));

      tournament.start();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.out.println(e.getStackTrace());
      System.out.println("Goodbye!");
    }
  }
}
