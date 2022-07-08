package org.momocrash.object.text;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import org.momocrash.IdleMain;

public abstract class Text {

    private final String text;
    private float x;
    private float y;

    private BitmapFont font;

    public Text(String text, float x, float y) {

        this.text = text;
        this.x = x;
        this.y = y;

    }

    public Text(String text, float x, float y, BitmapFont font) {

        this.text = text;
        this.x = x;
        this.y = y;
        this.font = font;

    }

    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }

    public void startAnimation(float x, float y) { }

    public boolean animate() { return false; }

    public String getText() {
        return text;
    }

    public void drawText() {

        if (this.font == null) {
            IdleMain.getInstance().getFont().draw(IdleMain.getInstance().getBatch(), this.text, this.x, this.y);
        }

    }

}
