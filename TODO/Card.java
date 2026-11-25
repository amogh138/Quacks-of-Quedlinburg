import java.awt.image.*;
import java.util.*;

public class Card {
    private int cardNum;
    private BufferedImage cardImage;
    private boolean isPink;
    private boolean isFortuneCard;

    public Card(int num, BufferedImage image, boolean isPink) {
        cardNum = num;
        cardImage = image;
        isPink = isPink;
        isFortuneCard = isPink;
    }

    public void getEffect() {
        if (!isFortuneCard) {
            return;
        }

        switch (cardNum) {
            case 1:
                System.out.println("Fortune Card 1: Blue chips - Move droplets forward 2 spaces");
                break;
            case 2:
                System.out.println("Fortune Card 2: Red chips - Orange chips cost 1 less coin");
                break;
            case 3:
                System.out.println("Fortune Card 3: Green chips - White chips move 1 space extra");
                break;
            case 4:
                System.out.println("Fortune Card 4: Purple chips - Yellow chips move 1 space extra");
                break;
            case 5:
                System.out.println("Fortune Card 5: Yellow chips - Blue chips cost 1 less coin");
                break;
            case 6:
                System.out.println("Fortune Card 6: Orange chips - Red chips move 1 space extra");
                break;
            case 7:
                System.out.println("Fortune Card 7: White chips - Purple chips move 1 space extra");
                break;
            case 8:
                System.out.println("Fortune Card 8: Purple chips - Blue chips move 1 space extra");
                break;
            case 9:
                System.out.println("Fortune Card 9: Green chips - Yellow chips cost 1 less coin");
                break;
            case 10:
                System.out.println("Fortune Card 10: Red chips - Green chips move 1 space extra");
                break;
            case 11:
                System.out.println("Fortune Card 11: Blue chips - Red chips cost 1 less coin");
                break;
            case 12:
                System.out.println("Fortune Card 12: White chips - Orange chips move 1 space extra");
                break;
            default:
                System.out.println("Fortune card " + cardNum + ": Standard effects");
        }
    }

    public int getCardNum() {
        return cardNum;
    }

    public BufferedImage getImage() {
        return cardImage;
    }

    public boolean isPink() {
        return isPink;
    }

    public boolean isFortuneCard() {
        return isFortuneCard;
    }
}

enum EffectType {
    BLUE_MOVEMENT_2,
    RED_COST_ORANGE,
    GREEN_MOVEMENT_WHITE,
    PURPLE_MOVEMENT_YELLOW,
    YELLOW_COST_BLUE,
    ORANGE_MOVEMENT_RED,
    WHITE_MOVEMENT_PURPLE,
    PURPLE_MOVEMENT_BLUE,
    GREEN_COST_YELLOW,
    RED_MOVEMENT_GREEN,
    BLUE_COST_RED,
    WHITE_MOVEMENT_ORANGE
}