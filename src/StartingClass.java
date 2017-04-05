import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;

import static java.lang.Thread.sleep;

public class StartingClass extends Applet implements Runnable, KeyListener {

    enum GameState {
        RUNNING, DEAD
    }



    static GameState state = GameState.RUNNING;

    static Hero elza;
    static Archer enemyArcher;
    static Arrow arrow;
    Chara enemyChara;

    private URL base;
    private Graphics second;

    private Image background, image;
    private static Image arrowImage;

    private Image archerStand, archerDied, archerHit1, archerHit2, archerHit3, archerHit4, archerHit5, archerHit6;
    private Image archerHit7, archerHit8, archerHit9, archerHit10, archerHit11;

    private Image charaStand1, charaStand2;
    private Image charaMovingLeft1, charaMovingLeft2, charaMovingLeft3, charaMovingLeft4, charaMovingLeft5;
    private Image charaMovingLeft6, charaMovingLeft7, charaMovingLeft8, charaMovingLeft9;

    private Image heroJump, heroJumpRight, heroJumpLeft, heroDucked, heroHit;
    private Image heroStandRight1, heroStandRight2;
    private Image heroStandLeft1, heroStandLeft2;
    private Image heroRunningRight1, heroRunningRight2, heroRunningRight3;
    private Image heroRunningLeft1, heroRunningLeft2, heroRunningLeft3;

    private Animation runningRightAnim, runningLeftAnim, standAnimRight, standAnimLeft, currentAnim;
    private static Animation archerHitAnim;
    private Animation charaStandAnim, charaMoveLeftAnim, charaMoveRightAnim, charaCurrentAnim;

