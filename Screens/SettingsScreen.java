package Screens;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import C.CharacterPanel;
import C.User;
import m.Main;
import m.MainFrame;

public class SettingsScreen extends JPanel{
    MainFrame theFrame;
    public SettingsScreen()
    {
        MainFrame.currentPanel = this;
        theFrame = MainFrame.theFrame;
        this.setLayout(null);
        ImageIcon backgroundIcon = new ImageIcon("graphs/settingsScreen.png");
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(backgroundIcon);
        backgroundLabel.setBounds(0, 0, 1920, 1080);
        
        this.setBounds(0, 0, 1920, 1080);
        
        //Remove the previous JPanel if existant
        theFrame.removePrevPanelsAndLabels();

        JLabel keybindingsText = new JLabel("Keybindings");
        keybindingsText.setBounds(300, 50, 1920, 1080);
        keybindingsText.setFont(Main.getFont());
        JLabel keyBindingsButton = new JLabel();
        keyBindingsButton.setIcon(new ImageIcon("graphs/button.png"));
        keyBindingsButton.setBounds(0, 0, 1920, 1080);


        this.add(keyBindingsButton);
        this.add(keybindingsText);
        this.add(backgroundLabel);
        theFrame.add(this);
        theFrame.repaint();
    }
    
}