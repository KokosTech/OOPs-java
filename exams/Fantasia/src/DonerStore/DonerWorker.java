package DonerStore;

// Дюнерджия:
//Дюнерджията се грижи за приготвянето на дюнери. Той получава Map<String, Integer> със
// съставките и количеството им за даден дюнер. Месото е “meat”, всичко останало са зеленчуци.
// Дюнерджията приготвя дюнера, като извиква методите useMeat и useVegetable на дюнерджийницата.
// Ако няма достатъчно месо, изчаква докато се зареди месо и след това продължава с правенето на дюнера.
// Същото важи и за зеленчуците, като ако забележи, че даден зеленчук е останал по-малко от 10% от максималния капацитет,
// то тогава уведомява помощниците.

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DonerWorker implements Runnable {
    DonerStore store;
    Map<String, Integer> ingredients;

    public DonerWorker(DonerStore store) {
        this.store = store;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (this) {
                    wait();
                }

                makeDuner();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void orderDoner(Map<String, Integer> ingredients) {
        this.ingredients = ingredients;

        synchronized (this) {
            notify();
        }
    }

    public void makeDuner() {
        int meat = ingredients.get("meat");

        store.useMeat(meat);

        for (String vegetable: ingredients.keySet()) {
            if (!vegetable.equals("meat")) {
                store.useVegetable(vegetable, ingredients.get(vegetable));
            }
        }
    }


}
