import java.awt.*;

class Enemy {

    private int centerX, centerY;
    private int currentHP;

    private boolean attack = false;
    private boolean isDied = false;

    Font hpFont = new Font(null, Font.BOLD, 20);

    private static Rectangle leftSide = new Rectangle(0, 0, 0, 0);

    Enemy(int centerX, int centerY, int currentHP) {
        setCurrentHP(currentHP);
        setCenterX(centerX);
        setCenterY(centerY);
        leftSide.setRect(centerX + 8, centerY - 40, 40, 140);
    }

    void update() {
        if(!isDied) {
            attack = StartingClass.getElza().getCenterX() > 250;
        }
        if (currentHP <= 0) {
            isDied = true;
            currentHP = 0;
        }
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
    static Rectangle getLeftSide() {
        return leftSide;
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


