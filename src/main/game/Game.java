package main.game;

import java.util.ArrayList;
import java.util.List;

import main.exceptions.MoveNotValidException;
import main.gui.TimelineController;
import main.board.Board;
import main.board.Change;
import main.board.Position;

/**
 * Game class. Governs the entire game. In terms of the backend, this is the top of the dome.
 * Straight from the top of my dome. Freestyler
 * https://www.youtube.com/watch?v=xWHBogvoHqM
 * 
 * @author Bart van Oort
 *
 */
public class Game {

	private Player player;
	private Board board;
	private boolean inProgress;

	/**
	 * Creates a new game object, with a freshly initialised player and a new board.
	 * The game is paused at the start.
	 */
	public Game() {
		player = new Player();
		board = new Board();
		inProgress = false;
	}

	/**
	 * Creates a new game object, with the given board and player.
	 * This constructor is used for loading the game.
	 * The game is paused at the start.
	 * @param newBoard Board to be loaded into the game.
	 * @param newPlayer Player to be loaded into the game.
	 */
	public Game(Board newBoard, Player newPlayer) {
		player = newPlayer;
		board = newBoard;
		inProgress = false;
	}

	/**
	 * Returns the player.
	 * @return The player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Returns the board.
	 * @return The board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Starts the game.
	 */
	public void start() {
		inProgress = true;
		//start any timers if there are any
	}

	/**
	 * Pauses the game.
	 */
	public void stop() {
		inProgress = false;
		//stop any timers if there are any.
	}

	/**
	 * Returns true if the game is in progress.
	 * @return True if the game is in progress.
	 */
	public boolean inProgress() {
		return inProgress;
	}

	/**
	 * Resets the game to how it was when it was just initialised.
	 * Ahhh, I remember it like it was yesterday. He was just such a cute little game.
	 * Wait, it was yesterday...
	 */
	public void reset() {
		player.reset();
		board.reset();
		//reset any timers if there are any.
	}

	/**
	 * Swaps two gems.
	 * @param one The first gem
	 * @param two The second gem
	 */
	public void swap(Position one, Position two) {
		board.swap(one.getX(), one.getY(), two.getX(), two.getY());
		TimelineController.swap(one, two);
	}

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
	public void makeMove(Position one, Position two) 
			throws MoveNotValidException {
		List<List<Change<Position>>> changes = new ArrayList<List<Change<Position>>>();
		TimelineController.clearSwapList();
		if (moveAllowed(one, two)) {
			swap(one, two);
			if (board.hasChain()) { // check if the board has any chains
				int bonus = 0;
				Logger.logInfo("Move was successful");

				do {
					player.addScore(board.calculateScore(bonus));        		
					changes.add(board.removeChains()); // remove them and properly refill the board
					changes.add(board.falldown());
					changes.add(board.fillEmptyCells());
					bonus++;
				}	while (board.hasChain());
			}
			else {  //if no new chains, then swap back using board.swap(x2, y2, x1, y1);
				swap(two, one);
				TimelineController.setList(changes);
				throw new MoveNotValidException("Move doesn't make a chain");
			}

			isGameOver();
		}
		TimelineController.setList(changes);
	}

	/**
	 * Checks if the move you want to make is allowed, i.e. 
	 * if the game is in progress and if the two cells are indeed adjacent.
	 * 
	 * @param one Position on the board of one selected gem
	 * @param two Position on the board of the other selected gem
	 * @return if the move is allowed
	 * @throws MoveNotValidException When the move is not allowed, because the cells 
	 * are not adjacent.
	 */
	public boolean moveAllowed(Position one, Position two) throws MoveNotValidException {
		if (inProgress) {
			Logger.logInfo("Attempting to swap gem at (" + one.getX() + "," + one.getY() + ") "
					+ "with (" + two.getX() + "," + two.getY() + ")");
			if (one.isAdjacentTo(two)) {
				return true;
			}
			else {
				throw new MoveNotValidException("Cells not adjacent");
			}
		}
		return false;
	}

	/**
	 * Checks if the game is over. A game is over when there are no more moves left.
	 * @return if the game is over.
	 */
	public boolean isGameOver() {
		if (!(board.checkMoves())) {
			Logger.logInfo("Game over! Score was " + player.getScore());
			stop();
			return true;
		}
		return false;
	}
}
