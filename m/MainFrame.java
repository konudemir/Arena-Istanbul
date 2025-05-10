package m;
import B.*;
import C.*;
import Screens.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Image;



public class MainFrame extends JFrame{
    public static MainFrame theFrame;
    public static JPanel currentPanel;
    public static ImageIcon gameLogoImage;
    public static ImageIcon techGladsImage;
    private static final ImageIcon NEW_GAME_BG_IMAGEICON = new ImageIcon("graphs/newGameGPT.png");
    
    
    public MainFrame() {
        this.setLayout(null);
        this.setSize(1920, 1080);
        this.setTitle("ARENA ISTANBUL");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);

        ImageIcon frameLogo = new ImageIcon("graphs/tadic.png");

        this.setIconImage(frameLogo.getImage());
        this.getContentPane().setBackground(Color.ORANGE);

        //Fake labels before the panel loads
        ImageIcon backgroundIcon = new ImageIcon(Main.mainScreenPath);
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(backgroundIcon);
        backgroundLabel.setBounds(0, 0, 1920, 1080);
        this.add(backgroundLabel);

        mainScreenButton newGameButton = new mainScreenButton("New Game",100, 225, 400, 115, false);
        this.add(newGameButton);

        mainScreenButton loadGameButton = new mainScreenButton("Load Game",100, 375, 400, 115, false);
        this.add(loadGameButton);
        
        mainScreenButton settingsButton = new mainScreenButton("Settings",100, 525, 400, 115, false);
        this.add(settingsButton);
        
        mainScreenButton creditsButton = new mainScreenButton("Credits",100, 675, 400, 115, false);
        this.add(creditsButton);

        JLabel gameLogo = new JLabel();
        gameLogoImage = new ImageIcon("graphs/gameLogo.png");
        gameLogoImage = new ImageIcon(gameLogoImage.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH));
        gameLogo.setBounds(1400, 0, 800, 750);
        gameLogo.setFont(new Font("Impact", Font.PLAIN, 100));
        gameLogo.setForeground(Color.white);
        gameLogo.setIcon(gameLogoImage);
        this.add(gameLogo);

        JLabel groupLogo = new JLabel();
        techGladsImage = new ImageIcon("graphs/techGlads.png");
        techGladsImage = new ImageIcon(techGladsImage.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH));
        groupLogo.setBounds(1450, 750, 800, 300);
        groupLogo.setFont(new Font("Impact", Font.PLAIN, 60));
        groupLogo.setForeground(Color.white);
        groupLogo.setIcon(techGladsImage);
        this.add(groupLogo);
        
        this.setVisible(true);
        theFrame = this;
    }  
    //Helper method for new game screen
    public void getBGButtonsForCustomization(JPanel newGameScreen, String context, int i, Color[] colors)
    {
        int jPanelY = 320;
        if(colors.length != 0)
        if(i < 3)
        {
            int jPanelx = 100;
            int amount = colors.length;
            final int SPACE = 10;
            int forEach = (360 - ((amount - 1) * SPACE)) / amount;
            int spaceAtEnds = 20 + (360 - (forEach * amount + SPACE * (amount - 1))) / 2;
            
            int currentX = jPanelx + spaceAtEnds;
            final int Y = 20 + jPanelY + 200 * i;
            final int height = 150 - 40;
            for(int j = 0; j < amount; j++)
            {
                colorPickButton colorButton = new colorPickButton(colors[j], context, currentX, Y, forEach, height, j);
                newGameScreen.add(colorButton);
                currentX += SPACE + forEach;
            }
        }
        else
        {
            int jPanelx = 550;
            int amount = colors.length;
            final int SPACE = 10;
            int forEach = (360 - ((amount - 1) * SPACE)) / amount;
            int spaceAtEnds = 20 + (360 - (forEach * amount + SPACE * (amount - 1))) / 2;
            
            int currentX = jPanelx + spaceAtEnds;
            final int Y = 20 + jPanelY + 200 * (i - 3);
            final int height = 150 - 40;
            for(int j = 0; j < amount; j++)
            {
                colorPickButton colorButton = new colorPickButton(colors[j], context, currentX, Y, forEach, height, j);
                newGameScreen.add(colorButton);
                currentX += SPACE + forEach;
            }
        }
        

        //Creating the background buttons
        if(i < 3)
        {
            JLabel bgButton = new JLabel();

            bgButton.setText(context);
            bgButton.setVerticalTextPosition(JLabel.TOP);
            bgButton.setHorizontalTextPosition(JLabel.RIGHT);
            bgButton.setIconTextGap(-380);
            bgButton.setForeground(Color.white);
            bgButton.setFont(new Font("Impact", Font.PLAIN, 30));

            bgButton.setIcon(new ImageIcon("graphs/character/buttons/bg.png"));
            bgButton.setBounds(100, jPanelY + 200 * i, 400, 150);
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
            bgButton.setBounds(550, jPanelY + 200 * (i - 3), 400, 150);
            newGameScreen.add(bgButton);
        }
        newGameScreen.repaint();
    }
    public void removePrevPanels()
    {
        //Remove the previous JPanel if existant
        if(this.getContentPane().getComponents().length > 0)
        {
            for(Component c : this.getContentPane().getComponents())
            {
                if(c instanceof JPanel) this.remove(c);
            }
        }
    }
    public void removePrevLabels()
    {
        //Remove the previous JPanel if existant
        if(this.getContentPane().getComponents().length > 0)
        {
            for(Component c : this.getContentPane().getComponents())
            {
                if(c instanceof JLabel) this.remove(c);
            }
        }
    }
    public void removePrevPanelsAndLabels()
    {
        this.removePrevLabels();
        this.removePrevPanels();
    }

    public JPanel getFirstScreen()
    {
        return new FirstMenu();
    }
    //Static versions of methods (S at the end representing "static")
    public static void removePrevPanelsAndLabelsS()
    {
        theFrame.removePrevPanelsAndLabels();
    }
    public static JPanel getFirstScreenS()
    {
        return theFrame.getFirstScreen();
    }
    public static JPanel newGameScreenS()
    {
        return new CustomizationScreen();
    }
    public static JPanel creditsScreenS()
    {
        return new CreditsScreen();
    }
    public static JPanel settingsScreenS()
    {
        return new SettingsScreen();
    }
    public static JPanel loadGameScreenS()
    {
        return new LoadGameScreen();
    }
    public static JPanel getCurrentScreen()
    {
        return currentPanel;
    }
    public void rp()
    {
        this.repaint();
    }


}
