package Lift.Type;

import Lift.Lift;

/*
capacity - от 2 до 8
рейтинга се пресмята по следната формула:
(capacity + manufactureYear + speed + (ако годината на производство е > 2010 се добавя 500, ако ли не се добавя 200)) \ 10

 */

public class ChairLift extends Lift {
    public ChairLift(String name, short capacity, short manufactureYear, short speed) {
        super(name, capacity, manufactureYear, speed);

        if (capacity < 2 || capacity > 8) {
            throw new IllegalArgumentException("Capacity must be between 2 and 8");
        }
    }

    @Override
    public double getRaiting() {
        int bonus = manufactureYear > 2010 ? 500 : 200;
        return (capacity + manufactureYear + speed + bonus) / 10.0;
    }
}
