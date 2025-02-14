import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Main {

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        getFirstScreen(frame);
    }

    public static JPanel getFirstScreen(MainFrame frame)
    {
        JPanel firstScreen = new JPanel();
        firstScreen.setLayout(null);
        ImageIcon backgroundIcon = new ImageIcon("graphs/MainScreen.png");
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(backgroundIcon);
        backgroundLabel.setBounds(0, 0, 1920, 1080);
        firstScreen.add(backgroundLabel);
        firstScreen.setBounds(0, 0, 1920, 1080);

        frame.add(firstScreen);
        return firstScreen;
    }
}