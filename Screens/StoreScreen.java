package Screens;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import C.CharacterPanel;
import C.User;
import m.MainFrame;

public class StoreScreen extends JPanel{
    public static MainFrame theFrame;
    public StoreScreen()
    {
        MainFrame.currentPanel = this;
        theFrame = MainFrame.theFrame;
        this.setLayout(null);
        ImageIcon backgroundIcon = new ImageIcon("graphs/store.png");
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(backgroundIcon);
        backgroundLabel.setBounds(0, 0, 1920, 1080);
        
        this.setBounds(0, 0, 1920, 1080);
        
        //Remove the previous JPanel if existant
        theFrame.removePrevPanelsAndLabels();
        User.getUser().moveTo(1000, 530);

        JLabel merchant = new JLabel();
        merchant.setIcon(new ImageIcon("graphs/merchant.png"));
        merchant.setBounds(700, 50, 1920, 1080);
        
        JLabel shelf = new JLabel();
        shelf.setIcon(new ImageIcon("graphs/storeShelf.png"));
        shelf.setBounds(0, 0, 1920, 1080);

        JLabel grayBG = new JLabel();
        grayBG.setIcon(new ImageIcon("graphs/mockupStore.png"));
        grayBG.setBounds(0, 0, 500, 1080);

        JLabel coinSpace = new JLabel();
        coinSpace.setIcon(new ImageIcon("graphs/coinSpace0.png"));
        coinSpace.setText("" + User.getUser().getCoins());
        coinSpace.setFont(new Font("Impact", Font.PLAIN, 100));
        coinSpace.setForeground(Color.white);
        coinSpace.setBounds(1450, 120, 400, 115);

        
        this.add(CharacterPanel.getCharPanel());
        this.add(grayBG);
        this.add(coinSpace);
        this.add(shelf);
        this.add(merchant);
        this.add(backgroundLabel);
        theFrame.add(this);
        theFrame.repaint();
    }
}
