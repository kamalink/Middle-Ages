import java.awt.*;

import static java.lang.Thread.sleep;

class Hero {

    int damage;
    int currentHP;

    Font hpFont = new Font(null,Font.BOLD, 20);

    private final int GROUND = 528;
    private final int JUMPSPEED = -15;
    private final int MOVESPEED = 5;

    private int speedX = 0;
    private int speedY = 0;

    private boolean movingLeft = false;
    private boolean movingRight = false;

    private int centerX = 100;
    private int centerY = GROUND;

    private boolean jumped = false;
    private boolean ducked = false;
    private boolean hit = false;

    Rectangle rect = new Rectangle(0,0,0,0);
    Rectangle rectBody = new Rectangle(0,0,0,0);


    void update() {

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
        rect.setRect(centerX+107, centerY+25, 20,20);
        rectBody.setBounds(centerX-40, centerY-100, 100,150);
    }


    Hero(int currentHP, int damage){
        setCurrentHP(currentHP);
        setDamage(damage);

    }

    void hit() {
        if(!isDucked() && !isJumped() && !isMovingLeft() && !isMovingRight()){
            hit = true;
            if(rect.intersects(Enemy.getLeftSide())){
                StartingClass.getEnemy1().setCurrentHP(StartingClass.getEnemy1().getCurrentHP() - damage);

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


    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public Font getHpFont() {
        return hpFont;
    }

    public void setHpFont(Font hpFont) {
        this.hpFont = hpFont;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public boolean isBeats() {
        return hit;
    }

    public void setBeats(boolean beats) {
        this.hit = beats;
    }

    public boolean isDucked() {
        return ducked;
    }

    public void setDucked(boolean ducked) {
        this.ducked = ducked;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }


    public int getGROUND() {
        return GROUND;
    }

    public int getMOVESPEED() {
        return MOVESPEED;
    }

    public int getJUMPSPEED() {
        return JUMPSPEED;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public boolean isMovingLeft() {
        return movingLeft;
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    public boolean isJumped() {
        return jumped;
    }

    public void setJumped(boolean jumped) {
        this.jumped = jumped;
    }

}