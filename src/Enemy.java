import java.awt.*;

public class Enemy {

    private int centerX, centerY;
    private int speedX;
    private int currentHP;

    boolean atack = false;

    Font hpFont = new Font(null,Font.BOLD, 20);

    Rectangle rightSide = new Rectangle(0,0,0,0);
    static Rectangle leftSide = new Rectangle(0,0,0,0);

    Enemy(int centerX, int centerY, int currentHP){
        setCurrentHP(currentHP);
        setCenterX(centerX);
        setCenterY(centerY);
    }

    void update(){
        rightSide.setRect(centerX+20, centerY-38, 65,100);
        leftSide.setRect(centerX+8,centerY-40,40,140);

    }



    public boolean isAtack() {
        return atack;
    }

    public void setAtack(boolean atack) {
        this.atack = atack;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public  Rectangle getRightSide() {
        return rightSide;
    }

    public void setRightSide(Rectangle rightSide) {
        this.rightSide = rightSide;
    }

    public static Rectangle getLeftSide() {
        return leftSide;
    }

    public void setLeftSide(Rectangle leftSide) {
        this.leftSide = leftSide;
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

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }


}
