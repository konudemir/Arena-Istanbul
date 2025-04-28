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
import Screens.CustomizationScreen;
import Screens.CustomizationScreenOld;
import Screens.FightScreen;
import Screens.FirstMenu;
import Screens.LobbyScreen;
import Screens.StoreScreen;
import Screens.StoryScreen;

public class CharacterPanel extends FighterPanel{
    private static final int AMOUNT_OF_CHARACTERS = 5;
    private static String[] CHARPATHS = new String[AMOUNT_OF_CHARACTERS];
    private boolean hasFilledPaths = false;
    private static CharacterPanel theCharPanel;
    private Image[] images = new Image[10];
    private int currentPhoto = 1; //0-9
    public static final int CUSTSCREEN = 8;
    public static final int LOBBY = 9;

    public CharacterPanel(Person person)
    {
        this.person = person;
        theCharPanel = this;
        if(!hasFilledPaths) fillPaths();
        //Get image
        this.image = images[1];
        this.setBounds(1000, 300, 700, 700);
        this.setLayout(null);
        this.setVisible(true);
        this.setOpaque(false);
    }
    @Override
    protected void paintComponent(Graphics g) {
        this.image = images[currentPhoto];
        moveAccordingToCurrentPhoto();
        g.drawImage(image, 0, 0, null);
        if(this.person.itemsList.size() == 0)
        {
            System.out.println("len 0");
            return;
        }
        for(Item item : this.person.itemsList)
        {
            g.drawImage(item.getImage(), 0, 0, null);
            System.out.println("DREW in CHARPANEL " + item + ", image: " + item.getImage());
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

    public void fillPaths()
    {
        for(int i = 0; i < 3; i++)
        try {
        this.images[i] = ImageIO.read(getClass().getResource("/graphs/character/current/" + (i + 1) + ".png"));
        } catch (Exception e) {
            System.out.println("NO IMAGE FOR FILL PATH " + (i+1));
        }
        for(int i = 0; i < 2; i++)
        try {
        this.images[i + 3] = ImageIO.read(getClass().getResource("/graphs/character/current/a" + (i + 1) + ".png"));
        } catch (Exception e) {
            System.out.println("NO IMAGE FOR FILL PATH a" + (i+1));
        }
        for(int i = 0; i < 4; i++)
        try {
        this.images[i + 4] = ImageIO.read(getClass().getResource("/graphs/character/current/w" + (i + 1) + ".png"));
        } catch (Exception e) {
            System.out.println("NO IMAGE FOR FILL PATH w" + (i+1));
        }
        try {
            this.images[8] = ImageIO.read(getClass().getResource("/graphs/character/current/custScreen.png"));
            } catch (Exception e) {
                System.out.println("NO IMAGE FOR FILL PATH custScreen");
            }
        try {
            this.images[9] = ImageIO.read(getClass().getResource("/graphs/character/current/lobby.png"));
            } catch (Exception e) {
            System.out.println("NO IMAGE FOR FILL PATH lobby");
            }
        System.out.println("FILL PATHS IN CHARPANEL " + this.images);
        this.image = this.images[1];
        hasFilledPaths = true;
    }

    public void breath()
    {
        Timer timer = new Timer(); // create the timer
        CharacterPanel thisPanel = this;
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                CharacterPanel.setNextImageIdleOf(thisPanel);
                //System.out.println("aa");
            }
        };
        timer.schedule(task, 1000, 5);
    }
    public static Image setNextImageIdleOf(CharacterPanel panel)
    {
        for(int i = 0; i < 3; i++) 
        if(panel.image == panel.images[i])
        {
            Image im = panel.image;
            panel.image = panel.images[(i+1) % 3];
            //System.out.println("aabbcc");
            //System.out.println(im == panel.image);
            break;
        }
        panel.repaint();
        return panel.image;
    }
    public void setImage(JPanel panel)
    {
        if(panel instanceof CustomizationScreenOld || panel instanceof CustomizationScreen)//1500x1500
        {
            this.image = images[8];
            currentPhoto = 8;
        } 
        else if(panel instanceof LobbyScreen || panel instanceof StoreScreen) {//1000x1000
            this.image = images[9];
            currentPhoto = 9;
        } 
        else if(panel instanceof FightScreen || panel instanceof StoryScreen)//700x700
        {
            this.image = images[1];
            currentPhoto = 1;
        }

        //ITEMS
        if(User.getUser().itemsList.size() != 0)
        for(Item i : User.getUser().itemsList)
        {
            if(panel instanceof CustomizationScreenOld || panel instanceof CustomizationScreen)i.setImage(5);
            else if(panel instanceof LobbyScreen || panel instanceof StoreScreen)i.setImage(6);
            else if(panel instanceof FightScreen)i.setImage(1);
        }
        this.repaint();
    }
    public void moveAccordingToCurrentPhoto()
    {
        if(this.currentPhoto == CUSTSCREEN) this.moveTo(500, -100);
        else if(this.currentPhoto == LOBBY) this.moveTo(-100, 325);

    }

    // CHANGE STUFF
    public void change(Color[] colors)
    {
        this.images = Coloring.createImagesByCommand(colors);
        this.fillPaths();    
    }
}
