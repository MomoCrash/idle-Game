package org.momocrash.object.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import org.momocrash.object.State;
import org.momocrash.object.menu.component.Button;
import org.momocrash.object.menu.component.Component;

public class Menu {

    private final ObjectList<Component> components = new ObjectArrayList<>();

    private final String title;
    private final int lineNumber;
    private final int cols;
    private final float x;
    private final float y;

    private State state = State.HIDDEN;

    public Menu(String title, float x, float y, int cols, int line) {

        this.title = title;
        this.lineNumber = line;
        this.cols = cols;
        this.x = x;
        this.y = y;
    }

    public void draw(ShapeRenderer renderer) {

        if (getState().equals(State.HIDDEN)) return;

        for (Component component : components) {
            component.draw(renderer);
        }

        renderer.setColor(Color.BLACK);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.rect(x, y, (cols * 64), -(lineNumber * 64));
        renderer.end();

    }

    public void addButton(float x, float y, int width, int height, String path, Runnable toRun) {

        components.add(new Button(this, x, y, width, height, path, toRun));

    }

    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }
    public float getY() {
        return y;
    }
    public float getX() {
        return x;
    }
    public ObjectList<Component> getComponents() {
        return components;
    }

}
