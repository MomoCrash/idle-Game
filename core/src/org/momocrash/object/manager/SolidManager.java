package org.momocrash.object.manager;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import org.momocrash.IdleMain;
import org.momocrash.object.solid.SolidObject;

public class SolidManager {

    private final ObjectList<SolidObject> solidObjects;

    public SolidManager() {
        solidObjects = new ObjectArrayList<>();
    }

    public void renderShape(ShapeRenderer renderer) {
        for (SolidObject solidObject : solidObjects) {
            solidObject.drawShape(renderer);
        }
    }

    public void renderImage() {
        for (SolidObject solidObject : solidObjects) {
            solidObject.drawImage();
        }
    }

    public void add(SolidObject object) {
        this.solidObjects.add(object);
    }

    public ObjectList<SolidObject> getAll() {
        return this.solidObjects;
    }

}
