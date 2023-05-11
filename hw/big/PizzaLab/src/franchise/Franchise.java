package franchise;

import franchise.Cook.Cook;
import franchise.Oven.Oven;
import franchise.Pizza.Ingredient.Ingredient;
import franchise.Pizza.Ingredient.IngredientType;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Franchise {
    Set<Ingredient<String, IngredientType>> ingredients;
    Set<Order> orders;
    Queue<Order> ordersReadyToCook;
    Set<Oven> ovens;
    Set<Thread> threads;
    Set<Cook> cooks;
    Boolean isOpen = false;
//    private final logger = new Utils.Logger("franchise.log")


    // Constructor

    public Franchise() {
        this.ingredients = new HashSet<>();
        this.orders = new HashSet<>();
        this.ordersReadyToCook = new ArrayDeque<>();
        this.ovens = new HashSet<>();
        this.cooks = new HashSet<>();
        this.threads = new HashSet<>();
    }

    // Adder methods

    public void addIngredient(Ingredient<String, IngredientType> ingredient) {

        ingredients.stream()
                .filter(p -> p.getName().equals(ingredient.getName()))
                .findFirst()
                .ifPresent(p -> {
                    throw new IllegalArgumentException("Ingredient already exists " + ingredient.getName());
                });

        ingredients.add(ingredient);
    }

    public void addCook(Cook cook) {
        cooks.add(cook);

        Thread thread = new Thread(cook);
        thread.start();
        threads.add(thread);
    }

    public void addOven(Oven oven) {
        ovens.add(oven);

        Thread thread = new Thread(oven);
        thread.start();
        threads.add(thread);
    }

    // Order Methods

    public void addOrder(Order order) {
        synchronized (orders) {
            orders.add(order);
            orders.notifyAll();
        }
    }

    public Order getNextOrderReadyToMake() {
        synchronized (orders) {
            while (orders.isEmpty() && isOpen) {
                try {
                    orders.wait();
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted");
                }
            }

            if (!isOpen)
                return null;

            Order order = orders.iterator().next();
            orders.remove(order);

            return order;
        }
    }

    public void addOrderReadyToBake(Order order) {
        synchronized (ordersReadyToCook) {
            ordersReadyToCook.add(order);
            ordersReadyToCook.notifyAll();
        }
    }

    public Order getNextOrderReadyToCook() throws InterruptedException {
        synchronized (ordersReadyToCook) {
            while (ordersReadyToCook.isEmpty() && isOpen) {
                try {
                    ordersReadyToCook.wait();
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted");
                }
            }

            if (!isOpen)
                return null;

            return ordersReadyToCook.poll();
        }
    }

    public void open() {
        isOpen = true;
    }

    public void close() {
        isOpen = false;

        for (Thread thread : threads) {
            thread.interrupt();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        threads.clear();
    }

    public Set<Ingredient<String, IngredientType>> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient<String, IngredientType>> ingredients) {
        this.ingredients = ingredients;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Queue<Order> getOrdersReadyToCook() {
        return ordersReadyToCook;
    }

    public void setOrdersReadyToCook(Queue<Order> ordersReadyToCook) {
        this.ordersReadyToCook = ordersReadyToCook;
    }

    public Set<Oven> getOvens() {
        return ovens;
    }

    public void setOvens(Set<Oven> ovens) {
        this.ovens = ovens;
    }

    public Set<Thread> getThreads() {
        return threads;
    }

    public void setThreads(Set<Thread> threads) {
        this.threads = threads;
    }

    public Set<Cook> getCooks() {
        return cooks;
    }

    public void setCooks(Set<Cook> cooks) {
        this.cooks = cooks;
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }
}
