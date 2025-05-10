package Screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import C_ITEMS.*;
import m.Main;
import m.MainFrame;

public class StoreScreen extends JPanel {
    public static MainFrame theFrame;
    public static StoreScreen theStoreScreen;
    public JLabel coinSpace;
    public JLabel storeLog;
    public ArrayList<String> storeLogStrings = new ArrayList<>();
    public static final int MAX_AMOUNT_OF_LINES = 15;

    public StoreScreen() {
        theStoreScreen = this;
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

        storeLog = new JLabel("", JLabel.CENTER);
        storeLog.setIcon(new ImageIcon("graphs/storeLog.png"));
        storeLog.setFont(new Font("Impact", Font.PLAIN, 30));
        storeLog.setForeground(Color.white);
        storeLog.setBounds(1450, 270, 400, 600);
        storeLog.setHorizontalTextPosition(JLabel.CENTER);

        // Add main components to the panel
        this.add(coinSpace);
        this.add(storeLog);
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
        createButton("Wooden Sword - 100 coins", 100, 150, _ -> purchaseItem(new Sword(0)));
        createButton("Iron Sword - 200 coins", 100, 300, _ -> purchaseItem(new Sword(1)));
        createButton("Gold Sword - 300 coins", 100, 450, _ -> purchaseItem(new Sword(2)));
        createButton("Diamond Sword - 4000 coins", 100, 600, _ -> purchaseItem(new Sword(3)));

        // Back button
        createButton("Back", 100, 750, _ -> createCategoryButtons());
    }

    private void showShields() {
        clearButtons();

        // Shield items
        createButton("Wooden Shield - 100 coins", 100, 150, _ -> purchaseItem(new Shield(0)));
        createButton("Iron Shield - 200 coins", 100, 300, _ -> purchaseItem(new Shield(1)));
        createButton("Gold Shield - 300 coins", 100, 450, _ -> purchaseItem(new Shield(2)));
        createButton("Diamond Shield - 400 coins", 100, 600, _ -> purchaseItem(new Shield(3)));

        // Back button
        createButton("Back", 100, 750, _ -> createCategoryButtons());
    }

    private void showHelmets() {
        clearButtons();

        // Helmet items
        createButton("Leather Helmet - 100 coins", 100, 150, _ -> purchaseItem(new Helmet(0)));
        createButton("Iron Helmet - 200 coins", 100, 300, _ -> purchaseItem(new Helmet(1)));
        createButton("Gold Helmet - 300 coins", 100, 450, _ -> purchaseItem(new Helmet(2)));
        createButton("Diamond Helmet - 400 coins", 100, 600, _ -> purchaseItem(new Helmet(3)));

        // Back button
        createButton("Back", 100, 750, _ -> createCategoryButtons());
    }

    private void showLeggings() {
        clearButtons();

        // Legging items
        createButton("Leather Leggings - 100 coins", 100, 150, _ -> purchaseItem(new Leggings(0)));
        createButton("Iron Leggings - 200 coins", 100, 300, _ -> purchaseItem(new Leggings(1)));
        createButton("Gold Leggings - 300 coins", 100, 450, _ -> purchaseItem(new Leggings(2)));
        createButton("Diamond Leggings - 400 coins", 100, 600, _ -> purchaseItem(new Leggings(3)));

        // Back button
        createButton("Back", 100, 750, _ -> createCategoryButtons());
    }

    private void showArmory() {
        clearButtons();

        // Armory items
        createButton("Wooden Armor - 100 coins", 100, 150, _ -> purchaseItem(new Armor(0)));
        createButton("Iron Armor - 200 coins", 100, 300, _ -> purchaseItem(new Armor(1)));
        createButton("Gold Armor - 300 coins", 100, 450, _ -> purchaseItem(new Armor(2)));
        createButton("Diamond Armor - 400 coins", 100, 600, _ -> purchaseItem(new Armor(3)));

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
            Main.theUser.buyItem(item); // Handles coin deduction and inventory
            coinSpace.setText("" + Main.theUser.getCoins());
            updateStoreLog(item, true);
        } else {
            updateStoreLog(item, false);
        }
        this.repaint();
    }


    public void updateStoreLog(Item item, boolean bought)
    {
        String status = bought ? "Bought" : "Could not buy";
        String logEntry = status + ": " + item.getName();

        storeLogStrings.add(logEntry);

        // Remove oldest entries if limit exceeded
        while (storeLogStrings.size() > MAX_AMOUNT_OF_LINES) {
            storeLogStrings.remove(0);
        }

        // Update label text with line breaks
        StringBuilder sb = new StringBuilder("<html>");
        for (String line : storeLogStrings) {
            sb.append(line).append("<br>");
        }
        sb.append("</html>");

        storeLog.setText(sb.toString());
    }

    private void returnToLobby() {
        new LobbyScreen();
    }
}