package C_ITEMS;

import javax.swing.ImageIcon;

import C.Person;
import m.Main;
import java.awt.Image;
import Coloring.*;

public class Armor extends Item{
    public int level;
    public static Image[][] icons = new Image[Item.AMOUNT_OF_ICONS][Coloring.AMOUNT];//First dimension: Levels, Second Dimension: Different sizes and positions
    public Armor(String name, int level, int price, double attack, double defense)
    {
        this(name, level, price, attack, defense, null);
    }
    public Armor(String name, int level, int price, double attack, double defense, Person owner)
    {
        this.image = icons[level][0];
        //this.setIcon(image);
        this.name = name;
        this.level = level;
        this.price = price;
        this.offenseBoost = attack;
        this.defenseBoost = defense;
        this.owner = owner;
    }

    public static void fillIcons()
    {
        for(int i = 0; i < Item.AMOUNT_OF_ICONS; i++)
        {
            for(int j = 0; j < Coloring.AMOUNT; j++)
            {
                System.out.println("trying " + i + " " + j);
                try {
                    icons[i][j] = new ImageIcon("graphs/character/items/armor/" + (i) + "/" + Coloring.names[j] + ".png").getImage();
                    } catch (Exception e) {
                        System.out.println("NO ARMOR IMAGE FOR i,j:" + i + " " + j);
                    }
            }
            
        }
    }
    public void setImage(int i)
    {
        this.image = icons[this.level][i];
        System.out.println("Shield set image to " + i + " image: " + this.image);
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    public int getImageOrder()
    {
        for(int i = 0; i < Coloring.AMOUNT; i++)
        {
            if(this.image == icons[level][i])return i;
        }
        return -1;
    }
}
