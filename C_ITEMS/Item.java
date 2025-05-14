package C_ITEMS;


import java.awt.Image;

import C.*;
import Screens.StoryScreen;
import m.MainFrame;

public abstract class Item{
    public static final int AMOUNT_OF_ICONS = 4;//WOODEN IRON GOLD DIOMAND
    protected int price = 0;
    protected String name;
    protected Person owner = null;
    protected double defenseBoost = 0;
    protected double offenseBoost = 0;
    protected Image image;
    
    


    //GETTERS AND SETTERS

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getOwner() {
        return this.owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public double getDefenseBoost() {
        return this.defenseBoost;
    }

    public void setDefenseBoost(double defenseBoost) {
        this.defenseBoost = defenseBoost;
    }

    public double getOffenseBoost() {
        return this.offenseBoost;
    }

    public void setOffenseBoost(double offenseBoost) {
        this.offenseBoost = offenseBoost;
    }

    public Image getImage()
    {
        if(this instanceof Shield)
        {
            Shield item = (Shield)this;
            return item.getAppropriateImage();
        }
        else if(this instanceof Armor)
        {
            Armor item = (Armor)this;
            return item.getAppropriateImage();
        }
        else if(this instanceof Leggings)
        {
            Leggings item = (Leggings)this;
            return item.getAppropriateImage();
        }
        else if(this instanceof Helmet)
        {
            Helmet item = (Helmet)this;
            return item.getAppropriateImage();
        }
        else if(this instanceof Sword)
        {
            Sword item = (Sword)this;
            return item.getAppropriateImage();
        }
        
        else if(this instanceof Cat)
        {
            if(MainFrame.currentPanel instanceof StoryScreen)return null;
            Cat item = (Cat)this;
            return item.getAppropriateImage();
        }
        return null;
    }
    public static int getPriceForLevel(int lev)
    {
        if(lev == 0)return 100;
        if(lev == 1)return 200;
        if(lev == 2)return 300;
        return 400;
    }
    public static String itemsFolder(Person person)
    {
        if(person instanceof User)return "items";
        else return "itemsForOpponents";
    }
    public static String getTypeOfLevel(int level)
    {
        switch (level) {
            case 0:
                return "Wooden";
            case 1:
                return "Silver";
            case 2:
                return "Golden";
            case 3:
                return "Diomand";
        }
        return "";
    }

    public void setImage(int i)
    {
        if(this instanceof Shield)
        {
            Shield item = (Shield)this;
            item.setImage(i);
        }
        else if(this instanceof Armor)
        {
            Armor item = (Armor)this;
            item.setImage(i);
        }
        else if(this instanceof Leggings)
        {
            Leggings item = (Leggings)this;
            item.setImage(i);
        }
        else if(this instanceof Helmet)
        {
            Helmet item = (Helmet)this;
            item.setImage(i);
        }
        else if(this instanceof Sword)
        {
            Sword item = (Sword)this;
            item.setImage(i);
        }
    }
    public int getImageOrder()
    {
        if(this instanceof Shield)
        {
            Shield item = (Shield)this;
            return item.getImageOrder();
        }
        else if(this instanceof Armor)
        {
            Armor item = (Armor)this;
            return item.getImageOrder();
        }
        else if(this instanceof Leggings)
        {
            Leggings item = (Leggings)this;
            return item.getImageOrder();
        }
        else if(this instanceof Helmet)
        {
            Helmet item = (Helmet)this;
            return item.getImageOrder();
        }
        else if(this instanceof Sword)
        {
            Sword item = (Sword)this;
            return item.getImageOrder();
        }
        return -2;
    }
    
}
