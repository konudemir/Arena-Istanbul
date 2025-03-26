package C;

import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class CharacterPanel extends JPanel{
    private static final int AMOUNT_OF_CHARACTERS = 5;
    private static String[] CHARPATHS = new String[AMOUNT_OF_CHARACTERS];
    private static boolean hasFilledPaths = false;
    private Image image;
    private static CharacterPanel theCharPanel;

    public CharacterPanel(int i)
    {
        theCharPanel = this;
        if(!hasFilledPaths) fillPaths();
        //Get image
        try {
        this.image = ImageIO.read(getClass().getResource(CHARPATHS[i]));
        } catch (Exception e) {
            System.out.println("NO IMAGE FOR i:" + i);
        }
        this.setBounds(1000, 300, 920, 580);
        this.setLayout(null);
        this.setVisible(true);
        this.setOpaque(false);
    }
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }
    public void resize(int height, int width)
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
    public static CharacterPanel getCharPanel()
    {
        return theCharPanel;
    }

    public static void fillPaths()
    {
        for(int i = 0; i < CHARPATHS.length; i++)
        {
            CHARPATHS[i] = "/graphs/character/" + i + ".png";
        }
        hasFilledPaths = true;
    }
}
