import java.util.*;
import java.awt.image.BufferedImage;

public class Game {
    private final int MAX_ROUNDS;
    private int playerCount;
    private int humanCount;
    private IngredientSet set;
    private ArrayList<Player> players;
    private ArrayList<Ingredient> shop;
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
    private List<String> fortuneCardEffects;
    private boolean rubySpaceUsed;

    public Game(int playerCount, int humanCount, IngredientSet set) {
        MAX_ROUNDS = 9;
        this.playerCount = playerCount;
        this.humanCount = humanCount;
        this.set = set;
        rng = new Random();
        players = new ArrayList<>();
        shop = new ArrayList<>();
        cards = new ArrayList<>();
        messages = new ArrayList<>();
        fortuneCardEffects = new ArrayList<>();
        decided = new boolean[playerCount];
        int length = 54;
        int[] coinTrack = new int[length];
        int[] pointTrack = new int[length];
        boolean[] rubySpaces = new boolean[length];
        for (int i = 0; i < length; i++) {
            coinTrack[i] = Math.max(1, i / 5);
            pointTrack[i] = i / 2;
            rubySpaces[i] = i > 0 && i % 5 == 0;
        }
        board = new Board(length, 300, coinTrack, pointTrack, rubySpaces);
        makeCards();
    }

    private void makeCards() {
        cards.clear();
        fortuneCardEffects.clear();
        for (int i = 1; i <= 24; i++) {
            boolean isPink = i <= 12;
            cards.add(new Card(i, null, isPink));
        }
        Collections.shuffle(cards, rng);
    }

    public void startGame() {
        players.clear();
        messages.clear();
        shop.clear();
        round = 1;
        current = 0;
        diceResult = 0;
        choosing = false;
        buying = false;
        gameOver = false;
        decided = new boolean[playerCount];
        makeCards();
        ArrayList<Ingredient> startingBag = new ArrayList<>(set.getStartBag());
        for (int i = 0; i < playerCount; i++) {
            AI ai = i < humanCount ? null : new AI(0.5);
            Player p = new Player(
                    i < humanCount ? "Player " + (i + 1)
                            : "Computer " + (i - humanCount + 1),
                    ai,
                    new ArrayList<>(startingBag));
            p.fillFlask();
            players.add(p);
        }
        addMessage("The game has started.");
        startRound();
    }

