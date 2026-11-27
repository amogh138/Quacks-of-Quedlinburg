import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game {
    public List<Player> players;
    public Board board;
    public List<Card> cards;
    public Random rng;
    public int currentRound;
    public final int MAX_ROUNDS = 9;
    public IngredientSet ingredientSet;
    public int turn;
    public int currentPlayerIndex;
    public Player currentPlayer;
    public List<Ingredient> boughtThisTurn;
    public List<Ingredient> bonusChips;
    public int bonusCount;
    public boolean isActive;
    public List<IngredientSet> ingredientBooks;
    public int totalRubyCollected;
    public int totalVPCollected;
    public int totalChipsBought;
    public int turnTracker;
    
    public Game(List<Player> players, Board board) {
        this.players = players;
        this.board = board;
        this.cards = new ArrayList<>();
        this.rng = new Random();
        this.currentRound = 1;
        this.ingredientSet = IngredientSet.getDefaultSet();
        this.turn = 1;
        this.currentPlayerIndex = 0;
        this.currentPlayer = players.get(0);
        this.boughtThisTurn = new ArrayList<>();
        this.bonusChips = new ArrayList<>();
        this.bonusCount = 0;
        this.isActive = false;
        this.ingredientBooks = new ArrayList<>();
        this.totalRubyCollected = 0;
        this.totalVPCollected = 0;
        this.totalChipsBought = 0;
        this.turnTracker = 1;
        
        makeCards();
        setupIngredientBooks();
    }
    
    public void makeCards() {
        cards.clear();
        for (int i = 1; i <= 24; i++) {
            cards.add(new Card(i, null, true));
        }
        Collections.shuffle(cards, rng);
    }
    
    public void setupIngredientBooks() {
        for (int i = 1; i <= 5; i++) {
            IngredientSet book = new IngredientSet("Book " + i, "Book " + i);
            book.setNumber(i);
            book.setRound(i);
            book.setActive(false);
            book.bonusChips = new ArrayList<>();
            book.bonusCount = 0;
            this.ingredientBooks.add(book);
        }
    }
    
    public List<Player> determineWinners() {
        int maxPoints = -1;
        List<Player> winners = new ArrayList<>();
        for (Player p : players) {
            if (p.getPoints() > maxPoints) {
                maxPoints = p.getPoints();
                winners.clear();
                winners.add(p);
            } else if (p.getPoints() == maxPoints) {
                winners.add(p);
            }
        }
        
        if (winners.size() > 1 && currentRound <= MAX_ROUNDS) {
            currentRound = 10;
            playRound();
            maxPoints = -1;
            List<Player> finalWinners = new ArrayList<>();
            for (Player p : players) {
                if (p.getPoints() > maxPoints) {
                    maxPoints = p.getPoints();
                    finalWinners.clear();
                    finalWinners.add(p);
                } else if (p.getPoints() == maxPoints) {
                    finalWinners.add(p);
                }
            }
            return finalWinners;
        }
        return winners;
    }
    
    public void playRound() {
        if (!cards.isEmpty()) {
            Card activeCard = cards.get(0);
            activeCard.getEffect(this);
            cards.remove(0);
        }
        
        if (currentRound > 1) {
            int ratDistance = calculateRatDistance();
            for (int i = 1; i < players.size(); i++) {
                players.get(i).addRatTails(ratDistance);
            }
        }
        
        for (Player p : players) {
            p.startRound(p.getBag());
            while (!p.isStopped() && !p.isExploded()) {
                Ingredient ing = p.draw();
                if (ing == null) break;
                
                if (ing.isCherryBomb()) {
                    p.addCherryBombs(ing.getValue());
                } else {
                    p.move(ing.getValue());
                }
                
                if (p.getAI() != null && p.getAI().shouldStop(p)) {
                    p.stop();
                }
            }
        }
    }
    
    public void playTurn() {
        turn++;
        if (turn > 9) {
            determineWinners();
            return;
        }
        
        currentPlayer = players.get(currentPlayerIndex);
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        
        activateIngredientBooks();
        playRound();
        evaluateRound();
    }
    
    private void activateIngredientBooks() {
        for (IngredientSet book : ingredientBooks) {
            if (book.getRound() == turn) {
                book.setActive(true);
            }
        }
        isActive = true;
    }
    
    private void evaluateRound() {
        for (Player p : players) {
            p.checkLastSpaceBonus(board);
        }
        
        for (Player p : players) {
            int position = p.getPosition();
            if (board.hasRuby(position)) {
                p.recordRubySpace();
            }
        }
        
        for (Player p : players) {
            int roll = (int)(Math.random() * 6) + 1;
            p.addPoints(roll);
        }
        
        for (Player p : players) {
            if (!p.isExploded()) {
                p.collectRuby();
            }
        }
        
        for (Player p : players) {
            Ingredient chip = buyChip(p);
            if (chip != null) {
                totalChipsBought++;
            }
        }
    }
    
    private Ingredient buyChip(Player p) {
        int coins = p.getCoins();
        if (coins == 0) return null;
        
        int bestChipValue = 0;
        for (int value = 4; value >= 1; value--) {
            int cost = ingredientSet.getCost(value);
            if (coins >= cost) {
                bestChipValue = value;
                break;
            }
        }
        
        if (bestChipValue == 0) return null;
        
        for (Ingredient i : ingredientSet.getIngredients()) {
            if (i.getValue() == bestChipValue && p.getCoins() >= i.getCost()) {
                p.spendCoins(i.getCost());
                p.addToBag(i);
                return i;
            }
        }
        
        return null;
    }
    
    private int calculateRatDistance() {
        return 1;
    }
    
    public void startGame() {
        for (Player p : players) {
            p.setCoins(0);
            p.setPoints(0);
        }
        makeCards();
        playTurn();
    }
    
    public IngredientSet getIngredientSet() {
        return ingredientSet;
    }
    
    public int getTurn() {
        return turn;
    }
    
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean active) {
        isActive = active;
    }
    
    public int getCurrentRound() {
        return currentRound;
    }
    
    public int getMaxRounds() {
        return MAX_ROUNDS;
    }
    
    public List<IngredientSet> getIngredientBooks() {
        return ingredientBooks;
    }
    
    public List<Ingredient> getBoughtThisTurn() {
        return boughtThisTurn;
    }
    
    public List<Ingredient> getBonusChips() {
        return bonusChips;
    }
    
    public int getBonusCount() {
        return bonusCount;
    }
    
    public void incrementBonusCount() {
        bonusCount++;
    }
    
    public void addBonusChip(Ingredient chip) {
        bonusChips.add(chip);
        bonusCount++;
    }
    
    public int getTotalRubyCollected() {
        return totalRubyCollected;
    }
    
    public int getTotalVPCollected() {
        return totalVPCollected;
    }
    
    public int getTotalChipsBought() {
        return totalChipsBought;
    }
    
    public int getTurnTracker() {
        return turnTracker;
    }
}