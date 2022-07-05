package org.momocrash;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.momocrash.data.GameData;
import org.momocrash.language.Language;
import org.momocrash.screens.GameScreen;

import java.util.UUID;

public class IdleMain extends Game {

	private static IdleMain instance;

	public final GameData gameData = new GameData();
	public final Player player = new Player(UUID.randomUUID().toString(), "KAMETOLANGUAGE", Language.ENGLISH, gameData);

	private SpriteBatch batch;
	private BitmapFont font;

	public IdleMain() {
		instance = this;
	}

	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont(); // use libGDX's default Arial font
		this.setScreen(new GameScreen(this));
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

	public Player getPlayer() {
		return player;
	}

	public GameData getGameData() {
		return gameData;
	}

	public static IdleMain getInstance() {
		return IdleMain.instance;
	}

}
