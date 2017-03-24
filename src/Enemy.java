import java.awt.*;

public class Enemy {

    int centerX, centerY;
    int speedX = -1;
    Rectangle r = new Rectangle(0,0,0,0);


    Enemy(int centerX, int centerY){
        setCenterX(centerX);
        setCenterY(centerY);
    }


    void update(){
        centerX+=speedX;
        r.setRect(centerX-10, centerY-33, 140,170);

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
