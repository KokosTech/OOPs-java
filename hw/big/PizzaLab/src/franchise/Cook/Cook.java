package franchise.Cook;

import franchise.Franchise;
import franchise.Order;
import utils.Logger.ThreadLogger;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.Thread.sleep;

public class Cook implements Runnable {
    private final String firstname;
    private final String lastname;
    private Integer workExperience;
    private Experience experience;
    private final Franchise franchise;

    private void calcExperience() {
        this.experience = switch (this.workExperience) {
            case 0, 1, 2, 3 -> Experience.JUNIOR;
            case 4, 5, 6, 7, 8 -> Experience.NORMAL;
            default -> Experience.SENIOR;
        };
    }

    public Cook(String firstname, String lastname, Integer experience, Franchise franchise) {
        if (Objects.isNull(firstname) || Objects.isNull(lastname) || firstname.isEmpty() || lastname.isEmpty()) {
            throw new IllegalArgumentException("First name and last name cannot be null or empty");
        }

        if (Objects.isNull(experience) || experience < 0) {
            throw new IllegalArgumentException("Experience cannot be null or negative");
        }

        if (Objects.isNull(franchise)) {
            throw new IllegalArgumentException("Franchise cannot be null");
        }

        this.firstname = firstname;
        this.lastname = lastname;
        this.workExperience = experience;
        this.calcExperience();
        this.franchise = franchise;
    }

    // Methods

    public Double getTimeToMake(Short numberOfIngredients) {
        return numberOfIngredients * experience.getSpeedMultiplier();
    }

    // Runnable

    @Override
    public void run() {
        while (franchise.getOpen()) {
            try {
                ThreadLogger threadLogger = new ThreadLogger("logs/cook" + Thread.currentThread().getName() + ".log");
                Order order = franchise.getNextOrderReadyToMake();

                if(Objects.isNull(order))
                    continue;

                System.out.println("Cook " + this + " is preparing order " + order.getId());
                sleep((long) (getTimeToMake(order.getPizza().getNumberOfIngredients()) * 1000));
                franchise.addOrderReadyToBake(order);
                threadLogger.log("Cook " + this + " has prepared order " + order.getId());
            } catch (InterruptedException e) {
                ThreadLogger threadLogger = null;
                try {
                    threadLogger = new ThreadLogger("logs/cook" + Thread.currentThread().getName() + ".log");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    threadLogger.log("Cook " + this + " has been killed");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Getters and Setters

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Integer getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(Integer workExperience) {
        if (workExperience < 0)
            throw new IllegalArgumentException("Experience cannot be negative");

        this.workExperience = workExperience;
        this.calcExperience();
    }

    public Experience getExperience() {
        return experience;
    }

    public Franchise getFranchise() {
        return franchise;
    }

    @Override
    public String toString() {
        return "Cook{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", workExperience=" + workExperience +
                ", experience=" + experience +
                ", franchise=" + franchise +
                '}';
    }
}
