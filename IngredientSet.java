import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class IngredientSet {
    public String name;
    public String description;
    public List<Ingredient> ingredients;
    public List<Ingredient> startBag;
    public int setNumber;
    public int round;
    public Map<String, Ingredient> ingredientMap;
    public List<Ingredient> boughtThisTurn;
    public boolean isActive;
    public List<Ingredient> bonusChips;
    public int bonusCount;
    public int cost1;
    public int cost2;
    public int cost4;
    public String bonusEffect;
    
    public IngredientSet(String name, String description) {
        this.name = name;
        this.description = description;
        this.ingredients = new ArrayList<>();
        this.startBag = new ArrayList<>();
        this.setNumber = 1;
        this.round = 1;
        this.ingredientMap = new HashMap<>();
        this.boughtThisTurn = new ArrayList<>();
        this.isActive = false;
        this.bonusChips = new ArrayList<>();
        this.bonusCount = 0;
        this.cost1 = 0;
        this.cost2 = 0;
        this.cost4 = 0;
        this.bonusEffect = "";
    }
    
    public void addIngredient(Ingredient i) { 
        ingredients.add(i);
        ingredientMap.put(i.name, i);
    }
    
    public void addStartChip(Ingredient i) { 
        startBag.add(i);
    }
    
    public void setActive(boolean active) { 
        isActive = active;
    }
    
    public void setNumber(int setNumber) {
        this.setNumber = setNumber;
    }
    
    public int getRound() {
        return round;
    }
    
    public void buy(Ingredient ingredient, Player player) {
        if (ingredient != null && player.getCoins() >= ingredient.getCost()) {
            player.spendCoins(ingredient.getCost());
            player.addToBag(ingredient);
            boughtThisTurn.add(ingredient);
        }
    }
    
    public int getCost(int value) {
        if (value == 1) return cost1;
        if (value == 2) return cost2;
        if (value == 4) return cost4;
        return 0;
    }
    
    public Ingredient getIngredient(String name) {
        return ingredientMap.get(name);
    }
    
    public List<Ingredient> getIngredients() {
        return ingredients;
    }
    
    public List<Ingredient> getStartBag() {
        return startBag;
    }
    
    public int getCosts() {
        int total = 0;
        for (Ingredient i : ingredients) {
            total += i.getCost();
        }
        return total;
    }
    
    public void setCosts(int c1, int c2, int c4, String bonus) {
        cost1 = c1;
        cost2 = c2;
        cost4 = c4;
        bonusEffect = bonus;
    }
    
    public void incrementSetNumber() {
        setNumber++;
    }
    
    public void setRound(int round) {
        this.round = round;
    }
    
    public static IngredientSet getDefaultSet() {
        IngredientSet set = new IngredientSet("Base Game Set", "Official base game ingredient set");
        
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
        
        set.addIngredient(new Ingredient("Orange 1-chip", "orange", 1, 6, 1, "Points/Coins", null));
        set.addIngredient(new Ingredient("Orange 2-chip", "orange", 2, 10, 1, "Points/Coins", null));
        set.addIngredient(new Ingredient("Orange 4-chip", "orange", 4, 16, 1, "Points/Coins", null));
        
        for (int i = 0; i < 4; i++) {
            set.addStartChip(new Ingredient("White 1-chip", "white", 1, 0, 1, "Cherry bomb", null));
        }
        set.addStartChip(new Ingredient("White 2-chip", "white", 2, 0, 1, "Cherry bomb", null));
        set.addStartChip(new Ingredient("White 3-chip", "white", 3, 0, 1, "Cherry bomb", null));
        set.addStartChip(new Ingredient("Orange 1-chip", "orange", 1, 0, 1, "Pumpkin", null));
        set.addStartChip(new Ingredient("Green 1-chip", "green", 1, 0, 1, "Garden Spider", null));
        
        set.setCosts(1, 2, 4, "");
        return set;
    }
    
    public void applyBonusEffect(Player player) {
        if (bonusEffect.equals("Bomb reduction")) {
            for (Ingredient i : player.getBag()) {
                if (i.isBlue()) {
                    if (i.getValue() == 1 && player.getCherryBombTotal() < 10) {
                        player.lowerBombs(1);
                    }
                }
            }
        } else if (bonusEffect.equals("Movement bonus")) {
            player.applyMovementBonus();
        } else if (bonusEffect.equals("Bomb threshold")) {
            player.LIMIT = 9;
        } else if (bonusEffect.equals("Ruby bonus")) {
            player.collectRuby();
        } else if (bonusEffect.equals("Victory points")) {
            player.addPoints(1);
        }
    }
}