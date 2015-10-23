package main.game;

import main.board.Board;
import main.board.Position;
import main.exceptions.MoveNotValidException;

/**
 * Represents the state of a game.
 * @author Ruben
 *
 */
public interface GameState {
	
	/**
	 * Returns the player.
	 * @return The player
	 */
	Player getPlayer();
	
	/**
	 * Returns the board.
	 * @return The board
	 */
	Board getBoard();
	
	/**
	 * Starts the game.
	 */
	void start();
	
	/**
	 * Stops the game.
	 */
	void stop();
	
	/**
	 * Resets the game to how it was when it was just initialised.
	 */
	void reset();
	
	/**
	 * Swaps two gems.
	 * @param one The first gem
	 * @param two The second gem
	 */
	void swap(Position one, Position two);
	
	/**
	 * Lets the player make a move, as long as moveAllowed returns true.
	 * 
	 * This means this method will swap the two cells, see if that creates at least
	 * one chain and if so, removes the created chains, makes the remaining cells
	 * fall down and fills the empty cell.
	 * 
	 * @param one Position on the board of one selected gem
	 * @param two Position on the board of the other selected gem
	 * @throws MoveNotValidException When the move is not allowed, because the cells 
	 * 		are not adjacent or because the move does not create a chain.
	 * be executed. 
	 */
	void makeMove(Position one, Position two);
	
	/**
	 * Checks if the move you want to make is allowed.
	 * 
	 * @param one Position on the board of one selected gem
	 * @param two Position on the board of the other selected gem
	 * @return if the move is allowed
	 * @throws MoveNotValidException When the move is not allowed, because the cells 
	 * are not adjacent.
	 */
	boolean moveAllowed(Position one, Position two) throws MoveNotValidException;
	
	/**
	 * Checks if the game is over.
	 */
	void checkGameOver();
	
	/**
	 * Closes the game.
	 */
	void close();
	
}
