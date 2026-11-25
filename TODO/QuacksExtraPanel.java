import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

//MAKE SURE TO ADD THE LOGIC TO BREW PANEL SO THAT WHENEVER THE PLAYER GETS A CERTAIN NUMBER OF POINTS ADDED, THEIR TOTAL POINTS GET SUBTRACTED BY
//WHAT THEY HAD BEFORE ADDING NEW POINTS. POINTS ARE NOT CUMULATIVE AND ONLY THE POINTS EARNED IN THAT ROUND ARE ADDED TO THE TOTAL POINTS.
public class QuacksExtraPanel extends JPanel implements MouseListener
{
    private BufferedImage selectSet, menu, rPg1, rPg2, rPg3, rPg4, rPg5, rPg6, rPg7, rPg8, pNum, busted, fCard, vBoard, yDrop, gDrop, rDrop, bDrop;
    private BufferedImage q1, q2, q3, q4, locked, board, gMode, fMarker, winner;
    private BufferedImage bPhase21, bPhase22, bPhase23, bPhase24, bPhase31, bPhase32, bPhase33, bPhase34;
    private BufferedImage bFCard1, bFCard2, bFCard3, bFCard4, bFCard5, bFCard6, bFCard7, bFCard8, bFCard9;
    private BufferedImage pFCard1, pFCard2, pFCard3, pFCard4, pFCard5, pFCard6, pFCard7, pFCard8, pFCard9;

    private Game game;
    Player p1, p2, p3, p4;
    IngredientSet startSet;

    private int selectedSet;
    private String m;
    private int numPlayers;
    private boolean hotSeat;
    private int cPlay;
    private int gDSpot; //current green drop position on victory board
    private int rDSpot; //current red drop position on victory board
    private int bDSpot; //current blue drop position on victory board
    private int yDSpot; //current yellow drop position on victory board
    
    private String choice = "menu";
    private String cMover = "gDrop"; //current drop being moved on victory board
    private BufferedImage activeDrawnCard = null; //current card displayed
    
    private ArrayList<BufferedImage> fortuneDeck = new ArrayList<>();

