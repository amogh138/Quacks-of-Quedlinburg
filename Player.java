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
    public Player(String name, AI ai, List<Ingredient> startBag) {}
    public Ingredient draw(Random rng) {}
    public boolean isExploded() {}
    public void startRound() {}
    public void endRound() {}
    public void undoLast() {}
    public void lowerBombs(int n) {}
    public void addToBag(Ingredient i) {}
    public void addCoins(int n) {}
    public void spendCoins(int n) {}
    public void addPoints(int n) {}
    public void addRubies(int n) {}
    public void spendRubies(int n) {}
    public void moveDrop(int n) {}
    public void move(int n) {}
    public void setRatTails(int n) {}
    public void fillFlask() {}
    public void useFlask() {}
    public void stop() {}
    public void resume() {}
    public void setTakeCoins(boolean takeCoins) {}
    public String getName() {}
    public AI getAI() {}
    public boolean isAI() {}
    public List<Ingredient> getBag() {}
    public List<Ingredient> getPot() {}
    public List<Ingredient> getBoughtThisRound() {}
    public int getPosition() {}
    public int getCherryBombTotal() {}
    public int getCoins() {}
    public int getPoints() {}
    public int getRubies() {}
    public int getRatTails() {}
    public boolean isFlaskFilled() {}
    public boolean isStopped() {}
    public boolean isTakeCoins() {}
}