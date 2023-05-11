package franchise.Cook;

public enum Experience {
    JUNIOR, NORMAL, SENIOR;

    public Double getSpeedMultiplier() {
        return switch (this) {
            case JUNIOR -> 1.0;
            case NORMAL -> 0.5;
            case SENIOR -> 0.3;
        };
    }
}
