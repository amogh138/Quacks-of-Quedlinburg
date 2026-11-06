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
    private Color[] colors;
    private BufferedImage selectSet, numPlayers;
    public QuacksPanel() {
        try {
            selectSet = ImageIO.read(QuacksBrewPanel.class.getResource(""));
        } catch(Exception E) {

        }
    }
    public void paint(Graphics g) {}
    private void drawSelectSet(Graphics2D g) {}
    private void drawSelectNumPlayers(Graphics2D g) {}
    private void drawHeader(Graphics2D g) {}
    private void drawBoard(Graphics2D g) {}
    private void drawCard(Graphics2D g) {}
    private void drawScoreboard(Graphics2D g) {}
    private void drawPot(Graphics2D g) {}
    private void drawShop(Graphics2D g) {}
    private void drawMessage(Graphics2D g) {}
    private void drawGameOver(Graphics2D g) {}
    private Color getColor(String name) {}
    private void handleKey(int keyCode) {}
    private void moveSelection(int amount) {}
}