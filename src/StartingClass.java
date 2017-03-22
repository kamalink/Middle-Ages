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

    private Image image, background, heroJumpRight, heroJumpLeft, heroDucked;
    private Image heliboy, heliboy2, heliboy3, heliboy4, heliboy5;
    private Image heroStandRight1, heroStandRight2, heroStandLeft1, heroStandLeft2;
    private Image heroRunningRight1, heroRunningRight2, heroRunningRight3;
    private Image heroRunningLeft1, heroRunningLeft2, heroRunningLeft3;

    private static Background bg, bg2;

    private Animation runningRightAnim, runningLeftAnim, standAnimRight, standAnimLeft, currentAnim;
    private Animation heliAnim;


    @Override
    public void init() {

        //INIT SETUP

        setSize(1360,768);
        setBackground(Color.black);
        setFocusable(true);
        addKeyListener(this);
        Frame frame = (Frame) this.getParent().getParent();
        frame.setTitle("Middle Ages");
        try {
            base = getDocumentBase();
        } catch (Exception e){
            // TODO: handle exception
        }

        //IMAGE SETUPS
        background = getImage(base, "/ToBeSurvivor/background.png");

        heroStandRight1 = getImage(base, "/ToBeSurvivor/heroStandRight1.png");
        heroStandRight2 = getImage(base, "/ToBeSurvivor/heroStandRight2.png");

        heroStandLeft1 = getImage(base,"/ToBeSurvivor/heroStandLeft1.png");
        heroStandLeft2 = getImage(base, "/ToBeSurvivor/heroStandLeft2.png");

        heroRunningRight1 = getImage(base, "/ToBeSurvivor/heroRunningRight1.png");
        heroRunningRight2 = getImage(base, "/ToBeSurvivor/heroRunningRight2.png");
        heroRunningRight3 = getImage(base, "/ToBeSurvivor/heroRunningRight3.png");

        heroRunningLeft1 = getImage(base, "/ToBeSurvivor/heroRunningLeft1.png");
        heroRunningLeft2 = getImage(base, "/ToBeSurvivor/heroRunningLeft2.png");
        heroRunningLeft3 = getImage(base, "/ToBeSurvivor/heroRunningLeft3.png");

        heroJumpRight = getImage(base, "/ToBeSurvivor/heroJumpRight.png");
        heroJumpLeft = getImage(base, "/ToBeSurvivor/heroJumpLeft.png");
        
        heroDucked = getImage(base, "/ToBeSurvivor/heroDucked.png");

        heliboy = getImage(base ,"/ToBeSurvivor/heliboy.png");
        heliboy2 = getImage(base, "/ToBeSurvivor/heliboy2.png");
        heliboy3 = getImage(base, "/ToBeSurvivor/heliboy3.png");
        heliboy4 = getImage(base, "/ToBeSurvivor/heliboy4.png");
        heliboy5 = getImage(base, "/ToBeSurvivor/heliboy5.png");


        //HERO ANIMATIONS
        {
            standAnimRight = new Animation();
            standAnimRight.addFrame(heroStandRight1, 200);
            standAnimRight.addFrame(heroStandRight2, 8000);
        }
        {
            standAnimLeft = new Animation();
            standAnimLeft.addFrame(heroStandLeft1,200);
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
        heliAnim = new Animation();
        heliAnim.addFrame(heliboy, 100);
        heliAnim.addFrame(heliboy2, 100);
        heliAnim.addFrame(heliboy3, 100);
        heliAnim.addFrame(heliboy4, 100);
        heliAnim.addFrame(heliboy5, 100);
        heliAnim.addFrame(heliboy4, 100);
        heliAnim.addFrame(heliboy3, 100);
        heliAnim.addFrame(heliboy2, 100);

    }


    @Override
    public void start() {
        bg = new Background(0,0);
        bg2 = new Background(1360,0);

        elza = new Hero();

        enemy1 = new Enemy(1000, 450);

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {

        while (true){


            elza.update();
            enemy1.update();

            bg.update();
            bg2.update();
            currentAnim();

            animate();
            repaint();

            try{
                Thread.sleep(17);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void animate() {
        heliAnim.update(50);
    }

    private void currentAnim(){
        currentAnim.update(50);
    }



    @Override
    public void update(Graphics g) {
        if(image==null){
            image = createImage(this.getWidth(),this.getHeight());
            second = image.getGraphics();
        }

        second.setColor(getBackground());
        second.fillRect(0,0,getWidth(),getHeight());
        second.setColor(getForeground());
        paint(second);
        g.drawImage(image,0,0,this);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(background,bg.getBgX(),bg.getBgY(),this);
        g.drawImage(background, bg2.getBgX(), bg2.getBgY(),this);

        if(!elza.isJumped() && !elza.isDucked()){
            g.drawImage(currentAnim.getImage(),elza.getCenterX(),elza.getCenterY(),this);
        }
        if(elza.isJumped() && elza.isMovingRight()){
            g.drawImage(heroJumpRight, elza.getCenterX(),elza.getCenterY(),this);
        }

        if(elza.isJumped() && elza.isMovingLeft()){
            g.drawImage(heroJumpLeft, elza.getCenterX(), elza.getCenterY(), this);
        }

        g.drawImage(heliAnim.getImage(), enemy1.getCenterX(), enemy1.getCenterY(), this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()){

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

        switch (e.getKeyCode()){

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


    public void setBackground(Image background) {
        this.background = background;
    }

    public Image getHero() {
        return heroStandRight1;
    }

    public void setHero(Image hero) {
        this.heroStandRight1 = hero;
    }

    public static Background getBg() {
        return bg;
    }

    public void setBg(Background bg) {
        this.bg = bg;
    }

    public Background getBg2() {
        return bg2;
    }

    public void setBg2(Background bg2) {
        this.bg2 = bg2;
    }
}
