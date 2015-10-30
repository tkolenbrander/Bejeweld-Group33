package main.game;

import java.util.ArrayList;
import java.util.List;

import main.exceptions.MoveNotValidException;
import main.gui.TimelineController;
import main.board.Board;
import main.board.BoardFactory;
import main.board.Change;
import main.board.Position;

/**
 * Game class. Governs the entire game. In terms of the back-end, this is the top of the dome.
 * Straight from the top of my dome. Freestyler
 * https://www.youtube.com/watch?v=xWHBogvoHqM
 * 
 * @author Bart van Oort
 */
public abstract class Game extends GameInProgress {

	private Player player;
	private Board board;
	private static BoardFactory boardFactory;

	/**
	 * Creates a new game object, with a freshly initialized player and a new board.
	 * The game is paused at the start.
	 */
	public Game() {
		player = new Player();
		boardFactory = new BoardFactory();
		board = boardFactory.generateBoard();
	}

	/**
	 * Creates a new game object, with the given board and player.
	 * This constructor is used for loading the game.
	 * The game is paused at the start.
	 * 
	 * @param newBoard Board to be loaded into the game.
	 * @param newPlayer Player to be loaded into the game.
	 */
	public Game(Board newBoard, Player newPlayer) {
		player = newPlayer;
		board = newBoard;
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public Board getBoard() {
		return board;
	}

	/**
	 * Resets the board of game.
	 */
	public void resetBoard() {
		board = boardFactory.generateBoard();
	}

	/**
	 * @return The boardFactory that is used.
	 */	
	public BoardFactory getBoardFactory() {
		return boardFactory;
	}

	@Override
	public void swap(Position one, Position two) {
		board.swap(one.getX(), one.getY(), two.getX(), two.getY());
		TimelineController.swap(one, two);
	}

	@Override
	public void makeMove(Position one, Position two) throws MoveNotValidException {
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
		}
		TimelineController.setList(changes);
	}

	@Override
	public void close() {
		player = null;
		board = null;
	}
}
