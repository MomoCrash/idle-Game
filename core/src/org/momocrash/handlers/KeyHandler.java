package org.momocrash.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import org.momocrash.IdleMain;
import org.momocrash.data.Player;
import org.momocrash.object.solid.SolidManager;

public class KeyHandler implements InputProcessor {

    private final static int SPEED = 180;

    @Override
    public boolean keyDown(int keycode) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            if (IdleMain.getInstance().paused()) {
                IdleMain.getInstance().play();
            } else {
                IdleMain.getInstance().pause();
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (IdleMain.getInstance().paused()) {
            IdleMain.getInstance().play();
        }

        if (pointer <= 0) return false;

        if (button == Input.Buttons.LEFT) {
            IdleMain.getInstance().getInteractiveManager().activate(IdleMain.getInstance().getPlayer());
            return true;
        } else if (button == Input.Buttons.RIGHT) {
            IdleMain.getInstance().getScreen().
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public void move(Player player) {

        float speed = (SPEED * Gdx.graphics.getDeltaTime());
        final SolidManager solidManager = IdleMain.getInstance().getSolidManager();

        if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
            speed = (speed * 2.4f);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            if (!solidManager.collideTo(player, player.getX(), player.getY() + speed)) {
                player.forward(speed);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            if (!solidManager.collideTo(player, player.getX(), player.getY() - speed)) {
                player.backward(speed);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (!solidManager.collideTo(player, player.getX() - speed, player.getY())) {
                player.left(speed);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            if (!solidManager.collideTo(player, player.getX() + speed, player.getY())) {
                player.right(speed);
            }
        }

    }

}
