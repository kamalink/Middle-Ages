import java.util.Random;

class Chara extends Enemy {
    private int centerX, centerY, currentHP, speedX;
    private boolean attack, isDied, movingLeft, movingRight;
    Random rand = new Random();

    Chara(int centerX, int centerY, int currentHP){
        setCenterX(centerX);
        setCenterY(centerY);
        setCurrentHP(currentHP);
    }


    void update() {
        centerX = bgScroll(centerX);
        isDied = death(currentHP, isDied);
        centerX+=speedX;
        follow();
    }

    void follow(){
        if(centerX < -95 || centerX > 1150){
            speedX = 0;
            movingLeft = false;
            movingRight = false;
        } else if(Math.abs(StartingClass.getElza().getCenterX() - centerX) < 50){
            speedX = 0;
            movingLeft = false;
            movingRight = false;
        } else{
            if(StartingClass.getElza().getCenterX() >= centerX){
                speedX = 1;
                movingRight = true;
            } else {
                speedX = -1;
                movingLeft = true;
            }
        }
    }

    @Override
    void hit() {
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
