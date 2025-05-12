package C_ITEMS;

import javax.swing.ImageIcon;

import Coloring.*;
import Screens.FightScreen;
import C.Fighter;
import C.Person;
import C.User;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

public class Cat extends Item{
    public int level;
    public static Image[] icons = new Image[Coloring.AMOUNT];//First dimension: Levels, Second Dimension: Different sizes and positions
    public Cat()
    {
        this.image = icons[0];
        this.level = 0;
        this.price = getPriceForLevel(level);
        this.offenseBoost = getAttackForLevel(level);
        this.defenseBoost = getDefenceForLevel(level);
    }
    public static int getPriceForLevel(int level)
    {
        return Item.getPriceForLevel(level);
    }
    public static double getAttackForLevel(int level)
    {
        return 0;
    }
    public int getPrice()
    {
        return Item.getPriceForLevel(this.level);
    }
    public String getName()
    {
        return Item.getTypeOfLevel(this.level) + " " + "Cat";
    }
    public static double getDefenceForLevel(int level)
    {
        if(level == 0)return 0.03;
        if(level == 1)return 0.06;
        if(level == 2)return 0.09;
        return 0.12;
    }public void setOwner(Person owner) {
        this.owner = owner;
    }
    public Image getAppropriateImage()
    {
        return this.image;
    }

    public static void fillIcons()
    {
        for(int j = 0; j < Coloring.AMOUNT; j++)
        {
            try {
                icons[j] = new ImageIcon("graphs/character/items/cat/" + Coloring.names[j] + ".png").getImage();
                } catch (Exception e) {
                    System.out.println("NO CAT IMAGE FOR i:"  + j);
                }
        }
    }
    public static int getAttackForDistance()
    {
        if(User.getCharPanel().getX() >= 350 && FightScreen.theFightScreen.theFighter.getFighterPanel().getX() <= 750)return new Random().nextInt(5) + 3;//3-7
        else return 0;
    }
    public void setImage(int i)
    {
        this.image = icons[i];
        //System.out.println("Shield set image to " + i + " image: " + this.image);
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
            if(this.image == icons[i])return i;
        }
        return -1;
    }
}
