package Screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class EscapeScreen extends JPanel {
    private final JButton btnResume;
    private final JButton btnSave;
    private final JButton btnSettings;
    private final JButton btnExitToMenu;
    private final JButton btnExitGame;

    /**
     * Create a full-screen, semi-transparent overlay with menu buttons.
     * @param frameWidth Width of the parent frame
     * @param frameHeight Height of the parent frame
     */
    public EscapeScreen(int frameWidth, int frameHeight) {
        // Full size overlay
        setBounds(0, 0, frameWidth, frameHeight);
        setBackground(new Color(0, 0, 0, 170));
        setLayout(new GridBagLayout());
        setOpaque(true);

        // Center container for title + buttons
        JPanel container = new JPanel(new GridLayout(6, 1, 0, 10));
        container.setOpaque(false);

        // Title
        JLabel title = new JLabel("Paused", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        container.add(title);

        // Buttons
        btnResume = createButton("Resume");
        btnSave = createButton("Save Game");
        btnSettings = createButton("Settings");
        btnExitToMenu = createButton("Exit to Main Menu");
        btnExitGame = createButton("Exit Game");

        container.add(btnResume);
        container.add(btnSave);
        container.add(btnSettings);
        container.add(btnExitToMenu);
        container.add(btnExitGame);

        // Add container centered
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(container, gbc);

        // Set default actions
        setDefaultActions();

        // Hidden at startup
        setVisible(false);
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.PLAIN, 18));
        return btn;
    }

    private void setDefaultActions() {
        setResumeAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EscapeScreen.this.setVisible(false);
            }
        });

        setSaveAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Game saved!");
            }
        });

        setSettingsAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Opening settings...");
            }
        });

        setExitToMenuAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Returning to main menu...");
            }
        });

        setExitGameAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * Bind ESC on the frame's root pane to toggle this overlay.
     */
    public void registerKeyBinding(JFrame frame) {
        JRootPane root = frame.getRootPane();
        KeyStroke escKey = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        String actionName = "toggleEscapeScreen";

        root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(escKey, actionName);
        root.getActionMap().put(actionName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(!isVisible());
            }
        });
    }

    // Action setters (allows overriding if needed)
    public void setResumeAction(AbstractAction action) {
        btnResume.setAction(action);
        btnResume.setText("Resume");
    }

    public void setSaveAction(AbstractAction action) {
        btnSave.setAction(action);
        btnSave.setText("Save Game");
    }

    public void setSettingsAction(AbstractAction action) {
        btnSettings.setAction(action);
        btnSettings.setText("Settings");
    }

    public void setExitToMenuAction(AbstractAction action) {
        btnExitToMenu.setAction(action);
        btnExitToMenu.setText("Exit to Main Menu");
    }

    public void setExitGameAction(AbstractAction action) {
        btnExitGame.setAction(action);
        btnExitGame.setText("Exit Game");
    }
}
