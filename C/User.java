package C;
public class User extends Person{
    private static User theUser;
    private CharacterPanel charPanel;
    private HealthBar healthBar;
    public User()
    {
        theUser = this;
        new CharacterPanel(0);
        this.charPanel = CharacterPanel.getCharPanel();
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
}
