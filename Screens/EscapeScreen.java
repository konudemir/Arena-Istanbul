package Screens;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import m.Main;
import m.MainFrame;

public class EscapeScreen extends JPanel{
    public static MainFrame theFrame;
    public EscapeScreen(JPanel panel)
    {
        MainFrame.currentPanel = this;
        theFrame = MainFrame.theFrame;
        this.setLayout(null);
        this.setBackground(Color.BLUE);
        this.setOpaque(true);
        ImageIcon backgroundIcon = new ImageIcon("graphs/semiOpaque.png");
        JLabel semiOpaqueBG = new JLabel();
        semiOpaqueBG.setIcon(backgroundIcon);
        semiOpaqueBG.setBounds(0, 0, 1920, 1080);
        this.add(semiOpaqueBG);
        this.setBounds(0, 0, 1920, 1080);
        this.repaint();
        panel.add(this);        
        panel.setComponentZOrder(this, 0);
        panel.repaint();
    }
}
