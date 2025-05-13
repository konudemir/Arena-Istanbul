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
    private static final ImageIcon GREEN_BUTTON = new ImageIcon("graphs/greenButton.png");
    private static final ImageIcon ENTERED_GREEN_BUTTON = new ImageIcon("graphs/enteredGreenButton.png");
    private static final ImageIcon CLICKED_GREEN_BUTTON = new ImageIcon("graphs/clickedGreenButton.png");
    private static final ImageIcon RED_BUTTON = new ImageIcon("graphs/redButton.png");
    private static final ImageIcon ENTERED_RED_BUTTON = new ImageIcon("graphs/enteredRedButton.png");
    private static final ImageIcon CLICKED_RED_BUTTON = new ImageIcon("graphs/clickedRedButton.png");

    public static boolean musicInFirstScreen = true;
    public static boolean musicInLobby = true;
    public static boolean musicInFight = true;
    public static boolean musicInStore = true;
    public static boolean musicInStory = false;
    
    public SettingsScreen() {
        MainFrame.currentPanel = this;
        theFrame = MainFrame.theFrame;
        this.setLayout(null);
        
        ImageIcon backgroundIcon = new ImageIcon("graphs/loadSavesBackground.png");
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(backgroundIcon);
        backgroundLabel.setBounds(0, 0, 1920, 1080);
        
        this.setBounds(0, 0, 1920, 1080);
        
        // Remove the previous JPanel if existant
        theFrame.removePrevPanelsAndLabels();
        
        JLabel keyBindingsButton = new JLabel("Music Volume");
        keyBindingsButton.setIcon(new ImageIcon("graphs/button.png"));
        keyBindingsButton.setBounds(150, 80, 325, 70);
        keyBindingsButton.setFont(new Font("Impact", Font.PLAIN, 50));
        keyBindingsButton.setForeground(Color.WHITE);
        keyBindingsButton.setVerticalAlignment(JLabel.CENTER);
        keyBindingsButton.setHorizontalAlignment(JLabel.CENTER);
        keyBindingsButton.setHorizontalTextPosition(JLabel.CENTER);
        
        // Create volume slider
        volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50); // min, max, initial value
        volumeSlider.setBounds(150, 200, 300, 50);
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
                    MusicPlayer.setMusicVolume = value;
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
        
        // Add components to panel (order matters for z-index)
        this.add(keyBindingsButton);
        //this.add(volumeText);
        this.add(volumeSlider);
        this.add(backButton);
        addClickButton("First Screen", 0);
        addClickButton("Lobby Screen", 1);
        addClickButton("Fight Screen", 2);
        addClickButton("Store Screen", 3);
        addClickButton("Story Screen", 4);

        this.add(backgroundLabel); // Background should be last to be on the bottom layer
        
        theFrame.add(this);
        theFrame.repaint();
    }
    public void addClickButton(String context, int i)
    {
        boolean isChecked = false;
        if(
        (context.equalsIgnoreCase("Lobby Screen") && musicInLobby) ||
        (context.equalsIgnoreCase("Fight Screen") && musicInFight) ||
        (context.equalsIgnoreCase("Store Screen") && musicInStore) ||
        (context.equalsIgnoreCase("Story Screen") && musicInStory) ||
        (context.equalsIgnoreCase("First Screen") && musicInFirstScreen)
        )isChecked = true;
        JLabel button = new JLabel(context);
        System.out.println(context + " " + isChecked);
        if(isChecked)button.setIcon(GREEN_BUTTON);
        else button.setIcon(RED_BUTTON);
        button.setHorizontalTextPosition(JLabel.CENTER);
        button.setForeground(Color.white);
        button.setFont(new Font("Impact", Font.PLAIN, 30));
        button.setVerticalAlignment(JLabel.CENTER);
        button.setHorizontalAlignment(JLabel.CENTER);
        button.setBounds(550 + (250 * i), 60, 200, 200);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                if(button.getIcon() == GREEN_BUTTON || button.getIcon() == ENTERED_GREEN_BUTTON || button.getIcon() == CLICKED_GREEN_BUTTON)
                {
                    button.setIcon(RED_BUTTON);
                }
                else button.setIcon(GREEN_BUTTON);
                //Change the status
                if(button.getText().equalsIgnoreCase("Lobby Screen"))musicInLobby = !musicInLobby;
                else if(button.getText().equalsIgnoreCase("Fight Screen"))musicInFight = !musicInFight;
                else if(button.getText().equalsIgnoreCase("Store Screen"))musicInStore = !musicInStore;
                else if(button.getText().equalsIgnoreCase("Story Screen"))musicInStory = !musicInStory;
                else if(button.getText().equalsIgnoreCase("First Screen"))musicInFirstScreen = !musicInFirstScreen;
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if(button.getIcon() == GREEN_BUTTON || button.getIcon() == ENTERED_GREEN_BUTTON || button.getIcon() == CLICKED_GREEN_BUTTON) button.setIcon(ENTERED_GREEN_BUTTON);
                else button.setIcon(ENTERED_RED_BUTTON);
            }
    
            @Override
            public void mouseExited(MouseEvent e) {
                if(button.getIcon() == GREEN_BUTTON || button.getIcon() == ENTERED_GREEN_BUTTON || button.getIcon() == CLICKED_GREEN_BUTTON) button.setIcon(GREEN_BUTTON);
                else button.setIcon(RED_BUTTON);
            }
    
            @Override
            public void mousePressed(MouseEvent e) {
                if(button.getIcon() == GREEN_BUTTON || button.getIcon() == ENTERED_GREEN_BUTTON || button.getIcon() == CLICKED_GREEN_BUTTON) button.setIcon(CLICKED_GREEN_BUTTON);
                else button.setIcon(CLICKED_RED_BUTTON);
            }
    
            @Override
            public void mouseReleased(MouseEvent e) {
                if(button.getIcon() == GREEN_BUTTON || button.getIcon() == ENTERED_GREEN_BUTTON || button.getIcon() == CLICKED_GREEN_BUTTON) button.setIcon(GREEN_BUTTON);
                else button.setIcon(RED_BUTTON);
            }
        });
        this.add(button);
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
