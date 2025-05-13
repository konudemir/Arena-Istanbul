package C_ITEMS;

import javax.swing.ImageIcon;

import C.*;
import Coloring.Coloring;
import m.Main;

import java.awt.Color;
import java.awt.Image;

public class Sword extends Item{
    public int level;
    public static Image[][] icons = new Image[5][Coloring.AMOUNT];//First dimension: Levels, Second Dimension: Different sizes and positions
    public static Image[][] iconsForOpponents = new Image[Item.AMOUNT_OF_ICONS][Coloring.AMOUNT];//First dimension: Levels, Second Dimension: Different sizes and positions
    public Sword(int level)
    {
        this.image = icons[level][0];
        this.level = level;
        this.price = getPriceForLevel(level);
        this.offenseBoost = getAttackForLevel(level);
        this.defenseBoost = getDefenceForLevel(level);
    }
    public Sword(int level,Person owner)
    {
        this.image = icons[level][0];
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
    public static double getAttackForLevel(int level)
    {
        if(level == 0)return 0.20;
        if(level == 1)return 0.23;
        if(level == 2)return 0.26;
        if(level == 3)return 0.30;
        else return 0.70;
    }
    public String getName()
    {
        return Item.getTypeOfLevel(this.level) + " " + "Sword";
    }
    public int getPrice()
    {
        return Item.getPriceForLevel(this.level);
    }
    public static double getDefenceForLevel(int level)
    {
        return 0;
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
                    icons[i][j] = new ImageIcon("graphs/character/items/swords/" + (i) + "/" + Coloring.names[j] + ".png").getImage();
                    } catch (Exception e) {
                        System.out.println("NO SWORD IMAGE FOR i,j:" + i + " " + j);
                    }
            }
            for(int j = 0; j < Coloring.AMOUNT; j++)
            {
                try {
                    iconsForOpponents[i][j] = new ImageIcon("graphs/character/itemsForOpponents/swords/" + (i) + "/" + Coloring.names[j] + ".png").getImage();
                    } catch (Exception e) {
                        System.out.println("NO SWORD IMAGE FOR i,j:" + i + " " + j);
                    }
            }
            
        }
        for(int i = 0; i < Coloring.AMOUNT; i++)
        {
            try {
                    icons[4][i] = new ImageIcon("graphs/character/items/swords/4/" + Coloring.names[i] + ".png").getImage();
                    } catch (Exception e) {
                        System.out.println("NO SWORD IMAGE FOR HACKED FOR " + Coloring.names[i]);
                    }
        }
    }
    public Image getAppropriateImage()
    {
        for(int i = 0; i < Coloring.AMOUNT; i++)
        {
            if(((this.level != 4) && (this.image == icons[level][i] || this.image == iconsForOpponents[level][i])) || (this.level == 4) && this.image == icons[4][i])
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
    public void setImage(int i)
    {
        if(this.owner instanceof Fighter)this.image = iconsForOpponents[this.level][i];
        else this.image = icons[this.level][i];
        //System.out.println("Sword set image to " + i + " image: " + this.image);
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
