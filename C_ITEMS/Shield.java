package C_ITEMS;
import C.*;

import java.io.File;

import javax.swing.ImageIcon;

public class Shield extends Item{
    public static final int AMOUNT_OF_ICONS = 1;
    public int order;
    public static ImageIcon[] icons = new ImageIcon[AMOUNT_OF_ICONS];
    public Shield(String name, int order, int price, double attack, double defense)
    {
        this(name, order, price, attack, defense, null);
    }
    public Shield(String name, int order, int price, double attack, double defense, Person owner)
    {
        this.image = icons[order];
        //this.setIcon(image);
        this.name = name;
        this.order = order;
        this.price = price;
        this.offenseBoost = attack;
        this.defenseBoost = defense;
        this.owner = owner;
        //this.setBounds(0, 0, 100, 100);
        //this.setVisible(true);
    }

    public static void fillIcons()
    {
        for(int i = 0; i < AMOUNT_OF_ICONS; i++)
        {
            System.out.println("TRYING SHIELD IMAGE FOR " + i);
            try {
            icons[i] = new ImageIcon("graphs/items/shields/" + (i) + ".png");
            } catch (Exception e) {
                System.out.println("NO SHIELD IMAGE FOR i:" + i);
            }
        }
    }

    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
