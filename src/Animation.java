import java.awt.*;
import java.util.ArrayList;

class Animation {
    private ArrayList frames;
    private int currentFrame;
    private long animTime;
    private long totalDuration;

    Animation(){
        frames = new ArrayList();
        totalDuration = 0;

        synchronized (this){
            animTime = 0;
            currentFrame = 0;
        }
    }

    synchronized void addFrame(Image image, long duration){
        totalDuration+=duration;
        frames.add(new AnimFrame(image, totalDuration));
    }

    synchronized void update(long elapsedTime){
        if(frames.size()>1) {
            animTime += elapsedTime;

            if (animTime >= totalDuration) {
                animTime = animTime % totalDuration;
                currentFrame = 0;
            }
        }
        while (animTime>getFrame(currentFrame).endTime){
            currentFrame++;
        }
    }

    synchronized Image getImage(){
        if(frames.size()==0){
            return null;
        }else {
            return getFrame(currentFrame).image;
        }
    }

    private AnimFrame getFrame(int i){
        return (AnimFrame) frames.get(i);
    }

    private class AnimFrame{
        Image image;
        long endTime;

        AnimFrame(Image image, long endTime){
            this.image = image;
            this.endTime = endTime;
        }
    }
    int getCurrentFrame() {
        return currentFrame;
    }
}

