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

        if (arrowRect.intersects(Hero.rectBody)) {
            if (StartingClass.arrow != null) {
                StartingClass.elza.setCurrentHP(StartingClass.elza.getCurrentHP() - damage);
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
