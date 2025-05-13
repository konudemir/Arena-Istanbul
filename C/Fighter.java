package C;

import java.util.Random;

import javax.swing.Timer;

import C_ITEMS.*;
import Screens.FightScreen;

public class Fighter extends Person {
    public static final int AMOUNT_OF_FIGHTERS = 10;
    private FighterPanel fighterPanel;
    private HealthBar healthBar;
    protected StaminaBar staminaBar;
    public boolean isFromStoryMode;
    public static Random random = new Random();
    public Fighter(int i, boolean isFromStoryMode)
    {
        this.isFromStoryMode = isFromStoryMode;
        this.fighterPanel = new FighterPanel(i, this);
        this.healthBar = new HealthBar(this);
        this.staminaBar = new StaminaBar(this);
        if(this.isFromStoryMode)this.createItemsForStoryModeFighter(i);
    }
    public Fighter()
    {
        this(random.nextInt(AMOUNT_OF_FIGHTERS), false);
        User u = User.getUser();
        if(u.willFighterHaveShield())this.itemsList.add(new Shield(u.getRandomLevelForFightersShield()));
        if(u.willFighterHaveHelmet())this.itemsList.add(new Helmet(u.getRandomLevelForFightersHelmet()));
        if(u.willFighterHaveLeggings())this.itemsList.add(new Leggings(u.getRandomLevelForFightersLeggings()));
        if(u.willFighterHaveArmor())this.itemsList.add(new Armor(u.getRandomLevelForFightersArmor()));
        this.itemsList.add(new Sword(u.getRandomLevelForFightersSword()));
        this.staminaBar = new StaminaBar(this);
    }
    public void createItemsForStoryModeFighter(int i)
    {
        if(i == 0)
        {
            this.itemsList.add(new Sword(0));
            this.itemsList.add(new Shield(0));
            this.itemsList.add(new Helmet(0));
            this.itemsList.add(new Leggings(0));
            this.itemsList.add(new Armor(0));
        }
        else if(i == 1)
        {
            this.itemsList.add(new Sword(1));
            this.itemsList.add(new Shield(1));
            this.itemsList.add(new Helmet(1));
            this.itemsList.add(new Leggings(0));
            this.itemsList.add(new Armor(0));
        }
        else if(i == 2)
        {
            this.itemsList.add(new Sword(1));
            this.itemsList.add(new Shield(2));
            this.itemsList.add(new Helmet(1));
            this.itemsList.add(new Leggings(1));
            this.itemsList.add(new Armor(1));
        }
        else if(i == 3)
        {
            this.itemsList.add(new Sword(2));
            this.itemsList.add(new Shield(2));
            this.itemsList.add(new Helmet(2));
            this.itemsList.add(new Leggings(3));
            this.itemsList.add(new Armor(3));
        }
        else if(i == 4)
        {
            this.itemsList.add(new Sword(3));
            this.itemsList.add(new Shield(3));
            this.itemsList.add(new Helmet(3));
            this.itemsList.add(new Leggings(3));
            this.itemsList.add(new Armor(3));
        }
        else if(i == 5)
        {
            this.itemsList.add(new Sword(4));
            this.itemsList.add(new Shield(4));
            this.itemsList.add(new Helmet(3));
            this.itemsList.add(new Leggings(4));
            this.itemsList.add(new Armor(4));
        }
    }
    public void fightersTurn()
    {
        //wait for a sec
        Timer timer = new Timer(1000, _ -> {
            this.whatShouldItDo();
        });
        timer.setRepeats(false);
        timer.start();
    }
    public void whatShouldItDo()
    {
        /* NOTE: final integers below could be changed.
         * I'm not sure about the part where we check they're close or not. I've found the code from fighter panel.
         * ***
         * fighter sleeps if to tired.
         * if not: if far, most of the time moves forward but has some randomness. if close, most of the time hits.
         */
        final int LOWER_STAMINA_THRESHOLD = 20;
        final int PROXY_ATTACK_RATIO = 5;
        final int PROXY_SLEEP_RATIO = 10;
        final int PROXY_MOVE_BACKWARDS_RATIO = 10;
        final int CLOSE_RANGE_ATTACK_RATIO = 80;
        final int CLOSE_RANGE_SLEEP_RATIO = 15;

        Random random = new Random();
        if(super.stamina <= LOWER_STAMINA_THRESHOLD){
            fighterPanel.sleep();
        }else if(User.getCharPanel().getX() >= 350 && FightScreen.theFightScreen.theFighter.getFighterPanel().getX() <= 750){ // means they're close
            int randomNumber = random.nextInt(100);
            if(randomNumber < CLOSE_RANGE_ATTACK_RATIO){
                fighterPanel.attack();
            }else if(randomNumber < CLOSE_RANGE_ATTACK_RATIO + CLOSE_RANGE_SLEEP_RATIO){
                fighterPanel.sleep();
            }else{ // step back
                fighterPanel.moveBackwards();
            }
        }else{ // ie. proxy
            int randomNumber = random.nextInt(100);
            if(randomNumber < PROXY_ATTACK_RATIO){
                fighterPanel.attack();
            }else if(randomNumber < PROXY_ATTACK_RATIO + PROXY_SLEEP_RATIO){
                fighterPanel.sleep();
            }else if(randomNumber < PROXY_ATTACK_RATIO + PROXY_SLEEP_RATIO + PROXY_MOVE_BACKWARDS_RATIO){
                fighterPanel.moveBackwards();
            }else{
                fighterPanel.moveForward();
            }
        }

        //At the end make sure to give the turn to the user
        FightScreen.usersTurn = true;
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

    public boolean isAtLeftEdge()
    {
        if(this.fighterPanel.getX() == 700)return true;
        return false;
    }
    public boolean isAtRightEdge()
    {
        if(this.fighterPanel.getX() == 1100)return true;
        return false;
    }
}