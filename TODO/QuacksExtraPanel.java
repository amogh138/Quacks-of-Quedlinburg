import java.awt.*;
import java.awt.image.*;
import java.awt.imageio.ImageIO;
import java.io.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class QuacksExtraPanel extends JPanel implements MouseListener, KeyListener{
    private BufferedImage c;
    private BufferedImage menu, setSelect;
    public void QuackExtraPanel()
    {
        try
        {
            menu = ImageIO.read(new File("MainMenu.png"));
            setSelect = ImageIO.read(new File("SetSelect.png"));
        }
        catch(Exception E)
        {
            System.out.println("Exception Error");
            return;
        }
    }
    public void paint(Graphics g)
    {
        super.paint(g);
		
    }
    private void drawScoreChart(Graphics g) {}
    private void drawSets(Graphics g) {}
    private void drawMainMenu(Graphics g) 
    {
        super.paint(g);
        
    }
    private void drawHowToPlay(Graphics g) {}
    private void drawPlayerNumSelect(Graphics g) {}
    private void drawSetSelect(Graphics g) {}
}