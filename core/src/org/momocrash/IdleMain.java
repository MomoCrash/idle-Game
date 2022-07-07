package org.momocrash;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.momocrash.data.Player;
import org.momocrash.data.PlayerData;
import org.momocrash.language.Language;
import org.momocrash.object.State;
import org.momocrash.object.interactive.InteractiveObjectManager;
import org.momocrash.object.solid.SolidManager;
import org.momocrash.object.text.TextHandler;
import org.momocrash.screens.GameScreen;

import java.util.UUID;

public class IdleMain extends Game {

	private static IdleMain instance;

	public final Player player = new Player(UUID.randomUUID().toString(), "KEVIN", Language.FRENCH, new PlayerData());

	private SpriteBatch batch;
	private BitmapFont font;
	private GameScreen screen;

	private State gameState = State.PLAY;

	public IdleMain() {
		instance = this;
	}

	public void create() {

		player.getLang().loadLanguage();

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

	// Main game logic accessors
	public static IdleMain getInstance() {
		return IdleMain.instance;
	}
	public SpriteBatch getBatch() {
		return batch;
	}
	public BitmapFont getFont() {
		return font;
	}
	public GameScreen getScreen() {
		return screen;
	}
	public Player getPlayer() {
		return player;
	}

	// Manager accessors
	public InteractiveObjectManager getInteractiveManager() {
		return screen.getInteractiveManager();
	}
	public SolidManager getSolidManager() {
		return screen.getSolidManager();
	}
	public TextHandler getTextManager() {
		return screen.getTextManager();
	}

	// Game state
	public boolean paused() { return gameState.equals(State.PAUSED); }
	public void pause() { gameState = State.PAUSED; }
	public void play() {
		this.gameState = State.PLAY;
	}
}
