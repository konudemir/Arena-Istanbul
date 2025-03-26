package C;

import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class HealthBar extends JLabel{
    
    private Image bar;
    private int health;
    private Person person;
    private static int[] POSITIONS_FOR_USER_BAR = new int[] {50 , 20};
    private static int[] POSITIONS_FOR_FIGHTER_BAR = new int[] {1242 , 20};
    private int[] positionsXY = new int[2];
    public HealthBar(Person person)
    {
        this.setBounds(0, 0, 1920, 300);
        this.setVisible(true);
        this.setOpaque(false);
        this.person = person;
        this.health = person.getHealth();
        if(person instanceof Fighter)
        {
            try {
                this.bar = ImageIO.read(getClass().getResource("/graphs/bar2.png"));
                } catch (Exception e) {
                    System.out.println("NO IMAGE FOR fighter bar!");
                }
            positionsXY[0] = POSITIONS_FOR_FIGHTER_BAR[0];
            positionsXY[1] = POSITIONS_FOR_FIGHTER_BAR[1];
        }
        else {
            try {
                this.bar = ImageIO.read(getClass().getResource("/graphs/bar1.png"));
                } catch (Exception e) {
                    System.out.println("NO IMAGE FOR user bar!");
                }
                positionsXY[0] = POSITIONS_FOR_USER_BAR[0];
                positionsXY[1] = POSITIONS_FOR_USER_BAR[1];
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(this.bar, positionsXY[0], positionsXY[1], null);
    }
    public void updateHealth(int health)
    {
        this.health = health;
        repaint();
    }
}
