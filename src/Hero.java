import java.awt.*;

class Hero {

    private int damage;
    int currentHP;

    Font hpFont = new Font(null, Font.BOLD, 20);

    private final int GROUND = 528;
    private final int JUMPSPEED = -15;
    private final int MOVESPEED = 5;

    private int speedX = 0;
    private int speedY = 0;

    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean inflictDamage = false;
    private boolean isDied = false;

    private int centerX = 100;
    private int centerY = GROUND;

    private boolean jumped = false;
    private boolean ducked = false;
    private boolean hit = false;

    private Rectangle rect = new Rectangle(0, 0, 0, 0);
    static Rectangle rectBody = new Rectangle(0, 0, 0, 0);


    void update() {
        if (currentHP <= 0) {
            isDied = true;
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
        } else if (centerX > 1050 && speedX > 0) {
            centerX = 1050;
        }

        //Handle jumping
        centerY += speedY;

        if (jumped) {
            speedY += 1;
            if (centerY >= GROUND) {
                centerY = GROUND;
                speedY = 0;
                jumped = false;
            }
        }
        rect.setRect(centerX + 107, centerY + 25, 20, 20);
        rectBody.setBounds(centerX - 40, centerY - 100, 100, 150);
    }

    Hero(int currentHP, int damage) {
        setCurrentHP(currentHP);
        setDamage(damage);
    }

    void hit() {
        if (!isDucked() && !isJumped() && !isMovingLeft() && !isMovingRight()) {
            hit = true;
            if (rect.intersects(Enemy.getLeftSide()) && !inflictDamage) {
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

    void setInflictDamage() {
        this.inflictDamage = false;
    }
    boolean isDied() {
        return isDied;
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
    void setBeats() {
        this.hit = false;
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
    void setMovingLeft() {
        this.movingLeft = false;
    }
    boolean isMovingRight() {
        return movingRight;
    }
    void setMovingRight() {
        this.movingRight = false;
    }
    boolean isJumped() {
        return jumped;
    }
    void setJumped() {
        this.jumped = true;
    }
}