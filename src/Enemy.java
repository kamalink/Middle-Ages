
public class Enemy {

    int centerX, centerY, speedX;


    Enemy(int centerX, int centerY){
        setCenterX(centerX);
        setCenterY(centerY);
    }


    void update(){

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
