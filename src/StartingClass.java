import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

public class StartingClass extends Applet implements Runnable, KeyListener {

    private Hero elza;
    private Enemy enemy1;
    private URL base;
    private Graphics second;

    private Image img, image;

    private Image enemyStand;
    private Image heroJump, heroJumpRight, heroJumpLeft, heroDucked;
    private Image heroStandRight1, heroStandRight2, heroStandLeft1, heroStandLeft2;
    private Image heroRunningRight1, heroRunningRight2, heroRunningRight3;
    private Image heroRunningLeft1, heroRunningLeft2, heroRunningLeft3;


    private Animation runningRightAnim, runningLeftAnim, standAnimRight, standAnimLeft, currentAnim;
    private Animation heliAnim;


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

        enemyStand = getImage(base,"/ToBeSurvivor/enemyStand.png");


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

    }

    @Override
    public void start() {
        elza = new Hero();
        enemy1 = new Enemy(1000, 450);

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            elza.update();
            currentAnim();

            repaint();

            try {
                Thread.sleep(17);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    private void currentAnim() {
        currentAnim.update(50);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, this);
        g.drawImage(enemyStand, 1050, 500, this);

        if (!elza.isJumped() && !elza.isDucked()) {
            g.drawImage(currentAnim.getImage(), elza.getCenterX(), elza.getCenterY(), this);
        }
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
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                elza.stop();
                elza.setMovingRight(false);
                currentAnim = standAnimRight;
                break;
            case KeyEvent.VK_LEFT:
                elza.stop();
                elza.setMovingLeft(false);
                currentAnim = standAnimLeft;
                break;
            case KeyEvent.VK_DOWN:
                elza.setDucked(false);
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


    public Hero getElza() {
        return elza;
    }

    public void setElza(Hero elza) {
        this.elza = elza;
    }

    public URL getBase() {
        return base;
    }

    public void setBase(URL base) {
        this.base = base;
    }

    public Graphics getSecond() {
        return second;
    }

    public void setSecond(Graphics second) {
        this.second = second;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getHero() {
        return heroStandRight1;
    }

    public void setHero(Image hero) {
        this.heroStandRight1 = hero;
    }
}
