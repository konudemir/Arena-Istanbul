package C_ITEMS;

import javax.swing.ImageIcon;

import C.Person;
import C.User;
import m.Main;
import java.awt.Image;

public class Cat extends Item{
    public static final int AMOUNT_OF_ICONS = 1;
    public int level;
    public static Image[][] icons = new Image[AMOUNT_OF_ICONS][Main.AMOUNT_OF_CHARACTER_PHOTOS];
    public Cat()
    {
        this.image = icons[level][0];
        this.offenseBoost = 0.1;
        this.defenseBoost = 0.1;
        this.owner = User.getUser();
    }

    public static void fillIcons()
    {
        for(int i = 0; i < AMOUNT_OF_ICONS; i++)
        {
            for(int j = 0; j < Main.AMOUNT_OF_CHARACTER_PHOTOS; j++)
            {
                System.out.println("trying " + i + " " + j);
                try {
                    icons[i][j] = new ImageIcon("graphs/character/items/armor/" + (i) + "/" + (j) + ".png").getImage();
                    } catch (Exception e) {
                        System.out.println("NO SHIELD IMAGE FOR i,j:" + i + " " + j);
                    }
            }
            
        }
    }

    public void setImage(int i)
    {
        this.image = icons[this.level][i];
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
