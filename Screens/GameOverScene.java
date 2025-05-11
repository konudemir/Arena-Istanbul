package Screens;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import C.User;

import java.awt.Dimension;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import m.*;
public class GameOverScene extends JPanel{
    public static MainFrame theFrame;
    public static GameOverScene theGameOverScene;
    public JLabel backButton;
    public static int statsAdded = 0;
    public static final int TOTAL_TO_BE_ADDED = 9;
    public static final int x = 500;
    public GameOverScene()
    {
        theGameOverScene = this;
        MainFrame.currentPanel = this;
        theFrame = MainFrame.theFrame;
        this.setLayout(null);
        this.setBackground(new Color(0x5300A0));

        this.setBounds(0, 0, 1920, 1080);
        theFrame.removePrevPanelsAndLabels();

        JLabel gameOver = new JLabel("GAME OVER, YOU WON!");
        gameOver.setBounds(x, 0, 600, 100);
        gameOver.setForeground(Color.WHITE);
        gameOver.setFont(gameOver.getFont().deriveFont(50.0f));
        this.add(gameOver);

        

        // Mouse Click to proceed dialogues
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(statsAdded == TOTAL_TO_BE_ADDED)System.exit(0);
                else
                {
                    addStat(statsAdded);
                    statsAdded++;
                }
            }
        });

        theFrame.add(this);
        theFrame.repaint();
    }
    public void addStat(int order)
    {
        JLabel stat = new JLabel();
        stat.setBounds(x, 100 + (100 * order), 1200, 100);
        stat.setFont(stat.getFont().deriveFont(50.0f));
        stat.setForeground(Color.WHITE);
        if(order == 0)stat.setText("TOTAL FIGHTS FOUGHT - " + User.getUser().totalFought);
        else if(order == 1)stat.setText("TOTAL FIGHTS WON - " + User.getUser().totalWon);
        else if(order == 2)stat.setText("FINAL COINS - " + User.getUser().getCoins());
        else if(order == 3)
        {
            if(User.getUser().hasArmor())stat.setText("ARMOR LEVEL - " + User.getUser().getArmor().level);
            else stat.setText("NO ARMOR");
        }
        else if(order == 4)
        {
            if(User.getUser().hasHelmet())stat.setText("HELMET LEVEL - " + User.getUser().getHelmet().level);
            else stat.setText("NO HELMET");
        }
        else if(order == 5)
        {
            if(User.getUser().hasShield())stat.setText("SHIELD LEVEL - " + User.getUser().getShield().level);
            else stat.setText("NO SHIELD");
        }
        else if(order == 6)
        {
            if(User.getUser().hasLeggings())stat.setText("LEGGINGS LEVEL - " + User.getUser().getLeggings().level);
            else stat.setText("NO LEGGINGS");
        }
        else if(order == 7)
        {
            if(User.getUser().hasSword())stat.setText("SWORD LEVEL - " + User.getUser().getSword().level);
            else stat.setText("NO SWORD");
        }
        else if(order == 8)
        {
            stat.setText("CLICK THE SCREEN TO END THE GAME!");
        }
        this.add(stat);
        this.repaint();

    }
}
