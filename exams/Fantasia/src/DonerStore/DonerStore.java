package DonerStore;

//Дюнерджийница:
//        maxMeat - максималното количество, което може да има в даден момент в дюнерджийницата. Цяло число.
//        currentMeat  - текущо количество месо. Цяло число.
//        Map<String, Integer> maxVegetables - ключ стойност, репрезентиращи наличните зеленчуци и тяхното максимално количество.
//        Map<String, Integer> currentVegetables - ключ стойност, репрезентиращи наличните зеленчуци и тяхното текущо количество.
//        Конструктор с 2 параметъра:
//        максимално количество месо
//        продуктите и тяхното максимално количество
//        При инициализиране, дюнерджийницата е заредена на максимум.
//Методи:
//        addMeat(int amount) - добавя налично количество месо. Ако месото е на максималната стойност, не добавя. Ако месото е под максималната стойност, добавя месо колкото е подадено или до максималния капацитет.
//        addVegetable(String vegetable, int amount) - като за месото, само че за зеленчук
//        getVegetableThatNeedsLoading() - връща един зеленчук, чието текущо количество е по-малко от 10% от максималното количество. Използвайте stream за да намерите зеленчука!
//        useMeat(int amount) - взима месо с посоченото количество.
//        useVegetable(String vegetable, int amount) - взима зеленчук с посоченото количество.


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DonerStore implements Runnable {
    private final Integer maxMeat;
    private Integer currentMeat = 0;
    private final ConcurrentHashMap<String, Integer> maxVegetables;
    private ConcurrentHashMap<String, Integer> currentVegetables;

    // orders and workers, helpers and shish
    private Shish shish;

    private Set<DonerWorker> workers;
    private Helper helper;
    private List<Map<String, Integer>> orders;
    private Set<Thread> threads;
    private List<DonerWorker> availableWorkers = new ArrayList<>();

    private void validateMeatMax(Integer maxMeat) {
        if (maxMeat < 0) {
            throw new IllegalArgumentException("Max meat cannot be negative");
        }
    }

    public DonerStore(int maxMeat, ConcurrentHashMap<String, Integer> maxVegetables, List<Map<String, Integer>> orders) {
        this.maxMeat = maxMeat;
        this.currentMeat = maxMeat;

        this.maxVegetables = maxVegetables;
        this.currentVegetables = new ConcurrentHashMap<>();
        for (String vegetable: maxVegetables.keySet()) {
            currentVegetables.put(vegetable, maxVegetables.get(vegetable));
        }

        this.orders = orders;
    }

    @Override
    public void run() {
        for (int i = 0; i < 2; ++i) {
            DonerWorker worker = new DonerWorker(this);
            Thread thread = new Thread(worker);
            thread.start();
            threads.add(thread);
            availableWorkers.add(worker);
        }

        helper = new Helper(this);
        Thread thread1 = new Thread(helper);
        thread1.start();
        threads.add(thread1);

        shish = new Shish(this);
        Thread thread2 = new Thread(shish);
        thread2.start();
        threads.add(thread2);

        while (orders.size() > 0) {
            if (!availableWorkers.isEmpty()) {
                DonerWorker worker = availableWorkers.get(0);
                availableWorkers.remove(worker);
                worker.orderDoner(orders.get(0));
                orders.remove(0);
            }
        }

        stop();
    }

    public void stop() {
        for (Thread thread: threads) {
            thread.interrupt();
        }
    }


    public synchronized void addMeat(Integer meat) {
        if (currentMeat + meat <= maxMeat) {
            currentMeat += meat;
        } else {
//            throw new IllegalArgumentException("Not enough space in the store!");
            currentMeat = maxMeat;
        }
    }

    public synchronized void addVegetable(String vegetable, int quantity) {
        if (!maxVegetables.containsKey(vegetable)) {
            throw new IllegalArgumentException("No such vegetable in the store!");
        }
        if (currentVegetables.get(vegetable) + quantity <= maxVegetables.get(vegetable)) {
            currentVegetables.put(vegetable, currentVegetables.get(vegetable) + quantity);
        } else {
            throw new IllegalArgumentException("Not enough space in the store!");
        }
    }

    public String getVegetableThatNeedsLoading() {
        return currentVegetables.entrySet().stream()
                .filter(vegetable -> vegetable.getValue() < maxVegetables.get(vegetable.getKey()) * 0.1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    public void useMeat(Integer amount) {
        // if not enough meat, wait for more meat
        synchronized (currentMeat) {
            if (currentMeat - amount < 0) {
                try {
                    currentMeat.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            currentMeat -= amount;
        }
    }

    public void useVegetable(String vegetable, Integer amount) {
        currentVegetables.put(vegetable, currentVegetables.get(vegetable) - amount);
    }

    public Integer getMaxMeat() {
        return maxMeat;
    }

    public Integer getCurrentMeat() {
        return currentMeat;
    }

    public Map<String, Integer> getMaxVegetables() {
        return maxVegetables;
    }

    public Map<String, Integer> getCurrentVegetables() {
        return currentVegetables;
    }

    public void setCurrentMeat(int currentMeat) {
        this.currentMeat = currentMeat;
    }

    public void setCurrentVegetables(ConcurrentHashMap<String, Integer> currentVegetables) {
        this.currentVegetables = currentVegetables;
    }

    // Всяка поръчка се дава на дюнерджия да я приготви. В даден момент не могат да се приготвят повече от 2 поръчки

}
