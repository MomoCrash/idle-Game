package org.momocrash.object.solid;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import org.momocrash.data.Player;

public class SolidManager {

    private final ObjectList<ISolidObject> ISolidObjects;

    public SolidManager() {
        ISolidObjects = new ObjectArrayList<>();
    }

    public void renderShape(ShapeRenderer renderer) {
        for (ISolidObject ISolidObject : ISolidObjects) {
            ISolidObject.drawShape(renderer);
        }
    }

    public void renderImage() {
        for (ISolidObject ISolidObject : ISolidObjects) {
            ISolidObject.drawImage();
        }
    }

    public void add(ISolidObject object) {
        this.ISolidObjects.add(object);
    }

    public ObjectList<ISolidObject> getAll() {
        return this.ISolidObjects;
    }

    public boolean collideTo(Player player, float nextX, float nextY) {

        for (ISolidObject object : getAll()) {

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
