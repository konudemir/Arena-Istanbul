package Screens;

import C.CharacterPanel;
import C.CharacterPanelOld;
import C.Fighter;
import C.User;
import m.*;

import javax.swing.JPanel;

import B.colorPickButton;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomizationScreen extends JPanel{
    public static MainFrame theFrame;
    private static final ImageIcon NEW_GAME_BG_IMAGEICON = new ImageIcon("graphs/newGameGPT.png");
    public CustomizationScreen()
    {
        MainFrame.currentPanel = this;
        theFrame = MainFrame.theFrame;
        this.setLayout(null);
        ImageIcon backgroundIcon = NEW_GAME_BG_IMAGEICON;
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(backgroundIcon);
        backgroundLabel.setBounds(0, 0, 1920, 1080);
        
        this.setBounds(0, 0, 1920, 1080);
        
        //Remove the previous JPanel if existant
        theFrame.removePrevPanelsAndLabels();
        
        //Add the exit button
        JLabel backButton = new JLabel();
        backButton.setIcon(new ImageIcon("graphs/arrow.png"));
        backButton.setText("BACK");
        backButton.setHorizontalTextPosition(JLabel.CENTER);
        backButton.setForeground(Color.white);
        backButton.setFont(new Font("Impact", Font.PLAIN, 50));
        backButton.setVerticalAlignment(JLabel.CENTER);
        backButton.setHorizontalAlignment(JLabel.CENTER);
        backButton.setBounds(1500, 850, 400, 200);

        //Add the exit button
        JLabel continueButton = new JLabel();
        continueButton.setIcon(new ImageIcon("graphs/frontArrow.png"));
        continueButton.setText("CONTINUE");
        continueButton.setHorizontalTextPosition(JLabel.CENTER);
        continueButton.setForeground(Color.white);
        continueButton.setFont(new Font("Impact", Font.PLAIN, 50));
        continueButton.setVerticalAlignment(JLabel.CENTER);
        continueButton.setHorizontalAlignment(JLabel.CENTER);
        continueButton.setBounds(1550, 200, 300, 95);

        //Add the exit button
        JLabel arrowToLeft = new JLabel();
        arrowToLeft.setIcon(new ImageIcon("graphs/arrowToLeft.png"));
        arrowToLeft.setHorizontalTextPosition(JLabel.CENTER);
        arrowToLeft.setForeground(Color.white);
        arrowToLeft.setFont(new Font("Impact", Font.PLAIN, 50));
        arrowToLeft.setVerticalAlignment(JLabel.CENTER);
        arrowToLeft.setHorizontalAlignment(JLabel.CENTER);
        arrowToLeft.setBounds(375, 500, 300, 190);

        JLabel arrowToRight = new JLabel();
        arrowToRight.setIcon(new ImageIcon("graphs/arrowToRight.png"));
        arrowToRight.setHorizontalTextPosition(JLabel.CENTER);
        arrowToRight.setForeground(Color.white);
        arrowToRight.setFont(new Font("Impact", Font.PLAIN, 50));
        arrowToRight.setVerticalAlignment(JLabel.CENTER);
        arrowToRight.setHorizontalAlignment(JLabel.CENTER);
        arrowToRight.setBounds(925, 500, 300, 190);
        

        JLabel randomButton = new JLabel();
        randomButton.setIcon(new ImageIcon("graphs/button.png"));
        randomButton.setBounds(100, 50, 400, 200);
        randomButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e)
            {
                
            }
            @Override
            public void mouseExited(MouseEvent e)
            {
                
            }
            @Override
            public void mousePressed(MouseEvent e)
            {
                
            }
            @Override
            public void mouseClicked(MouseEvent e)
            {
                
            }
            @Override
            public void mouseReleased(MouseEvent e)
            {
                CharacterPanelOld.randomizeChar();
            }
        });
        
        

        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new FirstMenu();
            }
    
            @Override
            public void mouseEntered(MouseEvent e) {
                backButton.setIcon(new ImageIcon("graphs/enteredArrow.png"));
                backButton.setFont(new Font("Impact", Font.PLAIN, 50));
            }
    
            @Override
            public void mouseExited(MouseEvent e) {
                backButton.setIcon(new ImageIcon("graphs/arrow.png"));
                backButton.setFont(new Font("Impact", Font.PLAIN, 50));
            }
    
            @Override
            public void mousePressed(MouseEvent e) {
                backButton.setIcon(new ImageIcon("graphs/clickedArrow.png"));
                backButton.setFont(new Font("Impact", Font.PLAIN, 46));
            }
    
            @Override
            public void mouseReleased(MouseEvent e) {
                backButton.setIcon(new ImageIcon("graphs/arrow.png"));
                backButton.setFont(new Font("Impact", Font.PLAIN, 50));
            }
            });

            continueButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //new FightScreen(User.getUser(), new Fighter(0));
                    //new StoreScreen();
                    new LobbyScreen();
                }
        
                @Override
                public void mouseEntered(MouseEvent e) {
                    continueButton.setIcon(new ImageIcon("graphs/enteredFrontArrow.png"));
                    continueButton.setFont(new Font("Impact", Font.PLAIN, 45));
                }
        
                @Override
                public void mouseExited(MouseEvent e) {
                    continueButton.setIcon(new ImageIcon("graphs/frontArrow.png"));
                    continueButton.setFont(new Font("Impact", Font.PLAIN, 45));
                }
        
                @Override
                public void mousePressed(MouseEvent e) {
                    continueButton.setIcon(new ImageIcon("graphs/clickedFrontArrow.png"));
                    continueButton.setFont(new Font("Impact", Font.PLAIN, 41));
                }
        
                @Override
                public void mouseReleased(MouseEvent e) {
                    continueButton.setIcon(new ImageIcon("graphs/frontArrow.png"));
                    continueButton.setFont(new Font("Impact", Font.PLAIN, 45));
                }
                });

        //Adding the labels (in descending order of showing up)
        this.add(backButton);
        this.add(continueButton);
        //this.add(randomButton); //Add theFrame when randomizeCharacter method works fine
        CharacterPanel charPanel = CharacterPanel.getCharPanel();
        charPanel.setHeight(650);
        charPanel.moveTo(650, 300);
        this.add(charPanel);
        this.add(arrowToLeft);
        this.add(arrowToRight);
        this.add(backgroundLabel);
        

        theFrame.add(this);
        theFrame.repaint();
    }
    //Helper method for new game screen
    public void getBGButtonsForCustomization(JPanel newGameScreen, String context, int i, Color[] colors)
    {
        int jPanelY = 320;
        if(colors.length != 0)
        if(i < 3)
        {
            int jPanelx = 100;
            int amount = colors.length;
            final int SPACE = 10;
            int forEach = (360 - ((amount - 1) * SPACE)) / amount;
            int spaceAtEnds = 20 + (360 - (forEach * amount + SPACE * (amount - 1))) / 2;
            
            int currentX = jPanelx + spaceAtEnds;
            final int Y = 20 + jPanelY + 200 * i;
            final int height = 150 - 40;
            for(int j = 0; j < amount; j++)
            {
                colorPickButton colorButton = new colorPickButton(colors[j], context, currentX, Y, forEach, height);
                newGameScreen.add(colorButton);
                currentX += SPACE + forEach;
            }
        }
        else
        {
            int jPanelx = 550;
            int amount = colors.length;
            final int SPACE = 10;
            int forEach = (360 - ((amount - 1) * SPACE)) / amount;
            int spaceAtEnds = 20 + (360 - (forEach * amount + SPACE * (amount - 1))) / 2;
            
            int currentX = jPanelx + spaceAtEnds;
            final int Y = 20 + jPanelY + 200 * (i - 3);
            final int height = 150 - 40;
            for(int j = 0; j < amount; j++)
            {
                colorPickButton colorButton = new colorPickButton(colors[j], context, currentX, Y, forEach, height);
                newGameScreen.add(colorButton);
                currentX += SPACE + forEach;
            }
        }
        

        //Creating the background buttons
        if(i < 3)
        {
            JLabel bgButton = new JLabel();

            bgButton.setText(context);
            bgButton.setVerticalTextPosition(JLabel.TOP);
            bgButton.setHorizontalTextPosition(JLabel.RIGHT);
            bgButton.setIconTextGap(-380);
            bgButton.setForeground(Color.white);
            bgButton.setFont(new Font("Impact", Font.PLAIN, 30));

            bgButton.setIcon(new ImageIcon("graphs/character/buttons/bg.png"));
            bgButton.setBounds(100, jPanelY + 200 * i, 400, 150);
            newGameScreen.add(bgButton);
        }
        else
        {
            JLabel bgButton = new JLabel();
            
            bgButton.setText(context);
            bgButton.setVerticalTextPosition(JLabel.TOP);
            bgButton.setHorizontalTextPosition(JLabel.RIGHT);
            bgButton.setIconTextGap(-380);
            bgButton.setForeground(Color.white);
            bgButton.setFont(new Font("Impact", Font.PLAIN, 30));

            bgButton.setIcon(new ImageIcon("graphs/character/buttons/bg.png"));
            bgButton.setBounds(550, jPanelY + 200 * (i - 3), 400, 150);
            newGameScreen.add(bgButton);
        }
        newGameScreen.repaint();
    }
}