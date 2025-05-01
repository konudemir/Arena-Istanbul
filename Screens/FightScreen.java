package Screens;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import C.*;
import m.MainFrame;
public class FightScreen extends JPanel{
    public static final int NORMAL_ATTACK_HIT = 8;
    public static MainFrame theFrame;
    public static int wonAgainstEnemies = 0;
    public FightScreen(User user, Fighter fighter)
    {
        CharacterPanel.getCharPanel().setImage(this);
        CharacterPanel.getCharPanel().moveTo(0, 500);
        MainFrame.currentPanel = this;
        theFrame = MainFrame.theFrame;
        this.setLayout(null);
        ImageIcon backgroundIcon = new ImageIcon("graphs/arenaScene.png");
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(backgroundIcon);
        backgroundLabel.setBounds(0, 0, 1920, 1080);
        //CAT
        JLabel cat = new JLabel();
        cat.setIcon(new ImageIcon("graphs/fight/cat.png"));
        cat.setBounds(650, 850, 200, 200);
        this.add(cat);
        
        this.setBounds(0, 0, 1920, 1080);
        
        //Remove the previous JPanel if existant
        theFrame.removePrevPanelsAndLabels();
        this.add(CharacterPanel.getCharPanel());
        theFrame.add(user.getHealthBar());
        this.add(fighter.getFighterPanel());
        
        //fighter.resizeComp(300, 132);
        fighter.moveTo(1100, 680);
        theFrame.add(fighter.getHealthBar());

        JLabel moveForward = new JLabel();
        moveForward.setIcon(new ImageIcon("graphs/fight/moveForward.png"));
        moveForward.setBounds(460 - (200), 420+ 108, 200, 200);
        this.add(moveForward);
        JLabel moveBackwards = new JLabel();
        moveBackwards.setIcon(new ImageIcon("graphs/fight/moveBackwards.png"));
        moveBackwards.setBounds(380 - (200), 510+ 108, 200, 200);
        this.add(moveBackwards);
        JLabel attack = new JLabel();
        attack.setIcon(new ImageIcon("graphs/fight/attack.png"));
        attack.setBounds(585 - (200), 420+ 108, 200, 200);
        this.add(attack);
        JLabel sleep = new JLabel();
        sleep.setIcon(new ImageIcon("graphs/fight/sleep.png"));
        sleep.setBounds(660 - (200), 510+ 108, 200, 200);
        this.add(sleep);
        JLabel usePet = new JLabel();
        usePet.setIcon(new ImageIcon("graphs/fight/usePet.png"));
        usePet.setBounds(660 - (200), 630+ 108, 200, 200);
        this.add(usePet);

        this.add(backgroundLabel);
        CharacterPanel.getCharPanel().breath();
        theFrame.add(this);
        theFrame.repaint();
    }
    public FightScreen()
    {
        //ToDo : This will be a main fight screen beforehand so that the user decides if they want to fight in the story mode or the arena mode.
        //Also make this the main fightScreen constructor and the only one that is public after you complete implementing this constructor.
        //Temporary code:
        this(User.getUser(), new Fighter(0));
    }
    public void rp()
    {
        this.repaint();
    }
}