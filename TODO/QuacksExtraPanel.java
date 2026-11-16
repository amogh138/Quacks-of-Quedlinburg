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
    public void paint(Graphics g)
    {
        
    }
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
