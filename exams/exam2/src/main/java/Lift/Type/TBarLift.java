package Lift.Type;

import Lift.Lift;

/*
capacity - 2
рейтинга се пресмята по следната формула:
(capacity + manufactureYear + speed) \ 10

 */
public class TBarLift extends Lift {
    public TBarLift(String name, short capacity, short manufactureYear, short speed) {
        super(name, capacity, manufactureYear, speed);

        if (capacity != 2) {
            throw new IllegalArgumentException("capacity must be 2");
        }
    }

    @Override
    public double getRaiting() {
        return (capacity + manufactureYear + speed) / 10.0;
    }
}
