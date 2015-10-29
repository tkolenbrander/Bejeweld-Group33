package main.game;

import main.board.Board;
import main.board.Position;
import main.exceptions.MoveNotValidException;

/**
 * Implementation for a classic game, where the game is over when there
 * 	are no more moves left on the board.
 * 
 * @author Bart van Oort
 */
public class ClassicGame extends Game {

	/**
	 * Creates a new ClassicGame-object, with a freshly initialized player and 
	 * 	a new board.
	 * The game is paused at the start.
	 */
	public ClassicGame() {
		super();
	}

	/**
	 * Creates a new ClassicGame object, with the given board and player.
	 * This constructor is used for loading the game.
	 * The game is paused at the start.
	 * 
	 * @param newBoard Board to be loaded into the game.
	 * @param newPlayer Player to be loaded into the game.
	 */
	public ClassicGame(Board newBoard, Player newPlayer) {
		super(newBoard, newPlayer);
	}

	@Override
	public void reset() {
		getPlayer().reset();
		resetBoard();
	}

	@Override
	public boolean moveAllowed(Position one, Position two) throws MoveNotValidException {
		Logger.logInfo("Attempting to swap gem at (" + one.getX() + "," + one.getY() + ") "
				+ "with (" + two.getX() + "," + two.getY() + ")");

		if (one.isAdjacentTo(two)) {
			return true;
		}
		else {
			throw new MoveNotValidException("Cells not adjacent");
		}
	}

	@Override
	public void checkGameOver() {
		if (!(getBoard().checkMoves())) {
			stop();
		}
	}
}
