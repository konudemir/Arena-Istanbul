package C_ITEMS;
import java.awt.Image;

import javax.swing.ImageIcon;

import C.Fighter;
import C.Person;
import C.User;
import Coloring.*;

public class Leggings extends Item{
    public int level;
    public static Image[][] icons = new Image[Item.AMOUNT_OF_ICONS][Coloring.AMOUNT];//First dimension: Levels, Second Dimension: Different sizes and positions
    public static Image[][] iconsForOpponents = new Image[Item.AMOUNT_OF_ICONS][Coloring.AMOUNT];//First dimension: Levels, Second Dimension: Different sizes and positions
    public Leggings(int level)
    {
        this.image = icons[level][0];
        this.level = level;
        this.price = getPriceForLevel(level);
        this.offenseBoost = getAttackForLevel(level);
        this.defenseBoost = getDefenceForLevel(level);
    }
    public Leggings(int level,Person owner)
    {
        this.image = icons[level][0];
        //this.setIcon(image);
        this.level = level;
        this.price = getPriceForLevel(level);
        this.offenseBoost = getAttackForLevel(level);
        this.defenseBoost = getDefenceForLevel(level);
        this.owner = owner;
    }
    public static int getPriceForLevel(int level)
    {
        return Item.getPriceForLevel(level);
    }
    public int getPrice()
    {
        return Item.getPriceForLevel(this.level);
    }
    public String getName()
    {
        return Item.getTypeOfLevel(this.level) + " " + "Leggings";
    }
    public static double getAttackForLevel(int level)
    {
        return 0;
    }
    public static double getDefenceForLevel(int level)
    {
        if(level == 0)return 0.03;
        if(level == 1)return 0.06;
        if(level == 2)return 0.09;
        return 0.12;
    }
    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public static void fillIcons()
    {
        for(int i = 0; i < Item.AMOUNT_OF_ICONS; i++)
        {
            for(int j = 0; j < Coloring.AMOUNT; j++)
            {
                try {
                    icons[i][j] = new ImageIcon("graphs/character/items/leggings/" + (i) + "/" + Coloring.names[j] + ".png").getImage();
                    } catch (Exception e) {
                        System.out.println("NO LEGGINGS IMAGE FOR i,j:" + i + " " + j);
                    }
            }
            for(int j = 0; j < Coloring.AMOUNT; j++)
            {
                try {
                    iconsForOpponents[i][j] = new ImageIcon("graphs/character/itemsForOpponents/leggings/" + (i) + "/" + Coloring.names[j] + ".png").getImage();
                    } catch (Exception e) {
                        System.out.println("NO LEGGINGS IMAGE FOR i,j:" + i + " " + j);
                    }
            }
            
        }
    }
    public void setImage(int i)
    {
        if(this.owner instanceof Fighter)this.image = iconsForOpponents[this.level][i];
        else this.image = icons[this.level][i];
    }
    public Image getAppropriateImage()
    {
        for(int i = 0; i < Coloring.AMOUNT; i++)
        {
            if(this.image == icons[level][i] || this.image == iconsForOpponents[level][i])
            {
                if(this.owner instanceof User)
                {
                    this.image = icons[level][i];
                    return icons[level][i];
                }
                else
                {
                    this.image = iconsForOpponents[level][i];
                    return iconsForOpponents[level][i];
                }
            }
        }
        return null;
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
            if(this.image == iconsForOpponents[level][i])return 20 + i;
        }
        return -1;
    }
}
