import java.awt.*;

class Chara extends Enemy {
    private int centerX, centerY, currentHP, speedX;
    private boolean attack, isMoving;

    private Rectangle charaBody = new Rectangle();

    Chara(int centerX, int centerY, int currentHP){
        setCenterX(centerX);
        setCenterY(centerY);
        setCurrentHP(currentHP);
    }


    void update() {
        charaBody.setRect(centerX+10, centerY, 140,350);

        centerX = bgScroll(centerX);
        centerX+=speedX;
        follow();

        if(charaBody.intersects(StartingClass.getElza().getRectBody())){
            if(!StartingClass.getElza().isDucked()){
                StartingClass.getElza().setCurrentHP(0);
            }
        }
    }

    void follow(){
        if(centerX < -95 || centerX > 1000){
            speedX = 0;
            isMoving = false;
        } else {
                speedX = -1;
            isMoving = true;
            }
        }

    @Override
    void hit() {
    }

    public Rectangle getCharaBody() {
        return charaBody;
    }

    public void setCharaBody(Rectangle charaBody) {
        this.charaBody = charaBody;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean movingLeft) {
        this.isMoving = movingLeft;
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
}
