import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class QuacksExtraPanel extends JPanel implements MouseListener, KeyListener
{
	private BufferedImage selectSet, menu, rPg1, rPg2, rPg3, rPg4, rPg5, rPg6, rPg7, rPg8, pNum, busted;
	private String choice = "menu";
    public QuacksExtraPanel()
	{
		try
		{
        	selectSet = ImageIO.read(QuacksExtraPanel.class.getResource("SetSelect.png"));
            menu = ImageIO.read(QuacksExtraPanel.class.getResource("menu.png"));
            pNum = ImageIO.read(QuacksExtraPanel.class.getResource("PlayerNum.png"));
            busted = ImageIO.read(QuacksExtraPanel.class.getResource("Busted.png"));
            rPg1 = ImageIO.read(QuacksExtraPanel.class.getResource("RulesPg1.png"));
            rPg2 = ImageIO.read(QuacksExtraPanel.class.getResource("RulesPg2.png"));
            rPg3 = ImageIO.read(QuacksExtraPanel.class.getResource("RulesPg3.png"));
            rPg4 = ImageIO.read(QuacksExtraPanel.class.getResource("RulesPg4.png"));
            rPg5 = ImageIO.read(QuacksExtraPanel.class.getResource("RulesPg5.png"));
            rPg6 = ImageIO.read(QuacksExtraPanel.class.getResource("RulesPg6.png"));
            rPg7 = ImageIO.read(QuacksExtraPanel.class.getResource("RulesPg7.png"));
            rPg8 = ImageIO.read(QuacksExtraPanel.class.getResource("RulesPg8.png"));

		} catch(Exception E)
		{
			System.out.println("Exception Error");
            return;
        }
        addKeyListener(this);
        addMouseListener(this);
	}
	
	private BufferedImage c;
    private void drawScoreChart(Graphics g) {}
    private void drawSets(Graphics g) 
    {
        
    }
    private void drawMainMenu(Graphics g) 
    {
       super.paint(g);
        switch(choice)
        {
            case "menu":
                g.drawImage(menu, 0, 0, null);
                break;

            default:
                g.drawImage(menu, 0, 0, null);
        }
    }
    private void drawHowToPlay(Graphics g)
    {
        super.paint(g);
        switch(choice)
        {
            case "rPg1":
                g.drawImage(rPg1, 0, 0, null);
                break;

            case "rPg2":
                g.drawImage(rPg2, 0, 0, null);
                break;

            case "rPg3":
                g.drawImage(rPg3, 0, 0, null);
                break;

            case "rPg4":
                g.drawImage(rPg4, 0, 0, null);
                break;

            case "rPg5":
                g.drawImage(rPg5, 0, 0, null);
                break;

            case "rPg6":
                g.drawImage(rPg6, 0, 0, null);
                break;

            case "rPg7":
                g.drawImage(rPg7, 0, 0, null);
                break;

            case "rPg8":
                g.drawImage(rPg8, 0, 0, null);
                break;

            default:
                g.drawImage(menu, 0, 0, null);
        }
    }
    private void drawPlayerNumSelect(Graphics g)
    {
        super.paint(g);
        switch(choice)
        {
            case "rPg1":
                g.drawImage(rPg1, 0, 0, null);
                break;

            default:
                g.drawImage(menu, 0, 0, null);
        }
    }
    private void drawSetSelect(Graphics g)
    {
        super.paint(g);
        switch(choice)
        {
            case "setSelect":
                g.drawImage(selectSet, 0, 0, null);
                break;
            default:
                g.drawImage(menu, 0, 0, null);
        }
    }

    private void drawScoreboard(Graphics2D g)
    {
        //kk
    }

    private void drawBusted(Graphics g)
    {
        super.paint(g);
        switch(choice)
        {
            case "busted":
                g.drawImage(busted, 0, 0, null);
                break;
            default:
                g.drawImage(menu, 0, 0, null);
        }
    }

	public void keyPressed(KeyEvent e) {}

	public void keyReleased(KeyEvent e) {}

	public void keyTyped(KeyEvent e) {}

	public void mouseClicked(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}
}