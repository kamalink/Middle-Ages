import java.awt.*;

public class Enemy {

    private int centerX, centerY;
    private int currentHP;

    private boolean atack = false;
    private boolean isDied = false;

    Font hpFont = new Font(null, Font.BOLD, 20);

    private static Rectangle leftSide = new Rectangle(0, 0, 0, 0);

    Enemy(int centerX, int centerY, int currentHP) {
        setCurrentHP(currentHP);
        setCenterX(centerX);
        setCenterY(centerY);
    }

    void update() {
        leftSide.setRect(centerX + 8, centerY - 40, 40, 140);

        if (currentHP <= 0) {
            isDied = true;
            currentHP = 0;
        }
    }

    boolean isDied() {
        return isDied;
    }
    public void setDied(boolean died) {
        isDied = died;
    }
    boolean isAtack() {
        return atack;
    }
    void setAtack(boolean atack) {
        this.atack = atack;
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


