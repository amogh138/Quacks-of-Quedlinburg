import java.util.*;

public class IngredientSet {
    private String name;
    private String description;
    private List<Ingredient> ingredients;
    private List<Ingredient> startBag;

    public IngredientSet(String name, String description) {
        this.name = name;
        this.description = description;
        this.ingredients = new ArrayList<>();
        this.startBag = new ArrayList<>();
    }

    public void addIngredient(Ingredient i) {
        ingredients.add(i);
    }

    public void addStartChip(Ingredient i) {
        startBag.add(i);
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public List<Ingredient> getIngredients() { return ingredients; }
    public List<Ingredient> getStartBag() { return startBag; }

    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setIngredients(List<Ingredient> ingredients) { this.ingredients = ingredients; }
    public void setStartBag(List<Ingredient> startBag) { this.startBag = startBag; }

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
        return "The set is " + name;
    }

    public static IngredientSet getDefaultSet() {
        IngredientSet set = new IngredientSet("Default Set", "Official default ingredient set");

        set.addIngredient(new Ingredient("Blue 1-chip", "blue", 1, 5, 1, "Bomb reduction", null));
        set.addIngredient(new Ingredient("Blue 2-chip", "blue", 2, 10, 1, "Bomb reduction", null));
        set.addIngredient(new Ingredient("Blue 4-chip", "blue", 4, 19, 1, "Bomb reduction", null));

        set.addIngredient(new Ingredient("Red 1-chip", "red", 1, 6, 1, "Movement bonus", null));
        set.addIngredient(new Ingredient("Red 2-chip", "red", 2, 10, 1, "Movement bonus", null));
        set.addIngredient(new Ingredient("Red 4-chip", "red", 4, 16, 1, "Movement bonus", null));

        set.addIngredient(new Ingredient("Yellow 1-chip", "yellow", 1, 8, 1, "Bomb threshold", null));
        set.addIngredient(new Ingredient("Yellow 2-chip", "yellow", 2, 12, 1, "Bomb threshold", null));
        set.addIngredient(new Ingredient("Yellow 4-chip", "yellow", 4, 18, 1, "Bomb threshold", null));

        set.addIngredient(new Ingredient("Green 1-chip", "green", 1, 4, 1, "Ruby bonus", null));
        set.addIngredient(new Ingredient("Green 2-chip", "green", 2, 8, 1, "Ruby bonus", null));
        set.addIngredient(new Ingredient("Green 4-chip", "green", 4, 14, 1, "Ruby bonus", null));

        set.addIngredient(new Ingredient("Purple 1-chip", "purple", 1, 9, 1, "Victory points", null));
        set.addIngredient(new Ingredient("Purple 2-chip", "purple", 2, 12, 1, "Victory points", null));

        for (int i = 0; i < 3; i++) {
            set.addStartChip(new Ingredient("White 1-chip", "white", 1, 0, 1, "Cherry bomb", null));
        }

        return set;
    }

    public static IngredientSet getSet1() {
        IngredientSet set = new IngredientSet("Set 1", "Standard game rules");

        set.addIngredient(new Ingredient("Blue 1-chip", "blue", 1, 5, 1, "Draw from bag", null));
        set.addIngredient(new Ingredient("Blue 2-chip", "blue", 2, 10, 1, "Draw 2 chips", null));
        set.addIngredient(new Ingredient("Blue 4-chip", "blue", 4, 19, 1, "Draw 4 chips", null));

        set.addIngredient(new Ingredient("Red 1-chip", "red", 1, 6, 1, "Move based on value", null));
        set.addIngredient(new Ingredient("Red 2-chip", "red", 2, 10, 1, "Move based on value", null));
        set.addIngredient(new Ingredient("Red 4-chip", "red", 4, 16, 1, "Move based on value", null));

        set.addIngredient(new Ingredient("Yellow 1-chip", "yellow", 1, 8, 1, "White chip removal", null));
        set.addIngredient(new Ingredient("Yellow 2-chip", "yellow", 2, 12, 1, "White chip removal", null));
        set.addIngredient(new Ingredient("Yellow 4-chip", "yellow", 4, 18, 1, "White chip removal", null));

        set.addIngredient(new Ingredient("Green 1-chip", "green", 1, 4, 1, "Ruby bonus", null));
        set.addIngredient(new Ingredient("Green 2-chip", "green", 2, 8, 1, "Ruby bonus", null));
        set.addIngredient(new Ingredient("Green 4-chip", "green", 4, 14, 1, "Ruby bonus", null));

        set.addIngredient(new Ingredient("Purple 1-chip", "purple", 1, 9, 1, "1 VP if 1 chip", null));
        set.addIngredient(new Ingredient("Purple 2-chip", "purple", 2, 12, 1, "1 VP + 1 ruby if 2 chips", null));

        for (int i = 0; i < 3; i++) {
            set.addStartChip(new Ingredient("White 1-chip", "white", 1, 0, 1, "Cherry bomb", null));
        }

        return set;
    }

