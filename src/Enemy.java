import java.awt.*;

class Enemy {

    private int centerX, centerY, speedX, currentHP;

    private boolean attack = false;
    private boolean isDied = false;

    Font hpFont = new Font(null, Font.BOLD, 20);

    private static Rectangle rectBody = new Rectangle(0, 0, 0, 0);

    Enemy(int centerX, int centerY, int currentHP) {
        setCurrentHP(currentHP);
        setCenterX(centerX);
        setCenterY(centerY);
    }

    void update() {
        int elzaX = StartingClass.getElza().getCenterX();

        attack = !isDied && elzaX < centerX && elzaX > 250 || !isDied && elzaX < centerX && centerX < 600;

        if (currentHP <= 0) {
            isDied = true;
            currentHP = 0;
        }
        if (StartingClass.getElza().getCenterX() > 599 && StartingClass.getElza().getSpeedX() > 0) {
            setSpeedX(-3);
            centerX += speedX;
        }
        rectBody.setRect(getCenterX() + 8, getCenterY(), 130, 140);
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    boolean isDied() {
        return isDied;
    }
    boolean isAtack() {
        return attack;
    }
    int getCurrentHP() {
        return currentHP;
    }
    void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }
    static Rectangle getRectBody() {
        return rectBody;
    }
    int getCenterX() {
        return centerX;
    }
    void setCenterX(int centerX) {
        this.centerX = centerX;
    }
    int getCenterY() {
        return centerY;
    }
    void setCenterY(int centerY) {
        this.centerY = centerY;
    }
}


