package Screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import C.CharacterPanel;
import m.MainFrame;

public class ArenaScreen extends JPanel {
    public static MainFrame theFrame;
    private static final ImageIcon ARENA_BG_IMAGE = new ImageIcon("graphs/arenaScene.png");
    private static final ImageIcon BOSS_BUTTON_IMAGE = new ImageIcon("graphs/bossButton.png");
    private static final ImageIcon ARENA_BUTTON_IMAGE = new ImageIcon("graphs/arenaButton.png");
    private static final ImageIcon BACK_BUTTON_IMAGE = new ImageIcon("graphs/arrow.png");
    
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
        
        // Add character panel
        CharacterPanel charPanel = CharacterPanel.getCharPanel();
        charPanel.moveTo(100, 560);
        this.add(charPanel);
        
        // Create buttons
        JButton bossButton = createMenuButton("Story Mode", 600, 300, BOSS_BUTTON_IMAGE);
        JButton arenaButton = createMenuButton("Arena Mode", 600, 500, ARENA_BUTTON_IMAGE);
        JButton backButton = createBackButton();
        
        // Add action listeners
        bossButton.addActionListener(_ -> startBossFight());
        arenaButton.addActionListener(_ -> startArenaFight());
        backButton.addActionListener(_ -> returnToLobby());
        
        // Add components to panel
        this.add(bossButton);
        this.add(arenaButton);
        this.add(backButton);
        this.add(backgroundLabel);
        
        theFrame.add(this);
        theFrame.repaint();
    }
    
    private JButton createMenuButton(String text, int width, int yPos, ImageIcon icon) {
        JButton button = new JButton(text, icon);
        button.setBounds(width, yPos, 300, 150);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setFont(new Font("Impact", Font.BOLD, 24));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(70, 70, 70, 200));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        return button;
    }
    
    private JButton createBackButton() {
        JButton button = new JButton(BACK_BUTTON_IMAGE);
        button.setBounds(1500, 850, 200, 100);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        return button;
    }
    
    private void startBossFight() {
        // Implement boss fight logic
        // This would typically create a FightScreen with a specific boss
        System.out.println("Starting boss fight...");
        // Example: new FightScreen(User.getUser(), BossFactory.getNextBoss());
    }
    
    private void startArenaFight() {
        // Implement random arena fight logic
        System.out.println("Starting arena fight...");
        // Example: new FightScreen(User.getUser(), EnemyFactory.getRandomEnemy());
    }
    
    private void returnToLobby() {
        new LobbyScreen();
    }
    
    // Helper method to create styled labels
    private JLabel createLabel(String text, int x, int y, int size) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 500, 100);
        label.setFont(new Font("Impact", Font.BOLD, size));
        label.setForeground(Color.WHITE);
        return label;
    }
}