package org.momocrash.data;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.momocrash.language.Language;
import org.momocrash.object.interactive.InteractiveObject;
import org.momocrash.utils.PositionUtils;

import java.util.UUID;

public class Player {
    private final UUID uniqueId;
    private final Language lang;
    private final String name;
    private final PlayerData playerData;

    private float x;
    private float y;

    private final float width = 50;
    private final float height = 50;

    public Player(String id, String name, Language lang, PlayerData data) {

        this.name = name;
        this.uniqueId = UUID.fromString(id);
        this.lang = lang;
        this.playerData = data;

    }

    public void forward(float pixel) {

        this.y += pixel;

    }

    public void backward(float pixel) {

        this.y -= pixel;

    }

    public void right(float pixel) {

        this.x += pixel;

    }

    public void left(float pixel) {

        this.x -= pixel;

    }

    public void draw(ShapeRenderer renderer) {

        renderer.setColor(Color.WHITE);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.rect(this.x, this.y, width, height);
        renderer.end();

    }

    public boolean touch(InteractiveObject object) {
        // [0] = x ; [1] = y ; [2] = xMax ; [3] = yMAx
        int[] bounds = object.bounds();

        return PositionUtils.objectIsInsideOther(getX(), getY(), getX() + width, getY() + getHeight(),
                bounds[0], bounds[1], bounds[2], bounds[3]);

    }

    public String getName() {
        return name;
    }
    public UUID getUniqueId() {
        return uniqueId;
    }
    public PlayerData getPlayerData() {
        return playerData;
    }
    public Language getLang() {
        return lang;
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public float getHeight() {
        return height;
    }
    public float getWidth() {
        return width;
    }

}
