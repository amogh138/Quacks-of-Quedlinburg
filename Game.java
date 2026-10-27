public class Game {
    private final int MAX_ROUNDS;
    private int playerCount;
    private int humanCount;
    private IngredientSet set;
    private List<Player> players;
    private List<Ingredient> shop;
    private List<Card> cards;
    private Board board;
    private Card currentCard;
    private Random rng;
    private int round;
    private int current;
    private int diceResult;
    private boolean choosing;
    private boolean buying;
    private boolean gameOver;
    private boolean[] decided;
    private List<String> messages;
    public Game(int playerCount, int humanCount, IngredientSet set) {}
    private void makeCards() {}
    public void startGame() {}
    private void startRound() {}
    private void giveRatTails() {}
    public void drawAll() {}
    private void drawChip(Player p) {}
    private void doEffect(Player p, Ingredient i) {}
    public void stopPlayer(int index) {}
    public void useFlask(int index) {}
    private void flask(Player p) {}
    private boolean everyoneStopped() {}
    private void endBrewing() {}
    private void nextChoice() {}
    public void choose(boolean takeCoins) {}
    private void rollDice() {}
    private void scoreAll() {}
    private void updateShop() {}
    public boolean canBuy(Ingredient i) {}
    private boolean canBuy(Player p, Ingredient i) {}
    public void buy(Ingredient i) {}
    private void buy(Player p, Ingredient i) {}
    public void buyDrop() {}
    public void buyFlask() {}
    public void doneBuying() {}
    private void nextBuyer() {}
    private void aiBuy(Player p) {}
    private void endRound() {}
    public Player winner() {}
    public void addMessage(String text) {}
    public Player getCurrentPlayer() {}
    public List<Player> getPlayers() {}
    public IngredientSet getSet() {}
    public List<Ingredient> getShop() {}
    public Board getBoard() {}
    public Card getCurrentCard() {}
    public int getRound() {}
    public int getCurrent() {}
    public int getDiceResult() {}
    public List<String> getMessages() {}
    public boolean isBrewing() {}
    public boolean isChoosing() {}
    public boolean isBuying() {}
    public boolean isGameOver() {}
}