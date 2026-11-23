import java.util.*;
public class IngredientSet {
    private String name;
    private String description;
    private List<Ingredient> ingredients;
    private List<Ingredient> startBag;scription) {
        this.name = name;
        this.description = description;
        this.ingredients = new ArrayList<>();
        this.startBag = new ArrayList<>();
    public IngredientSet(String name, String de
    }
    public void addIngredient(Ingredient i) {
        ingredients.add(i);
    }
    public void addStartChip(Ingredient i) {
        startBag.add(i);
    }
    public static List<IngredientSet> getAll() {
        List<IngredientSet> allSets = new ArrayList<>();
        IngredientSet set1 = new IngredientSet("Default Set", "This is the default set of ingredients.");
        IngredientSet set2 = new IngredientSet("Default Set", "This is the default set of ingredients.");
        IngredientSet set3 = new IngredientSet("Default Set", "This is the default set of ingredients.");
        IngredientSet set4 = new IngredientSet("Default Set", "This is the default set of ingredients.");
        allSets.add(set1);
        allSets.add(set2);
        allSets.add(set3);
        allSets.add(set4);
        return allSets;
    }
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
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
    public void setStartBag(List<Ingredient> startBag) {
        this.startBag = startBag;
    }
    public void removeIngredient(Ingredient i) {
        ingredients.remove(i);
    }
    public void removeStartChip(Ingredient i) {
        startBag.remove(i);
    }
    public void clearIngredients() {
        ingredients.clear();
    }
    public void clearStartBag() {
        startBag.clear();
    }
    public boolean containsIngredient(Ingredient i) {
        return ingredients.contains(i);
    }
    public boolean containsStartChip(Ingredient i) {
        return startBag.contains(i);
    }
    public int getIngredientCount() {
        return ingredients.size();
    }
    public int getStartChipCount() {
        return startBag.size();
    }
    public Ingredient getIngredient(int index) {
        return ingredients.get(index);
    }
    public Ingredient getStartChip(int index) {
        return startBag.get(index);
    }
    public void setIngredient(int index, Ingredient i) {
        ingredients.set(index, i);
    }
    public void setStartChip(int index, Ingredient i) {
        startBag.set(index, i);
    }
    public void addIngredient(int index, Ingredient i) {
        ingredients.add(index, i);
    }
    public void addStartChip(int index, Ingredient i) {
        startBag.add(index, i);
    }
    public String toString() {
        return("The set is "+name);
    }
}