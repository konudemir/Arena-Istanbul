package Screens;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import C.CharacterPanel;
import m.MainFrame;

public class LobbyScreen extends JPanel {
    public static MainFrame theFrame;

    public LobbyScreen() {
        MainFrame.currentPanel = this;
        theFrame = MainFrame.theFrame;
        this.setLayout(null);

        // Background setup
        ImageIcon backgroundIcon = new ImageIcon("graphs/lobby.png");
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(backgroundIcon);
        backgroundLabel.setBounds(0, 0, 1920, 1080);

        // Remove previous panels and set up the screen
        theFrame.removePrevPanelsAndLabels();
        this.setBounds(0, 0, 1920, 1080);
        
        // Add character panel
        this.add(CharacterPanel.getCharPanel());
        CharacterPanel.getCharPanel().moveTo(100, 560);
        
        // Add buttons
        addButton("Arena", 100, 200, e -> goToArena());
        addButton("Shop", 100, 300, e -> goToShop());
        addButton("Save", 100, 400, e -> saveGame());
        addButton("Quit", 100, 500, e -> quitGame());
        
        this.add(backgroundLabel);
        theFrame.add(this);
        theFrame.repaint();
    }

    private void addButton(String text, int x, int y, ActionListener action) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 200, 50);
        button.addActionListener(action);
        this.add(button);
    }

    private void goToArena() {
        System.out.println("Going to Arena...");
        // Implement arena transition logic
    }

    private void goToShop() {
        System.out.println("Going to Shop...");
        // Implement shop transition logic
    }

    private void saveGame() {
        System.out.println("Game Saved!");
        // Implement game-saving logic
    }

    private void quitGame() {
        System.out.println("Quitting Game...");
        System.exit(0);
    }
}