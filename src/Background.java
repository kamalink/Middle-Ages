
public class Background {

    private int bgX, bgY, speedX;

    Background(int x, int y){
        bgX = x;
        bgY = y;
        speedX = 0;
    }

    void update(){
        bgX+=speedX;

        if(bgX<-1360){
            bgX+=2720;
        }
    }

    public int getBgX() {
        return bgX;
    }

    public void setBgX(int bgX) {
        this.bgX = bgX;
    }

    public int getBgY() {
        return bgY;
    }

    public void setBgY(int bgY) {
        this.bgY = bgY;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }
}
