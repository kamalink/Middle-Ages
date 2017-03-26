import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import static java.lang.Thread.sleep;

public class StartingClass extends Applet implements Runnable, KeyListener {

    private Hero elza;
    private static Enemy enemy1;
    private static Arrow arrow;

    private URL base;
    private Graphics second;

    private Image img, image;
    private static Image Arrow;

    private Image enemyStand, enemyHit1, enemyHit2, enemyHit3, enemyHit4, enemyHit5, enemyHit6;
    private Image enemyHit7, enemyHit8, enemyHit9, enemyHit10, enemyHit11;

    private Image heroJump, heroJumpRight, heroJumpLeft, heroDucked, heroHit;
    private Image heroStandRight1, heroStandRight2, heroStandLeft1, heroStandLeft2;
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

        enemyStand = getImage(base, "/ToBeSurvivor/enemyStand.png");
        Arrow = getImage(base, "/ToBeSurvivor/Arrow.png");

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
        elza = new Hero(200, 1);
        enemy1 = new Enemy(1050, 535, 500);


        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            elza.update();
            enemy1.update();

            //If hero near to enemy, enemy will shot
            if(elza.getCenterX()>250){
                enemy1.setAtack(true);
            } else {
                enemy1.setAtack(false);
            }

            Animations();
            repaint();

            try {
                sleep(17);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void Animations() {
        currentAnim.update(50);

    }

    @Override
    public void paint(Graphics g) {
        //Start configs
        g.drawImage(img, 0, 0, this);

        //1)If frame in animation = 9, then create and draw arrow. 2) If arrow exist, then continue drawing
        if(enemyHitAnim.getCurrentFrame() == 9){
            arrow = new Arrow(1000, 575, -9);
            g.drawImage(Arrow, arrow.getCenterX(), arrow.getCenterY(),this);
        } else if(arrow != null){
            g.drawImage(Arrow, arrow.getCenterX(), arrow.getCenterY(),this);
            arrow.update();
        }

        //Draw Enemy Animation or Sprite
        if(enemy1.isAtack()){
            g.drawImage(enemyHitAnim.getImage(), enemy1.getCenterX(),enemy1.getCenterY(),this);
            enemyHitAnim.update(50);
        } else{
            g.drawImage(enemyStand, enemy1.getCenterX(),enemy1.getCenterY(),this);
        }

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
        if(elza.isBeats()) {
            g.drawImage(heroHit, elza.getCenterX(), elza.getCenterY(), this);
        }

        //Drawing hero HP bar
        g.setFont(elza.hpFont);
        g.setColor(Color.RED);
        g.drawString(Integer.toString(elza.currentHP), elza.getCenterX()+20,elza.getCenterY());

        //Drawing enemy HP bar
        g.setFont(enemy1.hpFont);
        g.setColor(Color.red);
        g.drawString(Integer.toString(enemy1.getCurrentHP()), enemy1.getCenterX()+20, enemy1.getCenterY());

        //Draw rectangles
        g.setColor(Color.yellow);
        g.drawRect((int)elza.rect.getCenterX(),(int)elza.rect.getCenterY(),(int)elza.rect.getWidth(),(int)elza.rect.getHeight());
        g.drawRect((int)elza.rectBody.getCenterX(), (int)elza.getCenterY(), (int)elza.rectBody.getWidth(),(int)elza.rectBody.getHeight());
        g.drawRect((int)enemy1.leftSide.getCenterX(), (int)enemy1.leftSide.getCenterY(), (int)enemy1.leftSide.getWidth(), (int)enemy1.leftSide.getHeight());
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

    public static void setArrow(Arrow arrow) {
        StartingClass.arrow = arrow;
    }

    public static Arrow getArrowObj(){
        return arrow;
    }

    public void setArrowObj(Arrow arrow) {
        this.arrow = arrow;
    }

    public static Image getArrow() {
        return Arrow;
    }

    public void setArrow(Image arrow) {
        Arrow = arrow;
    }


    public static Enemy getEnemy1() {
        return enemy1;
    }

    public static void setEnemy1(Enemy enemy1) {
        StartingClass.enemy1 = enemy1;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public Image getEnemyStand() {
        return enemyStand;
    }

    public void setEnemyStand(Image enemyStand) {
        this.enemyStand = enemyStand;
    }

    public Image getEnemyHit1() {
        return enemyHit1;
    }

    public void setEnemyHit1(Image enemyHit1) {
        this.enemyHit1 = enemyHit1;
    }

    public Image getEnemyHit2() {
        return enemyHit2;
    }

    public void setEnemyHit2(Image enemyHit2) {
        this.enemyHit2 = enemyHit2;
    }

    public Image getEnemyHit3() {
        return enemyHit3;
    }

    public void setEnemyHit3(Image enemyHit3) {
        this.enemyHit3 = enemyHit3;
    }

    public Image getEnemyHit4() {
        return enemyHit4;
    }

    public void setEnemyHit4(Image enemyHit4) {
        this.enemyHit4 = enemyHit4;
    }

    public Image getEnemyHit5() {
        return enemyHit5;
    }

    public void setEnemyHit5(Image enemyHit5) {
        this.enemyHit5 = enemyHit5;
    }

    public Image getEnemyHit6() {
        return enemyHit6;
    }

    public void setEnemyHit6(Image enemyHit6) {
        this.enemyHit6 = enemyHit6;
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

    public Image getEnemyHit7() {
        return enemyHit7;
    }

    public void setEnemyHit7(Image enemyHit7) {
        this.enemyHit7 = enemyHit7;
    }

    public Image getEnemyHit8() {
        return enemyHit8;
    }

    public void setEnemyHit8(Image enemyHit8) {
        this.enemyHit8 = enemyHit8;
    }

    public Image getEnemyHit9() {
        return enemyHit9;
    }

    public void setEnemyHit9(Image enemyHit9) {
        this.enemyHit9 = enemyHit9;
    }

    public Image getEnemyHit10() {
        return enemyHit10;
    }

    public void setEnemyHit10(Image enemyHit10) {
        this.enemyHit10 = enemyHit10;
    }

    public Image getEnemyHit11() {
        return enemyHit11;
    }

    public void setEnemyHit11(Image enemyHit11) {
        this.enemyHit11 = enemyHit11;
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

    public static Animation getEnemyHitAnim() {
        return enemyHitAnim;
    }

    public void setEnemyHitAnim(Animation enemyHitAnim) {
        this.enemyHitAnim = enemyHitAnim;
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
