package Lift;

/*
Lift(Лифт)
name - име на лифта
capacity - цяло положително число, репрезентира колко пътника могат да се возят на лифта. Максимална стойност 25
manufactureYear - цяло положително четирицифрено число, по-голямо от 1930. Репрезентира година на производство на лифта. Не може да е произведен в бъдещето
speed - цяло положително число, репрезентира колко пътника в час може да извози
И четирите полета са задължителни!
double getRaiting() - връща рейтинг на лифта
При всяко несъответствие да се хвърля грешка с подходящо съобщение!

 */

import java.time.Year;

public abstract class Lift {
    protected String name;
    protected short capacity;
    protected short manufactureYear;
    protected short speed;

    public Lift(String name, short capacity, short manufactureYear, short speed) {
        this.name = name;
        this.capacity = capacity;

        if (manufactureYear < 1930)
            throw new IllegalArgumentException("Manufacture year must be greater than 1930!");

        if(manufactureYear > Year.now().getValue())
            throw new IllegalArgumentException("Manufacture year must be less than 2021!");

        this.manufactureYear = manufactureYear;

        if (speed < 0)
            throw new IllegalArgumentException("Speed must be positive!");

        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getCapacity() {
        return capacity;
    }

    public void setCapacity(short capacity) {
        this.capacity = capacity;
    }

    public short getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(short manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public short getSpeed() {
        return speed;
    }

    public void setSpeed(short speed) {
        this.speed = speed;
    }

    public abstract double getRaiting();

    public void printInfo() {
        System.out.println("Name: " + name);
        System.out.println("Capacity: " + capacity);
        System.out.println("Manufacture year: " + manufactureYear);
        System.out.println("Speed: " + speed);
    }
}
