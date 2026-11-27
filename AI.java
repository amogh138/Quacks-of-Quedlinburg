import java.util.Random;

public class AI {
    public double risk;
    public double coinPreference;
    public double rubyPreference;
    public double stopThreshold;
    public Random rng;
    public int lastMove;
    public int lastRoll;
    
    public AI(double risk) {
        this.risk = risk;
        this.coinPreference = 0.5;
        this.rubyPreference = 0.5;
        this.stopThreshold = 0.5;
        this.rng = new Random();
        this.lastMove = 0;
        this.lastRoll = 0;
    }
    
    public boolean shouldStop(Player p) {
        int cherryBombs = p.getCherryBombTotal();
        int bagSize = p.getBag().size();
        
        if (bagSize <= 1) return true;
        if (cherryBombs >= 7 || (cherryBombs >= 6 && risk < 0.5)) return true;
        return rng.nextDouble() < risk || cherryBombs >= 5;
    }
    
    public boolean takeCoins(Player p, Board board, int round) {
        int position = p.getPosition();
        int coins = board.getCoins(position);
        int points = board.getPoints(position);
        
        if (coins > 0 && coins * 2 > points) return true;
        return risk > 0.7;
    }
    
    public Ingredient chooseBuy(Player p, java.util.List<Ingredient> shop) {
        if (shop.isEmpty()) return null;
        java.util.List<Ingredient> affordable = new java.util.ArrayList<>();
        for (Ingredient i : shop) {
            if (p.getCoins() >= i.getCost()) affordable.add(i);
        }
        if (affordable.isEmpty()) return null;
        java.util.Collections.shuffle(affordable, rng);
        return affordable.get(0);
    }
    
    public boolean wantFlask(Player p) {
        int cherryBombs = p.getCherryBombTotal();
        return cherryBombs > 0 && !p.isFlaskFilled() && p.getRubies() >= 2 && risk > 0.3;
    }
    
    public boolean wantDrop(Player p) {
        int rubies = p.getRubies();
        int position = p.getPosition();
        return rubies >= 2 && position < 30 && risk > 0.4;
    }
    
    public boolean shouldMoveDroplet(Player p, int rubies) {
        return rubies >= 2 && rng.nextDouble() < risk;
    }
    
    public boolean shouldTakeBonus(Player p, int bonus) {
        int rubies = p.getRubies();
        return bonus * 2 <= rubies && rng.nextDouble() < risk;
    }
    
    public boolean preferCoins(Player p, Board board) {
        return rng.nextDouble() < coinPreference;
    }
    
    public boolean preferRubies(Player p) {
        return rng.nextDouble() < rubyPreference;
    }
    
    public boolean isAggressive() {
        return risk > 0.7;
    }
    
    public boolean isConservative() {
        return risk < 0.3;
    }
    
    public void setRisk(double risk) {
        this.risk = risk;
    }
    
    public double getRisk() {
        return risk;
    }
    
    public void setCoinPreference(double preference) {
        this.coinPreference = preference;
    }
    
    public double getCoinPreference() {
        return coinPreference;
    }
    
    public void setRubyPreference(double preference) {
        this.rubyPreference = preference;
    }
    
    public double getRubyPreference() {
        return rubyPreference;
    }
    
    public void setStopThreshold(double threshold) {
        this.stopThreshold = threshold;
    }
    
    public double getStopThreshold() {
        return stopThreshold;
    }
    
    public void setLastMove(int move) {
        this.lastMove = move;
    }
    
    public int getLastMove() {
        return lastMove;
    }
    
    public void setLastRoll(int roll) {
        this.lastRoll = roll;
    }
    
    public int getLastRoll() {
        return lastRoll;
    }
}