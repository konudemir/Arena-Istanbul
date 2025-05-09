// File: EscapeScreen.java
package Screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

/**
 * Semi-transparent pause overlay with menu buttons.
 */
public class EscapeScreen extends JPanel {
    private final JButton btnResume;
    private final JButton btnSave;
    private final JButton btnSettings;
    private final JButton btnExitToMenu;
    private final JButton btnExitGame;

    /**
     * @param frameWidth  Width of parent frame
     * @param frameHeight Height of parent frame
     */
    public EscapeScreen(int frameWidth, int frameHeight) {
        setBounds(0, 0, frameWidth, frameHeight);
        setBackground(new Color(0, 0, 0, 170));
        setLayout(new GridBagLayout());
        setOpaque(true);

        JPanel container = new JPanel(new GridLayout(6, 1, 0, 10));
        container.setOpaque(false);

        JLabel title = new JLabel("Paused", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        container.add(title);

        btnResume     = createButton("Resume");
        btnSave       = createButton("Save Game");
        btnSettings   = createButton("Settings");
        btnExitToMenu = createButton("Exit to Map");  // changed label
        btnExitGame   = createButton("Exit Game");

        container.add(btnResume);
        container.add(btnSave);
        container.add(btnSettings);
        container.add(btnExitToMenu);
        container.add(btnExitGame);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(container, gbc);

        setDefaultActions();
        setVisible(false);
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.PLAIN, 18));
        return btn;
    }

    private void setDefaultActions() {
        setResumeAction(new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        setSaveAction(new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                System.out.println("Game saved!");
            }
        });
        setSettingsAction(new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                System.out.println("Opening settings...");
            }
        });
        // Default: no-op; override in FightScreen
        setExitToMenuAction(new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                // overridden by FightScreen to return to map
            }
        });
        setExitGameAction(new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * Toggle visibility on ESC key press.
     */
    public void registerKeyBinding(JRootPane root) {
        KeyStroke esc = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        String toggle = "togglePause";
        root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(esc, toggle);
        root.getActionMap().put(toggle, new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                setVisible(!isVisible());
            }
        });
    }

    public void setResumeAction(AbstractAction a)     { btnResume.setAction(a);     btnResume.setText("Resume"); }
    public void setSaveAction(AbstractAction a)       { btnSave.setAction(a);       btnSave.setText("Save Game"); }
    public void setSettingsAction(AbstractAction a)   { btnSettings.setAction(a);   btnSettings.setText("Settings"); }
    public void setExitToMenuAction(AbstractAction a) { btnExitToMenu.setAction(a); btnExitToMenu.setText("Exit to Map"); }
    public void setExitGameAction(AbstractAction a)   { btnExitGame.setAction(a);   btnExitGame.setText("Exit Game"); }
}
