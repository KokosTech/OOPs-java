package franchise.Pizza.Ingredient;

public class Ingredient<String, IngredientType> {
    private final String name;
    private final IngredientType type;

    public Ingredient(String name, IngredientType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public IngredientType getType() {
        return type;
    }
}