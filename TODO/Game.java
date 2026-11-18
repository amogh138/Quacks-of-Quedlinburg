import java.util.*;
import static java.lang.System.*;
public class Game {
    private final int MAX_ROUNDS;
    private int playerCount;
    private int humanCount;
    private IngredientSet set;
    private List<Player> players;
    private List<Ingredient> shop;
    private ArrayList<Card> cards;
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
    public Game(int playerCount, int humanCount, IngredientSet set) {
        cards = new ArrayList<Card>();
        //add all fortune cards into cards
        this.playerCount = playerCount;
        this.humanCount = humanCount;
        MAX_ROUNDS = 9;
        this.set = set;
        currentCard = cards.get(rng.nextInt(24) + 1);
        cards.remove(currentCard);
    }
    private void makeCards() {
        for (int i = 1; i <= 24; i++) {
            cards.add(cards.get(i));
        }
    }
    public void startGame() {
    }
    private void startRound() {}
    private void giveRatTails() {}
    public void drawAll() {}
    private void drawChip(Player p) {}
    private void doEffect(Player p, Ingredient i) {}
    public void stopPlayer(int index) {}
    public void useFlask(int index) {}
    private void flask(Player p) {}
    private boolean everyoneStopped() {
        for (boolean b : decided) {
            if (!b) {
                return false;
            }
        }
        return true;
    }
    private void endBrewing() {}
    private void nextChoice() {}
    public void choose(boolean takeCoins) {}
    private void rollDice() {}
    private void scoreAll() {}
    private void updateShop() {}
    public boolean canBuy(Ingredient i) {}
    private boolean canBuy(Player p, Ingredient i) {}
    public void buy(Ingredient i) {}
    private void buy(Player p, Ingredient i) {
        if (canBuy(p, i)) {
            p.getBag().add(i);
            p.setCoins(p.getCoins() - i.getCost());
            shop.remove(i);
        }
    }
    public void buyDrop() {}
    public void buyFlask() {}
    public void doneBuying() {
        buying = false;
        nextBuyer();
    }
    private void nextBuyer() {
        current = (current + 1) % playerCount;
    }
    private void aiBuy(Player p) {
        if (p.getAI().takeCoins(p, board, round)) {
            p.setTakeCoins(true);
            addMessage(p.getName() + " took coins.");
        } else {
            Ingredient i = p.getAI().chooseBuy(p, shop);
            if (i != null) {
                buy(p, i);
                addMessage(p.getName() + " bought " + i.getName() + ".");
            } else if (p.getAI().wantFlask(p)) {
                buyFlask();
                addMessage(p.getName() + " bought a flask.");
            } else if (p.getAI().wantDrop(p)) {
                buyDrop();
                addMessage(p.getName() + " bought a drop.");
            } else {
                doneBuying();
                addMessage(p.getName() + " is done buying.");
            }
        }
    }

    public int getNumPlayers() {
        return players.size();
    }
    private void endRound() {
        if (round == MAX_ROUNDS) {
            gameOver = true;
        } else {
            round++;
            startRound();
        }
    }
    public Player winner() {
        Player winner = players.get(0);
        for (Player p : players) {
            if (p.getPoints() > winner.getPoints()) {
                winner = p;
            }
        }
        return winner;
    }
    public void addMessage(String text) {
        messages.add(text);
    }
    public Player getCurrentPlayer() {
        return players.get(current);
    }
    public List<Player> getPlayers() {
        return players;
    }
    public IngredientSet getSet() {
        return set;
    }
    public List<Ingredient> getShop() {
        return shop;
    }
    public Board getBoard() {
        return board;
    }
    public Card getCurrentCard() {
        return currentCard;
    }
    public int getRound() {
        return round;
    }
    public int getCurrent() {
        return current;
    }
    public int getDiceResult() {
        return diceResult;
    }
    public List<String> getMessages() {
        return messages;
    }
    public boolean isBrewing() {
        return !choosing && !buying && !gameOver;
    }
    public boolean isChoosing() {
        return choosing;
    }
    public boolean isBuying() {
        return buying;
    }
    public boolean isGameOver() {
        return gameOver;
    }
}