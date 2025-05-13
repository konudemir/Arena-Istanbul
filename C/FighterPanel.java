package C;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;

import C_ITEMS.Item;
import Coloring.Coloring;
import Screens.*;
import Screens.FightScreen.FighterAnimation;

public class FighterPanel extends JPanel{
    private final int characterNo;
    private static final int AMOUNT_OF_CHARACTERS = 5;
    private static CharacterPanel theCharPanel;
    private Image[] images = new Image[11];
    private int currentPhoto = 1; //0-9
    private static boolean hasFilledPaths = false;
    protected Image image;
    protected Person person;

    public FighterPanel(int i, Person person)
    {
        fillPaths();
        characterNo = i;
        this.person = person;
        this.image = images[1];
        if(!hasFilledPaths) fillPaths();
        this.setBounds(1000, 300, 256, 256);
        this.setLayout(null);
        this.setVisible(true);
        this.setOpaque(false);
    }
    public FighterPanel(Person person)
    {
        this(0, person);
    }
    public FighterPanel()
    {
        this.characterNo = 0;
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
        int deltaX = ((this.getX() >= 1500 && x > 0) || (this.getX() <= 700 && x < 0) ? 0 : x / steps);
        int deltaY = ((this.getX() >= 1500 && x > 0) || (this.getX() <= 700 && x < 0) ? 0 : y / steps);
    
        Timer moveTimer = new Timer(delay, null);
        final int[] counter = {0};
    
        moveTimer.addActionListener(_ -> {
            if (counter[0]++ >= steps) {
                moveTimer.stop();
                FightScreen.theFightScreen.setFightersAnimationToIdle();
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
    public void setSize(int width, int height)
    {
        this.image = this.image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }
    public void setHeight(int height)
    {
        int width = (int)((double)height / (double)this.image.getHeight(null) * (double)this.image.getWidth(null));
        this.image = this.image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }
    public void setImage(Image image)
    {
        this.image = image;
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

    public void fillPaths()
    {
        for(int i = 1; i <= 3; i++)
        try {
        this.images[i - 1] = ImageIO.read(getClass().getResource("/graphs/character/opponents/" + characterNo + "/" + (i) + ".png"));
        } catch (Exception e) {
            System.out.println("NO IMAGE FOR FILL PATH " + (i) + "for fighter: " + characterNo);
        }
        for(int i = 4; i <= 5; i++)
        try {
        this.images[i - 1] = ImageIO.read(getClass().getResource("/graphs/character/opponents/" + characterNo + "/a" + (i - 3) + ".png"));
        } catch (Exception e) {
            System.out.println("NO IMAGE FOR FILL PATH a" + (i-3) + "for fighter: " + characterNo);
        }
        for(int i = 6; i <= 9; i++)
        try {
        this.images[i - 1] = ImageIO.read(getClass().getResource("/graphs/character/opponents/" + characterNo + "/w" + (i - 5) + ".png"));
        } catch (Exception e) {
            System.out.println("NO IMAGE FOR FILL PATH w" + (i-5) + "for fighter: " + characterNo);
        }
        try {
            this.images[CharacterPanel.CUSTSCREEN] = ImageIO.read(getClass().getResource("/graphs/character/opponents/" + characterNo + "/" + "custScreen.png"));
            } catch (Exception e) {
                System.out.println("NO IMAGE FOR FILL PATH custScreen for fighter: " + characterNo);
            }
        try {
            this.images[CharacterPanel.LOBBY] = ImageIO.read(getClass().getResource("/graphs/character/opponents/" + characterNo + "/" + "lobby.png"));
            } catch (Exception e) {
            System.out.println("NO IMAGE FOR FILL PATH lobby for fighter: " + characterNo);
            }
        this.image = this.images[1];
        hasFilledPaths = true;
    }
    public void setImage(JPanel panel)//Used when a new panel is created (add manually to the panel constructor)
    {
        if(panel instanceof FightScreen || panel instanceof StoryScreen)//700x700
        {
            this.image = images[1];
            currentPhoto = 1;
        }

        //ITEMS
        if(User.getUser().itemsList.size() != 0)
        for(Item i : User.getUser().itemsList)
        {
            if(panel instanceof FightScreen)i.setImage(1);
        }
        this.repaint();
    }
    public void moveAccordingToCurrentPhoto()
    {
        if(this.currentPhoto == CharacterPanel.CUSTSCREEN) this.moveTo(500, -100);
        else if(this.currentPhoto == CharacterPanel.LOBBY) this.moveTo(-100, 325);

    }

    public void attackTo(Person p)
    {
        if(this.getX() >= 350 && FightScreen.theFightScreen.theFighter.getFighterPanel().getX() <= 750);//it is close enough
        else return;
        FightScreen.theFightScreen.theUser.didItGetHit(this.person.getAttackPower());
    }
    public void attack()
    {
        FightScreen.theFightScreen.attack(FightScreen.theFightScreen.theFighter);
    }
    public void moveForward()
    {
        FightScreen.theFightScreen.moveForward(FightScreen.theFightScreen.theFighter);
    }
    public void moveBackwards()
    {
        FightScreen.theFightScreen.moveBackwards(FightScreen.theFightScreen.theFighter);
    }
    public void sleep()
    {
        FightScreen.theFightScreen.sleep(FightScreen.theFightScreen.theFighter);
    }
}
