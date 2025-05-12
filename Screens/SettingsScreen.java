package Screens;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import C.CharacterPanel;
import C.User;
import m.Main;
import m.MainFrame;
import m.MusicPlayer;

public class SettingsScreen extends JPanel {
    private MainFrame theFrame;
    private JSlider volumeSlider;
    
    public SettingsScreen() {
        MainFrame.currentPanel = this;
        theFrame = MainFrame.theFrame;
        this.setLayout(null);
        
        ImageIcon backgroundIcon = new ImageIcon("graphs/settingsScreen.png");
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(backgroundIcon);
        backgroundLabel.setBounds(0, 0, 1920, 1080);
        
        this.setBounds(0, 0, 1920, 1080);
        
        // Remove the previous JPanel if existant
        theFrame.removePrevPanelsAndLabels();
        
        // Keybindings section
        JLabel keybindingsText = new JLabel("Keybindings");
        keybindingsText.setBounds(300, 50, 300, 50);
        keybindingsText.setFont(Main.getFont());
        
        JLabel keyBindingsButton = new JLabel();
        keyBindingsButton.setIcon(new ImageIcon("graphs/button.png"));
        keyBindingsButton.setBounds(250, 40, 200, 70);
        
        // Volume control section
        JLabel volumeText = new JLabel("Music Volume");
        volumeText.setBounds(300, 150, 300, 50);
        volumeText.setFont(Main.getFont());
        
        // Create volume slider
        volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50); // min, max, initial value
        volumeSlider.setBounds(250, 200, 300, 50);
        volumeSlider.setMajorTickSpacing(25);
        volumeSlider.setMinorTickSpacing(5);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintLabels(true);
        volumeSlider.setOpaque(false);
        
        // Add change listener to update volume when slider is moved
        volumeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = volumeSlider.getValue();
                // Get singleton instance of music player and update volume
                // Assuming you have a way to access your MusicPlayer instance
                if (Main.musicPlayer != null) {
                    Main.musicPlayer.setVolume(value);
                }
            }
        });
        
        // Back button
        JLabel backButton = new JLabel();
        backButton.setIcon(new ImageIcon("graphs/arrow.png"));
        backButton.setText("BACK");
        backButton.setHorizontalTextPosition(JLabel.CENTER);
        backButton.setForeground(Color.white);
        backButton.setFont(new Font("Impact", Font.PLAIN, 50));
        backButton.setVerticalAlignment(JLabel.CENTER);
        backButton.setHorizontalAlignment(JLabel.CENTER);
        backButton.setBounds(1500, 850, 400, 200);
        
        // Add click listener to back button
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Navigate back to previous screen 
                // Assuming you have a main menu or previous screen to go back to
                goBack();
            }
        });
        
        // Add components to panel (order matters for z-index)
        this.add(keyBindingsButton);
        this.add(keybindingsText);
        this.add(volumeText);
        this.add(volumeSlider);
        this.add(backButton);
        this.add(backgroundLabel); // Background should be last to be on the bottom layer
        
        theFrame.add(this);
        theFrame.repaint();
    }
    
    private void goBack() {
        // Replace with the appropriate screen you want to navigate back to
        // For example:
        new FirstMenu();
        // or
        // new GameScreen();
        // This depends on your application's navigation flow
        System.out.println("Back button clicked - implement navigation logic");
    }
}
