package Screens;

import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import m.MainFrame;

import java.awt.*;
import java.awt.geom.*;

/**
 * A custom JPanel that replicates the credits screen design
 * with decorative corners and a vintage beige/golden color scheme.
 * Includes a new title section with Oğuzhan Aydın listed under it.
 */
public class CreditsScreen extends JPanel {
    private static CreditsScreen theCreditsScreen;
    // Colors from the image
    private final Color backgroundColor = new Color(209, 179, 122); // Main beige/gold background
    private final Color borderColor = new Color(42, 33, 27);       // Dark border color
    private final Color textColor = new Color(0, 0, 0);           // Black text color
    
    private boolean isAtSpecialThanks = true;
    
    // Names for main credits
    private final String[] creditNames = {
        "Demir Konu",
        "Efe Demir",
        "Mehmet Avni Arslan",
        "Taha Demirhan"
    };
    
    // Second title and name
    private final String secondTitle = "SPECIAL THANKS";
    private final String specialName = "Oğuzhan Aydin";
    
    public CreditsScreen() {
        theCreditsScreen = this;
        MainFrame.currentPanel = this;
        this.setLayout(null);
        MainFrame.theFrame.removePrevPanelsAndLabels();
        this.setBounds(0, 0, 1920, 1080);

        setBackground(backgroundColor);
        setLayout(null); // Using absolute positioning

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(isAtSpecialThanks)
                {
                    isAtSpecialThanks = false;
                    theCreditsScreen.repaint();
                    MainFrame.theFrame.repaint();
                }
                else new FirstMenu();
            }
        });
        MainFrame.theFrame.add(this);
        MainFrame.theFrame.repaint();
        this.repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int width = getWidth();
        int height = getHeight();
        
        // Draw outer border (90% of panel size)
        int borderMargin = Math.min(width, height) / 20;
        int borderWidth = width - (borderMargin * 2);
        int borderHeight = height - (borderMargin * 2);
        
        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(2.0f));
        g2d.drawRect(borderMargin, borderMargin, borderWidth, borderHeight);
        
        // Draw decorative corners
        int cornerSize = Math.min(width, height) / 15;
        drawCorner(g2d, borderMargin, borderMargin, cornerSize, 0); // Top left
        drawCorner(g2d, borderMargin + borderWidth - cornerSize, borderMargin, cornerSize, 1); // Top right
        drawCorner(g2d, borderMargin, borderMargin + borderHeight - cornerSize, cornerSize, 2); // Bottom left
        drawCorner(g2d, borderMargin + borderWidth - cornerSize, borderMargin + borderHeight - cornerSize, cornerSize, 3); // Bottom right
        
        // Draw "CREDITS" header
        Font headerFont = new Font("Serif", Font.BOLD, Math.min(width, height) / 15);
        g2d.setFont(headerFont);
        g2d.setColor(textColor);
        
        String creditsText = "CREDITS";
        FontMetrics fontMetrics = g2d.getFontMetrics();
        int creditsWidth = fontMetrics.stringWidth(creditsText);
        
        // Create a rectangle for the "CREDITS" text
        int creditsRectWidth = creditsWidth + 50;
        int creditsRectHeight = fontMetrics.getHeight() + 10;
        int creditsRectX = (width - creditsRectWidth) / 2;
        int creditsRectY = borderMargin + (borderHeight / 8);
        
        // Draw the rectangle around "CREDITS"
        g2d.setColor(borderColor);
        g2d.drawRect(creditsRectX, creditsRectY, creditsRectWidth, creditsRectHeight);
        
        // Draw the "CREDITS" text
        g2d.setColor(textColor);
        g2d.drawString(creditsText, creditsRectX + (creditsRectWidth - creditsWidth) / 2, 
                creditsRectY + fontMetrics.getAscent() + 5);
        
        // Draw names
        Font nameFont = new Font("Serif", Font.PLAIN, Math.min(width, height) / 25);
        g2d.setFont(nameFont);
        fontMetrics = g2d.getFontMetrics();
        
        int nameY = creditsRectY + creditsRectHeight + fontMetrics.getHeight() * 2;
        int nameSpacing = fontMetrics.getHeight() * 2;
        
        for (String name : creditNames) {
            int nameWidth = fontMetrics.stringWidth(name);
            g2d.drawString(name, (width - nameWidth) / 2, nameY);
            nameY += nameSpacing;
        }
        
        // Add some spacing between sections
        nameY += nameSpacing / 2;
        
        // Draw second title ("SPECIAL THANKS")
        Font secondTitleFont = new Font("Serif", Font.BOLD, Math.min(width, height) / 20);
        g2d.setFont(secondTitleFont);
        fontMetrics = g2d.getFontMetrics();
        int secondTitleWidth = fontMetrics.stringWidth(secondTitle);
        
        // Create rectangle for second title
        int secondTitleRectWidth = secondTitleWidth + 50;
        int secondTitleRectHeight = fontMetrics.getHeight() + 10;
        int secondTitleRectX = (width - secondTitleRectWidth) / 2;
        int secondTitleRectY = nameY;
        
        // Draw rectangle around second title
        g2d.setColor(borderColor);
        g2d.drawRect(secondTitleRectX, secondTitleRectY, secondTitleRectWidth, secondTitleRectHeight);
        
        // Draw second title text
        g2d.setColor(textColor);
        g2d.drawString(isAtSpecialThanks ? secondTitle : specialName, secondTitleRectX + (secondTitleRectWidth - secondTitleWidth) / 2 + (!isAtSpecialThanks ? 60 : 0), 
                secondTitleRectY + fontMetrics.getAscent() + 5);
        
        // Draw special name (Oğuzhan Aydın)
        g2d.setFont(nameFont);
        fontMetrics = g2d.getFontMetrics();
        nameY = secondTitleRectY + secondTitleRectHeight + fontMetrics.getHeight() * 2;
        
        int specialNameWidth = fontMetrics.stringWidth(specialName);
        g2d.drawString(specialName, (width - specialNameWidth) / 2, nameY);
    }
    
    /**
     * Draws a decorative corner pattern
     * @param g2d Graphics2D object
     * @param x Top-left x coordinate
     * @param y Top-left y coordinate
     * @param size Size of the corner
     * @param position 0=top-left, 1=top-right, 2=bottom-left, 3=bottom-right
     */
    private void drawCorner(Graphics2D g2d, int x, int y, int size, int position) {
        g2d.setColor(borderColor);
        
        // Store original transform
        AffineTransform originalTransform = g2d.getTransform();
        
        // Rotate and position based on corner position
        switch (position) {
            case 0: // Top left (no rotation needed)
                break;
            case 1: // Top right
                g2d.translate(x + size, y);
                g2d.rotate(Math.PI / 2);
                break;
            case 2: // Bottom left
                g2d.translate(x, y + size);
                g2d.rotate(-Math.PI / 2);
                break;
            case 3: // Bottom right
                g2d.translate(x + size, y + size);
                g2d.rotate(Math.PI);
                break;
        }
        
        // Draw the decorative corner scrollwork
        int scrollSize = size / 2;
        
        // Draw main diagonal line
        g2d.setStroke(new BasicStroke(2.0f));
        g2d.drawLine(0, 0, scrollSize, scrollSize);
        
        // Draw curved scrollwork
        g2d.drawArc(0, 0, scrollSize, scrollSize, 0, 90);
        g2d.drawArc(scrollSize/2, scrollSize/2, scrollSize/2, scrollSize/2, 180, 90);
        
        // Restore original transform
        g2d.setTransform(originalTransform);
    }   
}