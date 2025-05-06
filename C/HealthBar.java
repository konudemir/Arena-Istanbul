package C;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class HealthBar extends JLabel{
    
    private Image bar;
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
        g.setColor((person instanceof Fighter) ? new Color(0x1355A9) : new Color(0xE37D3D));
        g.fillRect(positionsXY[0] + 95, positionsXY[1] + 55, (int)(person.health * 4.38), 33);
        g.setColor(Color.white);
        g.setFont(new Font("Impact", Font.PLAIN, 30));
        g.drawString("HEALTH " + this.person.health, positionsXY[0] + 95 + 25, positionsXY[1] + 55 + 28);
        g.drawImage(this.bar, positionsXY[0], positionsXY[1], null);
    }
}
