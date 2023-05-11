package DonerStore;

// Помощник:
//Помощниците зареждат зеленчуци, като първо извикват метода getVegetableThatNeedsLoading,
// след което извикват addVegetable за зеленчука върнат от getVegetableThatNeedsLoading. Ако няма зеленчук,
// който има нужда от зареждане, помощник започва да чака, докато не бъде нотифициран отново, че някой зеленчук
// има нужда от зареждане. Зареденото количество зеленчук е произволно между текущото количество и максималното

public class Helper implements Runnable {
    private DonerStore store;
    private String vegetable;

    public Helper(DonerStore store) {
        this.store = store;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (this) {
                    wait();
                }

                if (vegetable != null) {
                    int currentVegetable = store.getCurrentVegetables().get(vegetable);
                    int maxVegetable = store.getMaxVegetables().get(vegetable);
                    int vegetableToLoad = (int) (Math.random() * (maxVegetable - currentVegetable) + currentVegetable);
                    store.addVegetable(vegetable, vegetableToLoad);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void loadVegetable() {
        String vegatable = store.getVegetableThatNeedsLoading();

        synchronized (this) {
            notify();
        }
    }
}
