package org.momocrash;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.momocrash.data.Player;
import org.momocrash.data.PlayerData;
import org.momocrash.language.Language;
import org.momocrash.screens.GameScreen;

import java.util.UUID;

public class IdleMain extends Game {

	private static IdleMain instance;

	public final PlayerData playerData = new PlayerData();
	public final Player player = new Player(UUID.randomUUID().toString(), "KAMETOLANGUAGE", Language.ENGLISH, playerData);

	private SpriteBatch batch;
	private BitmapFont font;
	private GameScreen screen;

	public IdleMain() {
		instance = this;
	}

	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont(); // use libGDX's default Arial font
		screen = new GameScreen(this);
		this.setScreen(screen);
	}

	public void render() {
		super.render();
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public BitmapFont getFont() {
		return font;
	}

	@Override
	public GameScreen getScreen() {
		return screen;
	}

	public Player getPlayer() {
		return player;
	}

	public PlayerData getGameData() {
		return playerData;
	}

	public static IdleMain getInstance() {
		return IdleMain.instance;
	}

}
