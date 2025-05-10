package Screens;

import javax.swing.*;


import java.awt.*;
import C.CharacterPanel;
import C.Fighter;
import C.User;
import m.MainFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ArenaScreen extends JPanel {
    public static MainFrame theFrame;
    private static final ImageIcon ARENA_BG_IMAGE = new ImageIcon("graphs/arenaScene.png");
    
    public ArenaScreen() {
        MainFrame.currentPanel = this;
        theFrame = MainFrame.theFrame;
        this.setLayout(null);
        
        // Background setup
        JLabel backgroundLabel = new JLabel(ARENA_BG_IMAGE);
        backgroundLabel.setBounds(0, 0, 1920, 1080);
        
        // Remove previous panels and set up the screen
        theFrame.removePrevPanelsAndLabels();
        this.setBounds(0, 0, 1920, 1080);
        
        JLabel storyDebugButton = new JLabel();
        storyDebugButton.setIcon(new ImageIcon("graphs/Arrow.png"));
        storyDebugButton.setText("BACK");
        storyDebugButton.setHorizontalTextPosition(JLabel.CENTER);
        storyDebugButton.setForeground(Color.white);
        storyDebugButton.setFont(new Font("Impact", Font.PLAIN, 50));
        storyDebugButton.setVerticalAlignment(JLabel.CENTER);
        storyDebugButton.setHorizontalAlignment(JLabel.CENTER);
        storyDebugButton.setBounds(1500, 800, 300, 95);
        storyDebugButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //new FightScreen(User.getUser(), new Fighter(0));
                //new StoreScreen();
                new LobbyScreen();
            }
    
            @Override
            public void mouseEntered(MouseEvent e) {
                storyDebugButton.setIcon(new ImageIcon("graphs/enteredArrow.png"));
                storyDebugButton.setFont(new Font("Impact", Font.PLAIN, 45));
            }
    
            @Override
            public void mouseExited(MouseEvent e) {
                storyDebugButton.setIcon(new ImageIcon("graphs/Arrow.png"));
                storyDebugButton.setFont(new Font("Impact", Font.PLAIN, 45));
            }
    
            @Override
            public void mousePressed(MouseEvent e) {
                storyDebugButton.setIcon(new ImageIcon("graphs/clickedArrow.png"));
                storyDebugButton.setFont(new Font("Impact", Font.PLAIN, 41));
            }
    
            @Override
            public void mouseReleased(MouseEvent e) {
                storyDebugButton.setIcon(new ImageIcon("graphs/Arrow.png"));
                storyDebugButton.setFont(new Font("Impact", Font.PLAIN, 45));
            }
            });
        this.add(storyDebugButton);

        JLabel storyFightButton = createChoiceButton(true);
        JLabel duelFightButton = createChoiceButton(false);
        this.add(storyFightButton);
        this.add(duelFightButton);



        // Add character panel
        CharacterPanel charPanel = CharacterPanel.getCharPanel();
        this.add(charPanel);
        this.add(backgroundLabel);
        
        theFrame.add(this);
        theFrame.repaint();
    }
    public JLabel createChoiceButton(boolean isStory)
    {
        JLabel fightButton = new JLabel();
        fightButton.setIcon(new ImageIcon("graphs/bigButton.png"));
        if(isStory)fightButton.setText("<html>STORY MOD FIGHT<br>Current Enemy: " + (FightScreen.wonAgainstEnemies + 1) + "</html>");
        else fightButton.setText("SIDE DUEL MOD FIGHT");

        fightButton.setHorizontalTextPosition(JLabel.CENTER);
        fightButton.setForeground(Color.white);
        fightButton.setFont(new Font("Impact", Font.PLAIN, 40));
        fightButton.setVerticalAlignment(JLabel.CENTER);
        fightButton.setHorizontalAlignment(JLabel.CENTER);
        if(isStory)fightButton.setBounds(1100, 100, 800, 400);
        else fightButton.setBounds(300, 100, 800, 400);
        fightButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //new FightScreen(User.getUser(), new Fighter(0));
                //new StoreScreen();
                if(isStory)new FightScreen(User.getUser(), new Fighter(FightScreen.wonAgainstEnemies, true));
                else new FightScreen();
            }
    
            @Override
            public void mouseEntered(MouseEvent e) {
                fightButton.setIcon(new ImageIcon("graphs/bigEnteredButton.png"));
                fightButton.setFont(new Font("Impact", Font.PLAIN, 40));
            }
    
            @Override
            public void mouseExited(MouseEvent e) {
                fightButton.setIcon(new ImageIcon("graphs/bigButton.png"));
                fightButton.setFont(new Font("Impact", Font.PLAIN, 40));
            }
    
            @Override
            public void mousePressed(MouseEvent e) {
                fightButton.setIcon(new ImageIcon("graphs/bigClickedButton.png"));
                fightButton.setFont(new Font("Impact", Font.PLAIN, 33));
            }
    
            @Override
            public void mouseReleased(MouseEvent e) {
                fightButton.setIcon(new ImageIcon("graphs/bigButton.png"));
                fightButton.setFont(new Font("Impact", Font.PLAIN, 40));
            }
            });
            return fightButton;
    }
}