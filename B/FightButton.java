package B;
import m.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import C.User;
import Screens.FightScreen;
import Screens.LobbyScreen;
import Screens.StoryScreen;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class FightButton extends JLabel implements MouseListener{
    private final ImageIcon buttonIcon;
    private final ImageIcon clickedButtonIcon;
    private final ImageIcon enteredButton;
    private String context;
    private boolean clickable;
    public static FightScreen theFightScreen;
    public FightButton(String name, int x, int y)
    {
        this.setBounds(x, y - 500, 90, 90);
        this.clickable = true;
        this.context = name;
        this.buttonIcon = new ImageIcon("graphs/fight/" + context + ".png");
        String cont = Character.toUpperCase(context.charAt(0)) + context.substring(1, context.length());
        this.clickedButtonIcon = new ImageIcon("graphs/fight/clicked" + cont + ".png");
        this.enteredButton = new ImageIcon("graphs/fight/entered" + cont + ".png");
        this.setIcon(buttonIcon);
        this.addMouseListener(this);
    }

    //Mouse methods
    @Override
    public void mouseClicked(MouseEvent e)
    {
        if(!FightScreen.usersTurn) return;
        this.setIcon(clickedButtonIcon);
        if(context.equals("attack"))theFightScreen.attack(User.getUser());
        else if(context.equals("moveForward"))theFightScreen.moveForward(User.getUser());
        else if(context.equals("moveBackwards"))theFightScreen.moveBackwards(User.getUser());
        else if(context.equals("sleep"))theFightScreen.sleep(User.getUser());
        else if(context.equals("usePet"))theFightScreen.usePet(User.getUser());
        FightScreen.usersTurn = false;
        FightScreen.theFightScreen.theFighter.whatShouldItDo();
    }
    @Override
    public void mousePressed(MouseEvent e)
    {
        this.setIcon(this.clickedButtonIcon);
        if(!FightScreen.usersTurn) return;
    }
    @Override
    public void mouseReleased(MouseEvent e)
    {
        this.setIcon(buttonIcon);
        if(!FightScreen.usersTurn) return;
    }
    @Override
    public void mouseEntered(MouseEvent e)
    {
        this.setIcon(enteredButton);
        if(!FightScreen.usersTurn) return;
    }
    @Override
    public void mouseExited(MouseEvent e)
    {
        this.setIcon(buttonIcon);
        if(!FightScreen.usersTurn) return;
    }
    
}
