public class Ingredient {
    private String name;
    private String color;
    private int value;
    private int cost;
    private int unlockRound;
    private String effect;
    public Ingredient(String name, String color, int value, int cost, int unlockRound, String effect) {}
    public boolean isCherryBomb() {
        if(color.equals("white")) 
            return true;
        return false;
    }
    public String getName() {
        return name;
    }
    public String getColor() {
        return color;
    }
    public int getValue() {
        return value;
    }
    public int getCost() {
        return cost;
    }
    public int getUnlockRound() {
        return unlockRound;
    }
    public String getEffect() {
        return effect;
    }
    public String toString() {
        return (color+" "+value);
    }
}