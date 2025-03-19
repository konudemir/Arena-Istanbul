package C;
public class User extends Person{
    private static User theUser;
    private CharacterPanel charPanel;
    public User()
    {
        theUser = this;
        new CharacterPanel();
        this.charPanel = CharacterPanel.getCharPanel();
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
}
