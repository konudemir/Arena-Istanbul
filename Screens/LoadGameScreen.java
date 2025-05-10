package Screens;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Dimension;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import m.*;

public class LoadGameScreen extends JPanel{
    public static MainFrame theFrame;
    public static LoadGameScreen theLoadGameScreen;
    public JLabel backButton;
    public LoadGameScreen()
    {
        theLoadGameScreen = this;
        MainFrame.currentPanel = this;
        theFrame = MainFrame.theFrame;
        this.setLayout(null);

        // Background setup
        ImageIcon backgroundIcon = new ImageIcon("graphs/loadSavesBackground.png");
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setBounds(0, 0, 1920, 1080);

        this.setBounds(0, 0, 1920, 1080);
        theFrame.removePrevPanelsAndLabels();

        this.addBackButton();
        this.addSaveButtons();
        this.add(backgroundLabel);

        theFrame.add(this);
        theFrame.repaint();
    }
    public void addBackButton()
    {
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

        for (String saveName : saves) {
            final String name = saveName;
            JButton saveButton = new JButton(saveName);
            saveButton.setFont(new Font("Impact", Font.PLAIN, 30));
            saveButton.setAlignmentX(CENTER_ALIGNMENT);
            Dimension size = new Dimension(600, 100);
            saveButton.setPreferredSize(size);
            saveButton.setMaximumSize(size);
            saveButton.setMinimumSize(size);
            saveButton.addActionListener(_ -> {
                try {
                Saves.readSave(name);
                new LobbyScreen();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            buttonPanel.add(saveButton);
            buttonPanel.add(Box.createVerticalStrut(10)); // Spacing between buttons
        }

        JScrollPane scrollPane = new JScrollPane(buttonPanel);
        scrollPane.setBounds(150, 100, 600, 800);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // smoother scroll
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);

        this.add(scrollPane);
    }
}
