package m;

import C.User;
import java.awt.Font;

public class Main {
    public static final String mainScreenPath = "graphs/gptMainScreen3.png";
    private static User theUser;
    private static Font font;
    public static void main(String[] args) {
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

    
}