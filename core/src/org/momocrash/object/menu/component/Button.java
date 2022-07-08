package org.momocrash.object.menu.component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.momocrash.IdleMain;
import org.momocrash.object.menu.Menu;

public class Button extends Component {

    private final Runnable toRun;
    private final String path;
    private Texture texture;

    public Button(Menu parent, float x, float y, int width, int height, String path, Runnable action) {
        super(parent, x, y, width, height);

        this.toRun = action;
        this.path = path;
        generateTexture();
    }

    @Override
    public void run() {
        toRun.run();
    }

    @Override
    public void draw(ShapeRenderer renderer) {
        renderer.setColor(Color.FIREBRICK);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.rect(this.getX(), this.getY(), this.getWidth(), -this.getHeight());
        renderer.end();
        IdleMain.getInstance().getBatch().begin();
        IdleMain.getInstance().getBatch().draw(texture, getX(), getY());
        IdleMain.getInstance().getBatch().end();
    }

    private void generateTexture() {

        Pixmap pixMap = new Pixmap(Gdx.files.internal(path));
        Pixmap pixMapResize = new Pixmap(getWidth(), getHeight(), pixMap.getFormat());
        pixMapResize.drawPixmap(pixMap,
                0, 0, pixMap.getWidth(), pixMap.getHeight(),
                0, 0, pixMapResize.getWidth(), pixMapResize.getHeight()
        );
        this.texture = new Texture(pixMapResize);

    }

}
