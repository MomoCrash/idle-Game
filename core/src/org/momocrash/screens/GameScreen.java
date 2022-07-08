package org.momocrash.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import org.momocrash.IdleMain;
import org.momocrash.data.Player;
import org.momocrash.data.PlayerData;
import org.momocrash.handlers.KeyHandler;
import org.momocrash.language.Translation;
import org.momocrash.object.interactive.InteractiveObjectManager;
import org.momocrash.handlers.MenuHandler;
import org.momocrash.object.solid.SolidManager;
import org.momocrash.handlers.TextHandler;
import org.momocrash.object.solid.Wall;
import org.momocrash.object.text.BasicText;
import org.momocrash.object.interactive.BasicBank;
import org.momocrash.object.text.TranslatedText;

public class GameScreen implements Screen {

    private final IdleMain game;

    private final OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;

    private final SolidManager solidManager;
    private final InteractiveObjectManager interactiveManager;
    private final TextHandler textHandler;
    private final MenuHandler menuHandler;
    private final KeyHandler keyHandler;

    private long lastSecond = TimeUtils.millis();
    private boolean antiSpam = true;

    public GameScreen(final IdleMain game) {
        this.game = game;

        shapeRenderer  = new ShapeRenderer();
        camera = new OrthographicCamera();

        solidManager = new SolidManager();
        interactiveManager = new InteractiveObjectManager();
        textHandler = new TextHandler();
        menuHandler = new MenuHandler(game.getPlayer());
        keyHandler = new KeyHandler();

        camera.setToOrtho(false, 1080, 720);
        solidManager.add(new Wall(500, 500, 100, 100));
        Gdx.input.setInputProcessor(keyHandler);
    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(Color.SKY);

        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        Player player = game.getPlayer();
        PlayerData playerData = player.getPlayerData();

        // Draw shape of objects
        solidManager.renderShape(shapeRenderer);
        interactiveManager.renderShape(shapeRenderer);

        // Pause the game
        if (IdleMain.getInstance().paused()) {
            pause();
            return;
        }

        // Input touch
        // Mouvement
        keyHandler.move(player);
        if (Gdx.input.isKeyPressed(Input.Keys.Q) && antiSpam()) {
            if (!interactiveManager.touch(player) && playerData.hasEnoughMoney(1000)) {
                interactiveManager.add(new BasicBank(player, 1000, 5, player.getX(), player.getY()));
                playerData.withdrawMoney(1000);
            }
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

        // Render Image
        solidManager.renderImage();
        interactiveManager.renderImage();

        // Animated Text
        textHandler.updateAnimation();

        // Render Text
        textHandler.renderText();

        // Static Text
        if (Gdx.input.isKeyPressed(Input.Keys.TAB)) {
            new BasicText(player.getName(), player.getX() + (1 + (player.getName().length() * 1.3f)),
                    player.getY() + 65).drawText();
            new BasicText("FPS=" + Gdx.graphics.getFramesPerSecond(), 10, 20).drawText();
        }

        new TranslatedText(player, Translation.MONEY,
                new String[]{"money," + playerData.getMoney(),
                        "money_pers," + playerData.getPerSecondMoney()},
                10, 710).drawText();

        new TranslatedText(player, Translation.ENERGY,
                new String[]{"energy," + playerData.getEnergy(),
                        "energy_pers," + playerData.getPerSecondEnergy()},
                10, 690).drawText();

        game.getBatch().end();

        menuHandler.renderMenu(shapeRenderer);

        // Draw player
        player.draw(shapeRenderer);

    }

    // OTHER FUNC

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
        new TranslatedText(player, Translation.PAUSE, 400, 400).drawText();
        game.getBatch().end();

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

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

    public TextHandler getTextManager() {
        return textHandler;
    }
    public InteractiveObjectManager getInteractiveManager() {
        return interactiveManager;
    }
    public SolidManager getSolidManager() {
        return solidManager;
    }
    public KeyHandler getKeyHandler() {
        return keyHandler;
    }
    public MenuHandler getMenuHandler() {
        return menuHandler;
    }
}
