package DonerStore;

// Шиша се грижи да продуцира месо. На произволен интервал от време между 1 и 5 секунди шиша продуцира 100 единици месо.
// Добавянето на месото към дюнерджийницата става чрез извикване на метода addMeat. Ако месото вече е на максимум,
// тогава шиша спира да произвежда, докато не бъде уведомен, че месо е било използвано.

public class Shish implements Runnable {
    DonerStore store;

    public Shish(DonerStore store) {
        this.store = store;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep((long) (Math.random() * 4000 + 1000));
                if (store.getCurrentMeat() < store.getMaxMeat()) {
                    store.addMeat(100);
                    // notify workers that meat is ready
                    synchronized (store.getCurrentMeat()) {
                        store.getCurrentMeat().notifyAll();
                    }
                } else {
                    synchronized (store) {
                        store.wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
