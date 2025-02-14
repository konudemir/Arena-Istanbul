import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainFrame extends JFrame{
    
    
    public MainFrame() {
        this.setLayout(null);
        this.setSize(1920, 1080);
        this.setTitle("Game Name");
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

        mainScreenButton loadGameButton = new mainScreenButton("Load Game",100, 375, 400, 115);
        this.add(loadGameButton);
        
        mainScreenButton settingsButton = new mainScreenButton("Settings",100, 525, 400, 115);
        this.add(settingsButton);
        
        mainScreenButton creditsButton = new mainScreenButton("Credits",100, 675, 400, 115);
        this.add(creditsButton);

        JLabel gameLogo = new JLabel("GAME LOGO");
        gameLogo.setBounds(1200, 100, 800, 300);
        gameLogo.setFont(new Font("Impact", Font.PLAIN, 120));
        gameLogo.setForeground(Color.white);
        this.add(gameLogo);

        JLabel groupLogo = new JLabel("GROUP LOGO");
        groupLogo.setBounds(1375, 750, 800, 300);
        groupLogo.setFont(new Font("Impact", Font.PLAIN, 60));
        groupLogo.setForeground(Color.white);
        this.add(groupLogo);

        
        


        this.setVisible(true);
    }
    /*
    private JLabel createFirstScreenButton(String context, int x, int y, int width, int height)
    {
        ImageIcon mainButton = new ImageIcon("button.png");
        
        
        //If the requested with or height isn't the same as the button
        if(mainButton.getIconHeight() != height || mainButton.getIconWidth() != width)
        {
            mainButton = new ImageIcon(mainButton.getImage().getScaledInstance(width, height, Image.SCALE_FAST));
        }

        final ImageIcon buttonIcon = mainButton;
        final ImageIcon clickedButtonIcon = new ImageIcon("clickedButton.png");

        JLabel button = new JLabel();
        button.setIcon(new ImageIcon("button.png"));
        button.setText(context);
        button.setHorizontalTextPosition(JLabel.CENTER);
        button.setForeground(Color.white);
        button.setFont(new Font("Impact", Font.PLAIN, 60));
        button.setVerticalAlignment(JLabel.CENTER);
        button.setHorizontalAlignment(JLabel.CENTER);
        
        button.setBounds(x, y + 25, width, height);// +25 to adjust the buttons after i wrote their codes
        
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Define your action here
                System.out.println("Clicked " + context);
            }
    
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setIcon(clickedButtonIcon);
                button.setFont(new Font("Impact", Font.PLAIN, 55));
                button.repaint();
            }
    
            @Override
            public void mouseExited(MouseEvent e) {
                button.setIcon(buttonIcon);
                button.setFont(new Font("Impact", Font.PLAIN, 60));
                button.repaint();
            }
        });
        
        return button;
    }
        */


}
