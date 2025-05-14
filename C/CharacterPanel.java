package C;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import C_ITEMS.Item;
import Coloring.Coloring;
import Screens.*;
import javax.swing.Timer;

import B.FightButton;
import java.util.Arrays;

public class CharacterPanel extends FighterPanel{
    private static final int AMOUNT_OF_CHARACTERS = 5;
    private static String[] CHARPATHS = new String[AMOUNT_OF_CHARACTERS];
    public int currentFolderOrder = 0;
    private boolean hasFilledPaths = false;
    private static CharacterPanel theCharPanel;
    private Image[] images = new Image[11];
    private int currentPhoto = 1; //0-9
    public static final int CUSTSCREEN = 10;
    public static final int LOBBY = 9;

    //Buttons
    public FightButton moveForward;
    public FightButton moveBackwards;
    public FightButton attack;
    public FightButton sleep;
    public FightButton usePet;

    public CharacterPanel(Person person)
    {
        this.person = person;
        theCharPanel = this;
        if(!hasFilledPaths) fillPathsCharPanel();
        //Get image
        this.image = images[1];
        this.setBounds(1000, 300, 700, 700);
        this.setLayout(null);
        this.setOpaque(false);
        this.setVisible(true);
    }
    @Override
    protected void paintComponent(Graphics g) {
        moveAccordingToCurrentPhoto();
        this.image = images[currentPhoto];
        g.drawImage(image, 0, 0, null);
        if(this.person.itemsList.size() == 0)
        {
            return;
        }
        for(Item item : this.person.listItemsInPriorityOfShowing())
        {
            //item.setImage(this.person.currentPhoto);
            g.drawImage(item.getImage(), 0, 0, null);
        }
        g.setColor(Color.WHITE);
    }
    public void moveTo(int x, int y)
    {
        this.setBounds(x, y, this.image.getWidth(null), this.image.getHeight(null));
    }
    public void moveBy(int x, int y, int time) {
        int steps = 5;
        int delay = time / steps;
        int deltaX = ((this.getX() >= 400 && x > 0) || (this.getX() == 0 && x < 0) ? 0 : x / steps);
        int deltaY = ((this.getX() >= 400 && x > 0) || (this.getX() == 0 && x < 0) ? 0 : y / steps);
    
        Timer moveTimer = new Timer(delay, null);
        final int[] counter = {0};
    
        moveTimer.addActionListener(_ -> {
            if (counter[0]++ >= steps) {
                moveTimer.stop();
                FightScreen.theFightScreen.setUsersAnimationToIdle();
                return;
            }
            this.setBounds(this.getX() + deltaX, this.getY() + deltaY,
                           this.image.getWidth(null), this.image.getHeight(null));
        });
    
        moveTimer.start();
    }
    public void moveBy(int x, int y)
    {
        this.setBounds(this.getX() + x, this.getY() + y, this.image.getWidth(null), this.image.getHeight(null));
    }
    public FightButton[] getFightButtons()
    {
        return new FightButton[] {moveForward, moveBackwards, attack, sleep, usePet};
    } 
    public void addTheButtons()
    {
        moveForward = new FightButton("moveForward", 260, 528);
        this.add(moveForward);

        moveBackwards = new FightButton("moveBackwards", 180, 618);
        this.add(moveBackwards);

        attack = new FightButton("attack", 385, 528);
        this.add(attack);

        sleep = new FightButton("sleep", 460, 618);
        this.add(sleep);

        usePet = new FightButton("usePet", 460, 738);
        this.add(usePet);
    }
    public void removeTheButtons()
    {
        if(moveForward != null && Arrays.asList(this.getComponents()).contains(moveForward))this.remove(moveForward);
        if(moveBackwards != null && Arrays.asList(this.getComponents()).contains(moveBackwards))this.remove(moveBackwards);
        if(attack != null && Arrays.asList(this.getComponents()).contains(attack))this.remove(attack);
        if(sleep != null && Arrays.asList(this.getComponents()).contains(sleep))this.remove(sleep);
        if(usePet != null && Arrays.asList(this.getComponents()).contains(usePet))this.remove(usePet);
    }
    public void setSize(int width, int height)
    {
        this.image = this.image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }
    public void setHeight(int height)
    {
        int width = (int)((double)height / (double)this.image.getHeight(null) * (double)this.image.getWidth(null));
        this.image = this.image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }
    public static CharacterPanel getCharPanel()
    {
        return theCharPanel;
    }
    public void setImage(Image image)
    {
        this.image = image;
    }