    @Override
    public void init() {

        //INIT SETUP
        MediaTracker tr = new MediaTracker(this);
        background = getImage(getCodeBase(), "/ToBeSurvivor/background.gif");
        tr.addImage(background, 0);
        setSize(1200, 768);
        setFocusable(true);
        addKeyListener(this);

        try {
            base = getDocumentBase();
        } catch (Exception e) {
            // TODO: handle exception
        }

        //IMAGE SETUPS
        {
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

            archerStand = getImage(base, "/ToBeSurvivor/enemyStand.png");
            archerDied = getImage(base, "/ToBeSurvivor/enemyDied.png");
            arrowImage = getImage(base, "/ToBeSurvivor/Arrow.png");

            archerHit1 = getImage(base, "/ToBeSurvivor/enemyHit1.png");
            archerHit2 = getImage(base, "/ToBeSurvivor/enemyHit2.png");
            archerHit3 = getImage(base, "/ToBeSurvivor/enemyHit3.png");
            archerHit4 = getImage(base, "/ToBeSurvivor/enemyHit4.png");
            archerHit5 = getImage(base, "/ToBeSurvivor/enemyHit5.png");
            archerHit6 = getImage(base, "/ToBeSurvivor/enemyHit6.png");
            archerHit7 = getImage(base, "/ToBeSurvivor/enemyHit7.png");
            archerHit8 = getImage(base, "/ToBeSurvivor/enemyHit8.png");
            archerHit9 = getImage(base, "/ToBeSurvivor/enemyHit9.png");
            archerHit10 = getImage(base, "/ToBeSurvivor/enemyHit10.png");
            archerHit11 = getImage(base, "/ToBeSurvivor/enemyHit11.png");

            charaStand1 = getImage(base, "/ToBeSurvivor/charaStand1.png");
            charaStand2 = getImage(base, "/ToBeSurvivor/charaStand2.png");

            charaMovingLeft1 = getImage(base, "/ToBeSurvivor/charaMovingLeft1.png");
            charaMovingLeft2 = getImage(base, "/ToBeSurvivor/charaMovingLeft2.png");
            charaMovingLeft3 = getImage(base, "/ToBeSurvivor/charaMovingLeft3.png");
            charaMovingLeft4 = getImage(base, "/ToBeSurvivor/charaMovingLeft4.png");
            charaMovingLeft5 = getImage(base, "/ToBeSurvivor/charaMovingLeft5.png");
            charaMovingLeft6 = getImage(base, "/ToBeSurvivor/charaMovingLeft6.png");
            charaMovingLeft7 = getImage(base, "/ToBeSurvivor/charaMovingLeft7.png");
            charaMovingLeft8 = getImage(base, "/ToBeSurvivor/charaMovingLeft8.png");
            charaMovingLeft9 = getImage(base, "/ToBeSurvivor/charaMovingLeft9.png");

        }
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

            charaCurrentAnim = new Animation();
            charaCurrentAnim = charaStandAnim;
        }
        //ARCHER ANIMATIONS
        {
            archerHitAnim = new Animation();
            archerHitAnim.addFrame(archerHit1, 800);
            archerHitAnim.addFrame(archerHit2, 800);
            archerHitAnim.addFrame(archerHit3, 800);
            archerHitAnim.addFrame(archerHit4, 600);
            archerHitAnim.addFrame(archerHit5, 600);
            archerHitAnim.addFrame(archerHit6, 600);
            archerHitAnim.addFrame(archerHit7, 600);
            archerHitAnim.addFrame(archerHit8, 600);
            archerHitAnim.addFrame(archerHit9, 600);
            archerHitAnim.addFrame(archerHit10, 600);
            archerHitAnim.addFrame(archerHit11, 900);
        }
        //CHARA ANIMATIONS
        {
            charaStandAnim = new Animation();
            charaStandAnim.addFrame(charaStand1, 9000);
            charaStandAnim.addFrame(charaStand2, 550);
        }
        {
            charaMoveLeftAnim = new Animation();
            charaMoveLeftAnim.addFrame(charaMovingLeft1, 400);
            charaMoveLeftAnim.addFrame(charaMovingLeft2, 400);
            charaMoveLeftAnim.addFrame(charaMovingLeft3, 400);
            charaMoveLeftAnim.addFrame(charaMovingLeft4, 400);
            charaMoveLeftAnim.addFrame(charaMovingLeft5, 400);
            charaMoveLeftAnim.addFrame(charaMovingLeft6, 400);
            charaMoveLeftAnim.addFrame(charaMovingLeft7, 400);
            charaMoveLeftAnim.addFrame(charaMovingLeft8, 400);
            charaMoveLeftAnim.addFrame(charaMovingLeft9, 400);
        }
    }

    @Override
    public void start() {
        elza = new Hero(200, 3);
        enemyArcher = new Archer(1050,535,500);
        enemyChara = new Chara(1600,405, 2000);


        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        if (state == GameState.RUNNING) {
            while (true) {
                //Animations
                {
                    elza.update();
                    enemyArcher.update();
                    enemyChara.update();
                    animations();
                }

                repaint();
                try {
                    sleep(17);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void animations(){

        currentAnim.update(50);

    }

    void restart() {
        if (state == GameState.DEAD) {
            elza = null;
            enemyArcher = null;
            init();
            start();
            run();
            state = GameState.RUNNING;

        }
    }

    @Override
    public void paint(Graphics g) {
        //Start configs
        if (state == GameState.RUNNING) {
            g.drawImage(background, 0, 0, this);

            //ARCHER
            {
                if (!enemyArcher.isDied()) {

                    // 1)If frame in animation = 9, create arrow. 2) If arrow obj exists, draw arrow
                    if (archerHitAnim.getCurrentFrame() == 9) {
                        arrow = new Arrow(enemyArcher.getCenterX() - 30, enemyArcher.getCenterY() + 40, -11);
                    } else if (arrow != null) {
                        g.drawImage(arrowImage, arrow.getCenterX(), arrow.getCenterY(), this);
                        arrow.update();
                    }

                    //Draw Enemy Animation or Sprite
                    if (enemyArcher.isAttack()) {
                        g.drawImage(archerHitAnim.getImage(), enemyArcher.getCenterX(), enemyArcher.getCenterY(), this);
                        archerHitAnim.update(50);
                    } else {
                        g.drawImage(archerStand, enemyArcher.getCenterX(), enemyArcher.getCenterY(), this);
                    }

                    //Drawing Archer HP bar
                    g.setFont(enemyArcher.hpFont);
                    g.setColor(Color.red);
                    g.drawString(Integer.toString(enemyArcher.getCurrentHP()), enemyArcher.getCenterX() + 20, enemyArcher.getCenterY());

                } else if (enemyArcher.isDied()) {
                    g.drawImage(archerDied, enemyArcher.getCenterX(), enemyArcher.getCenterY(), this);
                }
            }
            //CHARA
            {
                if(!enemyChara.isDied()){
                    if(enemyChara.isMovingLeft()){
                        g.drawImage(charaMoveLeftAnim.getImage(), enemyChara.getCenterX(), enemyChara.getCenterY(), this);
                        charaCurrentAnim = charaMoveLeftAnim;
                        charaCurrentAnim.update(50);
                    } else if(enemyChara.isMovingRight()){
                        charaCurrentAnim = charaMoveRightAnim;
                    } else {
                        g.drawImage(charaStandAnim.getImage(), enemyChara.getCenterX(), enemyChara.getCenterY(), this);
                    }

                    g.setFont(enemyChara.hpFont);
                    g.setColor(Color.red);
                    g.drawString(Integer.toString(enemyChara.getCurrentHP()), enemyChara.getCenterX(), enemyChara.getCenterY());

                } else if(enemyChara.isDied()){

                }
            }
            //HERO
            {
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
            }

            //Drawing hero HP bar
            g.setFont(elza.hpFont);
            g.setColor(Color.RED);
            g.drawString(Integer.toString(elza.getCurrentHP()), elza.getCenterX() + 20, elza.getCenterY());

        } else if (state == GameState.DEAD) {
            g.setColor(Color.black);
            g.fillRect(0, 0, 1200, 768);
            g.setColor(Color.white);
            g.drawString("Dead", 600, 384);
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
            case KeyEvent.VK_CONTROL:
                elza.hit();
                break;
            case KeyEvent.VK_TAB:
                restart();
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
            case KeyEvent.VK_CONTROL:
                elza.setBeats(false);
                elza.setInflictDamage(false);
                break;
            case KeyEvent.VK_TAB:
                restart();
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

    public static GameState getState() {
        return state;
    }

    public static void setState(GameState state) {
        StartingClass.state = state;
    }

    public static void setElza(Hero elza) {
        StartingClass.elza = elza;
    }

    public static void setEnemyArcher(Archer enemyArcher) {
        StartingClass.enemyArcher = enemyArcher;
    }

    public static Arrow getArrow() {
        return arrow;
    }

    public static void setArrow(Arrow arrow) {
        StartingClass.arrow = arrow;
    }

    public Chara getEnemyChara() {
        return enemyChara;
    }

    public void setEnemyChara(Chara enemyChara) {
        this.enemyChara = enemyChara;
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



    public void setBackground(Image background) {
        this.background = background;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public static Image getArrowImage() {
        return arrowImage;
    }

    public static void setArrowImage(Image arrowImage) {
        StartingClass.arrowImage = arrowImage;
    }

    public Image getArcherStand() {
        return archerStand;
    }

    public void setArcherStand(Image archerStand) {
        this.archerStand = archerStand;
    }

    public Image getArcherDied() {
        return archerDied;
    }

    public void setArcherDied(Image archerDied) {
        this.archerDied = archerDied;
    }

    public Image getArcherHit1() {
        return archerHit1;
    }

    public void setArcherHit1(Image archerHit1) {
        this.archerHit1 = archerHit1;
    }

    public Image getArcherHit2() {
        return archerHit2;
    }

    public void setArcherHit2(Image archerHit2) {
        this.archerHit2 = archerHit2;
    }

    public Image getArcherHit3() {
        return archerHit3;
    }

    public void setArcherHit3(Image archerHit3) {
        this.archerHit3 = archerHit3;
    }

    public Image getArcherHit4() {
        return archerHit4;
    }

    public void setArcherHit4(Image archerHit4) {
        this.archerHit4 = archerHit4;
    }

    public Image getArcherHit5() {
        return archerHit5;
    }

    public void setArcherHit5(Image archerHit5) {
        this.archerHit5 = archerHit5;
    }

    public Image getArcherHit6() {
        return archerHit6;
    }

    public void setArcherHit6(Image archerHit6) {
        this.archerHit6 = archerHit6;
    }

    public Image getArcherHit7() {
        return archerHit7;
    }

    public void setArcherHit7(Image archerHit7) {
        this.archerHit7 = archerHit7;
    }

    public Image getArcherHit8() {
        return archerHit8;
    }

    public void setArcherHit8(Image archerHit8) {
        this.archerHit8 = archerHit8;
    }

    public Image getArcherHit9() {
        return archerHit9;
    }

    public void setArcherHit9(Image archerHit9) {
        this.archerHit9 = archerHit9;
    }

    public Image getArcherHit10() {
        return archerHit10;
    }

    public void setArcherHit10(Image archerHit10) {
        this.archerHit10 = archerHit10;
    }

    public Image getArcherHit11() {
        return archerHit11;
    }

    public void setArcherHit11(Image archerHit11) {
        this.archerHit11 = archerHit11;
    }

    public Image getCharaStand1() {
        return charaStand1;
    }

    public void setCharaStand1(Image charaStand1) {
        this.charaStand1 = charaStand1;
    }

    public Image getCharaStand2() {
        return charaStand2;
    }

    public void setCharaStand2(Image charaStand2) {
        this.charaStand2 = charaStand2;
    }

    public Image getHeroJump() {
        return heroJump;
    }

    public void setHeroJump(Image heroJump) {
        this.heroJump = heroJump;
    }

    public Image getHeroJumpRight() {
        return heroJumpRight;
    }

    public void setHeroJumpRight(Image heroJumpRight) {
        this.heroJumpRight = heroJumpRight;
    }

    public Image getHeroJumpLeft() {
        return heroJumpLeft;
    }

    public void setHeroJumpLeft(Image heroJumpLeft) {
        this.heroJumpLeft = heroJumpLeft;
    }

    public Image getHeroDucked() {
        return heroDucked;
    }

    public void setHeroDucked(Image heroDucked) {
        this.heroDucked = heroDucked;
    }

    public Image getHeroHit() {
        return heroHit;
    }

    public void setHeroHit(Image heroHit) {
        this.heroHit = heroHit;
    }

    public Image getHeroStandRight1() {
        return heroStandRight1;
    }

    public void setHeroStandRight1(Image heroStandRight1) {
        this.heroStandRight1 = heroStandRight1;
    }

    public Image getHeroStandRight2() {
        return heroStandRight2;
    }

    public void setHeroStandRight2(Image heroStandRight2) {
        this.heroStandRight2 = heroStandRight2;
    }

    public Image getHeroStandLeft1() {
        return heroStandLeft1;
    }

    public void setHeroStandLeft1(Image heroStandLeft1) {
        this.heroStandLeft1 = heroStandLeft1;
    }

    public Image getHeroStandLeft2() {
        return heroStandLeft2;
    }

    public void setHeroStandLeft2(Image heroStandLeft2) {
        this.heroStandLeft2 = heroStandLeft2;
    }

    public Image getHeroRunningRight1() {
        return heroRunningRight1;
    }

    public void setHeroRunningRight1(Image heroRunningRight1) {
        this.heroRunningRight1 = heroRunningRight1;
    }

    public Image getHeroRunningRight2() {
        return heroRunningRight2;
    }

    public void setHeroRunningRight2(Image heroRunningRight2) {
        this.heroRunningRight2 = heroRunningRight2;
    }

    public Image getHeroRunningRight3() {
        return heroRunningRight3;
    }

    public void setHeroRunningRight3(Image heroRunningRight3) {
        this.heroRunningRight3 = heroRunningRight3;
    }

    public Image getHeroRunningLeft1() {
        return heroRunningLeft1;
    }

    public void setHeroRunningLeft1(Image heroRunningLeft1) {
        this.heroRunningLeft1 = heroRunningLeft1;
    }

    public Image getHeroRunningLeft2() {
        return heroRunningLeft2;
    }

    public void setHeroRunningLeft2(Image heroRunningLeft2) {
        this.heroRunningLeft2 = heroRunningLeft2;
    }

    public Image getHeroRunningLeft3() {
        return heroRunningLeft3;
    }

    public void setHeroRunningLeft3(Image heroRunningLeft3) {
        this.heroRunningLeft3 = heroRunningLeft3;
    }

    public Animation getRunningRightAnim() {
        return runningRightAnim;
    }

    public void setRunningRightAnim(Animation runningRightAnim) {
        this.runningRightAnim = runningRightAnim;
    }

    public Animation getRunningLeftAnim() {
        return runningLeftAnim;
    }

    public void setRunningLeftAnim(Animation runningLeftAnim) {
        this.runningLeftAnim = runningLeftAnim;
    }

    public Animation getStandAnimRight() {
        return standAnimRight;
    }

    public void setStandAnimRight(Animation standAnimRight) {
        this.standAnimRight = standAnimRight;
    }

    public Animation getStandAnimLeft() {
        return standAnimLeft;
    }

    public void setStandAnimLeft(Animation standAnimLeft) {
        this.standAnimLeft = standAnimLeft;
    }

    public Animation getCurrentAnim() {
        return currentAnim;
    }

    public void setCurrentAnim(Animation currentAnim) {
        this.currentAnim = currentAnim;
    }

    public static Animation getArcherHitAnim() {
        return archerHitAnim;
    }

    public static void setArcherHitAnim(Animation archerHitAnim) {
        StartingClass.archerHitAnim = archerHitAnim;
    }

    public Animation getCharaStandAnim() {
        return charaStandAnim;
    }

    public void setCharaStandAnim(Animation charaStandAnim) {
        this.charaStandAnim = charaStandAnim;
    }

    static Arrow getArrowObj() {
        return arrow;
    }

    static void setArrowObj() {
        StartingClass.arrow = null;
    }

    static Enemy getEnemyArcher() {
        return enemyArcher;
    }

    static Hero getElza() {
        return elza;
    }
}
