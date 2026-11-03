public class IngredientSet {
    private String name;
    private String description;
    private List<Ingredient> ingredients;
    private List<Ingredient> startBag;
    public IngredientSet(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public void addIngredient(Ingredient i) {}
    public void addStartChip(Ingredient i) {}
    public static List<IngredientSet> getAll() {}
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public List<Ingredient> getIngredients() {
        return ingredients;
    }
    public List<Ingredient> getStartBag() {
        return startBag;
    }
    public String toString() {}
}