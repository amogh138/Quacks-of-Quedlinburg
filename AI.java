import java.util.*;

public class AI {
    private double risk;
    private Random rng;

    public AI(double risk) {
        this.risk = risk;
        this.rng = new Random();
    }

    public boolean shouldStop(Player p) {
        int cherryBombs = p.getCherryBombTotal();
        int bagSize = p.getBag().size();

        if (bagSize <= 1) {
            return true;
        }

        if (cherryBombs >= 7 || cherryBombs >= 6 && risk < 0.5) {
            return true;
        }

        double randomFactor = rng.nextDouble();
        return randomFactor < risk || cherryBombs >= 5;
    }

    public boolean takeCoins(Player p, Board board, int round) {
        int position = p.getPosition();
        int coins = board.getCoins(position);
        int points = board.getPoints(position);

        if (coins > 0 && coins * 2 > points) {
            return true;
        }

        if (risk > 0.7) {
            return true;
        }

        return false;
    }

    public Ingredient chooseBuy(Player p, List<Ingredient> shop) {
        if (shop.isEmpty()) {
            return null;
        }

        List<Ingredient> affordable = new ArrayList<>();
        for (Ingredient i : shop) {
            if (p.getCoins() >= i.getCost()) {
                affordable.add(i);
            }
        }

        if (affordable.isEmpty()) {
            return null;
        }

        int cherryBombs = p.getCherryBombTotal();

        if (cherryBombs >= 4) {
            for (Ingredient i : affordable) {
                if (i.getColor().equals("blue") && i.getValue() == 1) {
                    return i;
                }
            }
        }

        for (Ingredient i : affordable) {
            if (i.getColor().equals("yellow")) {
                return i;
            }
        }

        for (Ingredient i : affordable) {
            if (i.getColor().equals("blue")) {
                return i;
            }
        }

        Collections.shuffle(affordable, rng);
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
}