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
    public StaminaBar getStaminaBar()
    {
        return this.staminaBar;
    }
    public int getCoins()
    {
        return this.coins;
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
