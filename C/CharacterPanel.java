package C;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Screens.FightScreen;
import m.MainFrame;

public class CharacterPanel extends JPanel{
    private static final int AMOUNT_OF_CHARACTERS = 5;
    private static String[] CHARPATHS = new String[AMOUNT_OF_CHARACTERS];
    private boolean hasFilledPaths = false;
    private Image image;
    private static CharacterPanel theCharPanel;
    private Image[] idle = new Image[3];
    private int charChosen = 0;

    public CharacterPanel(int i)
    {
        theCharPanel = this;
        if(!hasFilledPaths) fillPaths();
        //Get image
        this.image = idle[2];
        this.setBounds(1000, 300, 920, 580);
        this.setLayout(null);
        this.setVisible(true);
        this.setOpaque(false);
    }
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }
    //The issue is that we create a new image object with different size so we can't see whats equal
    //Solution resizeComp to a fixed final int size and put it in the idle array that way
    public void resizeComp(int height, int width)
    {
        this.image = this.image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }
    public void moveTo(int x, int y)
    {
        this.setBounds(x, y, this.image.getWidth(null), this.image.getHeight(null));
    }
    public void moveBy(int x, int y)
    {
        this.setBounds(this.getX() + x, this.getY() + y, this.image.getWidth(null), this.image.getHeight(null));
    }
    public void setSize(int width, int height)
    {
        this.image = this.image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }
    public void setHeight(int height)
    {
        int width = (int)((double)height / (double)this.image.getHeight(null) * (double)this.image.getWidth(null));
        this.image = this.image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }
    public static CharacterPanel getCharPanel()
    {
        return theCharPanel;
    }
    public void setImage(Image image)
    {
        this.image = image;
    }

    public void fillPaths()
    {
        for(int i = 0; i < 3; i++)
        try {
            idle[i] = ImageIO.read(getClass().getResource("/graphs/character/" + charChosen + "/" + (i + 1) + ".png"));
            } catch (Exception e) {
                System.out.println("NO IMAGE FOR i:" + i);
            }

        hasFilledPaths = true;
    }

    public void breath()
    {
        Timer timer = new Timer(); // create the timer
        CharacterPanel thisPanel = this;
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                CharacterPanel.setNextImageIdleOf(thisPanel);
                System.out.println("aa");
            }
        };
        timer.schedule(task, 1000, 5);
    }
    public static Image setNextImageIdleOf(CharacterPanel panel)
    {
        for(int i = 0; i < 3; i++) 
        if(panel.image == panel.idle[i])
        {
            Image im = panel.image;
            panel.image = panel.idle[(i+1) % 3];
            System.out.println("aabbcc");
            System.out.println(im == panel.image);
            break;
        }
        panel.repaint();
        return panel.image;
    }
}
