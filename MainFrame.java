import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;



import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.*;
import java.awt.GraphicsEnvironment;
import java.awt.Image;



public class MainFrame extends JFrame{
    public static MainFrame theFrame;
    public static JPanel currentPanel;
    private static ImageIcon gameLogoImage;
    private static ImageIcon techGladsImage;
    
    
    public MainFrame() {
        this.setLayout(null);
        this.setSize(1920, 1080);
        this.setTitle("ARENA ISTANBUL");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        ImageIcon frameLogo = new ImageIcon("graphs/tadic.png");

        this.setIconImage(frameLogo.getImage());
        this.getContentPane().setBackground(Color.YELLOW);

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

        new CharacterPanel();

        
        
        this.setVisible(true);
        theFrame = this;
    }
    
    public JPanel getFirstScreen()
    {
        removePrevPanels();
        JPanel firstScreen = new JPanel();
        firstScreen.setLayout(null);
        ImageIcon backgroundIcon = new ImageIcon(Main.mainScreenPath);
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

        JLabel gameLogo = new JLabel();
        gameLogo.setBounds(1400, 0, gameLogoImage.getIconHeight(), gameLogoImage.getIconHeight());
        gameLogo.setForeground(Color.white);
        gameLogo.setIcon(gameLogoImage);
        firstScreen.add(gameLogo);

        JLabel groupLogo = new JLabel();
        groupLogo.setBounds(1450, 750, 800, 300);
        groupLogo.setForeground(Color.white);
        groupLogo.setIcon(techGladsImage);
        firstScreen.add(groupLogo);
        //Remove the fake JLabels

        for(java.awt.Component removeLabel : this.getContentPane().getComponents())
        {
            if(removeLabel instanceof JLabel ) this.remove(removeLabel);
        }
        //Remove the previous JPanel if existant
        removePrevPanels();

        firstScreen.add(backgroundLabel);

        this.add(firstScreen);

        this.repaint();
        return firstScreen;
    }
    public JPanel newGameScreen()
    {
        JPanel newGameScreen = new JPanel();
        newGameScreen.setLayout(null);
        ImageIcon backgroundIcon = new ImageIcon("graphs/newGameScreen.png");
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(backgroundIcon);
        backgroundLabel.setBounds(0, 0, 1920, 1080);
        
        newGameScreen.setBounds(0, 0, 1920, 1080);
        
        //Remove the previous JPanel if existant
        removePrevPanels();
        
        //Add the exit button
        JLabel backButton = new JLabel();
        backButton.setIcon(new ImageIcon("graphs/arrow.png"));
        backButton.setText("BACK");
        backButton.setHorizontalTextPosition(JLabel.CENTER);
        backButton.setForeground(Color.white);
        backButton.setFont(new Font("Impact", Font.PLAIN, 50));
        backButton.setVerticalAlignment(JLabel.CENTER);
        backButton.setHorizontalAlignment(JLabel.CENTER);
        backButton.setBounds(1500, 850, 400, 200);

        JLabel randomButton = new JLabel();
        randomButton.setIcon(new ImageIcon("graphs/button.png"));
        randomButton.setBounds(100, 50, 400, 200);
        randomButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e)
            {
                
            }
            @Override
            public void mouseExited(MouseEvent e)
            {
                
            }
            @Override
            public void mousePressed(MouseEvent e)
            {
                
            }
            @Override
            public void mouseClicked(MouseEvent e)
            {
                
            }
            @Override
            public void mouseReleased(MouseEvent e)
            {
                CharacterPanel.randomizeChar();
            }
        });

        getBGButtonsForCustomization(newGameScreen, "Hair", 0, new Color[]{new Color(0xF5E0B7), new Color(0x3D2B24), new Color(0x3D3D3D), new Color(0x6A4E42), new Color(0xA52A2A)});
        getBGButtonsForCustomization(newGameScreen, "Skin", 1, new Color[]{new Color(0xFFD3BA), new Color(0xDBA772), new Color(0xB87D4B), new Color(0x7C4A3A)});
        getBGButtonsForCustomization(newGameScreen, "Jacket", 2, new Color[]{new Color(0xADADA3), new Color(0x303030), new Color(0x001438), new Color(0x672FAA), new Color(0x72A8A2), new Color(0x003FA5)});
        getBGButtonsForCustomization(newGameScreen, "Shirt", 3, new Color[]{new Color(0xADD8E6), new Color(0xFFFDD0), new Color(0xFFFFFF)});
        getBGButtonsForCustomization(newGameScreen, "Tie", 4, new Color[]{new Color(0x000000), new Color(0x800020), new Color(0xD6B745), new Color(0x7700FF)});
        getBGButtonsForCustomization(newGameScreen, "Pants", 5, new Color[]{new Color(0x7A7A6E), new Color(0x000C21), new Color(0x000000)});
        
        

        backButton.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            getFirstScreen();
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

        //Adding the labels (in descending order of showing up)
        newGameScreen.add(backButton);
        //newGameScreen.add(randomButton); //Add this when randomizeCharacter method works fine
        newGameScreen.add(CharacterPanel.getCharPanel());
        newGameScreen.add(backgroundLabel);

        this.add(newGameScreen);
        this.repaint();
        return newGameScreen;
    }
    //Helper method for new game screen
    public void getBGButtonsForCustomization(JPanel newGameScreen, String context, int i, Color[] colors)
    {
        int jPanelY = 220;
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
                colorPickButton colorButton = new colorPickButton(colors[j], context, currentX, Y, forEach, height);
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
                colorPickButton colorButton = new colorPickButton(colors[j], context, currentX, Y, forEach, height);
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


    //Static versions of methods (S at the end representing "static")
    public static void removePrevPanelsS()
    {
        theFrame.removePrevPanels();
    }
    public static JPanel getFirstScreenS()
    {
        return theFrame.getFirstScreen();
    }
    public static JPanel newGameScreenS()
    {
        return theFrame.newGameScreen();
    }


}
