package org.momocrash.object.text;

public class AnimatedText extends TextObject {

    private float finalX;
    private float finalY;

    private float xProgress;
    private float yProgress;

    public AnimatedText(String text, float x, float y) {
        super(text, x, y);
        finalX = x;
        finalY = y;
    }

    public void startAnimation(float x, float y) {

        this.xProgress = x;
        this.yProgress = y;

        animate();

    }

    public boolean animate() {

        boolean edited = false;
        if (getX() <= finalX) {
            setX(getX() + xProgress);
            edited = true;
        }
        if (getY() <= finalY) {
            setY(getY() + yProgress);
            edited = true;
        }

        return false;

    }

}
