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
    public Player(String name, AI ai, List<Ingredient> startBag) {
        this.ai = ai;
        this.name = name;
        bag = startBag;
        position = startPosition;
    }
    public Ingredient draw(Random rng) {}
    public boolean isExploded() {
        if(cherryBombTotal > LIMIT) {
            return true;
        }
        return false;
    }
    private void startRound(List<Ingredient> startBag) {
        bag = startBag;
        boughtThisRound = new ArrayList<>();
        position = 0;
        cherryBombTotal = 0;
        stopped = true;
    }
    public void endRound() {
        for(Ingredient i : bag) {
            pot.add(i);
        }
        bag = new ArrayList<>();
    }
    public void undoLast() {}
    public void lowerBombs(int n) {
        cherryBombTotal-=n;
    }
    public void addToBag(Ingredient i) {
        bag.add(i);
    }
    public void addCoins(int n) {
        coins+=n;
    }
    public void spendCoins(int n) {
        coins-=n;
    }
    public void addPoints(int n) {
        points+=n;
    }
    public void addRubies(int n) {
        rubies+=n;
    }
    public void spendRubies(int n) {
        rubies-=n;
    }
    public void moveDrop(int n) {
        position+=n;
    }
    public void move(int n) {}
    public void setRatTails(int n) {
        ratTails = n;
    }
    public void fillFlask() {
        flaskFilled = true;
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
    public AI getAI() {}
    public boolean isAI() {}
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