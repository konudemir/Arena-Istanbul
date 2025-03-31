package Screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
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
        Main.theUser.getUser().moveTo(1000, 530);
        JLabel merchant = new JLabel(new ImageIcon("graphs/merchant.png"));
        merchant.setBounds(700, 50, 1920, 1080);
        
        JLabel shelf = new JLabel(new ImageIcon("graphs/storeShelf.png"));
        shelf.setBounds(0, 0, 1920, 1080);


        // Coin display
        coinSpace = new JLabel("" + Main.theUser.getUser().getCoins(), JLabel.CENTER);
        coinSpace.setIcon(new ImageIcon("graphs/coinSpace0.png"));
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
        createButton("Swords", 100, 300, e -> showSwords());
        createButton("Shields", 100, 450, e -> showShields());
        createButton("Armory", 100, 600, e -> showArmorCategories());
    }
    
    private void showSwords() {
        clearButtons();
        
        // Sword items
        createButton("Iron Sword - 100 coins", 100, 300, e -> purchaseItem(100));
        createButton("Steel Sword - 250 coins", 100, 450, e -> purchaseItem(250));
        createButton("Diamond Sword - 500 coins", 100, 600, e -> purchaseItem(500));
        
        // Back button
        createButton("Back", 100, 750, e -> createCategoryButtons());
    }
    
    private void showShields() {
        clearButtons();
        
        // Shield items
        createButton("Wooden Shield - 80 coins", 100, 300, e -> purchaseItem(80));
        createButton("Iron Shield - 200 coins", 100, 450, e -> purchaseItem(200));
        createButton("Dragon Shield - 450 coins", 100, 600, e -> purchaseItem(450));
        
        // Back button
        createButton("Back", 100, 750, e -> createCategoryButtons());
    }
    
    private void showArmorCategories() {
        clearButtons();
        
        // Armor categories
        createButton("Helmets", 100, 300, e -> showHelmets());
        createButton("Chestplates", 100, 450, e -> showChestplates());
        createButton("Leggings", 100, 600, e -> showLeggings());
        createButton("Boots", 100, 750, e -> showBoots());
        
        // Back button
        createButton("Back", 100, 900, e -> createCategoryButtons());
    }
    
    private void showHelmets() {
        clearButtons();
        
        // Helmet items
        createButton("Leather Helmet - 50 coins", 100, 300, e -> purchaseItem(50));
        createButton("Iron Helmet - 150 coins", 100, 450, e -> purchaseItem(150));
        createButton("Dragon Helmet - 400 coins", 100, 600, e -> purchaseItem(400));
        
        // Back button
        createButton("Back", 100, 750, e -> showArmorCategories());
    }
    
    private void showChestplates() {
        clearButtons();
        
        // Chestplate items
        createButton("Leather Chestplate - 100 coins", 100, 300, e -> purchaseItem(100));
        createButton("Iron Chestplate - 250 coins", 100, 450, e -> purchaseItem(250));
        createButton("Dragon Chestplate - 600 coins", 100, 600, e -> purchaseItem(600));
        
        // Back button
        createButton("Back", 100, 750, e -> showArmorCategories());
    }
    
    private void showLeggings() {
        clearButtons();
        
        // Legging items
        createButton("Leather Leggings - 80 coins", 100, 300, e -> purchaseItem(80));
        createButton("Iron Leggings - 200 coins", 100, 450, e -> purchaseItem(200));
        createButton("Dragon Leggings - 500 coins", 100, 600, e -> purchaseItem(500));
        
        // Back button
        createButton("Back", 100, 750, e -> showArmorCategories());
    }
    
    private void showBoots() {
        clearButtons();
        
        // Boot items
        createButton("Leather Boots - 60 coins", 100, 300, e -> purchaseItem(60));
        createButton("Iron Boots - 150 coins", 100, 450, e -> purchaseItem(150));
        createButton("Dragon Boots - 350 coins", 100, 600, e -> purchaseItem(350));
        
        // Back button
        createButton("Back", 100, 750, e -> showArmorCategories());
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
        if (Main.theUser.getUser().getCoins() >= cost) {
            //Main.theUser.getUser().subtractCoins(cost);
            coinSpace.setText("" + Main.theUser.getUser().getCoins());
            // Here you would add the item to the user's inventory
            System.out.println("Item purchased for " + cost + " coins");
        } else {
            System.out.println("Not enough coins!");
        }
    }
}