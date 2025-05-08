package C;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import C_ITEMS.*;
import Screens.FightScreen;

public class Person {
    protected int health;
    protected int stamina;
    protected ArrayList<Item> itemsList = new ArrayList<>();
    protected FighterPanel panel;
    public static final double RANDOMNESS_IN_FIGHTS = 0.2;
    public static final int HIT_COEFFICIENT = 20;
    protected int currentPhoto = 1; //0 to 9
    public Person()
    {
        this.health = 100;
        this.stamina = 100;
    }
    public boolean didItGetHit(double attackPower)
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
        //TODO substract defense
        if(randomDouble <= totalDefensePower)
        {
            this.changeHealth((int)(- attackPower * HIT_COEFFICIENT));
            return true;
        }
        return false;
    }
    public double getAttackPower()
    {
        double totalAttackPower = 0;
        for(Item i : itemsList)
        {
            totalAttackPower += i.getOffenseBoost();
        }
        if(totalAttackPower > 1) totalAttackPower = 1.0;
        return totalAttackPower;
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
        item.setOwner(this);
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
        if((this.health + i) > 100)i = 100 - this.health;
        if((this.health + i) < 0)i = this.health;
        this.health += i;
        if(this instanceof User)
        {
            User u = (User)(this);
            u.getHealthBar().repaint();
            User.getCharPanel().repaint();
        }
        else if(this instanceof Fighter)
        {
            Fighter u = (Fighter)(this);
            u.getHealthBar().repaint();
            u.getFighterPanel().repaint();
        }
    }
    public void lowerStamina(int i)
    {
        if((this.stamina + i) < 0 )i = this.stamina;
        this.stamina -= i;
        if(this instanceof User)
        {
            User u = (User)(this);
            u.getStaminaBar().repaint();
            User.getCharPanel().repaint();
        }
        else if(this instanceof Fighter)
        {
            Fighter u = (Fighter)(this);
            u.getStaminaBar().repaint();
            u.getFighterPanel().repaint();
        }
    }
    public void increaseStamina(int i)
    {
        if((this.stamina + i) > 100 )i = 100 - this.stamina;
        this.stamina += i;
        if(this instanceof User)
        {
            User u = (User)(this);
            u.getStaminaBar().repaint();
            User.getCharPanel().repaint();
        }
        else if(this instanceof Fighter)
        {
            Fighter u = (Fighter)(this);
            u.getStaminaBar().repaint();
            u.getFighterPanel().repaint();
        }
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
