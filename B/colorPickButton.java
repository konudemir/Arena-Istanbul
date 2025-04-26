package B;
import C.*;
import Coloring.Coloring;

import javax.swing.JLabel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;

public class colorPickButton extends JLabel implements MouseListener{
    private static CharacterPanel theCharPanel = CharacterPanel.getCharPanel();
    public static Color[] colors = new Color[4];
    private Color c;
    private int order;
    private String context;
    private final int orgwidth;
    private final int orgHeight;
    private final int orgX;
    private final int orgY;
    private static final int dif = 35;
    public colorPickButton(Color c, String context, int x, int y, int width, int height, int order){
        this.c = c;
        this.order = order;
        colors[order] = c;
        this.context = context;
        this.setOpaque(true);
        this.orgwidth = width;
        this.orgHeight = height - dif;
        this.orgX = x;
        this.orgY = y + dif;
        this.setBackground(c);
        this.setBounds(orgX, orgY, orgwidth, orgHeight);
        this.addMouseListener(this);
    }

    //Mouse methods
    @Override
    public void mouseClicked(MouseEvent e)
    {
        for(int i = 0; i < 4; i++)
        {
            if(colors[i] == null)
            {
               colors[i] = Coloring.getOriginalColors()[i];
            }
        }
        theCharPanel.change(colors);
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

    public static String toHEXString(Color color)
    {
        return String.format("%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue());
    }
}
