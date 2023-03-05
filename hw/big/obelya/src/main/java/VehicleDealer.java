import Vehicle.Type.CarEquipment.EquipmentLevel;
import Vehicle.Vehicle;
import Vehicle.Type.Car;
import Vehicle.Type.SUV;
import Vehicle.Type.Truck;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class VehicleDealer {
    private final Set<Car> cars;
    private final Set<SUV> suvs;
    private final Set<Truck> trucks;

    private Double turnover;

    public VehicleDealer() {
        cars = new HashSet<>();
        suvs = new HashSet<>();
        trucks = new HashSet<>();

        turnover = 0.0;
    }

    // omg, here we use generics in java :0 :0 :0
    // and even the stream api, instead of repetitive loops and ifs
    // its getting to the JavaScript level of stupidity
    private static <T extends Vehicle> void checkIfExists(Set<T> setOfVehicles, T vehicle) {
        if (setOfVehicles.contains(vehicle)
                || setOfVehicles
                .stream()
                .anyMatch(item -> item.getId().equals(vehicle.getId())))
            throw new IllegalArgumentException("Vehicle with such ID already exists. Your vehicle must be unique, your right now is: " + vehicle);
    }

    public void addVehicle(Vehicle vehicle) {
        if (vehicle instanceof Car) {
            checkIfExists(cars, (Car) vehicle);
            cars.add((Car) vehicle);
        } else if (vehicle instanceof SUV) {
            checkIfExists(suvs, (SUV) vehicle);
            suvs.add((SUV) vehicle);
        } else if (vehicle instanceof Truck) {
            checkIfExists(trucks, (Truck) vehicle);
            trucks.add((Truck) vehicle);
        }
    }

    public Map<String, Map<Long, Vehicle>> getAllVehiclesByIdByMaker() {
        return Stream
                .of(cars, trucks, suvs)
                .flatMap(Set::stream)
                .collect(Collectors.groupingBy(
                        Vehicle::getMaker,
                        Collectors.toMap(
                                Vehicle::getId,
                                vehicle -> vehicle
                        )
                )); // every js developer will understand this, gundi ;)
    }

    Set<SUV> getAllSUVWith4by4() {
        return suvs
                .stream()
                .filter(SUV::getHas4by4) // checks if the SUV has 4by4 ✨ (omg, i can use emojis in comments)
                .collect(Collectors.toSet());
    }

    Set<Car> getAllCarsWithEquipmentLevel(EquipmentLevel equipmentLevel) {
        return cars
                .stream()
                .filter(car -> car.getEquipmentLevel().equals(equipmentLevel))
                .collect(Collectors.toSet());
    }

    public Vehicle getCheapestVehicle(List<Vehicle> vehicles) {
        return vehicles
                .stream()
                .min(Comparator.comparing(Vehicle::getPrice))
                .orElseThrow(NoSuchElementException::new);
    }

    long getTotalNumberOfModels(String maker) {
        return Stream
                .of(cars, suvs, trucks)
                .flatMap(Set::stream)
                .filter(vehicle -> vehicle.getMaker().equals(maker))
                .count();
    }


    private void removeVehicle(Vehicle vehicle) {
        if (vehicle == null)
            throw new IllegalArgumentException("Vehicle is empty!");

        if (vehicle instanceof Car) {
            cars.remove((Car) vehicle);
        } else if (vehicle instanceof SUV) {
            suvs.remove((SUV) vehicle);
        } else if (vehicle instanceof Truck) {
            trucks.remove((Truck) vehicle);
        }
    }

    void sellVehicle(Long vehicleId) {
        Vehicle vehicleForSale = Stream
                .of(cars, suvs, trucks)
                .flatMap(Set::stream)
                .filter(vehicle -> vehicle.getId().equals(vehicleId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No vehicle with such id: " + vehicleId + "!"));


        if (vehicleForSale == null || vehicleForSale.getId() == null)
            throw new IllegalArgumentException("No vehicle with such id: " + vehicleId + "!");

        removeVehicle(vehicleForSale);

        turnover += vehicleForSale.getPrice();
    }

    public Set<Car> getCars() {
        return Collections.unmodifiableSet(cars);
    }

    public Set<SUV> getSuvs() {
        return Collections.unmodifiableSet(suvs);
    }

    public Set<Truck> getTrucks() {
        return Collections.unmodifiableSet(trucks);
    }

    public double getTurnover() {
        return turnover;
    }

    @Override
    public String toString() {
        return "VehicleDealer [cars=" + cars + ", suvs=" + suvs + ", trucks=" + trucks + ", turnover=" + turnover + "]";
    }
}