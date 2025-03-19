package m;

import C.User;

public class Main {
    public static final String mainScreenPath = "graphs/gptMainScreen3.png";
    private static User theUser;

    public static void main(String[] args) {
        theUser = new User();
        MainFrame frame = new MainFrame();
        frame.getFirstScreen();
    }

    
}