package C;

import java.util.Random;

import C_ITEMS.*;

public class Fighter extends Person{
    public static final int AMOUNT_OF_FIGHTERS = 10;
    private FighterPanel fighterPanel;
    private HealthBar healthBar;
    protected StaminaBar staminaBar;
    public static Random random = new Random();
    public Fighter(int i)
    {
        this.fighterPanel = new FighterPanel(i, this);
        this.healthBar = new HealthBar(this);
        this.staminaBar = new StaminaBar(this);
    }
    public Fighter()
    {
        this(random.nextInt(AMOUNT_OF_FIGHTERS));
        if(random.nextBoolean())this.itemsList.add(new Shield(random.nextInt(4)));
        if(random.nextBoolean())this.itemsList.add(new Helmet(random.nextInt(4)));
        if(random.nextBoolean())this.itemsList.add(new Leggings(random.nextInt(4)));
        if(random.nextBoolean())this.itemsList.add(new Armor(random.nextInt(4)));
        this.itemsList.add(new Sword(random.nextInt(4)));
        this.staminaBar = new StaminaBar(this);
    }
    
    public FighterPanel getFighterPanel()
    {
        return this.fighterPanel;
    }
    public StaminaBar getStaminaBar()
    {
        return this.staminaBar;
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
