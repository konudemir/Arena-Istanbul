package m;

import C.User;
import C_ITEMS.Shield;

import java.awt.Font;

public class Main {
    public static final String mainScreenPath = "graphs/gptMainScreen3.png";
    public static final int AMOUNT_OF_CHARACTER_PHOTOS = 9;
    public static User theUser;
    private static Font font;
    public static void main(String[] args) {
        fillItemIcons();
        theUser = new User();
        MainFrame frame = new MainFrame();
        frame.getFirstScreen();
    }
    public static Font getFont()
    {
        return font;
    }
    public static void setFont(Font f)
    {
        font = f;
    }

    public static void fillItemIcons()
    {
        Shield.fillIcons();
    }
}