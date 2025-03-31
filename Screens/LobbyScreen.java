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
        
        // Adjusted button size
        int buttonSize = 180;
        int yPosition = 850;
        
        // Add buttons with resized images
        addButton("graphs/LobyButtons/ArenaButton.png", 500, yPosition, buttonSize, e -> goToArena());
        addButton("graphs/LobyButtons/ShopButton.png", 750, yPosition, buttonSize, e -> goToShop());
        addButton("graphs/LobyButtons/SaveButton.png", 1000, yPosition, buttonSize, e -> saveGame());
        addButton("graphs/LobyButtons/ExitButton.png", 1250, yPosition, buttonSize, e -> quitGame());
        
        this.add(backgroundLabel);
        theFrame.add(this);
        theFrame.repaint();
    }

    private void addButton(String imagePath, int x, int y, int size, ActionListener action) {
        ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH));
        JButton button = new JButton(icon);
        button.setBounds(x, y, size, size);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
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