package franchise;

import franchise.Oven.Oven;
import franchise.Pizza.Ingredient.Ingredient;
import franchise.Pizza.Ingredient.IngredientType;
import franchise.Pizza.Pizza;
import utils.Logger.ThreadLogger;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Order implements Runnable {
    private final Integer id;
    private static AtomicInteger idCount = new AtomicInteger(0);
    private final Pizza pizza;
    private Oven oven;

    public Order(Set<Ingredient<String, IngredientType>> ingredients) {
        this.id = idCount.getAndIncrement();
        this.pizza = new Pizza(ingredients);
    }


    // Runnable
    @Override
    public void run() {
        try {
            ThreadLogger logger = new ThreadLogger("logs/order " + Thread.currentThread().getName() + ".log");
            logger.log(this + " is being cooked " + pizza.getTimeToCook() + " seconds in " + oven);
            Thread.sleep((long) (pizza.getTimeToCook() * 1000));
            oven.finishOrder(this);
            logger.log(this + " is done cooking");
        } catch (InterruptedException e) {
            System.out.println("Pizza was interrupted");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public static AtomicInteger getIdCount() {
        return idCount;
    }

    public static void setIdCount(AtomicInteger idCount) {
        Order.idCount = idCount;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public Oven getOven() {
        return oven;
    }

    public void setOven(Oven oven) {
        this.oven = oven;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", pizza=" + pizza +
                ", oven=" + oven +
                '}';
    }
}
