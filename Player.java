import java.util.ArrayList;
import java.util.List;

public class Player {
    public int LIMIT = 7;
    public String name;
    public AI ai;
    public List<Ingredient> bag;
    public List<Ingredient> pot;
    public List<Ingredient> boughtThisRound;
    public int position;
    public int startPosition;
    public int cherryBombTotal;
    public int coins;
    public int points;
    public int rubies;
    public int ratTails;
    public boolean flaskFilled;
    public boolean stopped;
    public boolean takeCoins;
    public int bonusDieRoll;
    public int rubySpacesReached;
    public int lastScoringSpace;
    public int boughtChipsThisRound;
    public boolean hasLastSpaceBonus;
    public boolean appliedMovementBonus;
    public int movementBonusValue;
    
    public Player(String name, AI ai, List<Ingredient> startBag) {
        this.ai = ai;
        this.name = name;
        this.bag = new ArrayList<>(startBag);
        this.position = 0;
        this.startPosition = 0;
        this.pot = new ArrayList<>();
        this.boughtThisRound = new ArrayList<>();
        this.cherryBombTotal = 0;
        this.coins = 0;
        this.points = 0;
        this.rubies = 0;
        this.ratTails = 0;
        this.flaskFilled = true;
        this.stopped = true;
        this.takeCoins = false;
        this.bonusDieRoll = 0;
        this.rubySpacesReached = 0;
        this.lastScoringSpace = 0;
        this.boughtChipsThisRound = 0;
        this.hasLastSpaceBonus = false;
        this.appliedMovementBonus = false;
        this.movementBonusValue = 0;
    }
    
    public Ingredient draw() {
        if (bag.isEmpty()) return null;
        int r = (int) (Math.random() * bag.size());
        Ingredient ingredient = bag.get(r);
        pot.add(ingredient);
        bag.remove(r);
        return ingredient;
    }
    
    public boolean isExploded() { 
        return cherryBombTotal > LIMIT; 
    }
    
    public boolean hasWhiteChipSum(int sum) {
        int total = 0;
        for (Ingredient i : pot) {
            if (i.isCherryBomb()) {
                total += i.getValue();
            }
        }
        return total == sum;
    }
    
    public void startRound(List<Ingredient> baseBag) {
        bag = new ArrayList<>(baseBag);
        bag.addAll(pot);
        pot.clear();
        boughtThisRound = new ArrayList<>();
        position = getRatTails();
        cherryBombTotal = 0;
        stopped = false;
        flaskFilled = true;
        boughtChipsThisRound = 0;
        hasLastSpaceBonus = false;
        appliedMovementBonus = false;
        movementBonusValue = 0;
    }
    
    public void endRound() {
        bag.addAll(pot);
        pot.clear();
        boughtChipsThisRound = 0;
    }
    
    public void undoLast() {
        if (!pot.isEmpty()) pot.remove(pot.size() - 1);
    }
    
    public void lowerBombs(int n) { 
        cherryBombTotal = Math.max(0, cherryBombTotal - n); 
    }
    
    public void addToBag(Ingredient i) { 
        bag.add(i); 
        if (!i.isCherryBomb()) {
            boughtChipsThisRound++;
        }
    }
    
    public void addCoins(int n) { 
        coins += n; 
    }
    
    public void spendCoins(int n) { 
        coins -= n; 
    }
    
    public void addCherryBombs(int n) { 
        cherryBombTotal += n; 
    }
    
    public void addRatTails(int n) { 
        ratTails += n; 
    }
    
    public void spendRatTails(int n) { 
        ratTails = Math.max(0, ratTails - n); 
    }
    
    public void setPosition(int n) { 
        position = n; 
        if (n > lastScoringSpace) {
            lastScoringSpace = n;
        }
    }
    
    public void setStartPosition(int n) { 
        startPosition = n; 
    }
    
    public void setCoins(int n) { 
        coins = n; 
    }
    
    public void setPoints(int n) {
        points = n;
    }
    
    public void addPoints(int n) { 
        points += n; 
    }
    
    public void addRubies(int n) { 
        rubies += n; 
    }
    
    public void spendRubies(int n) { 
        rubies = Math.max(0, rubies - n); 
    }
    
    public void moveDrop(int n) { 
        startPosition += n; 
    }
    
    public void move(int n) { 
        position += n;
        if (position >= 33) {
            position = position % 33;
        }
    }
    
    public void setRatTails(int n) { 
        ratTails = n; 
    }
    
    public void fillFlask() { 
        this.flaskFilled = true; 
    }
    
    public void useFlask() { 
        this.flaskFilled = false;
        spendRubies(2);
    }
    
    public void stop() { 
        stopped = true; 
    }
    
    public void resume() { 
        stopped = false; 
    }
    
    public void setTakeCoins(boolean takeCoins) { 
        this.takeCoins = takeCoins; 
    }
    
    public void collectRuby() {
        rubies++;
    }
    
    public void applyMovementBonus() {
        appliedMovementBonus = true;
        movementBonusValue = 1;
    }
    
    public void applyCostReduction() {
        for (Ingredient i : pot) {
            if (i.isCostReductionApplied()) {
                i.setCost(i.getCost() - 1);
            }
        }
    }
    
    public void resetMovementBonus() {
        appliedMovementBonus = false;
        movementBonusValue = 0;
    }
    
    public void checkLastSpaceBonus(Board board) {
        if (board.isLastSpace(position)) {
            hasLastSpaceBonus = true;
            addPoints(15);
            addCoins(35);
        }
    }
    
    public void recordRubySpace() {
        rubySpacesReached++;
        addRubies(1);
    }
    
    public void recordRubySpaceExtraVP(int extraVP) {
        addPoints(extraVP);
    }
    
    public String getName() { return name; }
    public AI getAI() { return ai; }
    public boolean isAI() { return ai != null; }
    public List<Ingredient> getBag() { return bag; }
    public List<Ingredient> getPot() { return pot; }
    public List<Ingredient> getBoughtThisRound() { return boughtThisRound; }
    public int getPosition() { return this.position; }
    public int getCherryBombTotal() { return this.cherryBombTotal; }
    public int getCoins() { return this.coins; }
    public int getPoints() { return this.points; }
    public int getRubies() { return this.rubies; }
    public int getRatTails() { return this.ratTails; }
    public boolean isFlaskFilled() { return this.flaskFilled; }
    public boolean isStopped() { return this.stopped; }
    public boolean isTakeCoins() { return takeCoins; }
    public int getBonusDieRoll() { return bonusDieRoll; }
    public void setBonusDieRoll(int roll) { bonusDieRoll = roll; }
    public int getRubySpacesReached() { return rubySpacesReached; }
    public int getLastScoringSpace() { return lastScoringSpace; }
    public boolean hasLastSpaceBonus() { return hasLastSpaceBonus; }
    public boolean isAppliedMovementBonus() { return appliedMovementBonus; }
    public int getMovementBonusValue() { return movementBonusValue; }
    
    public void moveDroplet(int n) {
        startPosition += n;
    }
    
    public int getWhiteChipSum() {
        int total = 0;
        for (Ingredient i : pot) {
            if (i.isCherryBomb()) {
                total += i.getValue();
            }
        }
        return total;
    }
}