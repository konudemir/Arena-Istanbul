package C;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Fighter extends Person{
    public static final int AMOUNT_OF_FIGHTERS = 10;
    private FighterPanel fighterPanel;
    private HealthBar healthBar;
    public Fighter(int i)
    {
        this.fighterPanel = new FighterPanel(i);
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
    public void resize(int x, int y)
    {
        this.fighterPanel.resize(x, y);
    }
    public HealthBar getHealthBar()
    {
        return this.healthBar;
    }
}
