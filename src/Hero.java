import com.sun.org.apache.regexp.internal.RE;

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

    private boolean movingLeft;
    private boolean movingRight;
    private boolean inflictDamage;

    private int centerX = 100;
    private int centerY = GROUND;

    private boolean jumped;
    private boolean ducked;
    private boolean hit;

    private static Rectangle rect = new Rectangle(0, 0, 0, 0);
    private static Rectangle rectBody = new Rectangle(0, 0, 0, 0);


    Hero(int currentHP, int damage) {
        setCurrentHP(currentHP);
        setDamage(damage);
    }

    void update() {
        Rectangle enemyRect = Enemy.getRectBody();

        rect.setRect(centerX+120, centerY+40, 20, 20);
        rectBody.setBounds(centerX+8,centerY-1, 100, 150);

        //Death. That's all
        if (currentHP <= 0) {
            StartingClass.state = StartingClass.GameState.DEAD;
            speedX = 0;
            currentHP = 0;
        }

        //Moving left and right
        if (speedX > 0) {
            centerX += speedX;
        } else if (speedX < 0) {
            centerX += speedX;
        }

        //Preventing moving over borders(left, right)
        if (centerX < 0) {
            centerX = 0;
        } else if (centerX > 600) {
            centerX = 600;
        }


        //Gravity force
        centerY += speedY;

        if (centerY > GROUND) {
            centerY = GROUND;
            speedY = 0;
            jumped = false;
        }
        if (jumped) {
            speedY += 1;
        }

        // 1)Now you can climb on your enemy. 2)You can't go through your enemy. 3)If you already not on your enemy, you will fall
        if(rectBody.intersects(enemyRect) && jumped){
            centerY = StartingClass.getEnemy1().getCenterY()-145;
            speedY = 0;
            jumped = false;
        } else if(rectBody.intersects(enemyRect) && !jumped && centerY==GROUND){
            centerX = StartingClass.getEnemy1().getCenterX()-100;
        } else if(!rectBody.intersects(enemyRect) && centerY<GROUND){
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
}