import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
public class QuacksBrewPanel extends JPanel implements MouseListener {
    private Game game;
    private int selected;
    private BufferedImage background, board, rPot, bPot, gPot, yPot;
    private BufferedImage rDrop, bDrop, gDrop, yDrop, busted;
    public QuacksBrewPanel(Game game) {
        this.game = game;
        try {
            background = ImageIO.read(QuacksBrewPanel.class.getResource("/Image/Background.png"));
            board = ImageIO.read(QuacksBrewPanel.class.getResource("/Image/VictoryBoard.png"));
            rPot = ImageIO.read(QuacksBrewPanel.class.getResource("/Image/RedPot.png"));
            bPot = ImageIO.read(QuacksBrewPanel.class.getResource("/Image/BluePot.png"));
            gPot = ImageIO.read(QuacksBrewPanel.class.getResource("/Image/GreenPot.png"));
            yPot = ImageIO.read(QuacksBrewPanel.class.getResource("/Image/YellowPot.png"));
            rDrop = ImageIO.read(QuacksBrewPanel.class.getResource("/Image/rDrop.png"));
            bDrop = ImageIO.read(QuacksBrewPanel.class.getResource("/Image/bDrop.png"));
            gDrop = ImageIO.read(QuacksBrewPanel.class.getResource("/Image/gDrop.png"));
            yDrop = ImageIO.read(QuacksBrewPanel.class.getResource("/Image/yDrop.png"));
            busted = ImageIO.read(QuacksBrewPanel.class.getResource("/Image/busted.png"));
        } catch (Exception e) {
            System.out.println("Error");
        }
        addMouseListener(this);
    }
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        drawHeader(g);
        drawBoard(g);
        drawCard(g);
        drawPot(g);
        drawMessage(g);
        if (game.isBrewing()) {
            drawBrewButtons(g);
        }
        if (game.isChoosing()) {
            drawChoiceButtons(g);
        }
        if (game.isBuying()) {
            drawShop(g);
        }
        if (game.isGameOver()) {
            drawGameOver(g);
        }
    }
    private void drawHeader(Graphics g) {
        String phase;
        if (game.isGameOver()) {
            phase = "Game Over";
        } else if (game.isBrewing()) {
            phase = "Brewing";
        } else if (game.isChoosing()) {
            phase = "Pot Exploded!";
        } else if (game.isBuying()) {
            phase = "Buying";
        } else {
            phase = "Scoring";
        }
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, getWidth(), 40);
        g.setColor(Color.BLACK);
        g.setFont(new Font("arial", Font.BOLD, 14));
        g.drawString("Round: " + game.getRound() + "    Phase: " + phase + "    Dice Result: " + game.getDiceResult(), 20, 25);
    }
    private void drawBoard(Graphics g) {
        g.drawImage(board, 495, 290, 210, 160, null);
    }
    private void drawCard(Graphics g) {
        Card current = game.getCurrentCard();
        if (current != null) {
            g.drawImage(current.getCardImage(), 20, 50, 90, 140, null);
        }
    }
    private void drawBrewButtons(Graphics g) {
        g.setColor(new Color(40, 140, 60));
        g.fillRect(500, 470, 95, 35);
        g.setColor(Color.WHITE);
        g.drawRect(500, 470, 95, 35);
        g.drawString("Draw Chip", 516, 492);
        g.setColor(new Color(180, 50, 50));
        g.fillRect(605, 470, 95, 35);
        g.setColor(Color.WHITE);
        g.drawRect(605, 470, 95, 35);
        g.drawString("Stop", 638, 492);
    }
    private void drawChoiceButtons(Graphics g) {
        g.setColor(new Color(30, 20, 15, 235));
        g.fillRect(430, 220, 340, 130);
        g.setColor(Color.ORANGE);
        g.drawRect(430, 220, 340, 130);
        g.setColor(Color.WHITE);
        g.drawString("Pot Exploded! Choose Reward:", 505, 250);
        g.setColor(new Color(180, 130, 20));
        g.fillRect(455, 280, 130, 40);
        g.setColor(Color.WHITE);
        g.drawString("Keep Coins", 485, 305);
        g.setColor(new Color(50, 100, 180));
        g.fillRect(615, 280, 130, 40);
        g.setColor(Color.WHITE);
        g.drawString("Keep Points", 642, 305);
    }
    private void drawPot(Graphics g) {
        BufferedImage[] potImgs = {rPot, yPot, bPot, gPot};
        BufferedImage[] dropImgs = {rDrop, yDrop, bDrop, gDrop};
        int[][] coords = {{120, 50}, {690, 50}, {120, 410}, {690, 410}};
        List<Player> players = game.getPlayers();
        for (int i = 0; i < players.size() && i < 4; i++) {
            Player p = players.get(i);
            if (i == game.getCurrent()) {
                g.setColor(Color.RED);
                g.drawRect(coords[i][0] - 4, coords[i][1] - 4, 378, 308);
            }
            g.drawImage(potImgs[i], coords[i][0], coords[i][1], 370, 300, null);
            g.drawImage(dropImgs[i], coords[i][0] + 165, coords[i][1] + 125, 32, 28, null);
            List<Ingredient> potChips = p.getPot();
            for (int c = 0; c < potChips.size(); c++) {
                Ingredient chip = potChips.get(c);
                g.setColor(getColor(chip.getColor()));
                g.fillOval(coords[i][0] + 35 + (c % 8) * 38, coords[i][1] + 45 + (c / 8) * 38, 30, 30);
                g.setColor(Color.BLACK);
                g.drawOval(coords[i][0] + 35 + (c % 8) * 38, coords[i][1] + 45 + (c / 8) * 38, 30, 30);
                g.drawString(String.valueOf(chip.getValue()), coords[i][0] + 35 + (c % 8) * 38 + 11, coords[i][1] + 45 + (c / 8) * 38 + 20);
            }
            g.setColor(new Color(0, 0, 0, 190));
            g.fillRect(coords[i][0], coords[i][1] + 300, 370, 26);
            g.setColor(Color.WHITE);
            g.setFont(new Font("arial", Font.BOLD, 12));
            String status = "";
            if (p.isExploded()) {
                status = " [BUST]";
            }
            g.drawString(p.getName() + " | Bombs: " + p.getCherryBombTotal() + "/7 | Pts: " + p.getPoints() + " | Coins: " + p.getCoins() + status, coords[i][0] + 8, coords[i][1] + 317);
            if (p.isExploded()) {
                g.drawImage(busted, coords[i][0] + 85, coords[i][1] + 80, 200, 120, null);
            }
        }
    }
    private void drawShop(Graphics g) {
        g.setColor(new Color(30, 20, 15, 235));
        g.fillRect(430, 150, 340, 390);
        g.setColor(Color.ORANGE);
        g.drawRect(430, 150, 340, 390);
        g.setFont(new Font("arial", Font.BOLD, 16));
        g.drawString("Ingredient Shop (" + game.getCurrentPlayer().getName() + ")", 450, 180);
        List<Ingredient> shopItems = game.getShop();
        g.setFont(new Font("arial", Font.PLAIN, 13));
        for (int i = 0; i < shopItems.size(); i++) {
            Ingredient item = shopItems.get(i);
            if (i == selected) {
                g.setColor(new Color(255, 255, 255, 60));
                g.fillRect(440, 210 + i * 32 - 18, 320, 26);
            }
            g.setColor(getColor(item.getColor()));
            g.fillOval(448, 210 + i * 32 - 14, 18, 18);
            g.setColor(Color.WHITE);
            g.drawString(item.getName() + " (Val: " + item.getValue() + ") - " + item.getCost() + " coins", 475, 210 + i * 32);
        }
        g.setColor(new Color(40, 140, 60));
        g.fillRect(530, 495, 140, 32);
        g.setColor(Color.WHITE);
        g.drawRect(530, 495, 140, 32);
        g.drawString("Done Buying", 562, 516);
    }
    private void drawMessage(Graphics g) {
        List<String> msgs = game.getMessages();
        if (msgs != null && !msgs.isEmpty()) {
            g.setColor(new Color(0, 0, 0, 180));
            g.fillRect(0, getHeight() - 35, getWidth(), 35);
            g.setColor(Color.WHITE);
            g.drawString(msgs.get(msgs.size() - 1), 20, getHeight() - 12);
        }
    }
    private void drawGameOver(Graphics g) {
        g.setColor(new Color(0, 0, 0, 210));
        g.fillRect(300, 180, 600, 360);
        g.setColor(Color.YELLOW);
        g.drawRect(300, 180, 600, 360);
        g.setFont(new Font("arial", Font.BOLD, 28));
        g.drawString("GAME OVER!", 510, 230);
        List<Player> winners = game.getWinner();
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.BOLD, 20));
        int y = 275;
        for (int i = 0; i < winners.size(); i++) {
            g.drawString("Winner: " + winners.get(i).getName() + " (" + winners.get(i).getPoints() + " VP)", 450, y);
            y += 30;
        }
        g.setFont(new Font("arial", Font.PLAIN, 16));
        List<Player> players = game.getPlayers();
        for (int i = 0; i < players.size(); i++) {
            g.drawString(players.get(i).getName() + ": " + players.get(i).getPoints() + " Victory Points", 430, y + i * 35);
        }
    }
    private Color getColor(String name) {
        if (name.equals("white")) {
            return Color.WHITE;
        } else if (name.equals("orange")) {
            return Color.ORANGE;
        } else if (name.equals("green")) {
            return Color.GREEN;
        } else if (name.equals("blue")) {
            return Color.CYAN;
        } else if (name.equals("red")) {
            return Color.RED;
        } else if (name.equals("yellow")) {
            return Color.YELLOW;
        } else if (name.equals("purple")) {
            return new Color(160, 32, 240);
        } else if (name.equals("black")) {
            return Color.BLACK;
        }
        return Color.DARK_GRAY;
    }
    public void mousePressed(MouseEvent e) {
        if (game.isBrewing()) {
            if (e.getX() >= 500 && e.getX() <= 595 && e.getY() >= 470 && e.getY() <= 505) {
                game.drawAll();
            } else if (e.getX() >= 605 && e.getX() <= 700 && e.getY() >= 470 && e.getY() <= 505) {
                game.stopPlayer(0);
            }
        } else if (game.isChoosing()) {
            if (e.getX() >= 455 && e.getX() <= 585 && e.getY() >= 280 && e.getY() <= 320) {
                game.choose(true);
            } else if (e.getX() >= 615 && e.getX() <= 745 && e.getY() >= 280 && e.getY() <= 320) {
                game.choose(false);
            }
        } else if (game.isBuying()) {
            if (e.getX() >= 530 && e.getX() <= 670 && e.getY() >= 495 && e.getY() <= 527) {
                game.doneBuying();
                selected = 0;
            } else {
                List<Ingredient> shopItems = game.getShop();
                for (int i = 0; i < shopItems.size(); i++) {
                    if (e.getX() >= 440 && e.getX() <= 760 && e.getY() >= 210 + i * 32 - 18 && e.getY() <= 210 + i * 32 + 8) {
                        selected = i;
                        game.buy(shopItems.get(i));
                        break;
                    }
                }
            }
        }
        repaint();
    }
    public void mouseClicked(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}
