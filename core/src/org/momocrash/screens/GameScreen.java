package org.momocrash.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import org.momocrash.IdleMain;
import org.momocrash.data.Player;
import org.momocrash.data.PlayerData;
import org.momocrash.language.Translation;
import org.momocrash.object.manager.InteractiveObjectManager;
import org.momocrash.object.manager.SolidManager;
import org.momocrash.object.manager.TextManager;
import org.momocrash.object.solid.Wall;
import org.momocrash.object.text.BasicText;
import org.momocrash.object.interactive.BasicBank;
import org.momocrash.object.text.TranslatedText;

public class GameScreen implements Screen {

    private final static int SPEED = 180;
    private final IdleMain game;

    private final OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;

    private final SolidManager solidManager;
    private final InteractiveObjectManager interactiveManager;
    private final TextManager textManager;

    private long lastSecond = TimeUtils.millis();
    private boolean antiSpam = true;
    private boolean pause = false;

    public GameScreen(final IdleMain game) {
        this.game = game;

        shapeRenderer  = new ShapeRenderer();
        camera = new OrthographicCamera();

        solidManager = new SolidManager();
        interactiveManager = new InteractiveObjectManager();
        textManager = new TextManager();

        camera.setToOrtho(false, 1080, 720);
        solidManager.add(new Wall(500, 500, 100, 100));
    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0.1f, 0.1f, 0.1f, 1);

        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        Player player = game.getPlayer();
        PlayerData playerData = game.getGameData();

        // Draw shape of objects
        solidManager.renderShape(shapeRenderer);
        interactiveManager.renderShape(shapeRenderer);

        // Pause the game
        if (pause) {
            pause();
            return;
        }

        // Input touch
        // Mouvement
        movePlayer(player);
        if (Gdx.input.isKeyPressed(Input.Keys.Y) && antiSpam()) {
            if (!interactiveManager.touch(player) && playerData.hasEnoughMoney(1000)) {
                interactiveManager.add(new BasicBank(player, 100, 10, player.getX(), player.getY()));
                playerData.withdrawMoney(1000);
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.U)) {
            interactiveManager.activate(player);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            pause = true;
        }

        // Time management
        if (TimeUtils.timeSinceMillis(lastSecond) >= 1000) {
            playerData.incrementTime();
            interactiveManager.useAll();
            antiSpam = false;
            lastSecond = TimeUtils.millis();
        }

        // Draw on screen
        game.getBatch().begin();

        // Animated Text
        textManager.updateAnimation();

        // Render Text
        textManager.renderText();

        // Static Text
        if (Gdx.input.isKeyPressed(Input.Keys.TAB)) {
            new BasicText(player.getName(), player.getX() - (5 + (player.getName().length() * 1.3f)),
                    player.getY() + 65).drawText();
        }

        new TranslatedText(player, Translation.MONEY,
                new String[]{"money," + playerData.getMoney(),
                        "money_pers," + playerData.getPerSecondMoney()},
                10, 710).drawText();

        new TranslatedText(player, Translation.ENERGY,
                new String[]{"energy," + playerData.getEnergy(),
                        "energy_pers," + playerData.getPerSecondEnergy()},
                10, 690).drawText();


        // Render Image
        solidManager.renderImage();
        interactiveManager.renderImage();

        game.getBatch().end();

        // Draw player
        player.draw(shapeRenderer);

    }

    // OTHER FUNC
    public void movePlayer(Player player) {

        float speed = (SPEED * Gdx.graphics.getDeltaTime());

        if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
            speed = (speed * 2.4f);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (!player.collideTo(player.getX(), player.getY() + speed, solidManager.getAll())) {
                player.forward(speed);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if (!player.collideTo(player.getX(), player.getY() - speed, solidManager.getAll())) {
                player.backward(speed);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (!player.collideTo(player.getX() - speed, player.getY(), solidManager.getAll())) {
                player.left(speed);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (!player.collideTo(player.getX() + speed, player.getY(), solidManager.getAll())) {
                player.right(speed);
            }
        }

    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

        Player player = game.getPlayer();

        game.getBatch().begin();
        new TranslatedText(player, Translation.PAUSE, 475, 400).drawText();
        game.getBatch().end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            pause = false;
        }

    }

    @Override
    public void resume() {

        pause = false;

    }

    @Override
    public void hide() {

        pause = true;

    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }

    public boolean antiSpam() {

        if (!this.antiSpam) {
            this.antiSpam = true;
            return false;
        } else {
            this.antiSpam = false;
        }

        return true;

    }

    // GETTER

    public TextManager getTextManager() {
        return textManager;
    }

}