    public void fillPathsCharPanel()
    {
        fillPathsCharPanel("current");
    }
    public void fillPathsCharPanel(String folder)
    {
        for(int i = 1; i <= 3; i++)
        try {
        this.images[i - 1] = ImageIO.read(getClass().getResource("/graphs/character/" + folder + "/" + (i) + ".png"));
        } catch (Exception e) {
            System.out.println("NO IMAGE FOR FILL PATH " + (i+1));
        }
        for(int i = 4; i <= 5; i++)
        try {
        this.images[i - 1] = ImageIO.read(getClass().getResource("/graphs/character/" + folder + "/a" + (i - 3) + ".png"));
        } catch (Exception e) {
            System.out.println("NO IMAGE FOR FILL PATH a" + (i+1));
        }
        for(int i = 6; i <= 9; i++)
        try {
        this.images[i - 1] = ImageIO.read(getClass().getResource("/graphs/character/" + folder + "/w" + (i - 5) + ".png"));
        } catch (Exception e) {
            System.out.println("NO IMAGE FOR FILL PATH w" + (i+1));
        }
        try {
            this.images[CUSTSCREEN] = ImageIO.read(getClass().getResource("/graphs/character/" + folder + "/custScreen.png"));
            } catch (Exception e) {
                System.out.println("NO IMAGE FOR FILL PATH custScreen");
            }
        try {
            this.images[LOBBY] = ImageIO.read(getClass().getResource("/graphs/character/" + folder + "/lobby.png"));
            } catch (Exception e) {
            System.out.println("NO IMAGE FOR FILL PATH lobby");
            }
        this.image = this.images[1];
        hasFilledPaths = true;
    }
    public void setImage(JPanel panel)//Used when a new panel is created (add manually to the panel constructor)
    {
        if(panel instanceof CustomizationScreen || panel instanceof CustomizationScreen)//1500x1500
        {
            this.removeTheButtons();
            this.image = images[CUSTSCREEN];
            currentPhoto = CUSTSCREEN;
        } 
        else if(panel instanceof LobbyScreen || panel instanceof StoreScreen) {//1000x1000
            this.removeTheButtons();
            this.image = images[LOBBY];
            currentPhoto = LOBBY;
        } 
        else if(panel instanceof FightScreen)//700x700
        {
            this.addTheButtons();
            this.image = images[1];
            currentPhoto = 1;
        }
        else if(panel instanceof StoryScreen)//700x700
        {
            this.removeTheButtons();
            this.image = images[1];
            currentPhoto = 1;
        }

        //ITEMS
        if(User.getUser().itemsList.size() != 0)
        for(Item i : User.getUser().itemsList)
        {
            if(panel instanceof CustomizationScreen || panel instanceof CustomizationScreen)i.setImage(CUSTSCREEN);
            else if(panel instanceof LobbyScreen || panel instanceof StoreScreen)i.setImage(LOBBY);
            else if(panel instanceof FightScreen || panel instanceof StoryScreen)
            {
                i.setImage(1);
            }
        }
        this.repaint();
    }
    public void moveAccordingToCurrentPhoto()
    {
        if(this.currentPhoto == CUSTSCREEN) this.moveTo(25, -100);
        else if(this.currentPhoto == LOBBY) this.moveTo(-100, 325);

    }
    // CHANGE STUFF
    public void change(Color[] colors)
    {
        this.images = Coloring.createImagesByCommand(colors);
        this.fillPathsCharPanel(); 
    }

    public String getNextFolderName()
    {  
        currentFolderOrder ++;
        return "" + ((currentFolderOrder) % 5);
    }
    public String getPreviousFolderName()
    {
        currentFolderOrder --;
        return "" + ((currentFolderOrder) % 5);
    }

    public void setImage(int photoOrder)
    {
        this.currentPhoto = photoOrder;
        this.image = images[currentPhoto];

        if(this.person.itemsList.size() > 0)
        for(Item item : this.person.itemsList)
        {
            item.setImage(currentPhoto);
        }
        this.repaint();
    }

    //EMPTY
    public void attackTo(Person p)
    {
        if(User.getCharPanel().getX() >= 350 && FightScreen.theFightScreen.theFighter.getFighterPanel().getX() <= 750)
        {
        }
        else
        {
            return;
        }
        p.didItGetHit(this.person.getAttackPower());
    }
    public void attack()
    {
        FightScreen.theFightScreen.attack(FightScreen.theFightScreen.theUser);
    }
    public void moveForward()
    {
        FightScreen.theFightScreen.moveForward(FightScreen.theFightScreen.theUser);
    }
    public void moveBackwards()
    {
        FightScreen.theFightScreen.moveBackwards(FightScreen.theFightScreen.theUser);
    }
    public void sleep()
    {
        FightScreen.theFightScreen.sleep(FightScreen.theFightScreen.theUser);
    }
    public void usePet()
    {
        FightScreen.theFightScreen.usePet(FightScreen.theFightScreen.theUser);
    }
}
