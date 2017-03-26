import java.awt.*;

public class Arrow {
    private int centerX, centerY, speedX;
    Rectangle arrowRect;



    Arrow(int centerX, int centerY, int speedX){
        setCenterX(centerX);
        setCenterY(centerY);
        setSpeedX(speedX);
        arrowRect = new Rectangle(0,0,0,0);
    }

    void update(){
        arrowRect.setBounds(StartingClass.getArrowObj().getCenterX(), StartingClass.getArrowObj().getCenterY(), 60,20);
        centerX+=speedX;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public Rectangle getArrowRect() {
        return arrowRect;
    }

    public void setArrowRect(Rectangle arrowRect) {
        this.arrowRect = arrowRect;
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
