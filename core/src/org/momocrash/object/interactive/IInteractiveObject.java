package org.momocrash.object.interactive;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.momocrash.IdleMain;

public abstract class IInteractiveObject {

    private final int x;
    private final int y;
    private final int xMax;
    private final int yMax;

    public boolean isUse = false;
    private String imagePath;

    public IInteractiveObject(int x, int y, int width, int height, String imagePath) {

        this.x = x;
        this.y = y;
        this.xMax = x + width;
        this.yMax = y + height;

        this.imagePath = imagePath;

    }

    public int[] bounds() {
        return new int[] {x, y, xMax, yMax};
    }

    public void activate() { }
    public void use() {

    }

    public void drawShape(ShapeRenderer renderer) {

        if (enabled()) {
            renderer.setColor(Color.OLIVE);
        } else {
            renderer.setColor(Color.RED);
        }
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.rect(this.x-1, this.y-1, 51, 51);
        renderer.end();

    }

    public void drawImage() {

        IdleMain.getInstance().getBatch().draw(new Texture(imagePath), getX(), getY());

    }

    public boolean enabled() {
        return isUse;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
