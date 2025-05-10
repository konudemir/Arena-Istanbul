package Screens;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import C.CharacterPanel;
import C.CharacterPanelOld;
import C.Fighter;
import C.User;
import m.MainFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class LobbyScreen extends JPanel{
    public static MainFrame theFrame;
    public static LobbyScreen theLobbyScreen;
    public EscapeScreen escScreen = null;

    public LobbyScreen() {
        CharacterPanel.getCharPanel().setImage(this);
        theLobbyScreen = this;
        MainFrame.currentPanel = this;
        theFrame = MainFrame.theFrame;
        this.setLayout(null);

        // Background setup
        ImageIcon backgroundIcon = new ImageIcon("graphs/lobby.png");
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(backgroundIcon);
        backgroundLabel.setBounds(0, 0, 1920, 1080);

        // Remove previous panels and set up the screen
        theFrame.removePrevPanelsAndLabels();
        this.setBounds(0, 0, 1920, 1080);
        
        // Add character panel
        this.add(CharacterPanel.getCharPanel());
        CharacterPanel.getCharPanel().moveTo(100, 560);
        
        // Adjusted button size
        int buttonSize = 180;
        int yPosition = 850;
        //

        //ESC//
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "toggleEscape");
        this.getActionMap().put("toggleEscape", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (escScreen != null) {
                    theLobbyScreen.remove(escScreen);
                    escScreen = null;
                } else {
                    escScreen = new EscapeScreen(1920, 1080);
                    theLobbyScreen.add(escScreen);
                }
                revalidate();
                repaint();
            }
        });
        //ESC END//
        /*
        // Add buttons with resized images
        addButton("graphs/LobyButtons/ArenaButton.png", 500, yPosition, buttonSize, _ -> goToArena());
        addButton("graphs/LobyButtons/ShopButton.png", 750, yPosition, buttonSize, _ -> goToShop());
        addButton("graphs/LobyButtons/SaveButton.png", 1000, yPosition, buttonSize, _ -> saveGame());
        addButton("graphs/LobyButtons/ExitButton.png", 1250, yPosition, buttonSize, _ -> quitGame());
        */
        //START OF STORE BUTTON
        JLabel store = new JLabel();
        store.setOpaque(false);
        try {
            store.setIcon(new ImageIcon("graphs/lobbyStore.png"));
            } catch (Exception e) {
                System.out.println("NO IMAGE");
            }

        store.setBounds(230, 303, 1592, 718);
        store.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e)
            {
                
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                Image img = ((ImageIcon)store.getIcon()).getImage();
                BufferedImage image = toBufferedImage(img);
                if (x >= 0 && y >= 0 && x < image.getWidth(null) && y < image.getHeight(null)) {
                    int pixel = image.getRGB(x, y);
                    int alpha = (pixel >> 24) & 0xff;

                    if (alpha > 0) {
                        // Mouse is over a visible part of the image
                        store.setIcon(new ImageIcon("graphs/lobbyStoreEntered.png"));
                    }
                }
            }
            @Override
            public void mouseExited(MouseEvent e)
            {
                store.setIcon(new ImageIcon("graphs/lobbyStore.png"));
            }
            @Override
            public void mousePressed(MouseEvent e)
            {
                
            }
            @Override
            public void mouseClicked(MouseEvent e)
            {
                int x = e.getX();
                int y = e.getY();
                Image img = ((ImageIcon)store.getIcon()).getImage();
                BufferedImage image = toBufferedImage(img);
                if (x >= 0 && y >= 0 && x < image.getWidth(null) && y < image.getHeight(null)) {
                    int pixel = image.getRGB(x, y);
                    int alpha = (pixel >> 24) & 0xff;

                    if (alpha > 0) {
                        MainFrame.currentPanel = new StoreScreen();
                    }
                }
            }
            @Override
            public void mouseReleased(MouseEvent e)
            {
            }
        });

        store.addMouseMotionListener(new MouseAdapter() {
            ImageIcon storeEntered = new ImageIcon("graphs/lobbyStoreEntered.png");
            ImageIcon storeExited = new ImageIcon("graphs/lobbyStore.png");
            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                Image img = ((ImageIcon)store.getIcon()).getImage();
                BufferedImage image = toBufferedImage(img);
                if (x >= 0 && y >= 0 && x < image.getWidth(null) && y < image.getHeight(null)) {
                    int pixel = image.getRGB(x, y);
                    int alpha = (pixel >> 24) & 0xff;

                    if (alpha > 0) {
                        store.setIcon(storeEntered);
                    }
                    else store.setIcon(storeExited);
                }
            }
        });
        //END OF STORE BUTTON

        //START OF arena BUTTON
        JLabel arena = new JLabel();
        arena.setOpaque(false);
        try {
            arena.setIcon(new ImageIcon("graphs/lobbyArena.png"));
            } catch (Exception e) {
                System.out.println("NO IMAGE");
            }

        arena.setBounds(1440, 0, 479, 908);
        arena.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e)
            {
                
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                Image img = ((ImageIcon)arena.getIcon()).getImage();
                BufferedImage image = toBufferedImage(img);
                if (x >= 0 && y >= 0 && x < image.getWidth(null) && y < image.getHeight(null)) {
                    int pixel = image.getRGB(x, y);
                    int alpha = (pixel >> 24) & 0xff;

                    if (alpha > 0) {
                        // Mouse is over a visible part of the image
                        arena.setIcon(new ImageIcon("graphs/lobbyArenaEntered.png"));
                    } else arena.setIcon(new ImageIcon("graphs/lobbyArena.png"));
                }
            }
            @Override
            public void mouseExited(MouseEvent e)
            {
            }
            @Override
            public void mousePressed(MouseEvent e)
            {
                
            }
            @Override
            public void mouseClicked(MouseEvent e)
            {
                int x = e.getX();
                int y = e.getY();
                Image img = ((ImageIcon)arena.getIcon()).getImage();
                BufferedImage image = toBufferedImage(img);
                if (x >= 0 && y >= 0 && x < image.getWidth(null) && y < image.getHeight(null)) {
                    int pixel = image.getRGB(x, y);
                    int alpha = (pixel >> 24) & 0xff;

                    if (alpha > 0) {
                        //ToDo : 
                        MainFrame.currentPanel = new ArenaScreen();
                    }
                }
            }
            @Override
            public void mouseReleased(MouseEvent e)
            {
            }
        });

        arena.addMouseMotionListener(new MouseAdapter() {
            ImageIcon arenaEntered = new ImageIcon("graphs/lobbyArenaEntered.png");
            ImageIcon arenaExited = new ImageIcon("graphs/lobbyArena.png");
            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                Image img = ((ImageIcon)arena.getIcon()).getImage();
                BufferedImage image = toBufferedImage(img);
                if (x >= 0 && y >= 0 && x < image.getWidth(null) && y < image.getHeight(null)) {
                    int pixel = image.getRGB(x, y);
                    int alpha = (pixel >> 24) & 0xff;

                    if (alpha > 0) {
                        arena.setIcon(arenaEntered);
                    }
                    else arena.setIcon(arenaExited);
                }
            }
        });


        //THE 2ND STORY BUTTON (FOR DEBUG)
        //Add the exit button
        JLabel storyDebugButton = new JLabel();
        storyDebugButton.setIcon(new ImageIcon("graphs/frontArrow.png"));
        storyDebugButton.setText("STORY 2");
        storyDebugButton.setHorizontalTextPosition(JLabel.CENTER);
        storyDebugButton.setForeground(Color.white);
        storyDebugButton.setFont(new Font("Impact", Font.PLAIN, 50));
        storyDebugButton.setVerticalAlignment(JLabel.CENTER);
        storyDebugButton.setHorizontalAlignment(JLabel.CENTER);
        storyDebugButton.setBounds(200, 200, 300, 95);
        storyDebugButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //new FightScreen(User.getUser(), new Fighter(0));
                //new StoreScreen();
                new StoryScreen(false);
            }
    
            @Override
            public void mouseEntered(MouseEvent e) {
                storyDebugButton.setIcon(new ImageIcon("graphs/enteredFrontArrow.png"));
                storyDebugButton.setFont(new Font("Impact", Font.PLAIN, 45));
            }
    
            @Override
            public void mouseExited(MouseEvent e) {
                storyDebugButton.setIcon(new ImageIcon("graphs/frontArrow.png"));
                storyDebugButton.setFont(new Font("Impact", Font.PLAIN, 45));
            }
    
            @Override
            public void mousePressed(MouseEvent e) {
                storyDebugButton.setIcon(new ImageIcon("graphs/clickedFrontArrow.png"));
                storyDebugButton.setFont(new Font("Impact", Font.PLAIN, 41));
            }
    
            @Override
            public void mouseReleased(MouseEvent e) {
                storyDebugButton.setIcon(new ImageIcon("graphs/frontArrow.png"));
                storyDebugButton.setFont(new Font("Impact", Font.PLAIN, 45));
            }
            });
        this.add(storyDebugButton);
        this.add(arena);
        this.add(store);
        this.add(backgroundLabel);
        theFrame.add(this);
        theFrame.repaint();
    }

    private void addButton(String imagePath, int x, int y, int size, ActionListener action) {
        ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH));
        JButton button = new JButton(icon);
        button.setBounds(x, y, size, size);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.addActionListener(action);
        this.add(button);
    }

    private void goToArena() {
        System.out.println("Going to Arena...");
        new FightScreen(User.getUser(), new Fighter(0, false));
    }

    private void goToShop() {
        System.out.println("Going to Shop...");
        new StoreScreen();
    }

    private void saveGame() {
        System.out.println("Game Saved!");
        // Implement game-saving logic
    }

    private void quitGame() {
        System.out.println("Quitting Game...");
        System.exit(0);
    }

    public static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
    
        BufferedImage bimage = new BufferedImage(
            img.getWidth(null),
            img.getHeight(null),
            BufferedImage.TYPE_INT_ARGB
        );
    
        Graphics2D g2d = bimage.createGraphics();
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();
    
        return bimage;
    }
    
}