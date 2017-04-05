import java.awt.*;

class Hero {

    private int damage;
    private int currentHP;

    private final int GROUND = 528;
    private final int JUMPSPEED = -17;
    private final int MOVESPEED = 5;

    private int speedX;
    private int speedY;

    private int centerX = 100;
    private int centerY = GROUND;

    private boolean movingLeft;
    private boolean movingRight;
    private boolean inflictDamage;

    private boolean jumped;
    private boolean ducked;
    private boolean hit;

    Font hpFont = new Font(null, Font.BOLD, 20);

    static Rectangle rect  = new Rectangle(0, 0, 0, 0);
    static Rectangle rectBody = new Rectangle(0, 0, 0, 0);

    private Rectangle archerBody;
    private int archerHP;
    private int archerX;

    Hero(int currentHP, int damage) {
        setCurrentHP(currentHP);
        setDamage(damage);
    }

    void update() {
        archerBody = Archer.archerBody;
        archerHP = StartingClass.enemyArcher.getCurrentHP();
        archerX = StartingClass.enemyArcher.getCenterX();

        rect.setRect(centerX+120, centerY+40, 20, 20);
        rectBody.setBounds(centerX+8,centerY-1, 100, 150);

        //Death. That's all
        if (currentHP <= 0) {
            StartingClass.state = StartingClass.GameState.DEAD;
        }

        //Moving left and right
        if (speedX > 0 || speedX < 0) {
            centerX += speedX;
        }

        //Preventing moving over borders(left, right)
        if (centerX < 0) {
            centerX = 0;
        } else if (centerX > 500) {
            centerX = 500;
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
        if(rectBody.intersects(archerBody) && jumped){
            centerY = StartingClass.enemyArcher.getCenterY() -145;
            speedY = 0;
            jumped = false;
        } else if(rectBody.intersects(archerBody) && !jumped && centerY==GROUND){
            centerX = archerX-100;
        } else if(!rectBody.intersects(archerBody) && centerY<GROUND){
            jumped = true;
        }
    }

    void hit() {
        archerHP = StartingClass.enemyArcher.getCurrentHP();

        if (!ducked && !jumped && !movingLeft && !movingRight) {
            hit = true;
            if (rect.intersects(Archer.archerBody) && !inflictDamage) {
               StartingClass.enemyArcher.setCurrentHP(archerHP - damage);
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

    public int getDamage() {
        return damage;
    }

    public Font getHpFont() {
        return hpFont;
    }

    public void setHpFont(Font hpFont) {
        this.hpFont = hpFont;
    }

    public int getGROUND() {
        return GROUND;
    }

    public int getJUMPSPEED() {
        return JUMPSPEED;
    }

    public int getMOVESPEED() {
        return MOVESPEED;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public boolean isInflictDamage() {
        return inflictDamage;
    }

    public void setJumped(boolean jumped) {
        this.jumped = jumped;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public static Rectangle getRect() {
        return rect;
    }

    public static void setRect(Rectangle rect) {
        Hero.rect = rect;
    }

    public void setRectBody(Rectangle rectBody) {
        this.rectBody = rectBody;
    }



    Rectangle getRectBody() {
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
    void setDucked(boolean isDucked) {
        this.ducked = isDucked;
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