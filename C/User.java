package C;

import C_ITEMS.*;

public class User extends Person{
    private static User theUser;
    private CharacterPanel charPanel;
    private HealthBar healthBar;
    private int coins;
    protected StaminaBar staminaBar;
    public User()  
    {
        theUser = this;
        this.coins = 200;
        this.buyItem(new Sword(0));
        new CharacterPanel(this);
        this.charPanel = CharacterPanel.getCharPanel();
        this.panel = this.charPanel;
        this.healthBar = new HealthBar(this);
        this.staminaBar = new StaminaBar(this);
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
