import DonerStore.DonerStore;
import DonerStore.Helper;
import DonerStore.DonerWorker;
import DonerStore.Shish;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Main {
    // Main:
    // В main направете четене от конзолата на поръчки за дюнер.
    // Поръчките са не един ред в следния формат:
    // {ingredient}:{amount};{ingredient}:{amount};{ingredient}:{amount}
    // Всяка поръчка се дава на дюнерджия да я приготви. В даден момент не могат да се приготвят повече от 2 поръчки.
    public static void main(String[] args) {
        DonerStore store = new DonerStore(1000, new ConcurrentHashMap<String, Integer>() {{
            put("tomato", 1000);
            put("cucumber", 1000);
            put("onion", 1000);
            put("lettuce", 1000);
            put("pepper", 1000);
            put("garlic", 1000);
        }}, null);

        Shish shish = new Shish(store);
        List<DonerWorker> donerWorkers = List.of(
                new DonerWorker(store),
                new DonerWorker(store),
                new DonerWorker(store)
        );
        List<Helper> helpers = List.of(
                new Helper(store),
                new Helper(store)
        );

        Thread shishThread = new Thread(shish);
        shishThread.start();

        List<Thread> donerWorkerThreads = new ArrayList<>();
        for (DonerWorker donerWorker : donerWorkers) {
            Thread donerWorkerThread = new Thread(donerWorker);
            donerWorkerThread.start();

            donerWorkerThreads.add(donerWorkerThread);
        }

        List<Thread> helperThreads = new ArrayList<>();
        for (Helper helper : helpers) {
            Thread helperThread = new Thread(helper);
            helperThread.start();

            helperThreads.add(helperThread);
        }

        // get user input in while from console

        // Поръчките са на един ред в следния формат:
        // {ingredient}:{amount};{ingredient}:{amount};{ingredient}:{amount}
        // Всяка поръчка се дава на дюнерджия да я приготви. В даден момент не могат да се приготвят повече от 2 поръчки.

        while (true) {
            String input = System.console().readLine();

            if(input.equals("exit")) {
                break;
            }

            String[] orders = input.split(";");
            for (String order : orders) {
                String[] ingredients = order.split(":");
                String ingredient = ingredients[0];
                int amount = Integer.parseInt(ingredients[1]);

                // NO FUCKING CLUE
//                for (DonerWorker donerWorker : donerWorkers) {
//                    if (!donerWorker.isBusy()) {
//                        donerWorker.orderDoner(new ConcurrentHashMap<String, Integer>() {{
//                            put(ingredient, amount);
//                        }});
//                        break;
//                    }
//                }
//
//                for (Helper helper : helpers) {
//                    if (!helper.isBusy()) {
//                        helper.loadVegetable();
//                        break;
//                    }
//                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


        try {
            shishThread.join();
            for (Thread donerWorkerThread : donerWorkerThreads) {
                donerWorkerThread.join();
            }

            for (Thread helperThread : helperThreads) {
                helperThread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}