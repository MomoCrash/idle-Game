package org.momocrash.handlers;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import org.momocrash.data.Player;
import org.momocrash.object.State;
import org.momocrash.object.menu.Menu;
import org.momocrash.object.menu.component.Button;
import org.momocrash.object.menu.component.Component;
import org.momocrash.utils.PositionUtils;

import java.util.Map;

public class MenuHandler {

    private final Object2ObjectMap<String, Menu> menus = new Object2ObjectArrayMap<>();

    public MenuHandler(Player player) {
        Menu menu = this.createMenu("constructor", "Constuctor Menu", 5, 690, 5, 7);
        menu.addButton(5, 10, 50, 50, "wall.png", new Runnable() {
            @Override
            public void run() {
                System.out.println("Button Script");
            }
        });

    }

    public void renderMenu(ShapeRenderer renderer) {

        for (Menu value : menus.values()) {
            value.draw(renderer);
        }

    }

    public Menu createMenu(String id, String name, float x, float y, int cols, int lines) {
        Menu menu = new Menu(name, x, y, cols, lines);
        menus.put(id, menu);
        return menu;

    }

    public void toggleDisplay(String id) {

        Menu menu = menus.get(id);
        if (menu != null) {
            if (menu.getState().equals(State.DISPLAYED)) {
                menu.setState(State.HIDDEN);
            } else {
                menu.setState(State.DISPLAYED);
            }
        }

    }

    public Component handleClick(float x, float y) {

        for (Menu value : menus.values()) {
            for (Component component : value.getComponents()) {

                System.out.println(x + " " + y);
                System.out.println(component.getX() + " / " + (component.getX() + component.getWidth()) + " / " + component.getY() + " / " + (component.getY() - component.getHeight()));

                if (PositionUtils.pointIsInsideObject(x, y, component.getX(), (component.getY()  - component.getHeight()),
                        (component.getX() + component.getWidth()), component.getY())) {

                    System.out.println("Return");
                    return component;

                }

            }
        }
        return null;

    }

}
