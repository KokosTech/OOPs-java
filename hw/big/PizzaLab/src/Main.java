import franchise.Franchise;
import utils.UI.ConsoleUI;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Franchise PizzaLab = new Franchise();
        ConsoleUI ui = new ConsoleUI(PizzaLab);
        ui.start();
    }
}

/*
Примерен инпут:
HirePizzaMan, Tommy, Kelvin, 5
HirePizzaMan, Bay, Blagoy, 17
Oven, 2
Oven, 1
Ingredient, White, DOUGH
Ingredient, Cum, SAUCE
Ingredient, Crimean, CHEESE
Ingredient, Jewish, MEAT
Order, White Cum Crimean Jewish
 */