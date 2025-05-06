package C;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class StaminaBar extends JLabel{
    
    private Image bar;
    private Person person;
    private static int[] POSITIONS_FOR_USER_BAR = new int[] {100 , 120};
    private static int[] POSITIONS_FOR_FIGHTER_BAR = new int[] {1292 , 120};
    private int[] positionsXY = new int[2];
    public StaminaBar(Person person)
    {
        this.setBounds(0, 0, 1920, 300);
        this.setVisible(true);
        this.setOpaque(false);
        this.person = person;
        if(person instanceof Fighter)
        {
            try {
                this.bar = ImageIO.read(getClass().getResource("/graphs/staminaBar2.png"));
                } catch (Exception e) {
                    System.out.println("NO IMAGE FOR stamina bar!");
                }
            positionsXY[0] = POSITIONS_FOR_FIGHTER_BAR[0];
            positionsXY[1] = POSITIONS_FOR_FIGHTER_BAR[1];
        }
        else {
            try {
                this.bar = ImageIO.read(getClass().getResource("/graphs/staminaBar1.png"));
                } catch (Exception e) {
                    System.out.println("NO IMAGE FOR user stamina bar!");
                }
                positionsXY[0] = POSITIONS_FOR_USER_BAR[0];
                positionsXY[1] = POSITIONS_FOR_USER_BAR[1];
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor((person instanceof Fighter) ? new Color(0x1355A9) : new Color(0xE37D3D));
        g.fillRect(positionsXY[0] + 62, positionsXY[1] + 35, (int)(person.stamina * 2.78), 21);
        g.setColor(Color.white);
        g.setFont(new Font("Impact", Font.PLAIN, 20));
        g.drawString("STAMINA " + this.person.stamina, positionsXY[0] + 95 - 20, positionsXY[1] + 55 - 3);
        g.drawImage(this.bar, positionsXY[0], positionsXY[1], null);
    }
    public void repaintIt()
    {
        this.repaint();
    }
}
