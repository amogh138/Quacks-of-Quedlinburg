import java.awt.image.*;
import java.util.*;

public class Ingredient {
    private String name;
    private String color;
    private int value;
    private int cost;
    private int unlockRound;
    private String effect;
    private BufferedImage image;

    public Ingredient(String name, String color, int value, int cost,
                     int unlockRound, String effect, BufferedImage image) {
        this.name = name;
        this.color = color;
        this.value = value;
        this.cost = cost;
        this.unlockRound = unlockRound;
        this.effect = effect;
        this.image = image;
    }

    public boolean isCherryBomb() {
        return "white".equals(color);
    }

    public boolean isOrange() {
        return "orange".equals(color) || "pumpkin".equals(color);
    }

    public boolean isGreen() {
        return "green".equals(color) || "spider".equals(color);
    }

    public boolean isBlue() {
        return "blue".equals(color) || "crow".equals(color);
    }

    public boolean isRed() {
        return "red".equals(color) || "toadstool".equals(color);
    }

    public boolean isYellow() {
        return "yellow".equals(color) || "mandrake".equals(color);
    }

    public boolean isPurple() {
        return "purple".equals(color) || "ghost".equals(color);
    }

    public boolean isBlack() {
        return "black".equals(color) || "hawkmoth".equals(color);
    }

    public String getName() { return name; }
    public String getColor() { return color; }
    public int getValue() { return value; }
    public int getCost() { return cost; }
    public int getUnlockRound() { return unlockRound; }
    public String getEffect() { return effect; }
    public BufferedImage getImage() { return image; }

    public void setName(String name) { this.name = name; }
    public void setColor(String color) { this.color = color; }
    public void setValue(int value) { this.value = value; }
    public void setCost(int cost) { this.cost = cost; }
    public void setUnlockRound(int unlockRound) { this.unlockRound = unlockRound; }
    public void setEffect(String effect) { this.effect = effect; }
    public void setImage(BufferedImage image) { this.image = image; }

    @Override
    public String toString() {
        return color + " " + value + " (" + name + ")";
    }
}