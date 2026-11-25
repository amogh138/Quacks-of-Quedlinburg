import java.util.*;
import java.awt.image.BufferedImage;

public class Player {
    private final int LIMIT;
    private String name;
    private AI ai;
    private List<Ingredient> bag;
    private List<Ingredient> pot;
    private List<Ingredient> boughtThisRound;
    private int position;
    private int startPosition;
    private int cherryBombTotal;
    private int coins;
    private int points;
    private int rubies;
    private int ratTails;
    private boolean flaskFilled;
    private boolean stopped;
    private boolean takeCoins;

    public Player(String name, AI ai, ArrayList<Ingredient> startBag) {
        this.LIMIT = 7;
        this.ai = ai;
        this.name = name;
        bag = startBag;
        position = 0;
        startPosition = 0;
        pot = new ArrayList<>();
        boughtThisRound = new ArrayList<>();
        cherryBombTotal = 0;
        coins = 0;
        points = 0;
        rubies = 0;
        ratTails = 0;
        flaskFilled = false;
        stopped = true;
    }

    public Ingredient draw() {
        if (bag.isEmpty()) {
            return null;
        }
        int r = (int) Math.random() * bag.size();
        Ingredient ingredient = bag.get(r);
        pot.add(ingredient);
        bag.remove(r);
        return ingredient;
    }

    public boolean isExploded() {
        return cherryBombTotal > LIMIT;
    }

    public void startRound(List<Ingredient> startBag) {
        bag = startBag;
        boughtThisRound = new ArrayList<>();
        position = getRatTails();
        cherryBombTotal = 0;
        stopped = true;
    }

    public void endRound() {
        for(Ingredient i : bag) {
            pot.add(i);
        }
        bag = new ArrayList<>();
    }

    public void undoLast() {
        if (!pot.isEmpty()) {
            pot.remove(pot.size() - 1);
        }
    }

    public void lowerBombs(int n) {
        cherryBombTotal = Math.max(0, cherryBombTotal - n);
    }

    public void addToBag(Ingredient i) {
        bag.add(i);
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
    }

    public void setStartPosition(int n) {
        startPosition = n;
    }

    public void setCoins(int n) {
        coins = n;
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
    }

    public void setRatTails(int n) {
        ratTails = n;
    }

    public void fillFlask() {
        this.flaskFilled = true;
    }

    public void useFlask() {
        flaskFilled = false;
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

    public String getName() {
        return name;
    }

    public AI getAI() {
        return ai;
    }

    public boolean isAI() {
        return ai != null;
    }

    public List<Ingredient> getBag() {
        return bag;
    }

    public List<Ingredient> getPot() {
        return pot;
    }

    public List<Ingredient> getBoughtThisRound() {
        return boughtThisRound;
    }

    public int getPosition() {
        return this.position;
    }

    public int getCherryBombTotal() {
        return this.cherryBombTotal;
    }

    public int getCoins() {
        return this.coins;
    }

    public int getPoints() {
        return this.points;
    }

    public int getRubies() {
        return this.rubies;
    }

    public int getRatTails() {
        return this.ratTails;
    }

    public boolean isFlaskFilled() {
        return this.flaskFilled;
    }

    public boolean isStopped() {
        return this.stopped;
    }

    public boolean isTakeCoins() {
        return takeCoins;
    }
}