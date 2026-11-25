import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;

public class QuacksExtraPanel extends JPanel implements MouseListener, KeyListener
{
    private BufferedImage selectSet, menu, rPg1, rPg2, rPg3, rPg4, rPg5, rPg6, rPg7, rPg8, pNum, busted, fCard, vBoard, yDrop, gDrop, rDrop, bDrop;
    private BufferedImage q1, q2, q3, q4, locked, board, gMode, fMarker;
    private BufferedImage bPhase21, bPhase22, bPhase23, bPhase24, bPhase31, bPhase32, bPhase33, bPhase34;
    private BufferedImage bFCard1, bFCard2, bFCard3, bFCard4, bFCard5, bFCard6, bFCard7, bFCard8, bFCard9;
    private BufferedImage pFCard1, pFCard2, pFCard3, pFCard4, pFCard5, pFCard6, pFCard7, pFCard8, pFCard9;
    private int[] coins = new int[4];
    private int[] rubies = new int[4];
    private int[] vbSpot = new int[4];
    
    private int selectedSet;
    private String m;
    private int numPlayers;
    private boolean hotSeat;
    private int cBuyer = 0;
    
    private String choice = "menu";
    private BufferedImage activeDrawnCard = null; //current card displayed
    
    private ArrayList<BufferedImage> fortuneDeck = new ArrayList<>();

