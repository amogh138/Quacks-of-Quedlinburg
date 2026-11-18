import java.awt.*;
import java.awt.image.*;
<<<<<<< HEAD
import java.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.awt.event.MouseListener;
import java.awt.even.MouseEvent;
=======
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
>>>>>>> 3f97afe15f6fb098a1582d711dd69a1d6efc152c

public class QuacksBrewPanel extends JPanel implements MouseListener {
    private Game game;
    private int selected;
    private boolean showMenu;
    private BufferedImage menu, board, card, scoreboard, pot, shop;
    private Color[] colors;
<<<<<<< HEAD
    public QuacksPanel(Game g) {
        game = g;
        selected = 0;
        showMenu = false;
        try {
            
=======
    private BufferedImage selectSet, numPlayers;
    public QuacksBrewPanel() {
        try {
            selectSet = ImageIO.read(QuacksBrewPanel.class.getResource("/Image/Sets.png"));
            numPlayers = ImageIO.read(QuacksBrewPanel.class.getResource("/Image/"));
>>>>>>> 3f97afe15f6fb098a1582d711dd69a1d6efc152c
        } catch(Exception E) {

        }
    }
<<<<<<< HEAD

    
    public void paint(Graphics g) {}
    private void drawMenu(Graphics2D g) {}
    private void drawHeader(Graphics2D g) {
        g.setColor();
        g.fillRect(0, 0, getWidth(), 30);
        g.setColor();
        g.drawString("Round: " + game.getRound);
=======
    public void paint(Graphics g) {
        super.paint(g);
    }
    private void drawSelectSet(Graphics2D g) {
        g.drawImage(selectSet, 0, getHeight(), 0, getWidth(), null);
    }
    private void drawSelectNumPlayers(Graphics2D g) {}
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

>>>>>>> 3f97afe15f6fb098a1582d711dd69a1d6efc152c
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

    public void mouseClicked(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}