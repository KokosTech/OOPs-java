package franchise.Pizza.Ingredient;

public enum IngredientType {
    DOUGH, SAUCE, VEGGIES, CHEESE, MEAT;

    public Double getTimeToMake() {
        return switch (this) {
            case DOUGH -> 2.0;
            case SAUCE, CHEESE -> 1.0;
            case VEGGIES -> 0.5;
            case MEAT -> 1.5;
        };
    }
}
