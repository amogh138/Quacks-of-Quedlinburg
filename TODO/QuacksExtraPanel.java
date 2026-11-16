import java.awt.*;
import java.awt.image.*;
import java.awt.imageio.ImageIO;
import java.io.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class QuacksExtraPanel extends JPanel implements MouseListener, KeyListener
{
	private BufferedImage 
	private String choice = "menu";
    public QuacksExtraPanel()
	{
		try
		{
        	selectSet = ImageIO.read(QuacksExtraPanel.class.getResource("SetSelect.png"));
            menu = ImageIO.read(QuacksExtraPanel.class.getResource("menu.png"));
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
            switch (choice)
            {
            case "menu":
                g.drawImage(menu, 0, 0, null);
                break;
            default:
                g.drawImage(menu, 0, 0, null);
            }
        }
    }
    private void drawHowToPlay(Graphics g)
    {
        super.paint(g);
        switch(choice)
        {
            switch (choice)
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
    }
    private void drawPlayerNumSelect(Graphics g) {}
    private void drawSetSelect(Graphics g)
    {
        super.paint(g);
        switch(choice)
        {
            switch (choice) 
            {
            case "setSelect":
                g.drawImage(selectSet, 0, 0, null);
                break;
            default:
                g.drawImage(menu, 0, 0, null);
            }
        }
    }
}
