import java.awt.image.*;
public class Card {
    private int cardNum;
    private BufferedImage cardImage;
    private boolean cardColor;
    public Card(int num, BufferedImage image, boolean color) {
        cardNum = num;
        cardImage = image;
        cardColor = color;
    }
    private void getEffect(int cardNum) {
        switch (cardNum) {
            default:
                System.out.println("Make this");
        }
    }

    public BufferedImage getImage() {
        return cardImage;
    }
<<<<<<< HEAD
} 
=======
}
>>>>>>> 3f97afe15f6fb098a1582d711dd69a1d6efc152c
