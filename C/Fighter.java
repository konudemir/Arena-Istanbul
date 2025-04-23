package C;

public class Fighter extends Person{
    public static final int AMOUNT_OF_FIGHTERS = 10;
    private FighterPanel fighterPanel;
    private HealthBar healthBar;
    public Fighter(int i)
    {
        this.fighterPanel = new FighterPanel(i, this);
        this.healthBar = new HealthBar(this);
    }
    
    public FighterPanel getFighterPanel()
    {
        return this.fighterPanel;
    }
    public void moveTo(int x, int y)
    {
        this.fighterPanel.moveTo(x, y);
    }
    public void resizeComp(int x, int y)
    {
        this.fighterPanel.resize(x, y);
    }
    public HealthBar getHealthBar()
    {
        return this.healthBar;
    }
}
