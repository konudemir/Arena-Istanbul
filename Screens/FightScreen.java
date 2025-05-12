package Screens;
import javax.swing.*;

import B.FightButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import C.*;
import C_ITEMS.Cat;
import m.MainFrame;


public class FightScreen extends JPanel {
    public static boolean usersTurn = true;
    public static FightScreen theFightScreen;
    //CONSTANTS
    public static final int ATTACK1 = 3;
    public static final int ATTACK2 = 4;
    public static final int WALK1 = 5;
    public static final int WALK2 = 6;
    public static final int WALK3 = 7;
    public static final int WALK4 = 8;
    public static final int NORMAL_ATTACK_HIT = 8;
    public static final int FRAME_LENGTH = 150;
    //END OF CONSTANTS
    public static MainFrame theFrame;
    public static int wonAgainstEnemies = 0;//Story Mode Only
    public User theUser;
    public Fighter theFighter;
    public FighterAnimation fightersAnimation;
    public FighterAnimation usersAnimation;

    

    /* 
     * NOTES: This is an inner class to animate player movement in three modes: IDLE, MOVING, ATTACKING. Player mode is switched when fighter
     * moves/attacks, and returns back to IDLE after the movement is done. Enum is used for more robust and flexible implementation.
     * CAUTION: Image paths might not be correct and if this class is to be used with both fighters, it might be better to implement a constructor
     * with images as arguments. This class uses javax.swing.Timer which starts running after the class' instance is created and doesn't stop.
     * Instead of adding the fighter directly into this frame, it is necessary to create a correspondent FighterAnimation instance.
     */
    public class FighterAnimation {
        private enum FighterState { IDLE, /* Breathing animation */ MOVING, /* Movement animation */ ATTACKING /* For future expansion */ }
        
        private FighterState currentState = FighterState.IDLE;
        private int currentFrame = 0;
        private Timer animationTimer;
        private FighterPanel fighterPanel; // The component displaying your fighter
        
        // Animation frame arrays
        private final int[] breathingFrames = { 1, 0, 1, 2
        };
        private final int[] movingFrames = { 1, WALK1, WALK2, WALK3, WALK4, WALK2
        };
        private final int[] attackingFrames = {1,  ATTACK1, ATTACK2, ATTACK1
        };
        
        public FighterAnimation(FighterPanel fighterPanel) {
            this.fighterPanel = fighterPanel;
            setupTimer();
        }
        
        private void setupTimer() {
            animationTimer = new Timer(FRAME_LENGTH, _ -> { // 100ms between frames, subject to change.
                if(!(MainFrame.currentPanel instanceof FightScreen))
                {
                    animationTimer.stop();
                    return;
                }
                switch(currentState) {
                    case IDLE:
                        animateBreathing();
                        //System.out.println("IDLE INITIATED");
                        break;
                    case MOVING:
                        animateMovement();
                        //System.out.println("MOVING INITIATED");
                        break;
                    case ATTACKING:
                        animateAttack();
                        //System.out.println("ATTACKING INITIATED");
                        break;
                    default:
                        break;
                }
            });
            animationTimer.start();
        }
        
        private void animateBreathing() {
            currentFrame = (currentFrame + 1) % breathingFrames.length;
            //System.out.println("FRAME CHANGED FOR IDLE");
            fighterPanel.setImage(breathingFrames[currentFrame]);
        }
        
        private void animateMovement() {
            currentFrame = (currentFrame + 1) % movingFrames.length;
            //System.out.println("FRAME CHANGED FOR WALK");
            fighterPanel.setImage(movingFrames[currentFrame]);
        }

        public void animateAttack() {
            //System.out.println("FRAME CHANGED FOR ATTACK");
            switch(currentFrame) {
                case 0: // First frame (a1)
                    fighterPanel.setImage(attackingFrames[0]);
                    currentFrame = 1;
                    break;
                case 1: // Second frame (a2)
                    fighterPanel.setImage(attackingFrames[1]);
                    currentFrame = 2;
                    break;
                case 2: // Back to first frame (a1)
                    fighterPanel.setImage(attackingFrames[0]);
                    setState(FighterState.IDLE); // Return to idle
                    break;
            }
        }
        
        public void setState(FighterState newState) {
            if (this.currentState != newState) {
                this.currentState = newState;
                currentFrame = 0; // Reset animation frame when state changes
            }
        }
        
        // Call this when movement starts
        public void startMoving() {
            setState(FighterState.MOVING);
        }
        
        // Call this when movement ends
        public void stopMoving() {
            setState(FighterState.IDLE);
        }

        /* just in case. */
        public void startAttacking() {
            setState(FighterState.ATTACKING);
        }

        public void stopAttacking() {
            setState(FighterState.IDLE);
        }

    }

