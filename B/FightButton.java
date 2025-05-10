package B;
import m.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

import C.CharacterPanel;
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
import java.util.ArrayList;

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
        if(context.equals("usePet") && !User.getUser().hasPet())return;
        this.setIcon(clickedButtonIcon);
        allButtonsDisappearAfterTurn();
        if(context.equals("attack"))CharacterPanel.getCharPanel().attack();
        else if(context.equals("moveForward"))CharacterPanel.getCharPanel().moveForward();
        else if(context.equals("moveBackwards"))CharacterPanel.getCharPanel().moveBackwards();
        else if(context.equals("sleep"))CharacterPanel.getCharPanel().sleep();
        FightScreen.usersTurn = false;
        FightScreen.theFightScreen.theFighter.fightersTurn();
    }
    @Override
    public void mousePressed(MouseEvent e)
    {
        if(context.equals("usePet") && !User.getUser().hasPet())return;
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
        if(context.equals("usePet") && !User.getUser().hasPet())return;
        this.setIcon(enteredButton);
        if(!FightScreen.usersTurn) return;
    }
    @Override
    public void mouseExited(MouseEvent e)
    {
        this.setIcon(buttonIcon);
        if(!FightScreen.usersTurn) return;
    }
    public void disappearAfterTurn()
    {
        this.setVisible(false);
        Timer timer = new Timer(2000, _ -> {
            this.setVisible(true);
            FightScreen.theFightScreen.setFightersAnimationToIdle();
            FightScreen.theFightScreen.setUsersAnimationToIdle();
        });
        timer.setRepeats(false);
        timer.start();
    }
    public static void allButtonsDisappearAfterTurn()
    {
        for(FightButton fb : User.getCharPanel().getFightButtons())
        {
            fb.disappearAfterTurn();
        }
        FightScreen.theFightScreen.setUsersAnimationToIdle();
        FightScreen.theFightScreen.setFightersAnimationToIdle();
    }
    
}