    private void startRound() {
        if (round < 1 || round > MAX_ROUNDS) {
            gameOver = true;
            return;
        }
        choosing = false;
        buying = false;
        Arrays.fill(decided, false);
        current = 0;
        currentCard = cards.isEmpty() ? null : cards.remove(0);
        shop.clear();
        updateShop();

        if (currentCard != null && currentCard.isFortuneCard()) {
            currentCard.getEffect();
            applyFortuneCardEffect(currentCard.getCardNum());
        }

        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            p.getBag().addAll(p.getPot());
            p.getPot().clear();
            p.setPosition(p.getRatTails());
            int bombTotal = p.getCherryBombTotal();
            p.addCherryBombs(Math.max(0, -bombTotal));
            p.setTakeCoins(false);
            p.resume();
        }
        giveRatTails();
        addMessage("Round " + round + " has started.");
    }

    private void giveRatTails() {
        int highest = 0;
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            highest = Math.max(highest, p.getPoints());
        }
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            int tails = Math.max(0, (highest - p.getPoints()) / 2);
            p.setRatTails(tails);
            p.setPosition(tails);
        }
    }

    public void drawAll() {
        if (gameOver || choosing || buying) {
            return;
        }
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            if (!p.isStopped()) {
                if (p.isAI() && p.getAI().shouldStop(p)) {
                    stopPlayer(i);
                } else {
                    drawChip(p);
                }
            }
        }
        if (everyoneStopped()) {
            endBrewing();
        }
    }

    private void drawChip(Player p) {
        if (p == null || p.isStopped()) {
            return;
        }
        if (p.getBag().isEmpty()) {
            p.stop();
            addMessage(p.getName() + " has no ingredients left.");
            return;
        }
        Ingredient i = p.getBag().remove(rng.nextInt(p.getBag().size()));
        p.getPot().add(i);
        p.move(i.getValue());
        if (i.isCherryBomb()) {
            p.addCherryBombs(i.getValue());
        }
        doEffect(p, i);
        addMessage(p.getName() + " drew " + i.getName() + ".");
        if (p.isExploded()) {
            p.stop();
            addMessage(p.getName() + "'s pot exploded.");
        } else if (p.isAI() && p.getAI().shouldStop(p)) {
            p.stop();
        } else if (p.getBag().isEmpty()) {
            p.stop();
        }
    }

    private void doEffect(Player p, Ingredient i) {
        String color = i.getColor() == null
                ? "" : i.getColor().toLowerCase();
        String effect = i.getEffect() == null
                ? "" : i.getEffect().toLowerCase();
        if (color.equals("orange") || color.equals("pumpkin")) {
            if (effect.contains("coin")) {
                p.addCoins(1);
            }
        } else if (color.equals("green") || color.equals("spider")) {
            if (effect.contains("ruby")) {
                p.addRubies(1);
            }
        } else if (color.equals("blue") || color.equals("crow")) {
            if (effect.contains("bomb")) {
                p.lowerBombs(1);
            }
        } else if (color.equals("red") || color.equals("toadstool")) {
            if (effect.contains("move") || effect.contains("space")) {
                p.move(1);
            }
        }
    }

    public void stopPlayer(int index) {
        if (index < 0 || index >= players.size()
                || choosing || buying || gameOver) {
            return;
        }
        players.get(index).stop();
        addMessage(players.get(index).getName() + " stopped brewing.");
        if (everyoneStopped()) {
            endBrewing();
        }
    }

    public void useFlask(int index) {
        if (index < 0 || index >= players.size()
                || choosing || buying || gameOver) {
            return;
        }
        flask(players.get(index));
        if (everyoneStopped()) {
            endBrewing();
        }
    }

    private void flask(Player p) {
        if (p == null || !p.isFlaskFilled()
                || p.isStopped() || p.getPot().isEmpty()) {
            return;
        }
        if (p.getRubies() < 2) {
            return;
        }
        Ingredient last = p.getPot().get(p.getPot().size() - 1);
        if (!last.isCherryBomb()) {
            return;
        }
        p.getPot().remove(p.getPot().size() - 1);
        p.getBag().add(last);
        p.move(-last.getValue());
        p.lowerBombs(last.getValue());
        p.useFlask();
        p.spendRubies(2);
        p.resume();
        addMessage(p.getName() + " used a flask.");
    }

    private boolean everyoneStopped() {
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            if (!p.isStopped()) {
                return false;
            }
        }
        return true;
    }

    private void endBrewing() {
        if (choosing || buying || gameOver) {
            return;
        }
        choosing = true;
        Arrays.fill(decided, false);
        nextChoice();
    }

    private void nextChoice() {
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            if (p.isExploded() && !decided[i]) {
                current = i;
                if (p.isAI()) {
                    choose(p.getAI().takeCoins(p, board, round));
                }
                return;
            }
        }
        choosing = false;
        rollDice();
        scoreAll();
        buying = true;
        Arrays.fill(decided, false);
        current = 0;
        nextBuyer();
    }

    public void choose(boolean takeCoins) {
        if (!choosing || current < 0 || current >= players.size()) {
            return;
        }
        Player p = players.get(current);
        p.setTakeCoins(takeCoins);
        decided[current] = true;
        addMessage(p.getName() + (takeCoins ? " chose coins." : " chose points."));
        nextChoice();
    }

    private void rollDice() {
        diceResult = rng.nextInt(6) + 1;
    }

    private void scoreAll() {
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            int position = Math.min(p.getPosition(), board.getLength() - 1);

            if (p.isExploded()) {
                if (p.isTakeCoins()) {
                    p.addCoins(board.getCoins(position));
                } else {
                    p.addPoints(board.getPoints(position));
                }
            } else {
                p.addCoins(board.getCoins(position));
                p.addPoints(board.getPoints(position));
            }

            if (board.hasRuby(position)) {
                p.addRubies(1);
            }
        }
    }

    private void updateShop() {
        shop.clear();
        for (int i = 0; i < set.getIngredients().size(); i++) {
            Ingredient ingredient = set.getIngredients().get(i);
            if (ingredient.getUnlockRound() <= round) {
                shop.add(ingredient);
            }
        }
    }

    public boolean canBuy(Ingredient i) {
        if (current < 0 || current >= players.size()) {
            return false;
        }
        return canBuy(players.get(current), i);
    }

    private boolean canBuy(Player p, Ingredient i) {
        return buying && p != null && i != null
                && shop.contains(i) && p.getCoins() >= i.getCost();
    }

    public void buy(Ingredient i) {
        if (current >= 0 && current < players.size()) {
            buy(players.get(current), i);
        }
    }

    private void buy(Player p, Ingredient i) {
        if (!shop.contains(i)) {
            return;
        }
        if (canBuy(p, i)) {
            p.getBag().add(i);
            p.setCoins(p.getCoins() - i.getCost());
            p.getBoughtThisRound().add(i);
            shop.remove(i);
            addMessage(p.getName() + " bought " + i.getName() + ".");
        }
    }

    public void buyDrop() {
        if (!buying || current < 0 || current >= players.size()) {
            return;
        }
        Player p = players.get(current);
        if (p.getRubies() >= 2) {
            p.spendRubies(2);
            p.moveDrop(1);
            p.setPosition(p.getPosition() + 1);
            addMessage(p.getName() + " bought a drop.");
        }
    }

    public void buyFlask() {
        if (!buying || current < 0 || current >= players.size()) {
            return;
        }
        Player p = players.get(current);
        if (p.getRubies() >= 2 && !p.isFlaskFilled()) {
            p.spendRubies(2);
            p.fillFlask();
            addMessage(p.getName() + " bought a flask.");
        }
    }

    public void doneBuying() {
        if (!buying || current < 0 || current >= players.size() || gameOver) {
            return;
        }
        decided[current] = true;
        addMessage(players.get(current).getName() + " is done buying.");
        nextBuyer();
    }

    private void nextBuyer() {
        if (!buying) {
            return;
        }
        int checked = 0;
        while (checked < players.size() && decided[current]) {
            current = (current + 1) % players.size();
            checked++;
        }
        if (checked == players.size()) {
            endRound();
            return;
        }
        Player p = players.get(current);
        if (p.isAI()) {
            aiBuy(p);
            decided[current] = true;
            nextBuyer();
        }
    }

    private void aiBuy(Player p) {
        if (p.getAI().takeCoins(p, board, round)) {
            p.setTakeCoins(true);
            addMessage(p.getName() + " took coins.");
            return;
        }
        Ingredient i = p.getAI().chooseBuy(p, shop);
        if (i != null && canBuy(p, i)) {
            buy(p, i);
        } else if (p.getAI().wantFlask(p)
                && p.getRubies() >= 2 && !p.isFlaskFilled()) {
            p.spendRubies(2);
            p.fillFlask();
            addMessage(p.getName() + " bought a flask.");
        } else if (p.getAI().wantDrop(p)
                && p.getRubies() >= 2) {
            p.spendRubies(2);
            p.moveDrop(1);
            p.setPosition(p.getPosition() + 1);
            addMessage(p.getName() + " bought a drop.");
        } else {
            addMessage(p.getName() + " is done buying.");
        }
    }

    private void endRound() {
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            p.getBag().addAll(p.getPot());
            p.getPot().clear();
        }
        if (round == MAX_ROUNDS) {
            gameOver = true;
            buying = false;
            addMessage("Game over.");
        } else {
            round++;
            startRound();
        }
    }

    public Player winner() {
        if (players.isEmpty()) {
            return null;
        }
        Player winner = players.get(0);
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            if (p.getPoints() > winner.getPoints()
                    || (p.getPoints() == winner.getPoints() && p.getRubies() > winner.getRubies())
                    || (p.getPoints() == winner.getPoints() && p.getRubies() == winner.getRubies()
                        && p.getPot().size() > winner.getPot().size())) {
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

    public List<String> getFortuneCardEffects() {
        return fortuneCardEffects;
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

    private void applyFortuneCardEffect(int cardNum) {
        switch (cardNum) {
            case 1:
                for (Player p : players) {
                    for (Ingredient i : p.getPot()) {
                        if (i.getColor().equals("blue") || i.getColor().equals("crow")) {
                            i.setValue(i.getValue() + 2);
                        }
                    }
                }
                break;
            case 2:
                for (Ingredient i : set.getIngredients()) {
                    if (i.getColor().equals("orange") || i.getColor().equals("pumpkin")) {
                        i.setCost(Math.max(0, i.getCost() - 1));
                    }
                }
                break;
            case 3:
                for (Player p : players) {
                    for (Ingredient i : p.getPot()) {
                        if (i.getColor().equals("green") || i.getColor().equals("spider")) {
                            for (Ingredient j : p.getPot()) {
                                if (j.getColor().equals("white")) {
                                    j.setValue(j.getValue() + 1);
                                }
                            }
                        }
                    }
                }
                break;
            case 4:
                for (Player p : players) {
                    for (Ingredient i : p.getPot()) {
                        if (i.getColor().equals("purple") || i.getColor().equals("ghost")) {
                            for (Ingredient j : p.getPot()) {
                                if (j.getColor().equals("yellow") || j.getColor().equals("mandrake")) {
                                    j.setValue(j.getValue() + 1);
                                }
                            }
                        }
                    }
                }
                break;
            case 5:
                for (Ingredient i : set.getIngredients()) {
                    if (i.getColor().equals("blue") || i.getColor().equals("crow")) {
                        i.setCost(Math.max(0, i.getCost() - 1));
                    }
                }
                break;
            case 6:
                for (Player p : players) {
                    for (Ingredient i : p.getPot()) {
                        if (i.getColor().equals("orange") || i.getColor().equals("pumpkin")) {
                            for (Ingredient j : p.getPot()) {
                                if (j.getColor().equals("red") || j.getColor().equals("toadstool")) {
                                    j.setValue(j.getValue() + 1);
                                }
                            }
                        }
                    }
                }
                break;
            case 7:
                for (Player p : players) {
                    for (Ingredient i : p.getPot()) {
                        if (i.getColor().equals("white")) {
                            for (Ingredient j : p.getPot()) {
                                if (j.getColor().equals("purple") || j.getColor().equals("ghost")) {
                                    j.setValue(j.getValue() + 1);
                                }
                            }
                        }
                    }
                }
                break;
            case 8:
                for (Player p : players) {
                    for (Ingredient i : p.getPot()) {
                        if (i.getColor().equals("purple") || i.getColor().equals("ghost")) {
                            for (Ingredient j : p.getPot()) {
                                if (j.getColor().equals("blue") || j.getColor().equals("crow")) {
                                    j.setValue(j.getValue() + 1);
                                }
                            }
                        }
                    }
                }
                break;
            case 9:
                for (Ingredient i : set.getIngredients()) {
                    if (i.getColor().equals("yellow") || i.getColor().equals("mandrake")) {
                        i.setCost(Math.max(0, i.getCost() - 1));
                    }
                }
                break;
            case 10:
                for (Player p : players) {
                    for (Ingredient i : p.getPot()) {
                        if (i.getColor().equals("red") || i.getColor().equals("toadstool")) {
                            for (Ingredient j : p.getPot()) {
                                if (j.getColor().equals("green") || j.getColor().equals("spider")) {
                                    j.setValue(j.getValue() + 1);
                                }
                            }
                        }
                    }
                }
                break;
            case 11:
                for (Ingredient i : set.getIngredients()) {
                    if (i.getColor().equals("red") || i.getColor().equals("toadstool")) {
                        i.setCost(Math.max(0, i.getCost() - 1));
                    }
                }
                break;
            case 12:
                for (Player p : players) {
                    for (Ingredient i : p.getPot()) {
                        if (i.getColor().equals("white")) {
                            for (Ingredient j : p.getPot()) {
                                if (j.getColor().equals("orange") || j.getColor().equals("pumpkin")) {
                                    j.setValue(j.getValue() + 1);
                                }
                            }
                        }
                    }
                }
                break;
            default:
                break;
        }
    }
}