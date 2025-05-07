package Screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import C_ITEMS.*;
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
        createButton("Wooden Sword - 100 coins", 100, 150, _ -> purchaseItem(new Sword(1)));
        createButton("Iron Sword - 250 coins", 100, 300, _ -> purchaseItem(new Sword(2)));
        createButton("Gold Sword - 500 coins", 100, 450, _ -> purchaseItem(new Sword(3)));
        createButton("Diamond Sword - 1000 coins", 100, 600, _ -> purchaseItem(new Sword(4)));

        // Back button
        createButton("Back", 100, 750, _ -> createCategoryButtons());
    }


    private void showShields() {
        clearButtons();

        // Shield items
        createButton("Wooden Shield - 80 coins", 100, 150, _ -> purchaseItem(new Shield(1)));
        createButton("Iron Shield - 200 coins", 100, 300, _ -> purchaseItem(new Shield(2)));
        createButton("Gold Shield - 450 coins", 100, 450, _ -> purchaseItem(new Shield(3)));
        createButton("Diamond Shield - 900 coins", 100, 600, _ -> purchaseItem(new Shield(4)));

        // Back button
        createButton("Back", 100, 750, _ -> createCategoryButtons());
    }

    private void showHelmets() {
        clearButtons();

        // Helmet items
        createButton("Leather Helmet - 50 coins", 100, 150, _ -> purchaseItem(new Helmet(1)));
        createButton("Iron Helmet - 150 coins", 100, 300, _ -> purchaseItem(new Helmet(2)));
        createButton("Gold Helmet - 400 coins", 100, 450, _ -> purchaseItem(new Helmet(3)));
        createButton("Diamond Helmet - 800 coins", 100, 600, _ -> purchaseItem(new Helmet(4)));

        // Back button
        createButton("Back", 100, 750, _ -> createCategoryButtons());
    }

    private void showLeggings() {
        clearButtons();

        // Legging items
        createButton("Leather Leggings - 80 coins", 100, 150, _ -> purchaseItem(new Leggings(1)));
        createButton("Iron Leggings - 200 coins", 100, 300, _ -> purchaseItem(new Leggings(2)));
        createButton("Gold Leggings - 500 coins", 100, 450, _ -> purchaseItem(new Leggings(3)));
        createButton("Diamond Leggings - 1000 coins", 100, 600, _ -> purchaseItem(new Leggings(4)));

        // Back button
        createButton("Back", 100, 750, _ -> createCategoryButtons());
    }

    private void showArmory() {
        clearButtons();

        // Armory items
        createButton("Wooden Armor - 120 coins", 100, 150, _ -> purchaseItem(new Armor(1)));
        createButton("Iron Armor - 300 coins", 100, 300, _ -> purchaseItem(new Armor(2)));
        createButton("Gold Armor - 600 coins", 100, 450, _ -> purchaseItem(new Armor(3)));
        createButton("Diamond Armor - 1200 coins", 100, 600, _ -> purchaseItem(new Armor(4)));

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

    private void purchaseItem(Item item) {
        if (Main.theUser.getCoins() >= item.getPrice()) {
            item.setOwner(Main.theUser);
            Main.theUser.buyItem(item); // Handles coin deduction and inventory
            coinSpace.setText("" + Main.theUser.getCoins());
            System.out.println(item.getName() + " purchased for " + item.getPrice() + " coins");
        } else {
            System.out.println("Not enough coins!");
        }
    }


    private void returnToLobby() {
        new LobbyScreen();
    }

}
