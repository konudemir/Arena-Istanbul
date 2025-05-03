package Screens;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import C.*;
import m.MainFrame;
public class FightScreen extends JPanel{
    //CONSTANTS
    public static final int ATTACK1 = 3;
    public static final int ATTACK2 = 4;
    public static final int WALK1 = 5;
    public static final int WALK2 = 6;
    public static final int WALK3 = 7;
    public static final int WALK4 = 8;
    //END OF CONSTANTS
    public static final int NORMAL_ATTACK_HIT = 8;
    public static MainFrame theFrame;
    public static int wonAgainstEnemies = 0;
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
    class FighterAnimation {
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
            animationTimer = new Timer(100, _ -> { // 100ms between frames, subject to change.
                switch(currentState) {
                    case IDLE:
                        animateBreathing();
                        System.out.println("IDLE INITIATED");
                        break;
                    case MOVING:
                        animateMovement();
                        System.out.println("MOVING INITIATED");
                        break;
                    case ATTACKING:
                        animateAttack();
                        System.out.println("ATTACKING INITIATED");
                        break;
                    default:
                        break;
                }
            });
            animationTimer.start();
        }
        
        private void animateBreathing() {
            currentFrame = (currentFrame + 1) % breathingFrames.length;
            System.out.println("FRAME CHANGED FOR IDLE");
            fighterPanel.setImage(breathingFrames[currentFrame]);
        }
        
        private void animateMovement() {
            currentFrame = (currentFrame + 1) % movingFrames.length;
            System.out.println("FRAME CHANGED FOR WALK");
            fighterPanel.setImage(movingFrames[currentFrame]);
        }

        public void animateAttack() {
            System.out.println("FRAME CHANGED FOR ATTACK");
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
        theUser = user;
        theFighter = fighter;
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
        
        //fighter.resizeComp(300, 132);
        fighter.moveTo(1100, 500);
        theFrame.add(fighter.getHealthBar());
        theFrame.add(fighter.getStaminaBar());

        JLabel moveForward = new JLabel();
        moveForward.setIcon(new ImageIcon("graphs/fight/moveForward.png"));
        moveForward.setBounds(460 - (200), 420+ 108, 200, 200);
        this.add(moveForward);
        JLabel moveBackwards = new JLabel();
        moveBackwards.setIcon(new ImageIcon("graphs/fight/moveBackwards.png"));
        moveBackwards.setBounds(380 - (200), 510+ 108, 200, 200);
        this.add(moveBackwards);
        JLabel attack = new JLabel();
        attack.setIcon(new ImageIcon("graphs/fight/attack.png"));
        attack.setBounds(585 - (200), 420+ 108, 200, 200);
        this.add(attack);
        JLabel sleep = new JLabel();
        sleep.setIcon(new ImageIcon("graphs/fight/sleep.png"));
        sleep.setBounds(660 - (200), 510+ 108, 200, 200);
        this.add(sleep);
        JLabel usePet = new JLabel();
        usePet.setIcon(new ImageIcon("graphs/fight/usePet.png"));
        usePet.setBounds(660 - (200), 630+ 108, 200, 200);
        this.add(usePet);

        this.add(backgroundLabel);
        theFrame.add(this);
        theFrame.repaint();
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
}