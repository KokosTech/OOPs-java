package utils.UI;

import franchise.Cook.Cook;
import franchise.Franchise;
import franchise.Order;
import franchise.Oven.Oven;
import franchise.Pizza.Ingredient.Ingredient;
import franchise.Pizza.Ingredient.IngredientType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ConsoleUI {
    private Franchise franchise;
    private Scanner scanner;

    public ConsoleUI(Franchise franchise) {
        this.franchise = franchise;
        this.scanner = new Scanner(System.in);
    }

    // Util method

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void menu() {
        clearScreen();
        System.out.println("Welcome to PizzaLab");
        System.out.println("Hire pizza man -> HirePizzaMan, <First name>, <Last name>, <year experience>");
        System.out.println("Add oven -> Oven, <capacity>");
        System.out.println("Add ingredient -> Ingredient, <Ingredient name>, <Ingredient type>");
        System.out.println("Add order -> Order, <Ingredients>");
        System.out.println("To stop / exit program -> END or Ctrl / Cmd + C\t :)");
    }

    private String[] tokenize(String line) {
        return line.split(", ");
    }

    // Option methods

    private String[] restTokens(String[] tokens) {
        return Arrays.stream(tokens).skip(1).toArray(String[]::new);
    }

    private void hirePizzaMan(String[] restTokens) {
        String firstName = restTokens[0];
        String lastName = restTokens[1];
        Integer experience = null;

        try {
            experience = Integer.parseInt(restTokens[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error: Invalid data for experience");
        }

        franchise.addCook(new Cook(firstName, lastName, experience, franchise));
    }

    private void oven(String[] restTokens) {
        Integer capacity = null;

        try {
            capacity = Integer.parseInt(restTokens[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error: Invalid data for capacity");
        }

        franchise.addOven(new Oven(capacity, franchise));
    }

    private void ingredient(String[] restTokens) {
        String name = restTokens[0];
        System.out.println(restTokens[1].toUpperCase());
        IngredientType ingredientType = IngredientType.valueOf(restTokens[1].toUpperCase());

        franchise.addIngredient(new Ingredient<>(name, ingredientType));

        System.out.println("Ingredient added");
    }

    private void order(String[] restTokens) {
        String[] ingredients = restTokens[0].split(" ");
        Set<Ingredient<String, IngredientType>> ingredientSet = new HashSet<>();

        Arrays.stream(ingredients).forEach(ingredient -> {
            franchise.getIngredients().stream().filter(ingredient1 -> ingredient1.getName().equals(ingredient)).forEach(ingredientSet::add);
        });

        franchise.addOrder(new Order(ingredientSet));
    }

    // Run method

    private void closeFranchise() {
        franchise.close();
    }

    // Public methods

    private void run(String line) {
        String[] tokens = tokenize(line);

        System.out.println(tokens[0]);

        switch (tokens[0]) {
            case "HirePizzaMan" -> hirePizzaMan(restTokens(tokens));
            case "Oven" -> oven(restTokens(tokens));
            case "Ingredient" -> ingredient(restTokens(tokens));
            case "Order" -> order(restTokens(tokens));
            case "END" -> closeFranchise();
            default -> throw new RuntimeException("Invalid command");
        }
    }

    public void start() {
        franchise.open();
        while (franchise.getOpen()) {
            menu();
            String commandLine = scanner.nextLine();
            try {
                run(commandLine);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
