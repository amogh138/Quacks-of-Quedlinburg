import java.awt.image.BufferedImage;
public class Ingredient {
    private String name;
    private String color;
    private int value;
    private int cost;
    private int unlockRound;
    private String effect;
    private BufferedImage image;
    public Ingredient(String name, String color, int value, int cost, int unlockRound, String effect, BufferedImage image) {
        this.name = name;
        this.color = color;
        this.value = value;
        this.cost = cost;
        this.unlockRound = unlockRound;
        this.effect = effect;
        this.image = image;
    }
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
    public void setName(String name) {
        this.name = name;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }
    public void setUnlockRound(int unlockRound) {   
        this.unlockRound = unlockRound;
    }  
    public void setEffect(String effect) {
        this.effect = effect;
    }
    public void setImage(BufferedImage image) {
        this.image = image;
    }
    public BufferedImage getImage() {
        return image;
    }
    public String toString() {
        return (color+" "+value);
    }
}
