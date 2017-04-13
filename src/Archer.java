import java.awt.*;

class Archer extends Enemy {

    private int centerX, centerY, currentHP;
    private boolean attack, died;

    static Rectangle archerBody = new Rectangle(0, 0, 0, 0);

    Archer(int centerX, int centerY, int currentHP) {
        setCenterX(centerX);
        setCenterY(centerY);
        setCurrentHP(currentHP);
    }

    @Override
    void update() {
        if(archerBody != null) {
            archerBody.setRect(getCenterX() + 8, getCenterY(), 130, 140);
        }
        hit();
        centerX = bgScroll(centerX);
        died = death(currentHP, died);
    }

    @Override
    void hit() {
        elzaX = StartingClass.getElza().getCenterX();
        attack = !died && elzaX < centerX && elzaX > 250 || !died && elzaX < centerX && centerX < 600;
    }



    public static Rectangle getRectBody() {
        return archerBody;
    }

    public static void setRectBody(Rectangle rectBody) {
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
        return died;
    }

    public void setDied(boolean died) {
        this.died = died;
    }
}
