package Screens;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import C.CharacterPanel;
import m.MainFrame;

public class LobbyScreen extends JPanel{
    public static MainFrame theFrame;
    public LobbyScreen()
    {
        MainFrame.currentPanel = this;
        theFrame = MainFrame.theFrame;
        this.setLayout(null);
        ImageIcon backgroundIcon = new ImageIcon("graphs/lobby.png");
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(backgroundIcon);
        backgroundLabel.setBounds(0, 0, 1920, 1080);
        theFrame.removePrevPanelsAndLabels();
        this.setBounds(0, 0, 1920, 1080);
        this.add(CharacterPanel.getCharPanel());
        CharacterPanel.getCharPanel().moveTo(100, 560);
        this.add(backgroundLabel);

        theFrame.add(this);
        theFrame.repaint();
    }
}
