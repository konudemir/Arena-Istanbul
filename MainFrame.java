import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;



import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MainFrame extends JFrame{
    public static MainFrame theFrame;
    public static JPanel currentPanel;
    
    
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

        JLabel gameLogo = new JLabel("ARENA ISTANBUL");
        gameLogo.setBounds(1150, 100, 800, 300);
        gameLogo.setFont(new Font("Impact", Font.PLAIN, 100));
        gameLogo.setForeground(Color.white);
        this.add(gameLogo);

        JLabel groupLogo = new JLabel("TECHGLADS");
        groupLogo.setBounds(1300, 750, 800, 300);
        groupLogo.setFont(new Font("Impact", Font.PLAIN, 60));
        groupLogo.setForeground(Color.white);
        this.add(groupLogo);

        
        
        this.setVisible(true);
        theFrame = this;
    }
    
    //Methods (These methods are static only for easier access)
    public static JPanel getFirstScreen()
    {
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

        JLabel gameLogo = new JLabel("ARENA ISTANBUL");
        gameLogo.setBounds(1150, 100, 800, 300);
        gameLogo.setFont(new Font("Impact", Font.PLAIN, 100));
        gameLogo.setForeground(Color.white);
        firstScreen.add(gameLogo);

        JLabel groupLogo = new JLabel("TECHGLADS");
        groupLogo.setBounds(1300, 750, 800, 300);
        groupLogo.setFont(new Font("Impact", Font.PLAIN, 80));
        groupLogo.setForeground(Color.white);
        firstScreen.add(groupLogo);
        //Remove the fake JLabels

        for(java.awt.Component removeLabel : theFrame.getContentPane().getComponents())
        {
            if(removeLabel instanceof JLabel ) theFrame.remove(removeLabel);
        }
        //Remove the previous JPanel if existant
        removePrevPanels();

        firstScreen.add(backgroundLabel);

        theFrame.add(firstScreen);

        theFrame.repaint();
        return firstScreen;
    }

    public static JPanel newGameScreen()
    {
        JPanel newGameScreen = new JPanel();
        newGameScreen.setLayout(null);
        ImageIcon backgroundIcon = new ImageIcon("graphs/gptMainScreen2.png");
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(backgroundIcon);
        backgroundLabel.setBounds(0, 0, 1920, 1080);
        
        newGameScreen.setBounds(0, 0, 1920, 1080);
        
        //Remove the previous JPanel if existant
        removePrevPanels();

        JLabel backButton = new JLabel();
        backButton.setIcon(new ImageIcon("graphs/arrow.png"));
        backButton.setText("BACK");
        backButton.setHorizontalTextPosition(JLabel.CENTER);
        backButton.setForeground(Color.white);
        backButton.setFont(new Font("Impact", Font.PLAIN, 50));
        backButton.setVerticalAlignment(JLabel.CENTER);
        backButton.setHorizontalAlignment(JLabel.CENTER);
        backButton.setBounds(1500, 850, 400, 200);
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

        //Adding the labels
        newGameScreen.add(backButton);
        newGameScreen.add(backgroundLabel);

        theFrame.add(newGameScreen);
        theFrame.repaint();
        return newGameScreen;
    }


    public static void removePrevPanels()
    {
        //Remove the previous JPanel if existant
        if(theFrame.getContentPane().getComponents().length > 0)
        {
            for(Component c : theFrame.getContentPane().getComponents())
            {
                if(c instanceof JPanel) theFrame.remove(c);
            }
        }
    }


}
