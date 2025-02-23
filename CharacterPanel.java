import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
//This is the class that controls the looks of the character
public class CharacterPanel extends JPanel{
    private static CharacterPanel theCharPanel;
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
        theCharPanel = this;
        this.setLayout(null);
        this.setBounds(1000, 300, 920, 580);
        //Default pieces
        try {
            face = ImageIO.read(getClass().getResource("/graphs/character/face/0.png"));
            hair = ImageIO.read(getClass().getResource("/graphs/character/hair/gray.png"));
            hands = ImageIO.read(getClass().getResource("/graphs/character/hands/0.png"));
            jacket = ImageIO.read(getClass().getResource("/graphs/character/jacket/navy_blue.png"));
            pants = ImageIO.read(getClass().getResource("/graphs/character/pants/navy_blue.png"));
            shirts = ImageIO.read(getClass().getResource("/graphs/character/shirts/white.png"));
            shoes = ImageIO.read(getClass().getResource("/graphs/character/shoes/black.png"));
            tie = ImageIO.read(getClass().getResource("/graphs/character/ties/yellow.png"));
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
    public static CharacterPanel getCharPanel()
    {
        return theCharPanel;
    }
}
