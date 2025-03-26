package Screens;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import C.*;
import m.MainFrame;
public class FightScreen extends JPanel{
    public static MainFrame theFrame;
    public FightScreen(User user, Fighter fighter)
    {
        MainFrame.currentPanel = this;
        theFrame = MainFrame.theFrame;
        this.setLayout(null);
        ImageIcon backgroundIcon = new ImageIcon("graphs/arenaScene.png");
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(backgroundIcon);
        backgroundLabel.setBounds(0, 0, 1920, 1080);
        
        this.setBounds(0, 0, 1920, 1080);
        
        //Remove the previous JPanel if existant
        theFrame.removePrevPanelsAndLabels();
        this.add(CharacterPanel.getCharPanel());
        theFrame.add(user.getHealthBar());
        User.getUser().moveTo(300, 530);

        this.add(fighter.getFighterPanel());
        fighter.resize(500, 500);
        fighter.moveTo(1100, 480);
        theFrame.add(fighter.getHealthBar());

        JLabel button1 = new JLabel();
        button1.setIcon(new ImageIcon("graphs/circle.png"));
        button1.setBounds(460, 420, 200, 200);
        this.add(button1);
        JLabel button2 = new JLabel();
        button2.setIcon(new ImageIcon("graphs/circle.png"));
        button2.setBounds(360, 510, 200, 200);
        this.add(button2);
        JLabel button3 = new JLabel();
        button3.setIcon(new ImageIcon("graphs/circle.png"));
        button3.setBounds(585, 420, 200, 200);
        this.add(button3);
        JLabel button4 = new JLabel();
        button4.setIcon(new ImageIcon("graphs/circle.png"));
        button4.setBounds(660, 510, 200, 200);
        this.add(button4);
        JLabel button5 = new JLabel();
        button5.setIcon(new ImageIcon("graphs/circle.png"));
        button5.setBounds(660, 630, 200, 200);
        this.add(button5);

        this.add(backgroundLabel);

        theFrame.add(this);
        theFrame.repaint();
    }
}