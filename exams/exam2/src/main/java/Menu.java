/*
    При стартиране на програмата, да започне и четене от конзолата. Като потребителя ще въвежда данни за зимния курорт.
    Първо прочита име на курорта.
    След това следва четене на лифтове, като информацията за всеки лифт се подава на един ред и стойностите са разделени със запетаи.
    Формат:
    <LiftType>,<Name>,<Capacity>,<ManufactureYear>,<Speed>
    При възникнали грешки, да се изписва информативно съобщение в конзолата, но да не се прекъсва изпълнението на програмата.
    Пример:
    Недостатъчен брой полета, подадени в конзолата.
    Несъществуващ тип лифт.
    Несъответстващ тип лифт и капацитет.
    Невалидна година на производство.
    При прочитане на END, следва изписване на името на курорта и неговия рейтинг и приключване на програмата.
    Пример на изписването
    Winter resort Borovets has a rating of 95.43.
 */

import Lift.Type.ChairLift;
import Lift.Type.GondolaLift;
import Lift.Type.TBarLift;
import WinterResort.WinterResort;

import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    WinterResort winterResort;

    public Menu() {
        winterResort = new WinterResort();
    }

    public void start() {
        System.out.println("Enter winter resort name:");
        winterResort.setName(scanner.nextLine());

        System.out.println("Enter lifts:");
        String input = scanner.nextLine();

        while (!input.equals("END")) {
            String[] tokens = input.split(","); // just like our OS homework

            try {
                if (tokens.length != 5) {
                    System.out.println("Invalid input!");
                } else {
                    String liftType = tokens[0];
                    String name = tokens[1];

                    short capacity = Short.parseShort(tokens[2]);
                    short manufactureYear = Short.parseShort(tokens[3]);
                    short speed = Short.parseShort(tokens[4]);

                    if (liftType.equals("ChairLift")) {
                        ChairLift chairLift = new ChairLift(name, capacity, manufactureYear, speed);
                        winterResort.addLift(chairLift);
                        chairLift.printInfo();
                    } else if (liftType.equals("GondolaLift")) {
                        GondolaLift gondolaLift = new GondolaLift(name, capacity, manufactureYear, speed);
                        winterResort.addLift(gondolaLift);
                        gondolaLift.printInfo();
                    } else if (liftType.equals("TBarLift")) {
                        TBarLift tBarLift = new TBarLift(name, capacity, manufactureYear, speed);
                        winterResort.addLift(tBarLift);
                        tBarLift.printInfo();
                    } else {
                        System.out.println("Invalid lift type!");
                    }
                }
                input = scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                input = scanner.nextLine();
            }
        }
        winterResort.printInfo();
    }

}
