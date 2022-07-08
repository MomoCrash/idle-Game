package org.momocrash.object.interactive;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import org.momocrash.data.Player;

public class InteractiveObjectManager {

    private final ObjectList<InteractiveObject> IInteractiveObjects;

    public InteractiveObjectManager() {

        IInteractiveObjects = new ObjectArrayList<>();

    }

    public void renderShape(ShapeRenderer renderer) {

        for (InteractiveObject IInteractiveObject : IInteractiveObjects) {
            IInteractiveObject.drawShape(renderer);
        }

    }

    public void renderImage() {

        for (InteractiveObject IInteractiveObject : IInteractiveObjects) {
            IInteractiveObject.drawImage();
        }

    }

    public boolean touch(Player player) {
        for (InteractiveObject IInteractiveObject : IInteractiveObjects) {
            if (player.touch(IInteractiveObject)) {
                return true;
            }
        }
        return false;
    }

    public boolean activate(Player player) {

        for (InteractiveObject IInteractiveObject : IInteractiveObjects) {
            if (player.touch(IInteractiveObject) && !IInteractiveObject.enabled()) {
                IInteractiveObject.activate();
                return true;
            }
        }
        return false;
    }

    public void useAll() {

        for (InteractiveObject IInteractiveObject : IInteractiveObjects) {
            IInteractiveObject.use();
        }

    }

    public void add(InteractiveObject object) {
        this.IInteractiveObjects.add(object);
    }

}
