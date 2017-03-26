import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import static java.lang.Thread.sleep;

public class StartingClass extends Applet implements Runnable, KeyListener {

    private static Hero elza;
    private static Enemy enemy1;
    private static Arrow arrow;

    private URL base;
    private Graphics second;

    private Image img, image;
    private static Image arrowImage;

    private Image enemyStand, enemyDied, enemyHit1, enemyHit2, enemyHit3, enemyHit4, enemyHit5, enemyHit6;
    private Image enemyHit7, enemyHit8, enemyHit9, enemyHit10, enemyHit11;

    private Image heroJump, heroJumpRight, heroJumpLeft, heroDucked, heroHit, heroDied;
    private Image heroStandRight1, heroStandRight2;
    private Image heroStandLeft1;
    private Image heroStandLeft2;
    private Image heroRunningRight1, heroRunningRight2, heroRunningRight3;
    private Image heroRunningLeft1, heroRunningLeft2, heroRunningLeft3;

    private Animation runningRightAnim, runningLeftAnim, standAnimRight, standAnimLeft, currentAnim;
    private static Animation enemyHitAnim;

    @Override
    public void init() {

        //INIT SETUP
        MediaTracker tr = new MediaTracker(this);
        img = getImage(getCodeBase(), "/ToBeSurvivor/background.gif");
        tr.addImage(img, 0);
        setSize(1200, 768);
        setFocusable(true);
        addKeyListener(this);

        try {
            base = getDocumentBase();
        } catch (Exception e) {
            // TODO: handle exception
        }

        //IMAGE SETUPS
        heroStandRight1 = getImage(base, "/ToBeSurvivor/heroStandRight1.png");
        heroStandRight2 = getImage(base, "/ToBeSurvivor/heroStandRight2.png");

        heroStandLeft1 = getImage(base, "/ToBeSurvivor/heroStandLeft1.png");
        heroStandLeft2 = getImage(base, "/ToBeSurvivor/heroStandLeft2.png");

        heroRunningRight1 = getImage(base, "/ToBeSurvivor/heroRunningRight1.png");
        heroRunningRight2 = getImage(base, "/ToBeSurvivor/heroRunningRight2.png");
        heroRunningRight3 = getImage(base, "/ToBeSurvivor/heroRunningRight3.png");

        heroRunningLeft1 = getImage(base, "/ToBeSurvivor/heroRunningLeft1.png");
        heroRunningLeft2 = getImage(base, "/ToBeSurvivor/heroRunningLeft2.png");
        heroRunningLeft3 = getImage(base, "/ToBeSurvivor/heroRunningLeft3.png");

        heroJump = getImage(base, "/ToBeSurvivor/heroJump.png");
        heroJumpRight = getImage(base, "/ToBeSurvivor/heroJumpRight.png");
        heroJumpLeft = getImage(base, "/ToBeSurvivor/heroJumpLeft.png");

        heroDucked = getImage(base, "/ToBeSurvivor/heroDucked.png");
        heroHit = getImage(base, "/ToBeSurvivor/heroHit.png");
        heroDied = getImage(base, "/ToBeSurvivor/heroDied.png");

        enemyStand = getImage(base, "/ToBeSurvivor/enemyStand.png");
        enemyDied = getImage(base, "/ToBeSurvivor/enemyDied.png");
        arrowImage = getImage(base, "/ToBeSurvivor/Arrow.png");

        enemyHit1 = getImage(base, "/ToBeSurvivor/enemyHit1.png");
        enemyHit2 = getImage(base, "/ToBeSurvivor/enemyHit2.png");
        enemyHit3 = getImage(base, "/ToBeSurvivor/enemyHit3.png");
        enemyHit4 = getImage(base, "/ToBeSurvivor/enemyHit4.png");
        enemyHit5 = getImage(base, "/ToBeSurvivor/enemyHit5.png");
        enemyHit6 = getImage(base, "/ToBeSurvivor/enemyHit6.png");
        enemyHit7 = getImage(base, "/ToBeSurvivor/enemyHit7.png");
        enemyHit8 = getImage(base, "/ToBeSurvivor/enemyHit8.png");
        enemyHit9 = getImage(base, "/ToBeSurvivor/enemyHit9.png");
        enemyHit10 = getImage(base, "/ToBeSurvivor/enemyHit10.png");
        enemyHit11 = getImage(base, "/ToBeSurvivor/enemyHit11.png");

        //HERO ANIMATIONS
        {
            standAnimRight = new Animation();
            standAnimRight.addFrame(heroStandRight1, 200);
            standAnimRight.addFrame(heroStandRight2, 8000);
        }
        {
            standAnimLeft = new Animation();
            standAnimLeft.addFrame(heroStandLeft1, 200);
            standAnimLeft.addFrame(heroStandLeft2, 8000);
        }
        {
            runningLeftAnim = new Animation();
            runningLeftAnim.addFrame(heroRunningLeft1, 200);
            runningLeftAnim.addFrame(heroRunningLeft2, 300);
            runningLeftAnim.addFrame(heroRunningLeft3, 300);
        }
        {
            runningRightAnim = new Animation();
            runningRightAnim.addFrame(heroRunningRight1, 200);
            runningRightAnim.addFrame(heroRunningRight2, 300);
            runningRightAnim.addFrame(heroRunningRight3, 300);
        }
        //PRESET FOR INIT ANIMATION
        {
            currentAnim = new Animation();
            currentAnim = standAnimRight;
        }
        //ENEMY ANIMATIONS
        {
            enemyHitAnim = new Animation();
            enemyHitAnim.addFrame(enemyHit1, 800);
            enemyHitAnim.addFrame(enemyHit2, 800);
            enemyHitAnim.addFrame(enemyHit3, 800);
            enemyHitAnim.addFrame(enemyHit4, 600);
            enemyHitAnim.addFrame(enemyHit5, 600);
            enemyHitAnim.addFrame(enemyHit6, 600);
            enemyHitAnim.addFrame(enemyHit7, 600);
            enemyHitAnim.addFrame(enemyHit8, 600);
            enemyHitAnim.addFrame(enemyHit9, 600);
            enemyHitAnim.addFrame(enemyHit10, 600);
            enemyHitAnim.addFrame(enemyHit11, 900);
        }
    }

