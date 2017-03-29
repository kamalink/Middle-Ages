import java.awt.*;

class Hero {

    private int damage;
    private int currentHP;

    Font hpFont = new Font(null, Font.BOLD, 20);

    private final int GROUND = 528;
    private final int JUMPSPEED = -17;
    private final int MOVESPEED = 5;

    private int speedX;
    private int speedY;

    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean inflictDamage = false;

    private int centerX = 100;
    private int centerY = GROUND;

    private boolean jumped = false;
    private boolean ducked = false;
    private boolean hit = false;

    private static Rectangle rect = new Rectangle(0, 0, 0, 0);
    private static Rectangle rectBody = new Rectangle(0, 0, 0, 0);

    Hero(int currentHP, int damage) {
        setCurrentHP(currentHP);
        setDamage(damage);
    }

    void update() {
        if (currentHP <= 0) {
            StartingClass.state = StartingClass.GameState.DEAD;
            speedX = 0;
            currentHP = 0;
        }

        //Handle positive and negative moving
        if (speedX > 0) {
            centerX += speedX;
        } else if (speedX < 0) {
            centerX += speedX;
        }

        //Preventing moving over borders(left, right)
        if (centerX < 0 && speedX < 0) {
            centerX = 0;
        } else if (centerX > 600 && speedX > 0) {
            centerX = 600;
        }


        //Handle jumping
        centerY += speedY;

        if (jumped) {
            speedY += 1;
            if (centerY > GROUND) {
                centerY = GROUND;
                speedY = 0;
                jumped = false;
            }
        }
        rect.setRect(centerX+120, centerY+40, 20, 20);
        rectBody.setBounds(centerX+8,centerY-1, 100, 150);

        if(rectBody.intersects(Enemy.getRectBody()) && jumped){
            centerY = StartingClass.getEnemy1().getCenterY()-145;
            speedY = 0;
            jumped = false;
        } else if(rectBody.intersects(Enemy.getRectBody()) && !jumped && centerY==GROUND){
            centerX = StartingClass.getEnemy1().getCenterX()-100;
        } else if(!rectBody.intersects(Enemy.getRectBody()) && centerY<GROUND){
            jumped = true;
        }
    }

    void hit() {
        if (!isDucked() && !isJumped() && !isMovingLeft() && !isMovingRight()) {
            hit = true;
            if (rect.intersects(Enemy.getRectBody()) && !inflictDamage) {
                StartingClass.getEnemy1().setCurrentHP(StartingClass.getEnemy1().getCurrentHP() - damage);
                inflictDamage = true;
            }
        }
    }

    void jump() {
        if (!jumped && !ducked && !hit) {
            speedY = JUMPSPEED;
            jumped = true;
        }
    }

    void duck() {
        if (!jumped && !hit) {
            ducked = true;
            speedX = 0;
        }
    }

    void moveRight() {
        if (!ducked && !hit) {
            speedX = MOVESPEED;
            movingRight = true;
        }
    }

    void moveLeft() {
        if (!ducked && !hit) {
            speedX = -MOVESPEED;
            movingLeft = true;
        }
    }

    void stop() {
        speedX = 0;
    }



    public static Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        Hero.rect = rect;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    static Rectangle getRectBody() {
        return rectBody;
    }
    void setInflictDamage(boolean inflictDamage) {
        this.inflictDamage = inflictDamage;
    }
    void setDamage(int damage) {
        this.damage = damage;
    }
    int getCurrentHP() {
        return currentHP;
    }
    void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }
    boolean isBeats() {
        return hit;
    }
    void setBeats(boolean Beats) {
        this.hit = Beats;
    }
    boolean isDucked() {
        return ducked;
    }
    void setDucked() {
        this.ducked = false;
    }
    int getSpeedX() {
        return speedX;
    }
    int getCenterX() {
        return centerX;
    }
    int getCenterY() {
        return centerY;
    }
    boolean isMovingLeft() {
        return movingLeft;
    }
    void setMovingLeft(boolean MovingLeft) {
        this.movingLeft = MovingLeft;
    }
    boolean isMovingRight() {
        return movingRight;
    }
    void setMovingRight(boolean MovingRight) {
        this.movingRight = MovingRight;
    }
    boolean isJumped() {
        return jumped;
    }
    void setJumped(boolean Jump) {
        this.jumped = Jump;
    }
}