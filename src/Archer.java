import java.awt.*;

class Archer extends Enemy {

    private int centerX, centerY, currentHP;
    private boolean attack, isDied;

    static Rectangle archerBody = new Rectangle(0, 0, 0, 0);

    Archer(int centerX, int centerY, int currentHP) {
        setCenterX(centerX);
        setCenterY(centerY);
        setCurrentHP(currentHP);
    }

    @Override
    void update() {
        archerBody.setRect(getCenterX() + 8, getCenterY(), 130, 140);
        hit();
        centerX = bgScroll(centerX);
        isDied = death(currentHP, isDied);
    }

    @Override
    void hit() {
        elzaX = StartingClass.getElza().getCenterX();
        attack = !isDied && elzaX < centerX && elzaX > 250 || !isDied && elzaX < centerX && centerX < 600;
    }



    public static Rectangle getRectBody() {
        return archerBody;
    }

    public void setRectBody(Rectangle rectBody) {
        Archer.archerBody = rectBody;
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

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public boolean isAttack() {
        return attack;
    }

    public void setAttack(boolean attack) {
        this.attack = attack;
    }

    public boolean isDied() {
        return isDied;
    }

    public void setDied(boolean died) {
        isDied = died;
    }
}
