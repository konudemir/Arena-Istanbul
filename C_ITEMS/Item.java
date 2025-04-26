package C_ITEMS;


import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import C.*;

public abstract class Item{
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
        return this.image;
    }


    public int getX(Person p)
    {
        //ToDo : Return the constants for the item's positions.
        return 100;
    }
    public int getY(Person p)
    {
        //ToDo : Return the constants for the item's positions.
        return 100;
    }
    
}
