package C;

import C_ITEMS.Shield;

public class User extends Person{
    private static User theUser;
    private CharacterPanel charPanel;
    private HealthBar healthBar;
    private int coins;
    public User()
    {
        theUser = this;
        this.coins = 0;
        this.buyItem(new Shield("a", 0, 0, 0, 0));
        new CharacterPanel(this);
        this.charPanel = CharacterPanel.getCharPanel();
        this.panel = this.charPanel;
        this.healthBar = new HealthBar(this);
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
    public int getCoins()
    {
        return this.coins;
    }
    public void changeCoins(int i)
    {
        this.coins += i;
    }
}
