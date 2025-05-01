package C;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import C_ITEMS.*;
import Screens.FightScreen;

public class Person {
    protected int health;
    protected ArrayList<Item> itemsList = new ArrayList<>();
    protected FighterPanel panel;
    public static final double RANDOMNESS_IN_FIGHTS = 0.2;
    protected int currentPhoto = 1; //0 to 9
    public Person()
    {
        this.health = 100;
    }
    public int getHealth()
    {
        return this.health;
    }
    public boolean didItGetHit(double attackPower, int hit)
    {
        double totalDefensePower = 0;
        if(itemsList.size() > 0)
        for(Item i : itemsList)
        {
            totalDefensePower += i.getDefenseBoost();
        }
        if(totalDefensePower > 1) totalDefensePower = 1.0;
        //First bit of randomness
        Random random = new Random();
        totalDefensePower += random.nextDouble(2 * RANDOMNESS_IN_FIGHTS) - RANDOMNESS_IN_FIGHTS;
        if(totalDefensePower > 1) totalDefensePower = 1.0;
        //Decide if it got hit (randomness 2)
        double randomDouble = random.nextDouble(1.0);
        if(randomDouble <= totalDefensePower)
        {
            this.changeHealth(- hit);
            return true;
        }
        return false;
    }
    public Shield getShield()
    {
        if(this.itemsList.size() == 0)return null;
        for(Item i : this.itemsList)
        {
            if(i instanceof Shield) return (Shield)i;
        } return null;
    }
    public void buyItem(Item item)
    {
        if(this.itemsList.size() > 0)
        {
            if(this.itemsList.contains(item))return;
            for(Item i : itemsList)
            {
                if(i.getClass() == item.getClass() )
                {
                    this.sellItem(i);
                }
            }
        }
        itemsList.add(item);
        //this.panel.updateItems();
        if(this instanceof User)
        {
            User u = (User)this;
            u.changeCoins(- item.getPrice());
        }
        System.out.println("BOUGHT " + item);
    }
    public void sellItem(Item item)
    {
        if(this.itemsList.size() == 0)return;
        for(Item i : itemsList)
        {
            if(i == item)
            {
                itemsList.remove(i);
                if(this instanceof User)
                {
                    User u = (User)this;
                    u.changeCoins(i.getPrice());
                    return;
                }
            }
        }
    }
    public int getCurrentCharacterPhoto()
    {
        return this.currentPhoto;
    }

    public void changeHealth(int i)
    {
        this.health += i;
    }
    public Item[] listItemsInPriorityOfShowing()
    {
        List<Item> ordered = new ArrayList<>();
        for (Class<?> type : new Class[]{Leggings.class, Armor.class, Helmet.class, Shield.class, Sword.class}) {
            for (Item item : itemsList) {
                if (type.isInstance(item)) {
                    ordered.add(item);
                    break;
                }
            }
        }
        return ordered.toArray(new Item[0]);
    }
}
