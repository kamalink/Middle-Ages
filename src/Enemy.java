import java.awt.*;

abstract class Enemy {
    int elzaX;

    Font hpFont = new Font(null, Font.BOLD, 20);

    abstract void update();
    abstract void hit();

    int bgScroll(int centerX){
        elzaX = StartingClass.getElza().getCenterX();
        if (elzaX > 499 && StartingClass.getElza().getSpeedX() > 0) {
            centerX += -2;
        }
        return centerX;
    }

    boolean death(int currentHP, boolean isDied) {
        if (currentHP <= 0) {
            isDied = true;
        }
        return isDied;
    }
}


