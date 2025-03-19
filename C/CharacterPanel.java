package C;
import B.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
//This is the class that controls the looks of the character
public class CharacterPanel extends JPanel{
    //Constants
    private static final int length = 500;
    private static final int height = 500;
    private static final int FACE = 0;
    private static final int HAIR = 1;
    private static final int JACKET = 2;
    private static final int SHIRT = 3;
    private static final int TIES = 4;
    private static final int PANTS = 5;
    private static CharacterPanel theCharPanel;
    public static Random random = new Random();
    public String[][] paths;
    private static final String GRAPHSCHAR = "/graphs/character/";
    private boolean moveable;
    private BufferedImage face;
    private BufferedImage hair;
    private BufferedImage hands;
    private BufferedImage jacket;
    private BufferedImage pants;
    private BufferedImage shirts;
    private BufferedImage shoes;
    private BufferedImage tie;

    public CharacterPanel(boolean moveable)
    {
        //Sthis.innitializePaths();
        theCharPanel = this;
        this.setLayout(null);
        this.setBounds(1000, 300, 920, 580);
        //Default pieces
        try {
            face = ImageIO.read(getClass().getResource("/graphs/character/face/2.png"));
            hair = ImageIO.read(getClass().getResource("/graphs/character/hair/gray.png"));
            hands = ImageIO.read(getClass().getResource("/graphs/character/hands/0.png"));
            jacket = ImageIO.read(getClass().getResource("/graphs/character/jacket/001438.png"));
            pants = ImageIO.read(getClass().getResource("/graphs/character/pants/000C21.png"));
            shirts = ImageIO.read(getClass().getResource("/graphs/character/shirts/FFFFFF.png"));
            shoes = ImageIO.read(getClass().getResource("/graphs/character/shoes/black.png"));
            tie = ImageIO.read(getClass().getResource("/graphs/character/ties/D6B745.png"));
        } catch (IOException e){
            e.printStackTrace();
        }

        this.setVisible(true);
        this.setOpaque(false);
    }
    public CharacterPanel()//Main characterPanel is not movable in default
    {
        this(false);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.drawImage(face, 0, 0, null);
        g.drawImage(hair, 0, 0, null);
        g.drawImage(hands, 0, 0, null);
        g.drawImage(shirts, 0, 0, null);
        g.drawImage(jacket, 0, 0, null);
        g.drawImage(pants, 0, 0, null);
        g.drawImage(shoes, 0, 0, null);
        g.drawImage(tie, 0, 0, null);
    }

