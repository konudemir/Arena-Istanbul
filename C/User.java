package C;

import java.util.ArrayList;
import java.util.Random;

import C_ITEMS.*;

public class User extends Person{
    private static User theUser;
    private CharacterPanel charPanel;
    private HealthBar healthBar;
    private int coins;
    protected StaminaBar staminaBar;
    public int totalFought = 0;
    public int totalWon = 0;
    public User()  
    {
        theUser = this;
        this.itemsList = new ArrayList<>();
        this.coins = 500;
        this.buyItem(new Sword(0));
        new CharacterPanel(this);
        this.charPanel = CharacterPanel.getCharPanel();
        this.panel = this.charPanel;
        this.healthBar = new HealthBar(this);
        this.staminaBar = new StaminaBar(this);
    }
    public boolean hasOfItem(Item item)
    {
        if(item instanceof Armor && this.hasArmor())if(this.getArmor().getName().equals(item.getName()))return true;
        if(item instanceof Armor && this.hasHelmet())if(this.getHelmet().getName().equals(item.getName()))return true;
        if(item instanceof Armor && this.hasLeggings())if(this.getLeggings().getName().equals(item.getName()))return true;
        if(item instanceof Armor && this.hasShield())if(this.getShield().getName().equals(item.getName()))return true;
        if(item instanceof Armor && this.hasSword())if(this.getSword().getName().equals(item.getName()))return true;
        return false;
    }
    public static int getIntWithIncreasedOddsFor(int lev)
    {
        Random random = new Random();
        double r = random.nextDouble(1.0);
        if(lev == -1)//Means it doesnt have the same item
        {
            if(r < 0.5)return 0;
            if(r < 0.7)return 1;
            if(r < 0.85)return 2;
            else return 3;
        }
        if(lev == 0)
        {
            if(r < 0.4)return 0;
            if(r < 0.6)return 1;
            if(r < 0.8)return 2;
            else return 3;
        }
        if(lev == 1)
        {
            if(r < 0.3)return 0;
            if(r < 0.6)return 1;
            if(r < 0.8)return 2;
            else return 3;
        }
        if(lev == 2)
        {
            if(r < 0.3)return 0;
            if(r < 0.5)return 1;
            if(r < 0.85)return 2;
            else return 3;
        }
        if(lev == 3)
        {
            if(r < 0.3)return 0;
            if(r < 0.45)return 1;
            if(r < 0.7)return 2;
            else return 3;
        }
        return random.nextInt(4);
    }

    public int getRandomLevelForFightersSword()
    {
        if(this.hasSword())
        {
            return getIntWithIncreasedOddsFor(this.getSword().level);
        }
        return getIntWithIncreasedOddsFor(-1);
    }

    public int getRandomLevelForFightersShield()
    {
        if(this.hasShield())
        {
            return getIntWithIncreasedOddsFor(this.getShield().level);
        }
        return getIntWithIncreasedOddsFor(-1);
    }
    public int getRandomLevelForFightersHelmet() {
    if (this.hasHelmet()) {
        return getIntWithIncreasedOddsFor(this.getHelmet().level);
    }
    return getIntWithIncreasedOddsFor(-1);
    }

    public int getRandomLevelForFightersLeggings() {
        if (this.hasLeggings()) {
            return getIntWithIncreasedOddsFor(this.getLeggings().level);
        }
        return getIntWithIncreasedOddsFor(-1);
    }

    public int getRandomLevelForFightersArmor() {
        if (this.hasArmor()) {
            return getIntWithIncreasedOddsFor(this.getArmor().level);
        }
        return getIntWithIncreasedOddsFor(-1);
    }
    public static boolean getRandomBoolean(double chanceOfTrue)
    {
        double r = new Random().nextDouble(1.0);
        if(r <= chanceOfTrue)return true;
        else return false;
    }

    public boolean willFighterHaveShield()
    {
        if(this.hasShield())return getRandomBoolean(0.7);
        else return getRandomBoolean(0.3);
    }
    public boolean willFighterHaveSword()
    {
        if(this.hasSword())return getRandomBoolean(0.7);
        else return getRandomBoolean(0.3);
    }
    public boolean willFighterHaveHelmet() {
    if (this.hasHelmet()) return getRandomBoolean(0.7);
    else return getRandomBoolean(0.3);
    }

    public boolean willFighterHaveLeggings() {
        if (this.hasLeggings()) return getRandomBoolean(0.7);
        else return getRandomBoolean(0.3);
    }

    public boolean willFighterHaveArmor() {
        if (this.hasArmor()) return getRandomBoolean(0.7);
        else return getRandomBoolean(0.3);
    }


    public void moveTo(int x, int y)
    {
        this.charPanel.moveTo(x, y);
    }
    public void moveBy(int x, int y)
    {
        this.charPanel.moveBy(x, y);
    }
    public static CharacterPanel getCharPanel()
    {
        return theUser.charPanel;
    }
    public static User getUser()
    {
        return theUser;
    }
    public HealthBar getHealthBar()
    {
        return this.healthBar;
    }
    public boolean hasSword() {
    for (Item item : this.itemsList) {
        if (item instanceof Sword) return true;
    }
    return false;
    }

    public Sword getSword() {
        for (Item item : this.itemsList) {
            if (item instanceof Sword) return (Sword) item;
        }
        return null;
    }

    public boolean hasHelmet() {
        for (Item item : this.itemsList) {
            if (item instanceof Helmet) return true;
        }
        return false;
    }

    public Helmet getHelmet() {
        for (Item item : this.itemsList) {
            if (item instanceof Helmet) return (Helmet) item;
        }
        return null;
    }

    public boolean hasLeggings() {
        for (Item item : this.itemsList) {
            if (item instanceof Leggings) return true;
        }
        return false;
    }

    public Leggings getLeggings() {
        for (Item item : this.itemsList) {
            if (item instanceof Leggings) return (Leggings) item;
        }
        return null;
    }

    public boolean hasShield() {
        for (Item item : this.itemsList) {
            if (item instanceof Shield) return true;
        }
        return false;
    }

    public Shield getShield() {
        for (Item item : this.itemsList) {
            if (item instanceof Shield) return (Shield) item;
        }
        return null;
    }

    public boolean hasArmor() {
        for (Item item : this.itemsList) {
            if (item instanceof Armor) return true;
        }
        return false;
    }

    public Armor getArmor() {
        for (Item item : this.itemsList) {
            if (item instanceof Armor) return (Armor) item;
        }
        return null;
    }
    public boolean hasCat() {
    for (Item item : this.itemsList) {
        if (item instanceof Cat) return true;
    }
    return false;
    }

    public Cat getCat() {
        for (Item item : this.itemsList) {
            if (item instanceof Cat) return (Cat) item;
        }
        return null;
    }

    public StaminaBar getStaminaBar()
    {
        return this.staminaBar;
    }
    public int getCoins()
    {
        return this.coins;
    }
    public void setCoins (int coins)
    {
        this.coins = coins;
    }
    public void changeCoins(int i)
    {
        this.coins += i;
        if(this.coins < 0)this.coins = 0;
    }
    public boolean hasPet()
    {
        if(itemsList.size() > 0)
        for(Item item : this.itemsList)
        {
            if(item instanceof Cat)return true;
        }return false;
    }
    
}