    @Override
    public void start() {
        elza = new Hero(200, 3);
        enemy1 = new Enemy(1050, 535, 500);

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            elza.update();
            enemy1.update();

            //If hero near to enemy, enemy will shoot
            if(!enemy1.isDied()) {
                if (elza.getCenterX() > 250) {
                    enemy1.setAtack(true);
                } else {
                    enemy1.setAtack(false);
                }
            }

            currentAnim.update(50);
            repaint();

            try {
                sleep(17);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        //Start configs
        g.drawImage(img, 0, 0, this);

        if(!enemy1.isDied()) {

            // 1)If frame in animation = 9, then create and draw arrow. 2) If arrow exists, continue drawing
            if (enemyHitAnim.getCurrentFrame() == 9) {
                arrow = new Arrow(1000, 575, -9);
                g.drawImage(arrowImage, arrow.getCenterX(), arrow.getCenterY(), this);
            } else if (arrow != null) {
                g.drawImage(arrowImage, arrow.getCenterX(), arrow.getCenterY(), this);
                arrow.update();
            }

            //Draw Enemy Animation or Sprite
            if (enemy1.isAtack()) {
                g.drawImage(enemyHitAnim.getImage(), enemy1.getCenterX(), enemy1.getCenterY(), this);
                enemyHitAnim.update(50);
            } else {
                g.drawImage(enemyStand, enemy1.getCenterX(), enemy1.getCenterY(), this);
            }
        } else if(enemy1.isDied()){
            g.drawImage(enemyDied, enemy1.getCenterX(),enemy1.getCenterY(),this);
        }


        if(!elza.isDied()) {

            //Draw Hero Animation
            if (!elza.isJumped() && !elza.isDucked() && !elza.isBeats()) {
                g.drawImage(currentAnim.getImage(), elza.getCenterX(), elza.getCenterY(), this);
            }

            //Draw Hero sprites
            if (elza.isJumped() && elza.isMovingRight()) {
                g.drawImage(heroJumpRight, elza.getCenterX(), elza.getCenterY(), this);
            }
            if (elza.isJumped() && elza.isMovingLeft()) {
                g.drawImage(heroJumpLeft, elza.getCenterX(), elza.getCenterY(), this);
            }
            if (elza.isJumped() && (elza.getSpeedX() == 0)) {
                g.drawImage(heroJump, elza.getCenterX(), elza.getCenterY(), this);
            }
            if (elza.isDucked()) {
                g.drawImage(heroDucked, elza.getCenterX(), elza.getCenterY(), this);
            }
            if (elza.isBeats()) {
                g.drawImage(heroHit, elza.getCenterX(), elza.getCenterY(), this);
            }
        } else  if(elza.isDied()){
            g.drawImage(heroDied,elza.getCenterX(),elza.getCenterY(),this);
            elza.setJumped();
        }

        //Drawing hero HP bar
        g.setFont(elza.hpFont);
        g.setColor(Color.RED);
        g.drawString(Integer.toString(elza.currentHP), elza.getCenterX()+20,elza.getCenterY());

        //Drawing enemy HP bar
        g.setFont(enemy1.hpFont);
        g.setColor(Color.red);
        g.drawString(Integer.toString(enemy1.getCurrentHP()), enemy1.getCenterX()+20, enemy1.getCenterY());
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                elza.moveRight();
                currentAnim = runningRightAnim;
                break;
            case KeyEvent.VK_LEFT:
                elza.moveLeft();
                currentAnim = runningLeftAnim;
                break;
            case KeyEvent.VK_SPACE:
                elza.jump();
                break;
            case KeyEvent.VK_DOWN:
                elza.duck();
                break;
            case KeyEvent.VK_CONTROL:
                elza.hit();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                elza.stop();
                elza.setMovingRight();
                currentAnim = standAnimRight;
                break;
            case KeyEvent.VK_LEFT:
                elza.stop();
                elza.setMovingLeft();
                currentAnim = standAnimLeft;
                break;
            case KeyEvent.VK_DOWN:
                elza.setDucked();
                break;
            case KeyEvent.VK_CONTROL:
                elza.setBeats();
                elza.setInflictDamage();
                try {
                    sleep(120);
                } catch (InterruptedException a) {
                    a.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void update(Graphics g) {
        if (image == null) {
            image = createImage(this.getWidth(), this.getHeight());
            second = image.getGraphics();
        }
        second.setColor(getBackground());
        second.fillRect(0, 0, getWidth(), getHeight());
        second.setColor(getForeground());
        paint(second);
        g.drawImage(image, 0, 0, this);
    }

    static Arrow getArrowObj(){
        return arrow;
    }
    static void setArrowObj() {
        StartingClass.arrow = null;
    }
    static Enemy getEnemy1() {
        return enemy1;
    }
    static Hero getElza() {
        return elza;
    }
}
