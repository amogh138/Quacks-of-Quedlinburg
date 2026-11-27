import java.awt.image.BufferedImage;

public class Ingredient {
    public String name;
    public String color;
    public int value;
    public int cost;
    public int unlockRound;
    public String effect;
    public BufferedImage image;
    public int quantity;
    public boolean isActive;
    public int placedRound;
    public int placedPosition;
    public int effectDuration;
    public boolean effectApplied;
    public boolean canApplyEffect;
    public int rubyBonusApplied;
    public int movementBonusApplied;
    public boolean costReductionApplied;
    
    public Ingredient(String name, String color, int value, int cost, 
                     int unlockRound, String effect, BufferedImage image) {
        this.name = name;
        this.color = color;
        this.value = value;
        this.cost = cost;
        this.unlockRound = unlockRound;
        this.effect = effect;
        this.image = image;
        this.quantity = 0;
        this.isActive = false;
        this.placedRound = 0;
        this.placedPosition = -1;
        this.effectDuration = 0;
        this.effectApplied = false;
        this.canApplyEffect = true;
        this.rubyBonusApplied = 0;
        this.movementBonusApplied = 0;
        this.costReductionApplied = false;
    }
    
    public boolean isCherryBomb() { return "white".equals(color); }
    public boolean isOrange() { return "orange".equals(color); }
    public boolean isGreen() { return "green".equals(color); }
    public boolean isBlue() { return "blue".equals(color); }
    public boolean isRed() { return "red".equals(color); }
    public boolean isYellow() { return "yellow".equals(color); }
    public boolean isPurple() { return "purple".equals(color); }
    public boolean isBlack() { return "black".equals(color); }
    public boolean isWhite() { return "white".equals(color); }
    
    public String getName() { return name; }
    public String getColor() { return color; }
    public int getValue() { return value; }
    public int getCost() { return cost; }
    public int getUnlockRound() { return unlockRound; }
    public String getEffect() { return effect; }
    public BufferedImage getImage() { return image; }
    public void setCost(int cost) { this.cost = cost; }
    
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
    
    public int getPlacedRound() { return placedRound; }
    public void setPlacedRound(int round) { placedRound = round; }
    
    public int getPlacedPosition() { return placedPosition; }
    public void setPlacedPosition(int position) { placedPosition = position; }
    
    public int getEffectDuration() { return effectDuration; }
    public void setEffectDuration(int duration) { effectDuration = duration; }
    
    public boolean isEffectApplied() { return effectApplied; }
    public void setEffectApplied(boolean applied) { effectApplied = applied; }
    
    public boolean isEffectActive() { return isActive && effectApplied && effectDuration > 0; }
    
    public void decrementEffectDuration() {
        if (effectDuration > 0) {
            effectDuration--;
            if (effectDuration <= 0) {
                effectApplied = false;
                isActive = false;
            }
        }
    }
    
    public boolean canApplyEffect() { return canApplyEffect; }
    public void setCanApplyEffect(boolean can) { canApplyEffect = can; }
    
    public int getRubyBonusApplied() { return rubyBonusApplied; }
    public void setRubyBonusApplied(int bonus) { rubyBonusApplied = bonus; }
    
    public int getMovementBonusApplied() { return movementBonusApplied; }
    public void setMovementBonusApplied(int bonus) { movementBonusApplied = bonus; }
    
    public boolean isCostReductionApplied() { return costReductionApplied; }
    public void setCostReductionApplied(boolean applied) { costReductionApplied = applied; }
    
    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (!(other instanceof Ingredient)) return false;
        Ingredient o = (Ingredient) other;
        return this.name.equals(o.name) && this.color.equals(o.color);
    }
    
    @Override
    public int hashCode() { 
        return name.hashCode() + color.hashCode(); 
    }
}