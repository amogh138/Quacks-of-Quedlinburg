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
}
