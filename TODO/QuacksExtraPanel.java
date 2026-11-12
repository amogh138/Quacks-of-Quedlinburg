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
	private int choice;
    public QuacksExtraPanel()
	{
		try
			{
        		selectSet = ImageIO.read(QuacksExtraPanel.class.getResource(""));
			} catch(Exception E)
			{
				//;lmasc;lm
        	}
	}
	
	private BufferedImage c;
    private void drawScoreChart(Graphics g) {}
    private void drawSets(Graphics g) {}
    private void drawMainMenu(Graphics g) 
    {
        super.paint(g);
        try
        {
            //c = 
        }
        catch(Exception E)
        {

        }
    }
    private void drawHowToPlay(Graphics g) {}
    private void drawPlayerNumSelect(Graphics g) {}
    private void drawSetSelect(Graphics g) {}
}