    public static IngredientSet getSet2() {
        IngredientSet set = new IngredientSet("Set 2", "Intermediate difficulty");

        set.addIngredient(new Ingredient("Blue 1-chip", "blue", 1, 5, 1, "Extended bomb protection", null));
        set.addIngredient(new Ingredient("Blue 2-chip", "blue", 2, 10, 1, "Extended bomb protection", null));
        set.addIngredient(new Ingredient("Blue 4-chip", "blue", 4, 14, 1, "Extended bomb protection", null));

        set.addIngredient(new Ingredient("Red 1-chip", "red", 1, 4, 1, "Special placement", null));
        set.addIngredient(new Ingredient("Red 2-chip", "red", 2, 8, 1, "Special placement", null));
        set.addIngredient(new Ingredient("Red 4-chip", "red", 4, 14, 1, "Special placement", null));

        set.addIngredient(new Ingredient("Yellow 1-chip", "yellow", 1, 9, 1, "Double movement", null));
        set.addIngredient(new Ingredient("Yellow 2-chip", "yellow", 2, 13, 1, "Double movement", null));
        set.addIngredient(new Ingredient("Yellow 4-chip", "yellow", 4, 19, 1, "Double movement", null));

        set.addIngredient(new Ingredient("Green 1-chip", "green", 1, 6, 1, "Get chip bonus", null));
        set.addIngredient(new Ingredient("Green 2-chip", "green", 2, 11, 1, "Get chip bonus", null));
        set.addIngredient(new Ingredient("Green 4-chip", "green", 4, 18, 1, "Get chip bonus", null));

        set.addIngredient(new Ingredient("Purple 1-chip", "purple", 1, 12, 1, "Chip exchange", null));

        for (int i = 0; i < 3; i++) {
            set.addStartChip(new Ingredient("White 1-chip", "white", 1, 0, 1, "Cherry bomb", null));
        }

        return set;
    }

    public static IngredientSet getSet3() {
        IngredientSet set = new IngredientSet("Set 3", "Advanced strategies");

        set.addIngredient(new Ingredient("Blue 1-chip", "blue", 1, 4, 1, "Ruby on ruby space", null));
        set.addIngredient(new Ingredient("Blue 2-chip", "blue", 2, 8, 1, "Ruby on ruby space", null));
        set.addIngredient(new Ingredient("Blue 4-chip", "blue", 4, 14, 1, "Ruby on ruby space", null));

        set.addIngredient(new Ingredient("Red 1-chip", "red", 1, 5, 1, "White chip value addition", null));
        set.addIngredient(new Ingredient("Red 2-chip", "red", 2, 9, 1, "White chip value addition", null));
        set.addIngredient(new Ingredient("Red 4-chip", "red", 4, 15, 1, "White chip value addition", null));

        set.addIngredient(new Ingredient("Yellow 1-chip", "yellow", 1, 8, 1, "Progressive movement", null));
        set.addIngredient(new Ingredient("Yellow 2-chip", "yellow", 2, 12, 1, "Progressive movement", null));
        set.addIngredient(new Ingredient("Yellow 4-chip", "yellow", 4, 18, 1, "Progressive movement", null));

        set.addIngredient(new Ingredient("Green 1-chip", "green", 1, 6, 1, "White chip check", null));
        set.addIngredient(new Ingredient("Green 2-chip", "green", 2, 11, 1, "White chip check", null));
        set.addIngredient(new Ingredient("Green 4-chip", "green", 4, 18, 1, "White chip check", null));

        set.addIngredient(new Ingredient("Purple 1-chip", "purple", 1, 10, 1, "Position-based VP", null));

        for (int i = 0; i < 3; i++) {
            set.addStartChip(new Ingredient("White 1-chip", "white", 1, 0, 1, "Cherry bomb", null));
        }

        return set;
    }

    public static IngredientSet getSet4() {
        IngredientSet set = new IngredientSet("Set 4", "Expert level");

        set.addIngredient(new Ingredient("Blue 1-chip", "blue", 1, 5, 1, "VP on ruby space", null));
        set.addIngredient(new Ingredient("Blue 2-chip", "blue", 2, 10, 1, "2 VP on ruby space", null));
        set.addIngredient(new Ingredient("Blue 4-chip", "blue", 4, 20, 1, "4 VP on ruby space", null));

        set.addIngredient(new Ingredient("Red 1-chip", "red", 1, 7, 1, "White chip movement", null));
        set.addIngredient(new Ingredient("Red 2-chip", "red", 2, 11, 1, "White chip movement", null));
        set.addIngredient(new Ingredient("Red 4-chip", "red", 4, 17, 1, "White chip movement", null));

        set.addIngredient(new Ingredient("Yellow 1-chip", "yellow", 1, 8, 1, "1 extra space", null));
        set.addIngredient(new Ingredient("Yellow 2-chip", "yellow", 2, 12, 1, "2 extra spaces", null));
        set.addIngredient(new Ingredient("Yellow 4-chip", "yellow", 4, 18, 1, "3 extra spaces", null));

        set.addIngredient(new Ingredient("Green 1-chip", "green", 1, 4, 1, "Ruby for movement", null));
        set.addIngredient(new Ingredient("Green 2-chip", "green", 2, 8, 1, "Ruby for movement", null));
        set.addIngredient(new Ingredient("Green 4-chip", "green", 4, 14, 1, "Ruby for movement", null));

        set.addIngredient(new Ingredient("Purple 1-chip", "purple", 1, 11, 1, "Chip exchange", null));

        for (int i = 0; i < 3; i++) {
            set.addStartChip(new Ingredient("White 1-chip", "white", 1, 0, 1, "Cherry bomb", null));
        }

        return set;
    }
}