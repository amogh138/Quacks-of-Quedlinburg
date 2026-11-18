import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class QuacksBrewPanel extends JPanel implements MouseListener {
    private Game game;
    private int selected;
    private boolean showMenu;
    private BufferedImage menu, board, pot, shop, selectSet, numPlayers;
    private Color[] colors;

    public QuacksBrewPanel() {
        try {
            selectSet = ImageIO.read(QuacksBrewPanel.class.getResource("/Image/Sets.png"));
            numPlayers = ImageIO.read(QuacksBrewPanel.class.getResource("/Image/PlayerNum.png"));
        } catch(Exception E) {
            System.out.println("error");
        }
    }

    
    public void paint(Graphics g) {
        super.paint(g);
        drawMenu(g);
    }
    private void drawMenu(Graphics g) {}
    private void drawSelectSet(Graphics g) {
        g.drawImage(selectSet, 0, getHeight(), 0, getWidth(), null);
    }
    private void drawSelectNumPlayers(Graphics g) {}
    private void drawHeader(Graphics2D g) {
        String phase;
        if(game.isBrewing())
            phase = "Brewing";
        if(game.isBuying())
            phase = "Buying";
        else
            phase = "Game Over";
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, getWidth(), 40);
        g.setColor(Color.BLACK);
        g.drawString("Round :" + game.getRound() + "\tPhase: " + phase + "\tDice Result: " + game.getDiceResult(), 20, 20);
    }
    private void drawBoard(Graphics g) {
        g.drawImage()
    }
    private void drawCard(Graphics g) {
        g.drawImage(game.getCurrentCard().getImage(), 0, 0, 50, 50, null);
    }
    private void drawPot(Graphics g, String color) {}
    private void drawShop(Graphics2D g) {}
    private void drawMessage(Graphics2D g) {}
    private void drawGameOver(Graphics2D g) {}
    //private Color getColor(String name) {}
    private void handleKey(int keyCode) {}
    private void moveSelection(int amount) {}

    public void mouseClicked(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}