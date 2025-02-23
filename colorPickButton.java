import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;

public class colorPickButton extends JLabel implements MouseListener{
    private static CharacterPanel theCharPanel = CharacterPanel.getCharPanel();
    private Color c;
    private String context;
    private final int orgwidth;
    private final int orgHeight;
    private final int orgX;
    private final int orgY;
    public colorPickButton(Color c, String context, int x, int y, int width, int height){
        this.c = c;
        this.context = context;
        this.setOpaque(true);
        this.orgwidth = width;
        this.orgHeight = height;
        this.orgX = x;
        this.orgY = y;
        this.setBackground(c);
        this.setBounds(x, y, width, height);
        this.addMouseListener(this);
        
    }

    //Mouse methods
    @Override
    public void mouseClicked(MouseEvent e)
    {
        if(this.context.equalsIgnoreCase("hair"))theCharPanel.changeHair(c);
        if(this.context.equalsIgnoreCase("skin"))theCharPanel.changeSkin(c);
        theCharPanel.repaint();
    }
    @Override
    public void mousePressed(MouseEvent e)
    {
        this.setBounds(orgX + orgwidth / 5, orgY + orgHeight / 5, orgwidth * 4 / 5, orgHeight * 4 / 5);   
    }
    @Override
    public void mouseReleased(MouseEvent e)
    {
        this.setBounds(orgX, orgY, orgwidth, orgHeight);
    }
    @Override
    public void mouseEntered(MouseEvent e)
    {
    }
    @Override
    public void mouseExited(MouseEvent e)
    {
    }
}