    public QuacksExtraPanel()
    {
        try
        {
            board = ImageIO.read(QuacksExtraPanel.class.getResource("Image/Background.png"));
            winner = ImageIO.read(QuacksExtraPanel.class.getResource("Image/Winner.png"));
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
        else if (choice.equals("busted")) 
        {
            if (busted != null) 
            {
                g.drawImage(busted, 0, 0, panelWidth, panelHeight, null);
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
                if(game.getRound() == 1)
                {
                    g.drawImage(vBoard, 0, 0, panelWidth, panelHeight, null);
                    g.drawImage(gDrop, 272, 577, 63, 53, null);
                    g.drawImage(yDrop, 382, 663, 64, 53, null);
                    g.drawImage(rDrop, 384, 567, 63, 55, null);
                    g.drawImage(bDrop, 266, 663, 62, 50, null);
                    g.drawImage(fMarker, 419, 214, 45, 79, null);
                }
                if(game.getRound() == 2)
                {
                    g.drawImage(vBoard, 0, 0, panelWidth, panelHeight, null);
                    g.drawImage(fMarker, 1013/2, 683/2, 45, 79, null);
                }
                if(game.getRound() == 3)
                {
                    g.drawImage(vBoard, 0, 0, panelWidth, panelHeight, null);
                    g.drawImage(fMarker, 1208/2, 441/2, 45, 79, null);
                }
                if(game.getRound() == 4)
                {
                    g.drawImage(vBoard, 0, 0, panelWidth, panelHeight, null);
                    g.drawImage(fMarker, 1377/2, 678/2, 45, 79, null);
                }
                if(game.getRound() == 5)
                {
                    g.drawImage(vBoard, 0, 0, panelWidth, panelHeight, null);
                    g.drawImage(fMarker, 1553/2, 448/2, 45, 79, null);
                }
                if(game.getRound() == 6)
                {
                    g.drawImage(vBoard, 0, 0, panelWidth, panelHeight, null);
                    g.drawImage(fMarker, 1731/2, 677/2, 45, 79, null);
                }
                if(game.getRound() == 7)
                {
                    g.drawImage(vBoard, 0, 0, panelWidth, panelHeight, null);
                    g.drawImage(fMarker, 1906/2, 439/2, 45, 79, null);
                }
                if(game.getRound() == 8)
                {
                    g.drawImage(vBoard, 0, 0, panelWidth, panelHeight, null);
                    g.drawImage(fMarker, 2084/2, 682/2, 45, 79, null);
                }
                if(game.getRound() == 9)
                {
                    g.drawImage(vBoard, 0, 0, panelWidth, panelHeight, null);
                    g.drawImage(fMarker, 2260/2, 440/2, 45, 79, null);
                }
            }
            
            if(cMover.equals("gDrop"))
            {
                if(gDSpot == 1){g.drawImage(gDrop, 87, 772, 48, 40, null);}
                else if(gDSpot ==2) {g.drawImage(gDrop, 208/2, 1368/2, 48, 40, null);}
                else if(gDSpot ==3) {g.drawImage(gDrop, 320/2, 1242/2, 48, 40, null);}
                else if(gDSpot ==4) {g.drawImage(gDrop, 241/2, 1113/2, 48, 40, null);}
                else if(gDSpot ==5) {g.drawImage(gDrop, 333/2, 985/2, 48, 40, null);}
                else if(gDSpot ==6) {g.drawImage(gDrop, 264/2, 877/2, 48, 40, null);}
                else if(gDSpot ==7) {g.drawImage(gDrop, 194, 382, 48, 40, null);}
                else if(gDSpot ==8) {g.drawImage(gDrop, 293/2, 648/2, 48, 40, null);}
                else if(gDSpot ==9) {g.drawImage(gDrop, 421/2, 540/2, 48, 40, null);}
                else if(gDSpot ==10) {g.drawImage(gDrop, 306/2, 425/2, 48, 40, null);}
                else if(gDSpot ==11) {g.drawImage(gDrop, 410/2, 313/2, 48, 40, null);}
                else if(gDSpot ==12) {g.drawImage(gDrop, 347/2, 206/2, 48, 40, null);}
                else if(gDSpot ==13) {g.drawImage(gDrop, 513/2, 194/2, 48, 40, null);}
                else if(gDSpot ==14) {g.drawImage(gDrop, 663/2, 196/2, 48, 40, null);}
                else if(gDSpot ==15) {g.drawImage(gDrop, 828/2, 200/2, 48, 40, null);}
                else if(gDSpot ==16) {g.drawImage(gDrop, 984/2, 206/2, 48, 40, null);}
                else if(gDSpot ==17) {g.drawImage(gDrop, 1137/2, 204/2, 48, 40, null);}
                else if(gDSpot ==18) {g.drawImage(gDrop, 1287/2, 197/2, 48, 40, null);}
                else if(gDSpot ==19) {g.drawImage(gDrop, 1460/2, 202/2, 48, 40, null);}
                else if(gDSpot ==20) {g.drawImage(gDrop, 1602/2, 197/2, 48, 40, null);}
                else if(gDSpot ==21) {g.drawImage(gDrop, 1783/2, 195/2, 48, 40, null);}
                else if(gDSpot ==22) {g.drawImage(gDrop, 1926/2, 200/2, 48, 40, null);}
                else if(gDSpot ==23) {g.drawImage(gDrop, 2087/2, 205/2, 48, 40, null);}
                else if(gDSpot ==24) {g.drawImage(gDrop, 2245/2, 206/2, 48, 40, null);}
                else if(gDSpot ==25) {g.drawImage(gDrop, 2416/2, 208/2, 48, 40, null);}
                else if(gDSpot ==26) {g.drawImage(gDrop, 2565/2, 200/2, 48, 40, null);}
                else if(gDSpot ==27) {g.drawImage(gDrop, 2517/2, 326/2, 48, 40, null);}
                else if(gDSpot ==28) {g.drawImage(gDrop, 2610/2, 437/2, 48, 40, null);}
                else if(gDSpot ==29) {g.drawImage(gDrop, 2513/2, 542/2, 48, 40, null);}
                else if(gDSpot ==30) {g.drawImage(gDrop, 2636/2, 649/2, 48, 40, null);}
                else if(gDSpot ==31) {g.drawImage(gDrop, 2550/2, 773/2, 48, 40, null);}
                else if(gDSpot ==32) {g.drawImage(gDrop, 2669/2, 892/2, 48, 40, null);}
                else if(gDSpot ==33) {g.drawImage(gDrop, 2593/2, 1005/2, 48, 40, null);}
                else if(gDSpot ==34) {g.drawImage(gDrop, 2683/2, 1120/2, 48, 40, null);}
                else if(gDSpot ==35) {g.drawImage(gDrop, 2592/2, 1242/2, 48, 40, null);}
                else if(gDSpot ==36) {g.drawImage(gDrop, 2729/2, 1380/2, 48, 40, null);}
                else if(gDSpot ==37) {g.drawImage(gDrop, 2736/2, 1549/2, 48, 40, null);}
                else if(gDSpot == 38) {g.drawImage(gDrop, 2555/2, 1542/2, 48, 40, null);}
                else if(gDSpot == 39) {g.drawImage(gDrop, 2364/2, 1546/2, 48, 40, null);}
                else if(gDSpot == 40) {g.drawImage(gDrop, 2170/2, 1535/2, 48, 40, null);}
                else if(gDSpot == 41) {g.drawImage(gDrop, 2000/2, 1541/2, 48, 40, null);}
                else if(gDSpot == 42) {g.drawImage(gDrop, 1816/2, 1541/2, 48, 40, null);}
                else if(gDSpot == 43) {g.drawImage(gDrop, 1630/2, 1542/2, 48, 40, null);}
                else if(gDSpot == 44) {g.drawImage(gDrop, 1445/2, 1548/2, 48, 40, null);}
                else if(gDSpot == 45) {g.drawImage(gDrop, 1247/2, 1535/2, 48, 40, null);}
                else if(gDSpot == 46) {g.drawImage(gDrop, 1079/2, 1543/2, 48, 40, null);}
                else if(gDSpot == 47) {g.drawImage(gDrop, 906/2, 1544/2, 48, 40, null);}
                else if(gDSpot == 48) {g.drawImage(gDrop, 727/2, 1544/2, 48, 40, null);}
                else if(gDSpot == 49) {g.drawImage(gDrop, 538/2, 1535/2, 48, 40, null);}
                else if(gDSpot ==50) {g.drawImage(gDrop,345/2 ,1535/2 ,48 ,40 ,null );}
            }
            else if(cMover.equals("yDrop"))
            {
                if(yDSpot == 1){g.drawImage(yDrop, 87, 772, 48, 40, null);}
                else if(yDSpot ==2) {g.drawImage(yDrop, 208/2, 1368/2, 48, 40, null);}
                else if(yDSpot ==3) {g.drawImage(yDrop, 320/2, 1242/2, 48, 40, null);}
                else if(yDSpot ==4) {g.drawImage(yDrop, 241/2, 1113/2, 48, 40, null);}
                else if(yDSpot ==5) {g.drawImage(yDrop, 333/2, 985/2, 48, 40, null);}
                else if(yDSpot ==6) {g.drawImage(yDrop, 264/2, 877/2, 48, 40, null);}
                else if(yDSpot ==7) {g.drawImage(yDrop, 194, 382, 48, 40, null);}
                else if(yDSpot ==8) {g.drawImage(yDrop, 293/2, 648/2, 48, 40, null);}
                else if(yDSpot ==9) {g.drawImage(yDrop, 421/2, 540/2, 48, 40, null);}
                else if(yDSpot ==10) {g.drawImage(yDrop, 306/2, 425/2, 48, 40, null);}
                else if(yDSpot ==11) {g.drawImage(yDrop, 410/2, 313/2, 48, 40, null);}
                else if(yDSpot ==12) {g.drawImage(yDrop, 347/2, 206/2, 48, 40, null);}
                else if(yDSpot ==13) {g.drawImage(yDrop, 513/2, 194/2, 48, 40, null);}
                else if(yDSpot ==14) {g.drawImage(yDrop, 663/2, 196/2, 48, 40, null);}
                else if(yDSpot ==15) {g.drawImage(yDrop, 828/2, 200/2, 48, 40, null);}
                else if(yDSpot ==16) {g.drawImage(yDrop, 984/2, 206/2, 48, 40, null);}
                else if(yDSpot ==17) {g.drawImage(yDrop, 1137/2, 204/2, 48, 40, null);}
                else if(yDSpot ==18) {g.drawImage(yDrop, 1287/2, 197/2, 48, 40, null);}
                else if(yDSpot ==19) {g.drawImage(yDrop, 1460/2, 202/2, 48, 40, null);}
                else if(yDSpot ==20) {g.drawImage(yDrop, 1602/2, 197/2, 48, 40, null);}
                else if(yDSpot ==21) {g.drawImage(yDrop, 1783/2, 195/2, 48, 40, null);}
                else if(yDSpot ==22) {g.drawImage(yDrop, 1926/2, 200/2, 48, 40, null);}
                else if(yDSpot ==23) {g.drawImage(yDrop, 2087/2, 205/2, 48, 40, null);}
                else if(yDSpot ==24) {g.drawImage(yDrop, 2245/2, 206/2, 48, 40, null);}
                else if(yDSpot ==25) {g.drawImage(yDrop, 2416/2, 208/2, 48, 40, null);}
                else if(yDSpot ==26) {g.drawImage(yDrop, 2565/2, 200/2, 48, 40, null);}
                else if(yDSpot ==27) {g.drawImage(yDrop, 2517/2, 326/2, 48, 40, null);}
                else if(yDSpot ==28) {g.drawImage(yDrop, 2610/2, 437/2, 48, 40, null);}
                else if(yDSpot ==29) {g.drawImage(yDrop, 2513/2, 542/2, 48, 40, null);}
                else if(yDSpot ==30) {g.drawImage(yDrop, 2636/2, 649/2, 48, 40, null);}
                else if(yDSpot ==31) {g.drawImage(yDrop, 2550/2, 773/2, 48, 40, null);}
                else if(yDSpot ==32) {g.drawImage(yDrop, 2669/2, 892/2, 48, 40, null);}
                else if(yDSpot ==33) {g.drawImage(yDrop, 2593/2, 1005/2, 48, 40, null);}
                else if(yDSpot ==34) {g.drawImage(yDrop, 2683/2, 1120/2, 48, 40, null);}
                else if(yDSpot ==35) {g.drawImage(yDrop, 2592/2, 1242/2, 48, 40, null);}
                else if(yDSpot ==36) {g.drawImage(yDrop, 2729/2, 1380/2, 48, 40, null);}
                else if(yDSpot ==37) {g.drawImage(yDrop, 2736/2, 1549/2, 48, 40, null);}
                else if (yDSpot == 38) {g.drawImage(yDrop, 2555/2, 1542/2, 48, 40, null);}
                else if (yDSpot == 39) {g.drawImage(yDrop, 2364/2, 1546/2, 48, 40, null);}
                else if (yDSpot == 40) {g.drawImage(yDrop, 2170/2, 1535/2, 48, 40, null);}
                else if (yDSpot == 41) {g.drawImage(yDrop, 2000/2, 1541/2, 48, 40, null);}
                else if (yDSpot == 42) {g.drawImage(yDrop, 1816/2, 1541/2, 48, 40, null);}
                else if (yDSpot == 43) {g.drawImage(yDrop, 1630/2, 1542/2, 48, 40, null);}
                else if (yDSpot == 44) {g.drawImage(yDrop, 1445/2, 1548/2, 48, 40, null);}
                else if (yDSpot == 45) {g.drawImage(yDrop, 1247/2, 1535/2, 48, 40, null);}
                else if (yDSpot == 46) {g.drawImage(yDrop, 1079/2, 1543/2, 48, 40, null);}
                else if (yDSpot == 47) {g.drawImage(yDrop, 906/2, 1544/2, 48, 40, null);}
                else if (yDSpot == 48) {g.drawImage(yDrop, 727/2, 1544/2, 48, 40, null);}
                else if (yDSpot == 49) {g.drawImage(yDrop, 538/2, 1535/2, 48, 40, null);}
                else if (yDSpot ==50) {g.drawImage(yDrop,345/2 ,1535/2 ,48 ,40 ,null );}
            }
            else if(cMover.equals("bDrop"))
            {
                if(bDSpot == 1){g.drawImage(bDrop, 87, 772, 48, 40, null);}
                else if(bDSpot ==2) {g.drawImage(bDrop, 208/2, 1368/2, 48, 40, null);}
                else if(bDSpot ==3) {g.drawImage(bDrop, 320/2, 1242/2, 48, 40, null);}
                else if(bDSpot ==4) {g.drawImage(bDrop, 241/2, 1113/2, 48, 40, null);}
                else if(bDSpot ==5) {g.drawImage(bDrop, 333/2, 985/2, 48, 40, null);}
                else if(bDSpot ==6) {g.drawImage(bDrop, 264/2, 877/2, 48, 40, null);}
                else if(bDSpot ==7) {g.drawImage(bDrop, 194, 382, 48, 40, null);}
                else if(bDSpot ==8) {g.drawImage(bDrop, 293/2, 648/2, 48, 40, null);}
                else if(bDSpot ==9) {g.drawImage(bDrop, 421/2, 540/2, 48, 40, null);}
                else if(bDSpot ==10) {g.drawImage(bDrop, 306/2, 425/2, 48, 40, null);}
                else if(bDSpot ==11) {g.drawImage(bDrop, 410/2, 313/2, 48, 40, null);}
                else if(bDSpot ==12) {g.drawImage(bDrop, 347/2, 206/2, 48, 40, null);}
                else if(bDSpot ==13) {g.drawImage(bDrop, 513/2, 194/2, 48, 40, null);}
                else if(bDSpot ==14) {g.drawImage(bDrop, 663/2, 196/2, 48, 40, null);}
                else if(bDSpot ==15) {g.drawImage(bDrop, 828/2, 200/2, 48, 40, null);}
                else if(bDSpot ==16) {g.drawImage(bDrop, 984/2, 206/2, 48, 40, null);}
                else if(bDSpot ==17) {g.drawImage(bDrop, 1137/2, 204/2, 48, 40, null);}
                else if(bDSpot ==18) {g.drawImage(bDrop, 1287/2, 197/2, 48, 40, null);}
                else if(bDSpot ==19) {g.drawImage(bDrop, 1460/2, 202/2, 48, 40, null);}
                else if(bDSpot ==20) {g.drawImage(bDrop, 1602/2, 197/2, 48, 40, null);}
                else if(bDSpot ==21) {g.drawImage(bDrop, 1783/2, 195/2, 48, 40, null);}
                else if(bDSpot ==22) {g.drawImage(bDrop, 1926/2, 200/2, 48, 40, null);}
                else if(bDSpot ==23) {g.drawImage(bDrop, 2087/2, 205/2, 48, 40, null);}
                else if(bDSpot ==24) {g.drawImage(bDrop, 2245/2, 206/2, 48, 40, null);}
                else if(bDSpot ==25) {g.drawImage(bDrop, 2416/2, 208/2, 48, 40, null);}
                else if(bDSpot ==26) {g.drawImage(bDrop, 2565/2, 200/2, 48, 40, null);}
                else if(bDSpot ==27) {g.drawImage(bDrop, 2517/2, 326/2, 48, 40, null);}
                else if(bDSpot ==28) {g.drawImage(bDrop, 2610/2, 437/2, 48, 40, null);}
                else if(bDSpot ==29) {g.drawImage(bDrop, 2513/2, 542/2, 48, 40, null);}
                else if(bDSpot ==30) {g.drawImage(bDrop, 2636/2, 649/2, 48, 40, null);}
                else if(bDSpot ==31) {g.drawImage(bDrop, 2550/2, 773/2, 48, 40, null);}
                else if(bDSpot ==32) {g.drawImage(bDrop, 2669/2, 892/2, 48, 40, null);}
                else if(bDSpot ==33) {g.drawImage(bDrop, 2593/2, 1005/2, 48, 40, null);}
                else if(bDSpot ==34) {g.drawImage(bDrop, 2683/2, 1120/2, 48, 40, null);}
                else if(bDSpot ==35) {g.drawImage(bDrop, 2592/2, 1242/2, 48, 40, null);}
                else if(bDSpot ==36) {g.drawImage(bDrop, 2729/2, 1380/2, 48, 40, null);}
                else if(bDSpot ==37) {g.drawImage(bDrop, 2736/2, 1549/2, 48, 40, null);}
                else if (bDSpot == 38) {g.drawImage(bDrop, 2555/2, 1542/2, 48, 40, null);}
                else if (bDSpot == 39) {g.drawImage(bDrop, 2364/2, 1546/2, 48, 40, null);}
                else if (bDSpot == 40) {g.drawImage(bDrop, 2170/2, 1535/2, 48, 40, null);}
                else if (bDSpot == 41) {g.drawImage(bDrop, 2000/2, 1541/2, 48, 40, null);}
                else if (bDSpot == 42) {g.drawImage(bDrop, 1816/2, 1541/2, 48, 40, null);}
                else if (bDSpot == 43) {g.drawImage(bDrop, 1630/2, 1542/2, 48, 40, null);}
                else if (bDSpot == 44) {g.drawImage(bDrop, 1445/2, 1548/2, 48, 40, null);}
                else if (bDSpot == 45) {g.drawImage(bDrop, 1247/2, 1535/2, 48, 40, null);}
                else if (bDSpot == 46) {g.drawImage(bDrop, 1079/2, 1543/2, 48, 40, null);}
                else if (bDSpot == 47) {g.drawImage(bDrop, 906/2, 1544/2, 48, 40, null);}
                else if (bDSpot == 48) {g.drawImage(bDrop, 727/2, 1544/2, 48, 40, null);}
                else if (bDSpot == 49) {g.drawImage(bDrop, 538/2, 1535/2, 48, 40, null);}
                else if (bDSpot ==50) {g.drawImage(bDrop,345/2 ,1535/2 ,48 ,40 ,null );}
            }
            else if(cMover.equals("rDrop"))
            {
                if(rDSpot == 1){g.drawImage(rDrop, 87, 772, 48, 40, null);}
                else if(rDSpot ==2) {g.drawImage(rDrop, 208/2, 1368/2, 48, 40, null);}
                else if(rDSpot ==3) {g.drawImage(rDrop, 320/2, 1242/2, 48, 40, null);}
                else if(rDSpot ==4) {g.drawImage(rDrop, 241/2, 1113/2, 48, 40, null);}
                else if(rDSpot ==5) {g.drawImage(rDrop, 333/2, 985/2, 48, 40, null);}
                else if(rDSpot ==6) {g.drawImage(rDrop, 264/2, 877/2, 48, 40, null);}
                else if(rDSpot ==7) {g.drawImage(rDrop, 194, 382, 48, 40, null);}
                else if(rDSpot ==8) {g.drawImage(rDrop, 293/2, 648/2, 48, 40, null);}
                else if(rDSpot ==9) {g.drawImage(rDrop, 421/2, 540/2, 48, 40, null);}
                else if(rDSpot ==10) {g.drawImage(rDrop, 306/2, 425/2, 48, 40, null);}
                else if(rDSpot ==11) {g.drawImage(rDrop, 410/2, 313/2, 48, 40, null);}
                else if(rDSpot ==12) {g.drawImage(rDrop, 347/2, 206/2, 48, 40, null);}
                else if(rDSpot ==13) {g.drawImage(rDrop, 513/2, 194/2, 48, 40, null);}
                else if(rDSpot ==14) {g.drawImage(rDrop, 663/2, 196/2, 48, 40, null);}
                else if(rDSpot ==15) {g.drawImage(rDrop, 828/2, 200/2, 48, 40, null);}
                else if(rDSpot ==16) {g.drawImage(rDrop, 984/2, 206/2, 48, 40, null);}
                else if(rDSpot ==17) {g.drawImage(rDrop, 1137/2, 204/2, 48, 40, null);}
                else if(rDSpot ==18) {g.drawImage(rDrop, 1287/2, 197/2, 48, 40, null);}
                else if(rDSpot ==19) {g.drawImage(rDrop, 1460/2, 202/2, 48, 40, null);}
                else if(rDSpot ==20) {g.drawImage(rDrop, 1602/2, 197/2, 48, 40, null);}
                else if(rDSpot ==21) {g.drawImage(rDrop, 1783/2, 195/2, 48, 40, null);}
                else if(rDSpot ==22) {g.drawImage(rDrop, 1926/2, 200/2, 48, 40, null);}
                else if(rDSpot ==23) {g.drawImage(rDrop, 2087/2, 205/2, 48, 40, null);}
                else if(rDSpot ==24) {g.drawImage(rDrop, 2245/2, 206/2, 48, 40, null);}
                else if(rDSpot ==25) {g.drawImage(rDrop, 2416/2, 208/2, 48, 40, null);}
                else if(rDSpot ==26) {g.drawImage(rDrop, 2565/2, 200/2, 48, 40, null);}
                else if(rDSpot ==27) {g.drawImage(rDrop, 2517/2, 326/2, 48, 40, null);}
                else if(rDSpot ==28) {g.drawImage(rDrop, 2610/2, 437/2, 48, 40, null);}
                else if(rDSpot ==29) {g.drawImage(rDrop, 2513/2, 542/2, 48, 40, null);}
                else if(rDSpot ==30) {g.drawImage(rDrop, 2636/2, 649/2, 48, 40, null);}
                else if(rDSpot ==31) {g.drawImage(rDrop, 2550/2, 773/2, 48, 40, null);}
                else if(rDSpot ==32) {g.drawImage(rDrop, 2669/2, 892/2, 48, 40, null);}
                else if(rDSpot ==33) {g.drawImage(rDrop, 2593/2, 1005/2, 48, 40, null);}
                else if(rDSpot ==34) {g.drawImage(rDrop, 2683/2, 1120/2, 48, 40, null);}
                else if(rDSpot ==35) {g.drawImage(rDrop, 2592/2, 1242/2, 48, 40, null);}
                else if(rDSpot ==36) {g.drawImage(rDrop, 2729/2, 1380/2, 48, 40, null);}
                else if(rDSpot ==37) {g.drawImage(rDrop, 2736/2, 1549/2, 48, 40, null);}
                else if (rDSpot == 38) {g.drawImage(rDrop, 2555/2, 1542/2, 48, 40, null);}
                else if (rDSpot == 39) {g.drawImage(rDrop, 2364/2, 1546/2, 48, 40, null);}
                else if (rDSpot == 40) {g.drawImage(rDrop, 2170/2, 1535/2, 48, 40, null);}
                else if (rDSpot == 41) {g.drawImage(rDrop, 2000/2, 1541/2, 48, 40, null);}
                else if (rDSpot == 42) {g.drawImage(rDrop, 1816/2, 1541/2, 48, 40, null);}
                else if (rDSpot == 43) {g.drawImage(rDrop, 1630/2, 1542/2, 48, 40, null);}
                else if (rDSpot == 44) {g.drawImage(rDrop, 1445/2, 1548/2, 48, 40, null);}
                else if (rDSpot == 45) {g.drawImage(rDrop, 1247/2, 1535/2, 48, 40, null);}
                else if (rDSpot == 46) {g.drawImage(rDrop, 1079/2, 1543/2, 48, 40, null);}
                else if (rDSpot == 47) {g.drawImage(rDrop, 906/2, 1544/2, 48, 40, null);}
                else if (rDSpot == 48) {g.drawImage(rDrop, 727/2, 1544/2, 48, 40, null);}
                else if (rDSpot == 49) {g.drawImage(rDrop, 538/2, 1535/2, 48, 40, null);}
                else if (rDSpot ==50) {g.drawImage(rDrop,345/2 ,1535/2 ,48 ,40 ,null );}
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
        if(choice.contains("bPhase") && cPlay == 0)
        {
        	g.setColor(Color.GREEN);
            g.fillRect(282+50, 838, 100, 50);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString("Start", 282+78, 838+32);
        }
        if(choice.contains("bPhase") && cPlay > 0 && cPlay < 4)
        {
        	if(cPlay == 1)
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
        	if(cPlay == 2)
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
        	if(cPlay == 3)
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
        if(choice.contains("bPhase") && cPlay == 4)
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
        //WINNER SCREEN -> winning quacks shown
        if(choice.equals("winner"))
        {
            g.drawImage(winner, 0, 0, panelWidth, panelHeight, null);
            if(game.winner().getName().equals("Player 1"))
            {
                g.drawImage(q1, 505/2, 680/2, 802/2, 997/2, null);
                g.drawImage(gDrop, 2172/2, 806/2, 270/2, 253/2, null);
            }
            else if(game.winner().getName().equals("Player 2"))
            {
                g.drawImage(q2, 505/2, 680/2, 802/2, 997/2, null);
                g.drawImage(bDrop, 2172/2, 806/2, 270/2, 253/2, null);
            }
            else if(game.winner().getName().equals("Player 3"))
            {
                g.drawImage(q3, 505/2, 680/2, 802/2, 997/2, null);
                g.drawImage(rDrop, 2172/2, 806/2, 270/2, 253/2, null);
            }
            else if(game.winner().getName().equals("Player 4"))
            {
                g.drawImage(q4, 505/2, 680/2, 802/2, 997/2, null);
                g.drawImage(yDrop, 2172/2, 806/2, 270/2, 253/2, null);
            }
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
                startSet = IngredientSet.getSet1();
                game = new Game(numPlayers, 1, new IngredientSet("Set1", "Standard game rules"));
                System.out.println("Set number: "+ selectedSet);
            }
            else if (x >= 826 && x <= 1296 && y >= 310 && y <= 583)
            {
                selectedSet = 2;
                choice = "pNum";
                startSet = IngredientSet.getSet2();
                game = new Game(numPlayers, 1, new IngredientSet("Set2", "Intermediate difficulty"));
                System.out.println("Set number: "+ selectedSet);
            }
            else if (x >= 175 && x <= 611 && y >= 505 && y <= 791)
            {
                selectedSet = 3;
                choice = "pNum";
                startSet = IngredientSet.getSet3();
                game = new Game(numPlayers, 1, new IngredientSet("Set3", "Advanced strategies"));
                System.out.println("Set number: "+ selectedSet);
            }
            else if (x >= 795 && x <= 1258 && y >= 627 && y <= 903)
            {
                selectedSet = 4;
                startSet = IngredientSet.getSet4();
                game = new Game(numPlayers, 1, new IngredientSet("Set4", "Expert level"));
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
                p1 = new Player("Player 1", new AI(y), (ArrayList<Ingredient>) startSet.getStartBag());
                choice = "gMode";
                resetFortuneDeck();
                System.out.println("Number of Players: " + numPlayers);
            }
            else if (x >= 116 && x <= 597 && y >= 533 && y <= 909)
            {
                numPlayers = 2;
                p1 = new Player("Player 1", new AI(y), (ArrayList<Ingredient>) startSet.getStartBag());
                p2 = new Player("Player 2", new AI(y), (ArrayList<Ingredient>) startSet.getStartBag());
                choice = "gMode";
                resetFortuneDeck();
                System.out.println("Number of Players: " + numPlayers);
            }
            else if (x >= 836 && x <= 1375 && y >= 142 && y <= 530)
            {
                numPlayers = 3;
                p1 = new Player("Player 1", new AI(y), (ArrayList<Ingredient>) startSet.getStartBag());
                p2 = new Player("Player 2", new AI(y), (ArrayList<Ingredient>) startSet.getStartBag());
                p3 = new Player("Player 3", new AI(y), (ArrayList<Ingredient>) startSet.getStartBag());
                choice = "gMode";
                resetFortuneDeck();
                System.out.println("Number of Players: " + numPlayers);
            }
            else if (x >= 839 && x <= 1331 && y >= 547 && y <= 912)
            {
                numPlayers = 4;
                p1 = new Player("Player 1", new AI(y), (ArrayList<Ingredient>) startSet.getStartBag());
                p2 = new Player("Player 2", new AI(y), (ArrayList<Ingredient>) startSet.getStartBag());
                p3 = new Player("Player 3", new AI(y), (ArrayList<Ingredient>) startSet.getStartBag());
                p4 = new Player("Player 4", new AI(y), (ArrayList<Ingredient>) startSet.getStartBag());
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
        else if(choice.contains("bPhase"))
        {
        	if(x >= 328 && x <= 431 && y >= 842 && y <= 887)
        	{
                if(cPlay < 4)
        	{
        		cPlay++;
        	}
        	else if(cPlay == 4)
        	{
        		choice = "vBoard";
        	}
        	}
        	else if(x >= 100 && x <= 1541 && y >= 521 && y <= 756)
        	{
        		hotSeat = false;
                game.startGame();
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
        //BUYING PHASE -> Mapping buttons to buy stuff
        else if(choice.contains("bPhase") && x >=282+50 && y>= 838 && x<= 282+50+100 && y <= 838+50)
        {
        	if(cPlay < 4)
        	{
        		game.nextBuyer();
        	}
        	else if(cPlay == 4)
        	{
                game.endRound();
        		choice = "vBoard";
        	}
        }

        //VICTORY BOARD SCREEN -> Move Drop
        else if(choice.equals("vBoard") && x >=getWidth()/2-50 && y>= 866 && x<= getWidth()/2-50+150 && y <= 866+50)
        {
            int numPMoved = 0;
            if (numPMoved == 0 && numPlayers <= 4 && x >=getWidth()/2-50 && y>= 866 && x<= getWidth()/2-50+150 && y <= 866+50)
            {
                if(game.getRound() == 0)
                {
                    if(cMover.equals("gDrop"))
                    {
                        cMover = "yDrop";
                    }
                    else if(cMover.equals("yDrop"))
                    {
                        cMover = "rDrop";
                    }
                    else if(cMover.equals("rDrop"))
                    {
                        cMover = "bDrop";
                    }
                }
                gDSpot += p1.getPoints();
                p1.moveDrop(p1.getPoints());
                numPMoved++;

                yDSpot += p4.getPoints();
                p4.moveDrop(yDSpot);
                numPMoved++;

                rDSpot += p3.getPoints();
                p3.moveDrop(rDSpot);
                numPMoved++;

                bDSpot += p2.getPoints();
                p2.moveDrop(bDSpot);
                numPMoved++;
            }
        	if(game.getRound() < 9 && numPMoved == 4)
            {
                game.startRound();
                choice = "fCard";
            }
            else if(game.getRound() >= 9 && numPMoved == 4)
            {
                System.out.println("Game Over! All rounds completed.");
                choice = "winner";
                game.winner();
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
}