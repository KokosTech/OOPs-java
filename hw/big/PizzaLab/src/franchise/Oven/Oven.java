package franchise.Oven;

import franchise.Franchise;
import franchise.Order;
import franchise.Pizza.Pizza;
import utils.Logger.ThreadLogger;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Oven implements Runnable {
    private static Long idCount = 0L;
    private Long id;
    private final Integer capacity;
    private Integer currentSize;

    Franchise franchise;
    ExecutorService executor;

    public Oven(Integer capacity, Franchise franchise) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Invalid capacity is provided");
        }

        this.id = idCount++;
        this.capacity = capacity;
        this.currentSize = 0;

        this.franchise = franchise;
        this.executor = Executors.newFixedThreadPool(capacity);
    }

    @Override
    public void run() {
        try {
            ThreadLogger logger = new ThreadLogger("logs/oven" + Thread.currentThread().getName() + ".log");
            logger.log("Oven " + this + " is ready to bake");

            while (franchise.getOpen()) {
                if (currentSize < capacity) {
                    Order nextOrder = franchise.getNextOrderReadyToCook();

                    if (nextOrder == null)
                        break;

                    nextOrder.setOven(this);

                    logger.log(this + " is baking " + nextOrder);

                    currentSize++;

                    executor.execute(nextOrder);
                } else {
                    synchronized (this) {
                        logger.log(this + " is full");
                        wait();
                    }
                }
            }

            executor.shutdown();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }

    public void finishOrder(Order order) {
        synchronized (this) {
            currentSize--;
            notify();
        }
    }

    public Long getId() {
        return id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "Oven{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", currentSize=" + currentSize +
                '}';
    }
}
