import java.awt.*;
import java.awt.image.*;
import java.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.awt.event.MouseListener;
import java.awt.even.MouseEvent;

public class QuacksBrewPanel extends JPanel implements MouseListener {
    private Game game;
    private int selected;
    private boolean showMenu;
    private BufferedImage menu, board, card, scoreboard, pot, shop;
    private Color[] colors;
    public QuacksPanel(Game g) {
        game = g;
        selected = 0;
        showMenu = false;
        try {
            
        } catch(Exception E) {

        }
    }

    
    public void paint(Graphics g) {}
    private void drawMenu(Graphics2D g) {}
    private void drawHeader(Graphics2D g) {
        g.setColor();
        g.fillRect(0, 0, getWidth(), 30);
        g.setColor();
        g.drawString("Round: " + game.getRound);
    }
    private void drawBoard(Graphics2D g) {}
    private void drawCard(Graphics2D g) {
        g.drawImage(game.getCurrentCard().getImage(), 0, 0, 50, 50, null);
    }
    private void drawScoreboard(Graphics2D g) {}
    private void drawPot(Graphics2D g) {}
    private void drawShop(Graphics2D g) {}
    private void drawMessage(Graphics2D g) {}
    private void drawGameOver(Graphics2D g) {}
    private Color getColor(String name) {}
    private void handleKey(int keyCode) {}
    private void moveSelection(int amount) {}
}