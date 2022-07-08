package org.momocrash.object.solid;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import org.momocrash.data.Player;

public class SolidManager {

    private final ObjectList<SolidObject> SolidObjects;

    public SolidManager() {
        SolidObjects = new ObjectArrayList<>();
    }

    public void renderShape(ShapeRenderer renderer) {
        for (SolidObject SolidObject : SolidObjects) {
            SolidObject.drawShape(renderer);
        }
    }

    public void renderImage() {
        for (SolidObject SolidObject : SolidObjects) {
            SolidObject.drawImage();
        }
    }

    public void add(SolidObject object) {
        this.SolidObjects.add(object);
    }

    public ObjectList<SolidObject> getAll() {
        return this.SolidObjects;
    }

    public boolean collideTo(Player player, float nextX, float nextY) {

        for (SolidObject object : getAll()) {

            int[] bounds = object.bounds();

            if (((nextX >= bounds[0] && nextX <= bounds[2])
                    || ((nextX + player.getWidth()) >= bounds[0] && (nextX + player.getWidth()) <= bounds[2]))
                    && ((nextY >= bounds[1] && nextY <= bounds[3])
                    || (nextY + player.getHeight()) >= bounds[1] && (nextY + player.getHeight()) <= bounds[2])) {
                return true;
            }

        }

        return false;

    }

}
