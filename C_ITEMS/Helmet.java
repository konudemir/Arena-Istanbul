package C_ITEMS;

import javax.swing.ImageIcon;

import C.*;
import m.Main;
import java.awt.Image;
import Coloring.*;

public class Helmet extends Item{
    public int level;
    public static Image[][] icons = new Image[Item.AMOUNT_OF_ICONS][Coloring.AMOUNT];//First dimension: Levels, Second Dimension: Different sizes and positions
    public static Image[][] iconsForOpponents = new Image[Item.AMOUNT_OF_ICONS][Coloring.AMOUNT];//First dimension: Levels, Second Dimension: Different sizes and positions
    public Helmet(int level)
    {
        this.image = icons[level][0];
        this.level = level;
        this.price = getPriceForLevel(level);
        this.offenseBoost = getAttackForLevel(level);
        this.defenseBoost = getDefenceForLevel(level);
    }
    public Helmet(int level,Person owner)
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
        //ToDo fill out the prices for each level
        return 0;
    }
    public static double getAttackForLevel(int level)
    {
        //ToDo fill out for each level
        return 0;
    }
    public static double getDefenceForLevel(int level)
    {
        //ToDo fill out for each level
        return 0;
    }public void setOwner(Person owner) {
        this.owner = owner;
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

    public static void fillIcons()
    {
        for(int i = 0; i < Item.AMOUNT_OF_ICONS; i++)
        {
            for(int j = 0; j < Coloring.AMOUNT; j++)
            {
                try {
                    icons[i][j] = new ImageIcon("graphs/character/items/helmet/" + (i) + "/" + Coloring.names[j] + ".png").getImage();
                    } catch (Exception e) {
                        System.out.println("NO HELMET IMAGE FOR i,j:" + i + " " + j);
                    }
            }
        }
        for(int i = 0; i < Item.AMOUNT_OF_ICONS; i++)
        {
            for(int j = 0; j < Coloring.AMOUNT; j++)
            {
                try {
                    iconsForOpponents[i][j] = new ImageIcon("graphs/character/itemsForOpponents/helmet/" + (i) + "/" + Coloring.names[j] + ".png").getImage();
                    } catch (Exception e) {
                        System.out.println("NO HELMET IMAGE FOR i,j:" + i + " " + j);
                    }
            }
        }
    }
    public void setImage(int i)
    {
        if(this.owner instanceof Fighter)this.image = iconsForOpponents[this.level][i];
        else this.image = icons[this.level][i];
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
