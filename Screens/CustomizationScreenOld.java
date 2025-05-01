package Screens;

import C.CharacterPanel;
import C.CharacterPanelOld;
import m.*;

import javax.swing.JPanel;

import B.colorPickButton;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomizationScreenOld extends JPanel{
    public static MainFrame theFrame;
    private static final ImageIcon NEW_GAME_BG_IMAGEICON = new ImageIcon("graphs/newGameGPT.png");
    public CustomizationScreenOld()
    {
        CharacterPanel.getCharPanel().setImage(this);
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
        continueButton.setBounds(1500, 200, 300, 95);

        getBGButtonsForCustomization(this, "Eye", 0, new Color[]{new Color(0x000000), new Color(0x3B2F2F), new Color(0x5B4B3A), new Color(0x1E1E1E), new Color(0x223A5E)});
        getBGButtonsForCustomization(this, "Skin", 1, new Color[]{new Color(0xFFB570), new Color(0xD6A77A), new Color(0xC68642), new Color(0xB97A57), new Color(0x8E5A30)});
        getBGButtonsForCustomization(this, "Body", 2, new Color[]{new Color(0x57294B), new Color(0x4B3B47), new Color(0x6B4226), new Color(0x2F4F4F), new Color(0x7B3530)});
        getBGButtonsForCustomization(this, "Legs", 3, new Color[]{new Color(0xBA6156), new Color(0x7B3F00), new Color(0x466D1D), new Color(0x8B5A2B), new Color(0x0F3B57)});
        
        
        

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
                    //storeScreen();
                    //arenaScene(User.getUser(), new Fighter(0));
                    new StoryScreen(true);
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
        this.add(CharacterPanel.getCharPanel());
        CharacterPanel.getCharPanel().moveTo(1000, 480);
        this.add(backgroundLabel);
        

        theFrame.add(this);
        theFrame.repaint();
    }
    //Helper method for new game screen
    public void getBGButtonsForCustomization(JPanel newGameScreen, String context, int i, Color[] colors)
    {
        int jPanelY = 200;
        if(colors.length != 0)
        if(i <= 3)
        {
            int jPanelx = 450;
            int amount = colors.length;
            final int SPACE = 10;
            int forEach = (660 - ((amount - 1) * SPACE)) / amount;
            int spaceAtEnds = 20 + (360 - (forEach * amount + SPACE * (amount - 1))) / 2;
            
            int currentX = jPanelx + spaceAtEnds;
            final int Y = 20 + jPanelY + 200 * i;
            final int height = 110;
            for(int j = 0; j < amount; j++)
            {
                colorPickButton colorButton = new colorPickButton(colors[j], context, currentX, Y, forEach, height, i);
                newGameScreen.add(colorButton);
                currentX += SPACE + forEach;
            }
        }
        else
        {
            int jPanelx = 750;
            int amount = colors.length;
            final int SPACE = 10;
            int forEach = (7 - ((amount - 1) * SPACE)) / amount;
            int spaceAtEnds = 20 + (660 - (forEach * amount + SPACE * (amount - 1))) / 2;
            
            int currentX = jPanelx + spaceAtEnds;
            final int Y = 20 + jPanelY + 200 * (i - 3);
            final int height = 150 - 40;
            for(int j = 0; j < amount; j++)
            {
                colorPickButton colorButton = new colorPickButton(colors[j], context, currentX, Y, forEach, height, i);
                newGameScreen.add(colorButton);
                currentX += SPACE + forEach;
            }
        }
        

        //Creating the background buttons
        if(i <= 3)
        {
            JLabel bgButton = new JLabel();

            bgButton.setText(context + " DO NOT CLICK FOR NOW!!!");
            bgButton.setVerticalTextPosition(JLabel.TOP);
            bgButton.setHorizontalTextPosition(JLabel.RIGHT);
            bgButton.setIconTextGap(-380);
            bgButton.setForeground(Color.white);
            bgButton.setFont(new Font("Impact", Font.PLAIN, 30));

            bgButton.setIcon(new ImageIcon("graphs/character/buttons/bg.png"));
            bgButton.setBounds(300, jPanelY + 200 * i, 700, 150);
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
            bgButton.setBounds(750, jPanelY + 200 * (i - 3), 700, 150);
            newGameScreen.add(bgButton);
        }
        newGameScreen.repaint();
    }
}