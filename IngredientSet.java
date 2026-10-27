public class IngredientSet {
    private String name;
    private String description;
    private List<Ingredient> ingredients;
    private List<Ingredient> startBag;
    public IngredientSet(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public void addIngredient(Ingredient i) {
        
    }
    public void addStartChip(Ingredient i) {}
    public static List<IngredientSet> getAll() {}
    public String getName() {}
    public String getDescription() {}
    public List<Ingredient> getIngredients() {}
    public List<Ingredient> getStartBag() {}
    public String toString() {}
}