package main.game;

import main.board.Board;
import main.board.Position;
import main.exceptions.MoveNotValidException;
import main.gui.GameViewController;

/**
 * A class to represent the game when it's over.
 * The game has no functionality anymore in this state besides restarting.
 * 
 * @author Ruben Vrolijk
 */
public class GameOver implements GameState {

	private GameState oldGame;

	/**
	 * Creates a new GameOver object for the given game that just ended.
	 * 
	 * @param oldState	The game that is over.
	 */
	public GameOver(GameState oldState) {
		this.oldGame = oldState;
	}

	@Override
	public Player getPlayer() {
		return oldGame.getPlayer();
	}

	@Override
	public Board getBoard() {
		return oldGame.getBoard();
	}

	@Override
	public void start() {

	}

	@Override
	public void stop() {

	}

	/**
	 * Starts a new game of the same mode as the last game that ended.
	 */
	@Override
	public void reset() {
		if (oldGame instanceof TimeTrialGame) {
			GameViewController.show(new TimeTrialGame());
		} else {
			GameViewController.show(new ClassicGame());
		}
	}

	@Override
	public void swap(Position one, Position two) {

	}

	@Override
	public void makeMove(Position one, Position two) {

	}

	@Override
	public boolean moveAllowed(Position one, Position two) throws MoveNotValidException {
		return false;
	}

	@Override
	public void checkGameOver() {

	}

	@Override
	public void close() {
		oldGame.close();
		oldGame = null;
	}
}
