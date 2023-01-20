package Lift.Type;

/*
GondolaLift
capacity - от 6 до 25
рейтинг се пресмята по следната формула:
(capacity + manufactureYear + speed + (ако капацитета е > 15 се добавя 1200, ако ли не се добавя 600)) \ 10

 */

import Lift.Lift;

public class GondolaLift extends Lift {
    public GondolaLift(String name, short capacity, short manufactureYear, short speed) {
        super(name, capacity, manufactureYear, speed);

        if (capacity < 6 || capacity > 25) {
            throw new IllegalArgumentException("Capacity must be between 6 and 25");
        }
    }

    @Override
    public double getRaiting() {
        int bonus = capacity > 15 ? 1200 : 600;
        return (capacity + manufactureYear + speed + bonus) / 10.0;
    }
}
