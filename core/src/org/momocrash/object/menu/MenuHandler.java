package org.momocrash.object.menu;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;

public class MenuHandler {

    private ObjectList<Menu> menus = new ObjectArrayList<>();

    public MenuHandler() { }

    public void renderMenu(ShapeRenderer renderer) {

        for (Menu menu : menus) {
            menu.draw(renderer);
        }

    }

    public void createMenu(String name, float x, float y, int cols, int lines) {

        menus.add(new Menu(name, x, y, cols, lines));

    }

}
