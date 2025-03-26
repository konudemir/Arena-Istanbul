package Screens;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
        
        this.add(CharacterPanel.getCharPanel());
        this.add(shelf);
        this.add(merchant);
        this.add(backgroundLabel);
        theFrame.add(this);
        theFrame.repaint();
    }
}
