package C;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import C_ITEMS.Item;
import C_ITEMS.Shield;
import Coloring.Coloring;
import Screens.*;

public class CharacterPanel extends FighterPanel{
    private static final int AMOUNT_OF_CHARACTERS = 5;
    private static String[] CHARPATHS = new String[AMOUNT_OF_CHARACTERS];
    private int currentFolderOrder = 0;
    private boolean hasFilledPaths = false;
    private static CharacterPanel theCharPanel;
    private Image[] images = new Image[11];
    private int currentPhoto = 1; //0-9
    public static final int CUSTSCREEN = 10;
    public static final int LOBBY = 9;

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
        System.out.println("FILL PATHS IN CHARPANEL " + this.images);
        this.image = this.images[1];
        hasFilledPaths = true;
    }
    public void setImage(JPanel panel)//Used when a new panel is created (add manually to the panel constructor)
    {
        System.out.println("SETTING IMAGE FOR PANEL: "+ panel);
        if(panel instanceof CustomizationScreen || panel instanceof CustomizationScreen)//1500x1500
        {
            System.out.println("SET THE CHAR IMAGE FOR CUST SCREEN");
            this.image = images[CUSTSCREEN];
            currentPhoto = CUSTSCREEN;
        } 
        else if(panel instanceof LobbyScreen || panel instanceof StoreScreen) {//1000x1000
            System.out.println("SET THE CHAR IMAGE FOR LOBBY / STORE");
            this.image = images[LOBBY];
            currentPhoto = LOBBY;
        } 
        else if(panel instanceof FightScreen || panel instanceof StoryScreen)//700x700
        {
            System.out.println("SET THE CHAR IMAGE FOR FIGHT / STORY");
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
}
