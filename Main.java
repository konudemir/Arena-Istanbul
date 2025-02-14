import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;


public class Main {
    public static final String mainScreenPath = "graphs/gptMainScreen1.png";

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        getFirstScreen(frame);
    }

    public static JPanel getFirstScreen(MainFrame frame)
    {
        JPanel firstScreen = new JPanel();
        firstScreen.setLayout(null);
        ImageIcon backgroundIcon = new ImageIcon(mainScreenPath);
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(backgroundIcon);
        backgroundLabel.setBounds(0, 0, 1920, 1080);
        firstScreen.add(backgroundLabel);
        firstScreen.setBounds(0, 0, 1920, 1080);

        mainScreenButton newGameButton = new mainScreenButton("New Game",100, 225, 400, 115);
        firstScreen.add(newGameButton);

        mainScreenButton loadGameButton = new mainScreenButton("Load Game",100, 375, 400, 115);
        firstScreen.add(loadGameButton);
        
        mainScreenButton settingsButton = new mainScreenButton("Settings",100, 525, 400, 115);
        firstScreen.add(settingsButton);
        
        mainScreenButton creditsButton = new mainScreenButton("Credits",100, 675, 400, 115);
        firstScreen.add(creditsButton);

        JLabel gameLogo = new JLabel("GAME LOGO");
        gameLogo.setBounds(1200, 100, 800, 300);
        gameLogo.setFont(new Font("Impact", Font.PLAIN, 120));
        gameLogo.setForeground(Color.white);
        firstScreen.add(gameLogo);

        JLabel groupLogo = new JLabel("GROUP LOGO");
        groupLogo.setBounds(1375, 750, 800, 300);
        groupLogo.setFont(new Font("Impact", Font.PLAIN, 60));
        groupLogo.setForeground(Color.white);
        firstScreen.add(groupLogo);
        //Remove the fake JLabels
        for(java.awt.Component removeLabel : frame.getContentPane().getComponents())
        {
            if(removeLabel instanceof JLabel ) frame.remove(removeLabel);
        }

        firstScreen.add(backgroundLabel);

        frame.add(firstScreen);

        frame.repaint();
        return firstScreen;
    }
}