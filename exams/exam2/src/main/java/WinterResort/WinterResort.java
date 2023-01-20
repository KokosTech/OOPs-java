package WinterResort;

/*
WinterResort
name - име на курорта
lifts - контейнер с всички лифтове в курорта
addLift(Lift lift) - добавя лифт в курорта. Ако вече съществува лифт със същото име и капацитет, да се хвърля грешка, че такъв лифт вече съществува.
getResortRaiting() - връща цялостни рейтинг на курорта на база на лифтовете в курорта. Като върнатия рейтинг представлява средно аритметично на рейтингите на лифтовете.

 */

import Lift.Lift;

import java.util.ArrayList;

public class WinterResort {
    private String name;
    private ArrayList<Lift> lifts;

    public WinterResort() {
        this.lifts = new ArrayList<>();
    }

    public WinterResort(String name) {
        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        this.name = name;
        this.lifts = new ArrayList<>();
    }

    public void addLift(Lift lift) {
        if (lifts.contains(lift)) {
            throw new IllegalArgumentException("Lift already exists");
        }
        lifts.add(lift);
    }

    public double getResortRating() {
        if (lifts.size() == 0) {
            return 0;
        }

        double sum = 0;
        for (Lift lift : lifts) {
            sum += lift.getRaiting();
        }
        return sum / lifts.size();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Lift> getLifts() {
        return lifts;
    }

    public void setLifts(ArrayList<Lift> lifts) {
        this.lifts = lifts;
    }

    public void printInfo() {
        System.out.println("Winter resort: " + name);
        System.out.println("Lifts: ");
        for (Lift lift : lifts) {
            lift.printInfo();
        }
        System.out.println("Resort rating: " + getResortRating());
    }
}