    public FightScreen(User user, Fighter fighter)
    {
        theFightScreen = this;
        FightButton.theFightScreen = this;
        theUser = user;
        theFighter = fighter;
        user.changeHealth(100);
        fighter.changeHealth(100);
        user.increaseStamina(100);
        fighter.increaseStamina(100);
        fightersAnimation = new FighterAnimation(theFighter.getFighterPanel());
        usersAnimation = new FighterAnimation(User.getCharPanel());
        CharacterPanel.getCharPanel().setImage(this);
        CharacterPanel.getCharPanel().moveTo(0, 500);
        MainFrame.currentPanel = this;
        theFrame = MainFrame.theFrame;
        this.setLayout(null);
        ImageIcon backgroundIcon = new ImageIcon("graphs/arenaScene.png");
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(backgroundIcon);
        backgroundLabel.setBounds(0, 0, 1920, 1080);

        this.setBounds(0, 0, 1920, 1080);

        //Remove the previous JPanel if existant
        theFrame.removePrevPanelsAndLabels();
        this.add(CharacterPanel.getCharPanel());
        theFrame.add(user.getHealthBar());
        theFrame.add(user.getStaminaBar());
        this.add(fighter.getFighterPanel());

        fighter.moveTo(1100, 500);
        theFrame.add(fighter.getHealthBar());
        theFrame.add(fighter.getStaminaBar());

        this.add(backgroundLabel);
        theFrame.add(this);
        theFrame.repaint();
        // grab your real frame and size
        theFrame = MainFrame.theFrame;
        Dimension dim = theFrame.getContentPane().getSize();

        // Existing fight setup...
        this.setLayout(null);
        this.setBounds(0, 0, dim.width, dim.height);
        // add background, fighters, HUD, etc.

        // 1) Create & size the EscapeScreen overlay
        EscapeScreen esc = new EscapeScreen(dim.width, dim.height);
        esc.setBounds(0, 0, dim.width, dim.height);

        // Resume button action (redundant since default hides)
        esc.setResumeAction(new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                esc.setVisible(false);
            }
        });

        // Exit to Map action: remove fight screen and show map
        esc.setExitToMenuAction(new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                new LobbyScreen();
                esc.setVisible(false);
            }
        });
        esc.setSaveAction(new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                esc.setVisible(false);
                new SaveGameScreen();
                System.out.println("SAVE GAME SCREEN");
            }
        });


        // Add overlay in popup layer
        theFrame.getLayeredPane().add(esc, JLayeredPane.POPUP_LAYER);

        // Bind ESC key
        esc.registerKeyBinding(theFrame.getRootPane());
    }



    public FightScreen()
    {
        //This is the side dual fight which calls the fight after deciding a random opponent
        this(User.getUser(), new Fighter());
    }
    public void rp()
    {
        this.repaint();
    }

    //Buttons' results
    public void attack(Person person)
    {
        System.out.println("START OF THE ATTACK METHOD IN FIGHTERPANEL!!!");
        person.lowerStamina(10);
        if(person instanceof User)
        {
            System.out.println("SET THE ATTACKING STATE FOR USER!!!");
            this.usersAnimation.currentState = Screens.FightScreen.FighterAnimation.FighterState.ATTACKING;
            System.out.println("GOING TO ATTACK THE FIGHTER!!!");
            User.getCharPanel().attackTo(this.theFighter);
            System.out.println("FINISHED ATTACKING THE FIGHTER!!!");
            FightScreen.usersTurn = false;
        }
        else
        {
            System.out.println("SET THE ATTACKING STATE FOR USER!!!");
            this.fightersAnimation.currentState = Screens.FightScreen.FighterAnimation.FighterState.ATTACKING;
            System.out.println("GOING TO ATTACK THE FIGHTER!!!");
            Fighter fighter = (Fighter)person;
            fighter.getFighterPanel().attackTo(this.theUser);
            System.out.println("FINISHED ATTACKING THE FIGHTER!!!");
            FightScreen.usersTurn = true;
        }
    }
    public void moveForward(Person person)
    {
        person.lowerStamina(5);
        if(person instanceof User)
        {
            this.usersAnimation.currentState = Screens.FightScreen.FighterAnimation.FighterState.MOVING;
            User.getCharPanel().moveBy(80, 0, 5 * FRAME_LENGTH);
            FightScreen.usersTurn = false;
        }
        else
        {
            this.fightersAnimation.currentState = Screens.FightScreen.FighterAnimation.FighterState.MOVING;
            Fighter fighter = (Fighter)person;
            fighter.getFighterPanel().moveBy(-80, 0, 5 * FRAME_LENGTH);
            FightScreen.usersTurn = true;
        }
    }
    public void moveBackwards(Person person)
    {
        person.lowerStamina(5);
        if(person instanceof User)
        {
            User.getCharPanel().moveBy(-80, 0, 5 * FRAME_LENGTH);
            this.usersAnimation.currentState = Screens.FightScreen.FighterAnimation.FighterState.MOVING;
            FightScreen.usersTurn = false;
        }
        else
        {
            Fighter fighter = (Fighter)person;
            fighter.getFighterPanel().moveBy(80, 0, 5 * FRAME_LENGTH);
            this.fightersAnimation.currentState = Screens.FightScreen.FighterAnimation.FighterState.MOVING;
            FightScreen.usersTurn = true;
        }
    }
    public void sleep(Person person)
    {
        person.increaseStamina(20);
        if(person instanceof User)
        {
            this.usersAnimation.currentState = Screens.FightScreen.FighterAnimation.FighterState.IDLE;
            FightScreen.usersTurn = false;
            return;
        }
        this.fightersAnimation.currentState = Screens.FightScreen.FighterAnimation.FighterState.IDLE;
        FightScreen.usersTurn = true;
    }
    public void usePet(Person person)
    {
        System.out.println("INSIDE USEPET IN FIGHTSCREEN");
        this.usersAnimation.currentState = Screens.FightScreen.FighterAnimation.FighterState.IDLE;
        FightScreen.usersTurn = false;
        FightScreen.theFightScreen.theFighter.changeHealth(Cat.getAttackForDistance());
        //Fighters dont have pets
    }
    public void setFightersAnimationToIdle()
    {
        this.fightersAnimation.currentState = Screens.FightScreen.FighterAnimation.FighterState.IDLE;
        //System.out.println("FIGHTER SET TO IDLE BECAUSE OF THE END OF AN ANIMATION");
    }
    public void setUsersAnimationToIdle()
    {
        this.usersAnimation.currentState = Screens.FightScreen.FighterAnimation.FighterState.IDLE;
        //System.out.println("USER SET TO IDLE BECAUSE OF THE END OF AN ANIMATION");
    }


    public class MainMenuPanel extends JPanel {
        public MainMenuPanel() {
            MainFrame.currentPanel = this;
            theFrame = MainFrame.theFrame;
            this.setLayout(new BorderLayout());
            JLabel label = new JLabel("Main Menu", SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 36));
            this.add(label, BorderLayout.CENTER);
            this.setBounds(0, 0, 1920, 1080);
        
            //Remove the previous JPanel if existant
            theFrame.removePrevPanelsAndLabels();
            MainFrame.theFrame.add(this);
        }
    }
        public void showEndGamePanel(String message) {
        boolean wasFromStoryMode = FightScreen.theFightScreen.theFighter.isFromStoryMode;
        JPanel endPanel = new JPanel();
        endPanel.setLayout(null);
        endPanel.setBackground(Color.ORANGE);
        endPanel.setBounds(0, 0, 1920, 1080);

        JLabel resultLabel = new JLabel(message, JLabel.CENTER);
        resultLabel.setBounds(700, 350, 400, 100);
        resultLabel.setFont(resultLabel.getFont().deriveFont(36.0f));
        resultLabel.setIcon(new ImageIcon("graphs/sword.png"));
        endPanel.add(resultLabel);
        
        JLabel coinsLabel = new JLabel((message.equalsIgnoreCase("USER WON")) ? ("Gained " + 300 + " Coins!") : ("Lost " +  (-1 * 200) + " Coins!"));
        coinsLabel.setBounds(700, 500, 600, 100);
        coinsLabel.setFont(resultLabel.getFont().deriveFont(36.0f));
        coinsLabel.setIcon(new ImageIcon("graphs/coins.png"));
        endPanel.add(coinsLabel);
        User.getUser().totalFought++;
        if(message.equalsIgnoreCase("User Won"))
        {
            User.getUser().changeCoins(300);
            User.getUser().totalWon++;
            if(wasFromStoryMode)
            {
                FightScreen.wonAgainstEnemies ++;
                if(wonAgainstEnemies == 2)//It will give a cat after they win against the second enemy
                {
                    JLabel addCatLabel = new JLabel("After your amazing performance against your enemies, a vezier has decided to gift you a cat!");
                    addCatLabel.setBounds(600, 700, 900, 100);
                    addCatLabel.setFont(resultLabel.getFont().deriveFont(36.0f));
                    endPanel.add(addCatLabel);
                    JLabel addCatLabel2 = new JLabel("You may use your cat during fights to harm your enemies while resting!");
                    addCatLabel2.setBounds(600, 800, 900, 100);
                    addCatLabel2.setFont(resultLabel.getFont().deriveFont(36.0f));
                    endPanel.add(addCatLabel2);
                    User.getUser().buyItem(new Cat());
                }
            }
        }
        else User.getUser().changeCoins(-200);

        JLabel resultCoinsLabel = new JLabel("You now have " + User.getUser().getCoins() + " coins!");
        resultCoinsLabel.setBounds(700, 600, 600, 100);
        resultCoinsLabel.setFont(resultLabel.getFont().deriveFont(36.0f));
        endPanel.add(resultCoinsLabel);


        theFrame.removePrevPanelsAndLabels();
        MainFrame.theFrame.add(endPanel);
        this.repaint();
        MainFrame.theFrame.repaint();

        endPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(wonAgainstEnemies == 4)
                {
                    FightScreen.theFightScreen.remove(endPanel);
                    new StoryScreen(false);
                }
                else if(wonAgainstEnemies == 5)
                {
                    FightScreen.theFightScreen.remove(endPanel);
                    new GameOverScene();
                    return;
                }
                else new LobbyScreen();
            }
        });

        // Disable all interactions or timers if needed
        usersAnimation.animationTimer.stop();
        fightersAnimation.animationTimer.stop();
    }
}