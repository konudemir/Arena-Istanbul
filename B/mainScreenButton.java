package B;
import m.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Screens.StoryScreen;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Font;

public class mainScreenButton extends JLabel implements MouseListener{
    private final ImageIcon buttonIcon;
    private final ImageIcon clickedButtonIcon;
    private final ImageIcon enteredButton;
    private String context;
    private boolean clickable;

    public mainScreenButton(String context, int x, int y, int width, int height, boolean clickable){
        this.clickable = clickable;
        ImageIcon mainButton = new ImageIcon("graphs/button.png");
        this.context = context;
        //If the requested with or height isn't the same as the button
        if(mainButton.getIconHeight() != height || mainButton.getIconWidth() != width)
        {
            mainButton = new ImageIcon(mainButton.getImage().getScaledInstance(width, height, Image.SCALE_FAST));
        }
        buttonIcon = mainButton;
        clickedButtonIcon = new ImageIcon("graphs/clickedButton.png");
        enteredButton = new ImageIcon("graphs/enteredButton.png");
        this.setIcon(buttonIcon);
        this.setText(this.context);
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setForeground(Color.white);
        this.setFont(new Font("Impact", Font.PLAIN, 60));
        this.setVerticalAlignment(JLabel.CENTER);
        this.setHorizontalAlignment(JLabel.CENTER);

        this.setBounds(x, y, width, height);   

        this.addMouseListener(this);
    }

    public mainScreenButton(String context, int x, int y, int width, int height){
        this(context, x, y, width, height, true);
    }

    //Mouse methods
    @Override
    public void mouseClicked(MouseEvent e)
    {
        if(!this.clickable) return;
        if(context.equals("New Game"))MainFrame.newGameScreenS();
        if(context.equals("Settings"))MainFrame.settingsScreenS();
        if(context.equals("Credits"))MainFrame.creditsScreenS();
        if(context.equals("Load Game"))MainFrame.loadGameScreenS();
    }
    @Override
    public void mousePressed(MouseEvent e)
    {
        if(!this.clickable) return;
        this.setIcon(clickedButtonIcon);
        this.setFont(new Font("Impact", Font.PLAIN, 58));
    }
    @Override
    public void mouseReleased(MouseEvent e)
    {
        if(!this.clickable) return;
        this.setIcon(enteredButton);
        this.setFont(new Font("Impact", Font.PLAIN, 60));
    }
    @Override
    public void mouseEntered(MouseEvent e)
    {
        if(!this.clickable) return;
        this.setIcon(enteredButton);
        this.setFont(new Font("Impact", Font.PLAIN, 60));
    }
    @Override
    public void mouseExited(MouseEvent e)
    {
        if(!this.clickable) return;
        this.setIcon(buttonIcon);
        this.setFont(new Font("Impact", Font.PLAIN, 60));
    }
}
