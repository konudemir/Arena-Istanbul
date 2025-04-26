package C;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import C_ITEMS.Item;

public class FighterPanel extends JPanel{
    private static String[] FIGHTERPATHS = new String[Fighter.AMOUNT_OF_FIGHTERS];
    private static boolean hasFilledPaths = false;
    protected Image image;
    protected Person person;

    public FighterPanel(int i, Person person)
    {
        this.person = person;
        if(!hasFilledPaths) fillPaths();
        //Get image
        try {
        this.image = ImageIO.read(getClass().getResource(FIGHTERPATHS[i]));
        } catch (Exception e) {
            System.out.println("NO IMAGE FOR i:" + i);
        }
        this.setBounds(1000, 300, 256, 256);
        this.setLayout(null);
        this.setVisible(true);
        this.setOpaque(false);
    }
    public FighterPanel()//Just to allow characterpanel having a empty constructor //NON USABLE
    {
        //this(0, null);
    }
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, null);
        if(this.person.itemsList.size() == 0)
        {
            System.out.println("len 0");
            return;
        }
        for(Item item : this.person.itemsList)
        {
            g.drawImage(item.getImage(), 0, 0, null);
            System.out.println("DREW " + item);
        }
    }
    public void moveTo(int x, int y)
    {
        this.setBounds(x, y, this.image.getWidth(null), this.image.getHeight(null));
    }
    /*
    public void updateItems()
    {
        System.out.println("updating items");
        for(Item item : this.person.itemsList)
        {
            for(Component jc : this.getComponents())
            {
                if(jc == item)
                this.remove(item);
                System.out.println(item + " removed");
            }
        }
        for(Item item : this.person.itemsList)
        {
            this.add(item);
            System.out.println(item + " added");
        }
        this.repaint();
    }
    */

    public void fillPaths()
    {
        for(int i = 0; i < FIGHTERPATHS.length; i++)
        {
            FIGHTERPATHS[i] = "/graphs/fighters/" + i + ".png";
        }
        hasFilledPaths = true;
    }
}
