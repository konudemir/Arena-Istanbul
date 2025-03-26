package Screens;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import B.mainScreenButton;
import m.*;

public class FirstMenu extends JPanel{
    public static MainFrame theFrame;
    private static ImageIcon gameLogoImage;
    private static ImageIcon techGladsImage;
    public FirstMenu()
    {
        gameLogoImage = MainFrame.gameLogoImage;
        techGladsImage = MainFrame.techGladsImage;
        MainFrame.currentPanel = this;
        theFrame = MainFrame.theFrame;
        theFrame.removePrevPanelsAndLabels();
        this.setLayout(null);
        ImageIcon backgroundIcon = new ImageIcon(Main.mainScreenPath);
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(backgroundIcon);
        backgroundLabel.setBounds(0, 0, 1920, 1080);
        this.add(backgroundLabel);
        this.setBounds(0, 0, 1920, 1080);

        mainScreenButton newGameButton = new mainScreenButton("New Game",100, 225, 400, 115);
        this.add(newGameButton);

        mainScreenButton loadGameButton = new mainScreenButton("Load Game",100, 375, 400, 115);
        this.add(loadGameButton);
        
        mainScreenButton settingsButton = new mainScreenButton("Settings",100, 525, 400, 115);
        this.add(settingsButton);
        
        mainScreenButton creditsButton = new mainScreenButton("Credits",100, 675, 400, 115);
        this.add(creditsButton);

        JLabel gameLogo = new JLabel();
        gameLogo.setBounds(1400, 0, gameLogoImage.getIconHeight(), gameLogoImage.getIconHeight());
        gameLogo.setForeground(Color.white);
        gameLogo.setIcon(gameLogoImage);
        this.add(gameLogo);

        JLabel groupLogo = new JLabel();
        groupLogo.setBounds(1450, 750, 800, 300);
        groupLogo.setForeground(Color.white);
        groupLogo.setIcon(techGladsImage);
        this.add(groupLogo);
        //Remove the fake JLabels

        for(java.awt.Component removeLabel : theFrame.getContentPane().getComponents())
        {
            if(removeLabel instanceof JLabel ) theFrame.remove(removeLabel);
        }
        //Remove the previous JPanel if existant
        theFrame.removePrevPanelsAndLabels();

        this.add(backgroundLabel);

        theFrame.add(this);

        theFrame.repaint();
    }
}
