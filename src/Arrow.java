import java.awt.*;

class Arrow {
    private int centerX, centerY, speedX;
    private static Rectangle arrowRect;
    private int damage = 35;

    Arrow(int centerX, int centerY, int speedX) {
        setCenterX(centerX);
        setCenterY(centerY);
        setSpeedX(speedX);
        arrowRect = new Rectangle(0, 0, 0, 0);
    }

    void update() {
        arrowRect.setBounds(centerX - 16, centerY-1, 100, 16);
        centerX += speedX;

        if (arrowRect.intersects(Hero.getRectBody())) {
            if (StartingClass.getArrowObj() != null) {
                StartingClass.getElza().setCurrentHP(StartingClass.getElza().getCurrentHP() - damage);
                StartingClass.setArrowObj();
            }
        }
    }

    public static Rectangle getArrowRect() {
        return arrowRect;
    }

    public static void setArrowRect(Rectangle arrowRect) {
        Arrow.arrowRect = arrowRect;
    }

    int getCenterX() {
        return centerX;
    }
    void setCenterX(int centerX) {
        this.centerX = centerX;
    }
    int getCenterY() {
        return centerY;
    }
    void setCenterY(int centerY) {
        this.centerY = centerY;
    }
    void setSpeedX(int speedX) {
        this.speedX = speedX;
    }
}