    public QuacksExtraPanel()
    {
        try
        {
            board = ImageIO.read(QuacksExtraPanel.class.getResource("Image/Background.png"));
            fMarker = ImageIO.read(QuacksExtraPanel.class.getResource("Image/FlameMarker.png"));
            bPhase24 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/buyPhase.png"));
            bPhase23 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/buyPhase23.png"));
            bPhase22 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/buyPhase22.png"));
            bPhase21 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/buyPhase21.png"));
            bPhase34 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/buyPhase34.png"));
            bPhase33 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/buyPhase33.png"));
            bPhase32 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/buyPhase32.png"));
            bPhase31 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/buyPhase31.png"));
            gMode = ImageIO.read(QuacksExtraPanel.class.getResource("Image/GameMode.png"));
            selectSet = ImageIO.read(QuacksExtraPanel.class.getResource("Image/SetSelect.png"));
            menu = ImageIO.read(QuacksExtraPanel.class.getResource("Image/MainMenu.png"));
            pNum = ImageIO.read(QuacksExtraPanel.class.getResource("Image/PlayerNum.png"));
            busted = ImageIO.read(QuacksExtraPanel.class.getResource("Image/Busted.png"));
            fCard = ImageIO.read(QuacksExtraPanel.class.getResource("Image/FortuneCard.png"));
            vBoard = ImageIO.read(QuacksExtraPanel.class.getResource("Image/VictoryBoard.png"));
            yDrop = ImageIO.read(QuacksExtraPanel.class.getResource("Image/yDrop.png"));
            rDrop = ImageIO.read(QuacksExtraPanel.class.getResource("Image/rDrop.png"));
            bDrop = ImageIO.read(QuacksExtraPanel.class.getResource("Image/bDrop.png"));
            gDrop = ImageIO.read(QuacksExtraPanel.class.getResource("Image/gDrop.png"));
            locked = ImageIO.read(QuacksExtraPanel.class.getResource("Image/locked.png"));
            q1 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/quack1.png"));
            q2 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/quack2.png"));
            q3 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/quack3.png"));
            q4 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/quack4.png"));
            rPg1 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/RulesPg1.png"));
            rPg2 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/RulesPg2.png"));
            rPg3 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/RulesPg3.png"));
            rPg4 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/RulesPg4.png"));
            rPg5 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/RulesPg5.png"));
            rPg6 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/RulesPg6.png"));
            rPg7 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/RulesPg7.png"));
            rPg8 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/RulesPg8.png"));
            
            bFCard1 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/BlueCard1.png"));
            bFCard2 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/BlueCard2.png"));
            bFCard3 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/BlueCard3.png"));
            bFCard4 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/BlueCard4.png"));
            bFCard5 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/BlueCard5.png"));
            bFCard6 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/BlueCard6.png"));
            bFCard7 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/BlueCard7.png"));
            bFCard8 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/BlueCard8.png"));
            bFCard9 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/BlueCard9.png"));
            
            pFCard1 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/PinkCard1.png"));
            pFCard2 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/PinkCard2.png"));
            pFCard3 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/PinkCard3.png"));
            pFCard4 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/PinkCard4.png"));
            pFCard5 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/PinkCard5.png"));
            pFCard6 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/PinkCard6.png"));
            pFCard7 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/PinkCard7.png"));
            pFCard8 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/PinkCard8.png"));
            pFCard9 = ImageIO.read(QuacksExtraPanel.class.getResource("Image/PinkCard9.png"));

        } catch(Exception E)
        {
            System.out.println("Exception Error loading images: " + E.getMessage());
            return;
        }
        
        setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);
    }
    
    // Method to initialize and shuffle the deck
    private void resetFortuneDeck() 
    {
        fortuneDeck.clear();
        if (bFCard1 != null) fortuneDeck.add(bFCard1);
        if (bFCard2 != null) fortuneDeck.add(bFCard2);
        if (bFCard3 != null) fortuneDeck.add(bFCard3);
        if (bFCard4 != null) fortuneDeck.add(bFCard4);
        if (bFCard5 != null) fortuneDeck.add(bFCard5);
        if (bFCard6 != null) fortuneDeck.add(bFCard6);
        if (bFCard7 != null) fortuneDeck.add(bFCard7);
        if (bFCard8 != null) fortuneDeck.add(bFCard8);
        if (bFCard9 != null) fortuneDeck.add(bFCard9);
        
        if (pFCard1 != null) fortuneDeck.add(pFCard1);
        if (pFCard2 != null) fortuneDeck.add(pFCard2);
        if (pFCard3 != null) fortuneDeck.add(pFCard3);
        if (pFCard4 != null) fortuneDeck.add(pFCard4);
        if (pFCard5 != null) fortuneDeck.add(pFCard5);
        if (pFCard6 != null) fortuneDeck.add(pFCard6);
        if (pFCard7 != null) fortuneDeck.add(pFCard7);
        if (pFCard8 != null) fortuneDeck.add(pFCard8);
        if (pFCard9 != null) fortuneDeck.add(pFCard9);
        
        Collections.shuffle(fortuneDeck);
        activeDrawnCard = null; // Reset current card view
    }
    
    public void paint(Graphics g) 
    {
        super.paint(g);
        
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        
        //Draw the base Background image
        if (board != null)
        {
            g.drawImage(board, 0, 0, panelWidth, panelHeight, null);
        }
        
        //Render based on current state choice
        if (choice.equals("menu")) 
        {
            if (menu != null)
            {
                int targetHeight = 820;
                double aspectRatio = (double) menu.getWidth() / menu.getHeight();
                int targetWidth = (int) (targetHeight * aspectRatio);
                int x = (panelWidth - targetWidth) / 2;
                int y = (panelHeight - targetHeight) / 2;
                g.drawImage(menu, x, y, targetWidth, targetHeight, null);
            }
        } 
        else if (choice.equals("setSelect")) 
        {
            if (selectSet != null)
            {
                g.drawImage(selectSet, 0, 0, panelWidth, panelHeight, null);
            }
        } 
        else if (choice.equals("pNum")) 
        {
            if (pNum != null)
            {
                g.drawImage(pNum, 0, 0, panelWidth, panelHeight, null);
            }
        } 
        else if (choice.equals("gMode")) 
        {
            if (gMode != null)
            {
                g.drawImage(gMode, 0, 0, panelWidth, panelHeight, null);
            }
        }
        else if (choice.equals("fCard")) 
        {
            if (fCard != null)
            {
                g.drawImage(fCard, 0, 0, panelWidth, panelHeight, null);
            }
            
            //drawn card
            if (activeDrawnCard != null)
            {
                g.drawImage(activeDrawnCard, 1006, 121, 408, 733, null);
                
                g.setColor(new Color(30, 100, 200)); // Custon Blue🥶
                g.fillRect(632, 613, 247, 75);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 22));
                g.drawString("Start brewing", 680, 660);
            }
        } 
        else if (choice.equals("bPhase24")) 
        {
            if (bPhase24 != null)
            {
                g.drawImage(bPhase24, 0, 0, panelWidth, panelHeight, null);
            }
        }
        else if (choice.equals("bPhase23")) 
        {
            if (bPhase23 != null)
            {
                g.drawImage(bPhase23, 0, 0, panelWidth, panelHeight, null);
            }
        }
        else if (choice.equals("bPhase22")) 
        {
            if (bPhase22 != null)
            {
                g.drawImage(bPhase22, 0, 0, panelWidth, panelHeight, null);
            }
        }
        else if (choice.equals("bPhase21")) 
        {
            if (bPhase21 != null)
            {
                g.drawImage(bPhase21, 0, 0, panelWidth, panelHeight, null);
            }
        }
        else if (choice.equals("bPhase34")) 
        {
            if (bPhase34 != null)
            {
                g.drawImage(bPhase34, 0, 0, panelWidth, panelHeight, null);
            }
        }
        else if (choice.equals("bPhase33")) 
        {
            if (bPhase33 != null)
            {
                g.drawImage(bPhase33, 0, 0, panelWidth, panelHeight, null);
            }
        }
        else if (choice.equals("bPhase32")) 
        {
            if (bPhase32 != null)
            {
                g.drawImage(bPhase32, 0, 0, panelWidth, panelHeight, null);
            }
        }
        else if (choice.equals("bPhase31")) 
        {
            if (bPhase31 != null)
            {
                g.drawImage(bPhase31, 0, 0, panelWidth, panelHeight, null);
            }
        }
        else if (choice.equals("busted")) 
        {
            if (busted != null) 
            {
                g.drawImage(busted, 0, 0, panelWidth, panelHeight, null);
            }
        } 
        else if (choice.equals("vBoard")) 
        {
            if (vBoard != null) 
            {
                g.drawImage(vBoard, 0, 0, panelWidth, panelHeight, null);
            }
        } 
        else if (choice.startsWith("rPg")) 
        {
            BufferedImage ruleImg = getRuleImage(choice);
            if (ruleImg != null) 
            {
                int targetHeight = 850;
                double aspectRatio = (double) ruleImg.getWidth() / ruleImg.getHeight();
                int targetWidth = (int) (targetHeight * aspectRatio);
                int x = (panelWidth - targetWidth) / 2;
                int y = (panelHeight - targetHeight) / 2;
                g.drawImage(ruleImg, x, y, targetWidth, targetHeight, null);
            }
        }

        //Brown "Back" button on all screens except the main menu
        if (!choice.equals("menu")) 
        {
            g.setColor(new Color(120, 66, 18)); // Brown color
            g.fillRect(30, 30, 100, 50);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString("← Back", 48, 62);
        }
        
      //Green "Next" button on buying phase
        if(choice.contains("bPhase") && cBuyer == 0)
        {
        	g.setColor(Color.GREEN);
            g.fillRect(282+50, 838, 100, 50);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString("Start", 282+78, 838+32);
        }
        if(choice.contains("bPhase") && cBuyer > 0 && cBuyer < 4)
        {
        	if(cBuyer == 1)
        	{
        		int xSpot = 78;
                int ySpot = 108;
                int width = 578;
                int height = 125;
                int thickness = 10;

                g.setColor(Color.WHITE);

                for (int i = 0; i < thickness; i++) 
                {
                    g.drawRect(xSpot + i, ySpot + i, width - (i * 2), height - (i * 2));
                }
                g.setColor(Color.GREEN);
                g.fillRect(282+50, 838, 100, 50);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 18));
                g.drawString("Next", 282+78, 838+32);
        	}
        	if(cBuyer == 2)
        	{
        		int xSpot = 78;
                int ySpot = 108+173;
                int width = 578;
                int height = 125;
                int thickness = 10;

                g.setColor(Color.WHITE);

                for (int i = 0; i < thickness; i++) 
                {
                    g.drawRect(xSpot + i, ySpot + i, width - (i * 2), height - (i * 2));
                }
                g.setColor(Color.GREEN);
                g.fillRect(282+50, 838, 100, 50);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 18));
                g.drawString("Next", 282+78, 838+32);
        	}
        	if(cBuyer == 3)
        	{
        		int xSpot = 78;
                int ySpot = 108+173+203;
                int width = 578;
                int height = 125;
                int thickness = 10;

                g.setColor(Color.WHITE);

                for (int i = 0; i < thickness; i++) 
                {
                    g.drawRect(xSpot + i, ySpot + i, width - (i * 2), height - (i * 2));
                }
                g.setColor(Color.GREEN);
                g.fillRect(282+50, 838, 100, 50);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 18));
                g.drawString("Next", 282+78, 838+32);
        	}
        }
        if(choice.contains("bPhase") && cBuyer == 4)
        {
        	int xSpot = 78;
            int ySpot = 740-37-20;
            int width = 578;
            int height = 125;
            int thickness = 10;

            g.setColor(Color.WHITE);

            for (int i = 0; i < thickness; i++) 
            {
                g.drawRect(xSpot + i, ySpot + i, width - (i * 2), height - (i * 2));
            }
        	g.setColor(Color.GREEN);
            g.fillRect(282+50, 838, 100, 50);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString("Continue", 282+63, 838+32);
        }
        if(choice.equals("vBoard"))
        {
        	g.setColor(Color.GREEN);
            g.fillRect(getWidth()/2-50, 866, 150, 50);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString("Move Drop", getWidth()/2-50+23, 866+32);//(496,866)
        }
    }

    //get rule pages
    private BufferedImage getRuleImage(String page) 
    {
        switch(page)
        {
            case "rPg1": return rPg1;
            case "rPg2": return rPg2;
            case "rPg3": return rPg3;
            case "rPg4": return rPg4;
            case "rPg5": return rPg5;
            case "rPg6": return rPg6;
            case "rPg7": return rPg7;
            case "rPg8": return rPg8;
            default: return rPg1;
        }
    }

    public void mousePressed(MouseEvent e) 
    {
        int x = e.getX();
        int y = e.getY();
        
        System.out.println("Clicked -> X: " + x + ", Y: " + y);

        //BACK BUTTON
        if (!choice.equals("menu") && x >= 30 && x <= 130 && y >= 30 && y <= 80) 
        {
            if (choice.equals("setSelect"))
            {
                choice = "menu";
            } else if (choice.equals("pNum")) 
            {
                choice = "setSelect";
            } else if (choice.equals("gMode")) 
            {
                choice = "pNum";
            }  else if (choice.equals("bPhase24")) 
            {
                choice = "fCard";
            }  else if (choice.equals("bPhase23")) 
            {
                choice = "fCard";
            }  else if (choice.equals("bPhase22")) 
            {
                choice = "fCard";
            }  else if (choice.equals("bPhase21")) 
            {
                choice = "fCard";
            }  else if (choice.equals("bPhase34")) 
            {
                choice = "fCard";
            }  else if (choice.equals("bPhase33")) 
            {
                choice = "fCard";
            }  else if (choice.equals("bPhase32")) 
            {
                choice = "fCard";
            }  else if (choice.equals("bPhase31")) 
            {
                choice = "fCard";
            }
            else if (choice.startsWith("rPg")) 
            {
                if (choice.equals("rPg1")) 
                {
                    choice = "menu";
                } else
                {
                    int pageNum = Integer.parseInt(choice.substring(3)) - 1;
                    choice = "rPg" + pageNum;
                }
            } else if (choice.equals("busted")) 
            {
                choice = "fCard";
            }else if (choice.equals("fCard"))
            {
            	choice = "gMode";
            }
            else if(choice.equals("vBoard"))
            {
            	choice = m;
            }
            repaint();
            return;
        }

        //MAIN MENU SCREEN
        if (choice.equals("menu")) 
        {
            if (x >= 590 && x <= 875 && y >= 350 && y <= 419) 
            {
                choice = "setSelect";
            } 
            else if (x >= 587 && x <= 878 && y >= 434 && y <= 504) 
            {
                choice = "rPg1";
            }
        } 
        //SET SELECT SCREEN -> leads to Player Number Select
        else if (choice.equals("setSelect")) 
        {
            if (x >= 159 && x <= 633 && y >= 156 && y <= 437)
            {
                selectedSet = 1;
                choice = "pNum";
                System.out.println("Set number: "+ selectedSet);
            }
            else if (x >= 826 && x <= 1296 && y >= 310 && y <= 583)
            {
                selectedSet = 2;
                choice = "pNum";
                System.out.println("Set number: "+ selectedSet);
            }
            else if (x >= 175 && x <= 611 && y >= 505 && y <= 791)
            {
                selectedSet = 3;
                choice = "pNum";
                System.out.println("Set number: "+ selectedSet);
            }
            else if (x >= 795 && x <= 1258 && y >= 627 && y <= 903)
            {
                selectedSet = 4;
                choice = "pNum";
                System.out.println("Set number: "+ selectedSet);
            }
        }
        //PLAYER NUMBER SELECT SCREEN -> leads to Fortune Card screen & resets deck
        else if (choice.equals("pNum"))
        {
            if (x >= 171 && x <= 533 && y >= 155 && y <= 511)
            {
                numPlayers = 1;
                choice = "gMode";
                resetFortuneDeck();
                System.out.println("Number of Players: " + numPlayers);
            }
            else if (x >= 116 && x <= 597 && y >= 533 && y <= 909)
            {
                numPlayers = 2;
                choice = "gMode";
                resetFortuneDeck();
                System.out.println("Number of Players: " + numPlayers);
            }
            else if (x >= 836 && x <= 1375 && y >= 142 && y <= 530)
            {
                numPlayers = 3;
                choice = "gMode";
                resetFortuneDeck();
                System.out.println("Number of Players: " + numPlayers);
            }
            else if (x >= 839 && x <= 1331 && y >= 547 && y <= 912)
            {
                numPlayers = 4;
                choice = "gMode";
                resetFortuneDeck();
                System.out.println("Number of Players: " + numPlayers);
            }
        }
        else if(choice.equals("gMode"))
        {
        	if(x >= 179 && x <= 719 && y >= 521 && y <= 757)
        	{
        		hotSeat = true;
        		choice = "fCard";
        	}
        	else if(x >= 100 && x <= 1541 && y >= 521 && y <= 756)
        	{
        		hotSeat = false;
        		choice = "fCard";
        	}
        }
        else if(choice.equals("bPhase24"))
        {
        	if(x >= 179 && x <= 719 && y >= 521 && y <= 757)
        	{
        		if(cBuyer == 1)
        		{
        			cBuyer = 2;
        		}
        		else if(cBuyer == 2)
        		{
        			cBuyer = 3;
        		}
        		else if(cBuyer == 3)
        		{
        			cBuyer = 4;
        		}
        		else if(cBuyer == 4)
        		{
        			
        		}
        	}
        	else if(x >= 100 && x <= 1541 && y >= 521 && y <= 756)
        	{
        		hotSeat = false;
        		choice = "fCard";
        	}
        }
        //FORTUNE CARD SCREEN -> Draw Card & Start brewing
        else if (choice.equals("fCard")) 
        {
            if (activeDrawnCard == null && x >= 581 && x <= 929 && y >= 410 && y <= 575) 
            {
                if (!fortuneDeck.isEmpty()) 
                {
                    activeDrawnCard = fortuneDeck.remove(0); // Take the top unique card without repeating
                } else 
                {
                    System.out.println("All fortune cards have been drawn!");
                }
            }
            // Start Brewing Button -> CURRENTLY LEADS TO BUY PHASE
            else if (activeDrawnCard != null && x >= 632 && x <= 879 && y >= 613 && y <= 688) 
            {
                System.out.println("Start brewing clicked! Moving to brewing phase.");
                if(numPlayers <= 2)
                {
                	if(selectedSet == 4)
                	{
                		choice = m = "bPhase24";
                		System.out.println("1-2 Players, Set4");
                	}
                	if(selectedSet == 3)
                	{
                		choice = m = "bPhase23";
                		System.out.println("1-2 Players, Set3");
                	}
                	if(selectedSet == 2)
                	{
                		choice = m = "bPhase22";
                		System.out.println("1-2 Players, Set2");
                	}
                	if(selectedSet == 1)
                	{
                		choice = m = "bPhase21";
                		System.out.println("1-2 Players, Set1");
                	}
                }
                else if(numPlayers > 2)
                {
                	if(selectedSet == 4)
                	{
                		choice = m = "bPhase34";
                		System.out.println("3-4 Players, Set4");
                	}
                	if(selectedSet == 3)
                	{
                		choice = m = "bPhase33";
                		System.out.println("3-4 Players, Set3");
                	}
                	if(selectedSet == 2)
                	{
                		choice = m = "bPhase32";
                		System.out.println("3-4 Players, Set2");
                	}
                	if(selectedSet == 1)
                	{
                		choice = m = "bPhase31";
                		System.out.println("3-4 Players, Set1");
                	}
                }
            }
        }
        //BUYING PHASE
        else if(choice.contains("bPhase") && x >=282+50 && y>= 838 && x<= 282+50+100 && y <= 838+50)
        {
        	if(cBuyer < 4)
        	{
        		cBuyer++;
        	}
        	else if(cBuyer == 4)
        	{
        		choice = "vBoard";
        	}
        }
        
        //RULES PAGES NAVIGATION
        else if (choice.startsWith("rPg")) 
        {
            int pageNum = Integer.parseInt(choice.substring(3));
            // Next button
            if (x >= 753 && x <= 821 && y >= 859 && y <= 890) 
            {
                if (pageNum < 8)
                {
                    choice = "rPg" + (pageNum + 1);
                }
            } //back button
            else if (pageNum > 1 && x >= 693 && x <= 762 && y >= 858 && y <= 890) 
            {
                choice = "rPg" + (pageNum - 1);
            }
        }

        repaint();
    }

    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}

    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}