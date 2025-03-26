package C;

import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class FighterPanel extends JPanel{
    private static String[] FIGHTERPATHS = new String[Fighter.AMOUNT_OF_FIGHTERS];
    private static boolean hasFilledPaths = false;
    private Image image;

    public FighterPanel(int i)
    {
        if(!hasFilledPaths) fillPaths();
        //Get image
        try {
        this.image = ImageIO.read(getClass().getResource(FIGHTERPATHS[i]));
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

    public static void fillPaths()
    {
        for(int i = 0; i < FIGHTERPATHS.length; i++)
        {
            FIGHTERPATHS[i] = "/graphs/fighters/" + i + ".png";
        }
        hasFilledPaths = true;
    }
}
