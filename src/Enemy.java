
public class Enemy {

    int centerX, centerY, speedX;
    private Background bg = StartingClass.getBg();

    Enemy(int centerX, int centerY){
        setCenterX(centerX);
        setCenterY(centerY);
    }


    void update(){
        centerX+=speedX;
        speedX = bg.getSpeedX()*2;
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

    public Background getBg() {
        return bg;
    }

    public void setBg(Background bg) {
        this.bg = bg;
    }
}
