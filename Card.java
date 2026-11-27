import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Card {
    public int cardNum;
    public BufferedImage cardImage;
    public boolean isPink;
    public boolean isFortuneCard;
    
    public Card(int num, BufferedImage image, boolean isPink) {
        this.cardNum = num;
        this.cardImage = image;
        this.isPink = isPink;
        this.isFortuneCard = isPink;
    }
    
    public void getEffect(Game game) {
        if (!isFortuneCard) {
            return;
        }
        
        switch (cardNum) {
            case 1:
                for (Player p : game.players) { 
                    p.moveDroplet(2); 
                }
                break;
            case 2:
                for (Player p : game.players) {
                    for (Ingredient i : p.getBag()) {
                        if ("orange".equals(i.getColor())) {
                            i.setCost(i.getCost() - 1);
                        }
                    }
                }
                break;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                break;
            case 13:
                giveRubiesToFewest(game);
                break;
            case 14:
                drawFiveChipsAndDistribute(game);
                break;
            case 15:
                bonusDieForAll(game);
                break;
            case 16:
                for (Player p : game.players) {
                    p.LIMIT = 9;
                }
                break;
            case 17:
                for (Player p : game.players) {
                    if (p.hasWhiteChipSum(1)) {
                        p.addRubies(4);
                        p.spendRubies(4);
                    } else {
                        p.undoLast();
                    }
                }
                break;
            case 18:
                for (Player p : game.players) {
                    if (p.hasWhiteChipSum(7)) {
                        p.moveDroplet(1);
                    }
                }
                break;
            case 19:
                for (Player p : game.players) {
                    int position = p.getPosition();
                    if (game.board.hasRuby(position)) {
                        p.addPoints(2);
                    }
                }
                break;
            case 20:
                for (Player p : game.players) {
                    int position = p.getPosition();
                    if (game.board.hasRuby(position)) {
                        p.addRubies(1);
                    }
                }
                break;
            case 21:
                break;
            case 22:
                for (Player p : game.players) {
                    p.setBonusDieRoll(p.getBonusDieRoll() * 2);
                }
                break;
            case 23:
                break;
            case 24:
                for (Player p : game.players) {
                    for (Ingredient i : p.getBag()) {
                        if ("orange".equals(i.getColor())) {
                            p.move(1);
                        }
                    }
                }
                break;
            default:
                break;
        }
    }
    
    private void giveRubiesToFewest(Game game) {
        Player lowestRuby = null;
        int lowestRubies = Integer.MAX_VALUE;
        
        for (Player p : game.players) {
            if (p.getRubies() < lowestRubies) {
                lowestRuby = p;
                lowestRubies = p.getRubies();
            }
        }
        
        if (lowestRuby != null) {
            lowestRuby.addRubies(1);
        }
    }
    
    private void drawFiveChipsAndDistribute(Game game) {
        for (Player p : game.players) {
            for (int i = 0; i < 5; i++) {
                Ingredient chip = p.draw();
                if (chip != null) {
                    p.addToBag(chip);
                }
            }
        }
        
        Player lowest = null;
        int lowestSum = Integer.MAX_VALUE;
        for (Player p : game.players) {
            int sum = p.getWhiteChipSum();
            if (sum < lowestSum) {
                lowest = p;
                lowestSum = sum;
            }
        }
        
        if (lowest != null && lowestSum < 20) {
            Ingredient blue2chip = new Ingredient("Blue 2-chip", "blue", 2, 10, 1, "Bomb reduction", null);
            lowest.addToBag(blue2chip);
        }
    }
    
    private void bonusDieForAll(Game game) {
        for (Player p : game.players) {
            if (!p.isExploded()) {
                int roll = (int)(Math.random() * 6) + 1;
                p.setBonusDieRoll(roll);
                p.addPoints(roll);
            }
        }
    }
    
    public int getCardNum() { return cardNum; }
    public BufferedImage getImage() { return cardImage; }
    public boolean isPink() { return isPink; }
    public boolean isFortuneCard() { return isFortuneCard; }
}