package franchise.Pizza;

import franchise.Pizza.Ingredient.Ingredient;
import franchise.Pizza.Ingredient.IngredientType;

import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class Pizza {
    private final Set<Ingredient<String, IngredientType>> ingredients;

    public Pizza(Set<Ingredient<String, IngredientType>> ingredients) {
        /*
            - Всяка пица е направена от тесто и има минимум 1 сос и 1 сирене
            - Пиците не могат да се правят от повече от едно тесто
            - Пиците могат да се правят с не повече от 2 соса
              Пиците могат да се правят с неограничено количество сирена, зеленчуци и месо
         */

        if(ingredients.stream().filter(ingredient -> ingredient.getType().equals(IngredientType.DOUGH)).count() != 1) {
            throw new IllegalArgumentException("Pizza must have exactly one dough");
        }

        if(ingredients.stream().filter(ingredient -> ingredient.getType().equals(IngredientType.SAUCE)).count() > 2) {
            throw new IllegalArgumentException("Pizza can be made with no more than two sauces");
        }

        if(ingredients.stream().noneMatch(ingredient -> ingredient.getType().equals(IngredientType.CHEESE))) {
            throw new IllegalArgumentException("Pizza must have at least one cheese");
        }

        this.ingredients = ingredients;
    }

    // Time Methods

    public Short getNumberOfIngredients() {
        return (short) ingredients.size();
    }

    public Double getTimeToCook() {
        AtomicReference<Double> time = new AtomicReference<>(0.0);

        /*
        Времето за изпичане на една пица се определя от времето за изпичане на типа на състваките в нея и количесвото. За всеки тип продукт, който е нужен повече от 1 път, времето за изпичане се увеличава с 10% от времето на изпичане на посочения продукт.

        Пример:

        - Пица Маргарите: бяло тесто, доматен сос и моцарела

                2сек (тесто) + 1сек (доматен сос) + 1сек (моцарела) = 4000мс

        - Пица по избор: черно тесто, барбекю сос, моцарела, чедър, царевица, гъби, телешко, бекон:

            2сек (тесто) + 1сек (барбекю сос) + 1сек (моцарела) 0.1*1сек (чедър) + 0.5сек (царевица) + 0.1*0.5сек (гъби) + 1.5сек (телешко) + 0.1*1.5сек (бекон) = 6300мс
         */

        Stream.of(IngredientType.values()).forEach(ingredientType -> {
            long count = ingredients.stream().filter(ingredient -> ingredient.getType() == ingredientType).count();
            if (count > 0) {
                time.updateAndGet(v -> v + ingredientType.getTimeToMake() * (1 + 0.1 * (count - 1)));
            }
        });

        return time.get();
    }

    // Getters and Setters

    public Set<Ingredient<String, IngredientType>> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "ingredients=" + ingredients +
                '}';
    }
}
