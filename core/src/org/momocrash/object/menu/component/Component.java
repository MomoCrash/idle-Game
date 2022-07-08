package org.momocrash.object.menu.component;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.momocrash.object.menu.Menu;

public abstract class Component {

    private final float x, y ;
    private final int width, height;

    public Component(Menu parent, float x, float y, int width, int height) {

        this.x = parent.getX() + x;
        this.y = parent.getY() + -y;
        this.height = height;
        this.width = width;

    }

    public void run() { }
    public void draw(ShapeRenderer renderer) { }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

}
