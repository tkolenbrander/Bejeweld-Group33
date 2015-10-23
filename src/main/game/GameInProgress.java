package main.game;

import main.gui.GameViewController;

/**
 * Represents a game that is in progress.
 * @author Ruben
 *
 */
public abstract class GameInProgress implements GameState {

	/**
	 * Starts the game.
	 * Does nothing as every subclass is assumed to be in progress.
	 */
	@Override
	public void start() {
		
	}

	/**
	 * Stops the game.
	 * Game over.
	 */
	@Override
	public void stop() {
		Logger.logInfo("Game over! Score was " + getPlayer().getScore());
		GameViewController.getGVC().setGameOver();
	}

}
