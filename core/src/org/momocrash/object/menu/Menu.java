package org.momocrash.object.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.momocrash.data.Player;

public class Menu {

    private final String title;
    private final int lineNumber;
    private final int cols;
    private final float x;
    private final float y;

    public Menu(String title, float x, float y, int cols, int line) {

        this.title = title;
        this.lineNumber = line;
        this.cols = cols;
        this.x = x;
        this.y = y;
    }

    public void draw(ShapeRenderer renderer) {

        renderer.setColor(Color.PINK);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.rect(x, y, (cols * 64), (lineNumber * 64));
        renderer.end();

    }

}
