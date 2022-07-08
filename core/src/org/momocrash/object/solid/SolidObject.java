package org.momocrash.object.solid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.momocrash.IdleMain;

public abstract class SolidObject {

    private final int x;
    private final int y;
    private final int xMax;
    private final int yMax;
    private final int width;
    private final int height;

    private final String imagePath;
    private Texture texture;

    public SolidObject(int x, int y, int width, int height, String imagePath) {

        this.x = x;
        this.y = y;
        this.yMax = y + width;
        this.xMax = x + height;
        this.width = width;
        this.height = height;

        this.imagePath = imagePath;

        generateTexture();

    }

    public int[] bounds() {
        return new int[] {x, y, xMax, yMax};
    }

    public void drawShape(ShapeRenderer renderer) {

        renderer.setColor(Color.BLACK);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.rect(this.x, this.y, width, height);
        renderer.end();

    }

    private void generateTexture() {

        Pixmap pixMap = new Pixmap(Gdx.files.internal(imagePath));
        Pixmap pixMapResize = new Pixmap(width, height, pixMap.getFormat());
        pixMapResize.drawPixmap(pixMap,
                0, 0, pixMap.getWidth(), pixMap.getHeight(),
                0, 0, pixMapResize.getWidth(), pixMapResize.getHeight()
        );
        this.texture = new Texture(pixMapResize);

    }

    public void drawImage() {

        IdleMain.getInstance().getBatch().draw(texture, getX(), getY());

    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

}
