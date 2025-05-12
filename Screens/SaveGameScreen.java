package Screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import m.*;

public class SaveGameScreen extends JPanel {
    public static MainFrame theFrame;
    public static SaveGameScreen theSaveGameScreen;
    public JLabel backButton;

    public SaveGameScreen() {
        theSaveGameScreen = this;
        MainFrame.currentPanel = this;
        theFrame = MainFrame.theFrame;
        this.setLayout(null);

        // Background setup
        ImageIcon backgroundIcon = new ImageIcon("graphs/loadSavesBackground.png");
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setBounds(0, 0, 1920, 1080);

        this.setBounds(0, 0, 1920, 1080);
        theFrame.removePrevPanelsAndLabels();
        FightScreen.theFightScreen.remove(EscapeScreen.theEscapeScreen);

        this.addBackButton();
        this.addSaveButtons();
        this.add(backgroundLabel);

        theFrame.add(this);
        theFrame.repaint();
    }

    public void addBackButton() {
        backButton = new JLabel();
        backButton.setIcon(new ImageIcon("graphs/arrow.png"));
        backButton.setText("BACK");
        backButton.setHorizontalTextPosition(JLabel.CENTER);
        backButton.setForeground(Color.white);
        backButton.setFont(new Font("Impact", Font.PLAIN, 50));
        backButton.setVerticalAlignment(JLabel.CENTER);
        backButton.setHorizontalAlignment(JLabel.CENTER);
        backButton.setBounds(1500, 850, 400, 200);
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new FirstMenu();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                backButton.setIcon(new ImageIcon("graphs/enteredArrow.png"));
                backButton.setFont(new Font("Impact", Font.PLAIN, 50));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backButton.setIcon(new ImageIcon("graphs/arrow.png"));
                backButton.setFont(new Font("Impact", Font.PLAIN, 50));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                backButton.setIcon(new ImageIcon("graphs/clickedArrow.png"));
                backButton.setFont(new Font("Impact", Font.PLAIN, 46));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                backButton.setIcon(new ImageIcon("graphs/arrow.png"));
                backButton.setFont(new Font("Impact", Font.PLAIN, 50));
            }
        });
        this.add(backButton);
    }

    public void addSaveButtons() {
        ArrayList<String> saves = Saves.getAllSaves();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.ORANGE);

        // Add "New Save Slot" button
            JLabel newSaveButton = new JLabel("NEW SAVE");
            newSaveButton.setIcon(new ImageIcon("graphs/save.png"));
            newSaveButton.setFont(new Font("Impact", Font.PLAIN, 30));
            newSaveButton.setVerticalAlignment(JLabel.CENTER);
            newSaveButton.setHorizontalAlignment(JLabel.CENTER);
            newSaveButton.setHorizontalTextPosition(JLabel.CENTER);
            newSaveButton.setForeground(Color.white);
            Dimension size = new Dimension(600, 100);
            newSaveButton.setPreferredSize(size);
            newSaveButton.setMaximumSize(size);
            newSaveButton.setMinimumSize(size);
            newSaveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            String saveName = JOptionPane.showInputDialog("Enter new save name!", "Enter new save name:");
            if (saveName != null && !saveName.trim().isEmpty()) {
                try {
                    Saves.saveGame(saveName);
                    new LobbyScreen(); // or you can refresh SaveGameScreen to show new entry
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            }
    
            @Override
            public void mouseEntered(MouseEvent e) {
                newSaveButton.setIcon(new ImageIcon("graphs/saveEntered.png"));
                newSaveButton.setFont(new Font("Impact", Font.PLAIN, 30));
            }
    
            @Override
            public void mouseExited(MouseEvent e) {
                newSaveButton.setIcon(new ImageIcon("graphs/save.png"));
                newSaveButton.setFont(new Font("Impact", Font.PLAIN, 30));
            }
    
            @Override
            public void mousePressed(MouseEvent e) {
                newSaveButton.setIcon(new ImageIcon("graphs/saveClicked.png"));
                newSaveButton.setFont(new Font("Impact", Font.PLAIN, 30));
            }
    
            @Override
            public void mouseReleased(MouseEvent e) {
                newSaveButton.setIcon(new ImageIcon("graphs/save.png"));
                newSaveButton.setFont(new Font("Impact", Font.PLAIN, 30));
            }
            });

        buttonPanel.add(newSaveButton);
        buttonPanel.add(Box.createVerticalStrut(20));

        // Add existing save buttons
        for (String saveName : saves) {
            final String name = saveName;
            JLabel saveButton = new JLabel(saveName.substring(6, saveName.length() - 4));
            saveButton.setIcon(new ImageIcon("graphs/save.png"));
            saveButton.setFont(new Font("Impact", Font.PLAIN, 30));
            saveButton.setVerticalAlignment(JLabel.CENTER);
            saveButton.setHorizontalAlignment(JLabel.CENTER);
            saveButton.setHorizontalTextPosition(JLabel.CENTER);
            saveButton.setForeground(Color.white);
            Dimension size2 = new Dimension(600, 100);
            saveButton.setPreferredSize(size2);
            saveButton.setMaximumSize(size2);
            saveButton.setMinimumSize(size2);
            saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            try {
                    Saves.saveGame(name);
                    new LobbyScreen();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
    
            @Override
            public void mouseEntered(MouseEvent e) {
                saveButton.setIcon(new ImageIcon("graphs/saveEntered.png"));
                saveButton.setFont(new Font("Impact", Font.PLAIN, 30));
            }
    
            @Override
            public void mouseExited(MouseEvent e) {
                saveButton.setIcon(new ImageIcon("graphs/save.png"));
                saveButton.setFont(new Font("Impact", Font.PLAIN, 30));
            }
    
            @Override
            public void mousePressed(MouseEvent e) {
                saveButton.setIcon(new ImageIcon("graphs/saveClicked.png"));
                saveButton.setFont(new Font("Impact", Font.PLAIN, 30));
            }
    
            @Override
            public void mouseReleased(MouseEvent e) {
                saveButton.setIcon(new ImageIcon("graphs/save.png"));
                saveButton.setFont(new Font("Impact", Font.PLAIN, 30));
            }
            });
            buttonPanel.add(saveButton);
            buttonPanel.add(Box.createVerticalStrut(10)); // Spacing between buttons
        }

        JScrollPane scrollPane = new JScrollPane(buttonPanel);
        scrollPane.setBounds(150, 100, 600, 800);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);

        this.add(scrollPane);
    }
}