import Vehicle.Type.Car;
import Vehicle.Type.CarEquipment.EquipmentLevel;
import Vehicle.Type.SUV;
import Vehicle.Type.Truck;

public class Main {
    public static void main(String[] args) {
        // TODO: Test the js looking java here with different vehicles - Gundi Edition:

        Car car1 = new Car("BMW", "X5", (short) 2019, "color", 12000.0, (short) 2, (short) 2, EquipmentLevel.BASIC);
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Car car2 = new Car("Mazda", "CX-5", (short) 2019, "color", 12000.0, (short) 4, (short) 4, EquipmentLevel.SPORT);
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Car car3 = new Car("Audi", "A4", (short) 2019, "color", 12000.0, (short) 2, (short) 2, EquipmentLevel.PREMIUM);
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Car car4 = new Car("Kia", "Sorento", (short) 2019, "color", 12000.0, (short) 4, (short) 4, EquipmentLevel.BASIC);
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Car car5 = new Car("Toyota", "RAV4", (short) 2019, "color", 12000.0, (short) 3, (short) 4, EquipmentLevel.SPORT);
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Car car6 = new Car("Ford", "Escape", (short) 2019, "color", 12000.0, (short) 4, (short) 4, EquipmentLevel.PREMIUM);
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Car car7 = new Car("Honda", "CR-V", (short) 2019, "color", 12000.0, (short) 4, (short) 4, EquipmentLevel.BASIC);
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Car car8 = new Car("Hyundai", "Tucson", (short) 2019, "color", 12000.0, (short) 4, (short) 4, EquipmentLevel.SPORT);
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Car car9 = new Car("Nissan", "Rogue", (short) 2019, "color", 12000.0, (short) 4, (short) 4, EquipmentLevel.PREMIUM);
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Car car10 = new Car("Subaru", "Forester", (short) 2019, "color", 12000.0, (short) 4, (short) 4, EquipmentLevel.BASIC);
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Car car11 = new Car("Volkswagen", "Tiguan", (short) 2019, "color", 12000.0, (short) 4, (short) 4, EquipmentLevel.SPORT);
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Car car12 = new Car("Mitsubishi", "Outlander", (short) 2019, "color", 12000.0, (short) 4, (short) 4, EquipmentLevel.PREMIUM);
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Car car13 = new Car("Toyota", "Corolla", (short) 2019, "color", 12000.0, (short) 2, (short) 2, EquipmentLevel.BASIC);
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        Car car2 = new Car("Mazda", "CX-5", (short) 2019, (short) 5, (short) 5, EquipmentLevel.SPORT);
//        Car car3 = new Car("Audi", "A4", (short) 2019, (short) 2, (short) 2, EquipmentLevel.PREMIUM);
//        Car car4 = new Car("Kia", "Sorento", (short) 2019, (short) 5, (short) 5, EquipmentLevel.BASIC);
//        Car car5 = new Car("Toyota", "RAV4", (short) 2019, (short) 3, (short) 4, EquipmentLevel.SPORT);
//        Car car6 = new Car("Ford", "Escape", (short) 2019, (short) 5, (short) 5, EquipmentLevel.PREMIUM);
//        Car car7 = new Car("Honda", "CR-V", (short) 2019, (short) 5, (short) 5, EquipmentLevel.BASIC);
//        Car car8 = new Car("Hyundai", "Tucson", (short) 2019, (short) 5, (short) 5, EquipmentLevel.SPORT);
//        Car car9 = new Car("Nissan", "Rogue", (short) 2019, (short) 5, (short) 5, EquipmentLevel.PREMIUM);
//        Car car10 = new Car("Subaru", "Forester", (short) 2019, (short) 5, (short) 5, EquipmentLevel.BASIC);
//        Car car11 = new Car("Volkswagen", "Tiguan", (short) 2019, (short) 5, (short) 5, EquipmentLevel.SPORT);
//        Car car12 = new Car("Mitsubishi", "Outlander", (short) 2019, (short) 5, (short) 5, EquipmentLevel.PREMIUM);
//        Car car13 = new Car("Toyota", "Corolla", (short) 2019, (short) 5, (short) 5, EquipmentLevel.BASIC);

        SUV suv1 = new SUV("Porsche", "Yanaki", (short) 2000, "gray", 15000.0, false, 2);
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SUV suv2 = new SUV("BMW", "X5", (short) 2019, "color", 12000.0, true, 2);
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SUV suv3 = new SUV("Mazda", "CX-5", (short) 2019, "color", 12000.0, true, 4);
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Truck truck1 = new Truck("Tesla", "Cybertruck", (short) 2022, "orange", 10000.0, 100, 1000.0);
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Truck truck2 = new Truck("Mercedes", "Jordan", (short) 2019, "purple", 10000.0, 100, 1000.0);
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Truck truck3 = new Truck("Ford", "F-150", (short) 2019, "blue", 10000.0, 100, 1000.0);

        VehicleDealer dealer = new VehicleDealer();

        dealer.addVehicle(car1);
        dealer.addVehicle(car2);
        dealer.addVehicle(car3);
        dealer.addVehicle(car4);
        dealer.addVehicle(car5);
        dealer.addVehicle(car6);
        dealer.addVehicle(car7);
        dealer.addVehicle(car8);
        dealer.addVehicle(car9);
        dealer.addVehicle(car10);
        dealer.addVehicle(car11);
        dealer.addVehicle(car12);
        dealer.addVehicle(car13);

        dealer.addVehicle(suv1);
        dealer.addVehicle(suv2);
        dealer.addVehicle(suv3);

        dealer.addVehicle(truck1);
        dealer.addVehicle(truck2);
        dealer.addVehicle(truck3);

        System.out.println("All vehicles:");
        System.out.println(dealer.getAllVehiclesByIdByMaker());
    }
}