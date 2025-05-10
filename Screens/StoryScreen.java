package Screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import C.CharacterPanel;
import C.User;
import m.MainFrame;

public class StoryScreen extends JPanel {
    public static MainFrame theFrame;

    private static ImageIcon mom = new ImageIcon("graphs/character/story/mom.png");
    private static ImageIcon dad = new ImageIcon("graphs/character/story/dad.png");
    private static ImageIcon backGroundIcon = new ImageIcon("graphs/storyBG.png");

    private JLabel dialogueLabel;
    private int dialogueIndex = 0;
    private String[] firstSceneDialogues = {
            "Father: My son, I have reached the twilight of my life...",
            /*
            "Father: And I have failed to leave a legacy...",
            "Father: It is now your duty to achieve what I could not.",
            "Father: It is now your duty to become a strong and powerful man",
            "Father: a man who the cruel fear, but the oppressed seek for help...",
            "Father: From now on, you will carry the family name!..",
            "You: I promise, father. I will make you proud."
            */
    };
    private String[] secondSceneDialogues = {
            "Mother: Son... tragedy has struck.",
            "You: What has happened mother, is everyone alright?",
            "Mother: The brother of the man you defeated, X, sought revenge.",
            "You: This can not be!..",
            "Mother: He found your father... and ended his life.",
            "You: No... ",
            "You: I swear, I will avenge you, father!"
    };

    private boolean isFirstScene;

    public StoryScreen(boolean isFirstScene) {
        this.isFirstScene = isFirstScene;
        User.getCharPanel().setImage(this);

        MainFrame.currentPanel = this;
        theFrame = MainFrame.theFrame;
        theFrame.removePrevPanelsAndLabels();

        this.setLayout(null);
        this.setBounds(0, 0, 1920, 1080);

        // Background
        JLabel backgroundLabel = new JLabel(backGroundIcon);
        backgroundLabel.setBounds(0, 0, 1920, 1080);

        // Main Character (Son)
        User.getCharPanel().setImage(this);
        User.getCharPanel().setBounds(0, 350, 1920, 1080);
        this.add(User.getCharPanel());

        // Dad or Mom Character
        JLabel otherCharacter = new JLabel(isFirstScene ? dad : mom);
        otherCharacter.setBounds(1400, 500, 400, 400);
        this.add(otherCharacter);

        // Dialogue Label
        dialogueLabel = new JLabel("", SwingConstants.CENTER);
        dialogueLabel.setForeground(Color.WHITE);
        dialogueLabel.setFont(new Font("Serif", Font.BOLD, 32));
        dialogueLabel.setBounds(300, 800, 1300, 100);
        this.add(dialogueLabel);

        showNextDialogue();

        // Mouse Click to proceed dialogues
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showNextDialogue();
            }
        });
        
        this.add(backgroundLabel);

        theFrame.add(this);
        theFrame.repaint();
    }

    private void showNextDialogue() {
        String[] dialogues = isFirstScene ? firstSceneDialogues : secondSceneDialogues;
        if (dialogueIndex < dialogues.length) {
            dialogueLabel.setText(dialogues[dialogueIndex]);
            dialogueIndex++;
        } else {
            if (isFirstScene) {
                // After first scene ends, go to second scene
                new LobbyScreen();
            } else {
                // After second scene, move to the real game screen (replace with your game screen)
                ////theFrame.setPanel(new MainGameScreen()); // Assuming you have a MainGameScreen class
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
