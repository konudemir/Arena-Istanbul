package d;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
public class UiFrame extends JFrame{
    public UiFrame()
    {
        this.setLayout(null);
        this.setSize(1920, 1080);
        this.setTitle("GUI DESIGNER");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

        ImageIcon frameLogo = new ImageIcon("graphs/tadic.png");

        this.setIconImage(frameLogo.getImage());
        this.getContentPane().setBackground(Color.YELLOW);
    }
}