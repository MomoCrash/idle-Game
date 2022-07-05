package org.momocrash.object.manager;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import org.momocrash.Player;
import org.momocrash.object.interactive.InteractiveObject;

public class InteractiveObjectManager {

    private final ObjectList<InteractiveObject> interactiveObjects;

    public InteractiveObjectManager() {

        interactiveObjects = new ObjectArrayList<>();

    }

    public void renderShape(ShapeRenderer renderer) {

        for (InteractiveObject interactiveObject : interactiveObjects) {
            interactiveObject.drawShape(renderer);
        }

    }

    public void renderImage() {

        for (InteractiveObject interactiveObject : interactiveObjects) {
            interactiveObject.drawImage();
        }

    }

    public boolean touch(Player player) {
        for (InteractiveObject interactiveObject : interactiveObjects) {
            if (player.touch(interactiveObject)) {
                return true;
            }
        }
        return false;
    }

    public boolean activate(Player player) {

        for (InteractiveObject interactiveObject : interactiveObjects) {
            if (player.touch(interactiveObject) && !interactiveObject.enabled()) {
                interactiveObject.activate();
                return true;
            }
        }
        return false;
    }

    public void useAll() {

        for (InteractiveObject interactiveObject : interactiveObjects) {
            interactiveObject.use();
        }

    }

    public void add(InteractiveObject object) {
        this.interactiveObjects.add(object);
    }

}
