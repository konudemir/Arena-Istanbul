package C;

import java.util.Random;

import C_ITEMS.*;

public class Fighter extends Person{
    public static final int AMOUNT_OF_FIGHTERS = 10;
    private FighterPanel fighterPanel;
    private HealthBar healthBar;
    public static Random random = new Random();
    public Fighter(int i)
    {
        this.fighterPanel = new FighterPanel(i, this);
        this.healthBar = new HealthBar(this);
    }
    public Fighter()
    {
        this(random.nextInt(AMOUNT_OF_FIGHTERS));
        if(random.nextBoolean())this.itemsList.add(new Shield(random.nextInt(4)));
        if(random.nextBoolean())this.itemsList.add(new Helmet(random.nextInt(4)));
        if(random.nextBoolean())this.itemsList.add(new Leggings(random.nextInt(4)));
        if(random.nextBoolean())this.itemsList.add(new Armor(random.nextInt(4)));
        this.itemsList.add(new Sword(random.nextInt(4)));
    }
    
    public FighterPanel getFighterPanel()
    {
        return this.fighterPanel;
    }
    public void moveTo(int x, int y)
    {
        this.fighterPanel.moveTo(x, y);
    }
    public HealthBar getHealthBar()
    {
        return this.healthBar;
    }
}
