package C;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;

import C_ITEMS.Item;
import Screens.*;

public class FighterPanel extends JPanel{
    private final int characterNo;
    private static final int AMOUNT_OF_CHARACTERS = 5;
    private static CharacterPanel theCharPanel;
    private Image[] images = new Image[11];
    private int currentPhoto = 1; //0-9
    private static String[] FIGHTERPATHS = new String[Fighter.AMOUNT_OF_FIGHTERS];
    private static boolean hasFilledPaths = false;
    protected Image image;
    protected Person person;

    public FighterPanel(int i, Person person)
    {
        characterNo = i;
        this.person = person;
        if(!hasFilledPaths) fillPaths();
        //Get image
        try {
        this.image = ImageIO.read(getClass().getResource(FIGHTERPATHS[i]));
        } catch (Exception e) {
            System.out.println("NO IMAGE FOR i:" + i);
        }
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
            System.out.println("len 0");
            return;
        }
        for(Item item : this.person.listItemsInPriorityOfShowing())
        {
            //item.setImage(this.person.currentPhoto);
            g.drawImage(item.getImage(), 0, 0, null);
            //System.out.println("DREW in CHARPANEL " + item + ", image: " + item.getImage());
        }
        g.setColor(Color.WHITE);
        g.drawString("Character Image: " + currentPhoto, 100, 10);
        for(int i = 0; i < this.person.itemsList.size(); i++)
        {
            g.drawString("Item " + this.person.itemsList.get(i) + " image: " + this.person.itemsList.get(i).getImageOrder(), 100, 10 * i + 20);
        }
    }
    public void moveTo(int x, int y)
    {
        this.setBounds(x, y, this.image.getWidth(null), this.image.getHeight(null));
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
        System.out.println("FILL PATHS IN FIGHTERPANEL " + this.images);
        this.image = this.images[1];
        hasFilledPaths = true;
    }
    public void setImage(JPanel panel)//Used when a new panel is created (add manually to the panel constructor)
    {
        System.out.println("SETTING IMAGE FOR PANEL: "+ panel);
        if(panel instanceof FightScreen || panel instanceof StoryScreen)//700x700
        {
            System.out.println("SET THE CHAR IMAGE FOR FIGHT / STORY");
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
}
