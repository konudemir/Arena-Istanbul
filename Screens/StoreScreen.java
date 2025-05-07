package Screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import m.Main;
import m.MainFrame;

public class StoreScreen extends JPanel {
    public static MainFrame theFrame;
    private JLabel coinSpace;

    public StoreScreen() {
        MainFrame.currentPanel = this;
        theFrame = MainFrame.theFrame;
        this.setLayout(null);

        // Background setup
        ImageIcon backgroundIcon = new ImageIcon("graphs/store.png");
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setBounds(0, 0, 1920, 1080);

        this.setBounds(0, 0, 1920, 1080);
        theFrame.removePrevPanelsAndLabels();

        // Character and merchant setup
        Main.theUser.moveTo(1000, 530);
        JLabel merchant = new JLabel(new ImageIcon("graphs/merchant.png"));
        merchant.setBounds(0, 50, 1920, 1080);

        JLabel shelf = new JLabel(new ImageIcon("graphs/storeShelf.png"));
        shelf.setBounds(0, 0, 1920, 1080);


        // Coin display
        coinSpace = new JLabel("" + Main.theUser.getCoins(), JLabel.CENTER);
        coinSpace.setIcon(new ImageIcon("graphs/coinSpace.png"));
        coinSpace.setFont(new Font("Impact", Font.PLAIN, 100));
        coinSpace.setForeground(Color.white);
        coinSpace.setBounds(1450, 120, 400, 115);
        coinSpace.setHorizontalTextPosition(JLabel.CENTER);

        // Add main components to the panel
        this.add(coinSpace);
        this.add(shelf);
        this.add(merchant);
        this.add(backgroundLabel);

        // Create category buttons
        createCategoryButtons();

        theFrame.add(this);
        theFrame.repaint();
    }

    private void createCategoryButtons() {
        // Clear existing buttons first
        clearButtons();

        // Create category buttons
        createButton("Swords", 100, 300, _ -> showSwords());
        createButton("Shields", 100, 450, _ -> showShields());
        createButton("Armory", 100, 600, _ -> showArmory());
        createButton("Helmet", 100, 750, _ -> showHelmets());
        createButton("Leggings", 100, 900, _ -> showLeggings());
        createButton("Back", 1500, 900, _ -> returnToLobby());
    }

    private void showSwords() {
        clearButtons();

        // Sword items
        createButton("Wooden Sword - 100 coins", 100, 150, _ -> purchaseItem(100));
        createButton("Iron Sword - 250 coins", 100, 300, _ -> purchaseItem(250));
        createButton("Gold Sword - 500 coins", 100, 450, _ -> purchaseItem(500));
        createButton("Diamond Sword - 1000 coins", 100, 600, _ -> purchaseItem(1000));

        // Back button
        createButton("Back", 100, 750, _ -> createCategoryButtons());
    }


    private void showShields() {
        clearButtons();

        // Shield items
        createButton("Wooden Shield - 80 coins", 100, 150, _ -> purchaseItem(80));
        createButton("Iron Shield - 200 coins", 100, 300, _ -> purchaseItem(200));
        createButton("Gold Shield - 450 coins", 100, 450, _ -> purchaseItem(450));
        createButton("Diamond Shield - 900 coins", 100, 600, _ -> purchaseItem(900));

        // Back button
        createButton("Back", 100, 750, _ -> createCategoryButtons());
    }

    private void showHelmets() {
        clearButtons();

        // Helmet items
        createButton("Leather Helmet - 50 coins", 100, 150, _ -> purchaseItem(50));
        createButton("Iron Helmet - 150 coins", 100, 300, _ -> purchaseItem(150));
        createButton("Gold Helmet - 400 coins", 100, 450, _ -> purchaseItem(400));
        createButton("Diamond Helmet - 800 coins", 100, 600, _ -> purchaseItem(800));

        // Back button
        createButton("Back", 100, 750, _ -> createCategoryButtons());
    }

    private void showLeggings() {
        clearButtons();

        // Legging items
        createButton("Leather Leggings - 80 coins", 100, 150, _ -> purchaseItem(80));
        createButton("Iron Leggings - 200 coins", 100, 300, _ -> purchaseItem(200));
        createButton("Gold Leggings - 500 coins", 100, 450, _ -> purchaseItem(500));
        createButton("Diamond Leggings - 1000 coins", 100, 600, _ -> purchaseItem(1000));

        // Back button
        createButton("Back", 100, 750, _ -> createCategoryButtons());
    }

    private void showArmory() {
        clearButtons();

        // Armory items
        createButton("Wooden Armor - 120 coins", 100, 150, _ -> purchaseItem(120));
        createButton("Iron Armor - 300 coins", 100, 300, _ -> purchaseItem(300));
        createButton("Gold Armor - 600 coins", 100, 450, _ -> purchaseItem(600));
        createButton("Diamond Armor - 1200 coins", 100, 600, _ -> purchaseItem(1200));

        // Back button
        createButton("Back", 100, 750, _ -> createCategoryButtons());
    }

    private void createButton(String text, int x, int y, ActionListener action) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 300, 100);
        button.setFont(new Font("Impact", Font.PLAIN, 20));
        button.setBackground(new Color(70, 70, 70, 200));
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.addActionListener(action);
        this.add(button);
        this.setComponentZOrder(button, 0); // Bring to front
    }

    private void clearButtons() {
        // Remove all buttons from the panel
        for (java.awt.Component comp : this.getComponents()) {
            if (comp instanceof JButton) {
                this.remove(comp);
            }
        }
        repaint();
    }

    private void purchaseItem(int cost) {
        if (Main.theUser.getCoins() >= cost) {
            //Main.theUser.subtractCoins(cost);
            coinSpace.setText("" + Main.theUser.getCoins());
            // Here you would add the item to the user's inventory
            System.out.println("Item purchased for " + cost + " coins");
        } else {
            System.out.println("Not enough coins!");
        }
    }

    private void returnToLobby() {
        new LobbyScreen();
    }
    private JButton createBackButton() {
        JButton button = new JButton(BACK_BUTTON_IMAGE);
        button.setBounds(1500, 850, 200, 100);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        return button;
    }
}