    public void changeHair(String context)
    {
        if(context.equalsIgnoreCase("Black"))
        {
            try {
            this.hair = ImageIO.read(getClass().getResource("/graphs/character/hair/black.png"));
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        else if(context.equalsIgnoreCase("Gray"))
        {
            try {
            this.hair = ImageIO.read(getClass().getResource("/graphs/character/hair/gray.png"));
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        else if(context.equalsIgnoreCase("blonde"))
        {
            try {
            this.hair = ImageIO.read(getClass().getResource("/graphs/character/hair/blonde.png"));
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        else if(context.equalsIgnoreCase("blue"))
        {
            try {
            this.hair = ImageIO.read(getClass().getResource("/graphs/character/hair/blue.png"));
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        else if(context.equalsIgnoreCase("dark_brown"))
        {
            try {
            this.hair = ImageIO.read(getClass().getResource("/graphs/character/hair/dark_brown.png"));
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        else if(context.equalsIgnoreCase("red"))
        {
            try {
            this.hair = ImageIO.read(getClass().getResource("/graphs/character/hair/red.png"));
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        else if(context.equalsIgnoreCase("light_brown"))
        {
            try {
            this.hair = ImageIO.read(getClass().getResource("/graphs/character/hair/light_brown.png"));
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public void changeSkin(String context)
    {
        try {
        this.face = ImageIO.read(getClass().getResource("/graphs/character/face/" + context.toLowerCase() + ".png"));
        } catch (IOException e){
            e.printStackTrace();
        }
        try {
        this.hands = ImageIO.read(getClass().getResource("/graphs/character/hands/" + context.toLowerCase() + ".png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void changeHair(Color c)
    {
        if(c.equals(Color.BLACK)) changeHair("black");
        else if(c.equals(new Color(0xF5E0B7))) changeHair("blonde");
        else if(c.equals(new Color(0x3D2B24))) changeHair("dark_brown");
        else if(c.equals(new Color(0x3D3D3D))) changeHair("gray");
        else if(c.equals(new Color(0x6A4E42))) changeHair("light_brown");
        else if(c.equals(new Color(0xA52A2A))) changeHair("red");
        else if(c.equals(new Color(0xA52A2A))) changeHair("blue");
    }
    public void changeSkin(Color c)
    {
        if(c.equals(new Color(0xFFD3BA))) changeSkin("0");
        else if(c.equals(new Color(0xDBA772))) changeSkin("1");
        else if(c.equals(new Color(0xB87D4B))) changeSkin("2");
        else if(c.equals(new Color(0x7C4A3A))) changeSkin("3");
    }
    public void changeJacket(Color c)
    {
        String hex = colorPickButton.toHEXString(c);
        try {
        this.jacket = ImageIO.read(getClass().getResource("/graphs/character/jacket/" + hex + ".png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void changeTie(Color c)
    {
        String hex = colorPickButton.toHEXString(c);
        try {
        this.tie = ImageIO.read(getClass().getResource("/graphs/character/ties/" + hex + ".png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public void changeShirts(Color c)
    {
        String hex = colorPickButton.toHEXString(c);
        try {
        this.shirts = ImageIO.read(getClass().getResource("/graphs/character/shirts/" + hex + ".png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void changePants(Color c)
    {
        String hex = colorPickButton.toHEXString(c);
        try {
        this.pants = ImageIO.read(getClass().getResource("/graphs/character/pants/" + hex + ".png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static CharacterPanel getCharPanel()
    {
        return theCharPanel;
    }
    public static void randomizeChar()
    {
        theCharPanel.randomizeLooks();
    }
    public void randomizeLooks()
    {
        try {
            String faceS = returnRandomPath(FACE);
            face = ImageIO.read(getClass().getResource(faceS));
            hair = ImageIO.read(getClass().getResource(returnRandomPath(FACE)));
            hands = ImageIO.read(getClass().getResource(convertFaceToHand(faceS)));
            jacket = ImageIO.read(getClass().getResource(returnRandomPath(JACKET)));
            pants = ImageIO.read(getClass().getResource(returnRandomPath(PANTS)));
            shirts = ImageIO.read(getClass().getResource(returnRandomPath(SHIRT)));
            tie = ImageIO.read(getClass().getResource(returnRandomPath(TIES)));
        } catch (IOException e){
            e.printStackTrace();
        }
        this.repaint();
    }
    private static String returnRandomPath(int constant)
    {
        int randomInt = random.nextInt(theCharPanel.paths[constant].length);
        return theCharPanel.paths[constant][randomInt];
    }
    private static String convertFaceToHand(String s)
    {
        int indexStart = s.indexOf("/face");
        int indexEnd = s.indexOf("/", indexStart + 1);
        return s.substring(0, indexStart) + "/hands" + s.substring(indexEnd, s.length());
    }
    private void innitializePaths()
    {
        paths = new String[6][];
        innitializePaths(0, "face", new String[]{"0", "1", "2", "3"});
        innitializePaths(1, "hair", new String[]{"black","blonde", "blue", "dark_brown", "gray", "light_brown", "red"});
        innitializePaths(2, "jacket", new String[]{"ADADA3", "303030", "001438"});
        innitializePaths(3, "shirts", new String[]{"ADD8E6", "FFFDD0", "FFFFFF"});
        innitializePaths(4, "ties", new String[]{"0x000000", "0x800020", "0xD6B745"});
        innitializePaths(5, "pants", new String[]{"0x7A7A6E", "0x000C21", "0x000000"});
    }
    /*
    private void innitializePaths(int i, String context)
    {
        String path = GRAPHSCHAR + context;
        File folder = new File(path);
        paths[i] = new String[folder.list().length];
        for(int j = 0; j < folder.list().length; j++)
        {
            paths[i][j] = folder.list()[j];
        }
    } */
   private void innitializePaths(int i, String context, String[] hex)
   {
    String path = GRAPHSCHAR + context + "/";
    paths[i] = new String[hex.length];
    for(int j = 0; j < hex.length; j++)
    {
        paths[i][j] = path + hex[j] + ".png";
    }
   }
   public void moveTo(int x, int y)
   {
    this.setBounds(x, y, length, height);
   }
   public void moveBy(int x, int y)
   {
    this.setBounds(this.getX() + x, this.getY() + y, length, height);
   }
}
