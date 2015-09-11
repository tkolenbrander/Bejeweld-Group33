package game;

import exceptions.MoveNotValidException;
import board.Board;
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
    inProgress = false;
    player.reset();
    board.reset();
    //reset any timers if there are any.
  }
  
  /**
   * Lets the player make a move, as long as the game is in progress.
   * Checks if the move you want to make is allowed, i.e. if the two cells are indeed
   * adjacent.
   * @param x1 x-coordinate of cell 1
   * @param y1 y-coordinate of cell 1
   * @param x2 x-coordinate of cell 2
   * @param y2 y-coordinate of cell 2
   * @throws MoveNotValidException When the move is not allowed, because the cells 
   * are not adjacent or because the move does not create a chain.
   */
  public void makeMove(int x1, int y1, int x2, int y2) throws MoveNotValidException {
    if (inProgress) {
      if (board.isAdjacent(x1, y1, x2, y2)) {
        board.swap(x1, y1, x2, y2);
        //check if the board has any chains and remove them and properly refill the board
        if (board.hasChain()) {
        	int bonus = 0;
        	while (board.hasChain()){
            player.addScore(board.calculateScore(bonus));        		
        	board.removeChains();
        	board.falldown();
        	board.fillEmptyCells();
        	bonus++;
        	}	
        	System.out.println("Succesful move!");
        }
        else {  //if no new chains, then swap back using board.swap(x2, y2, x1, y1);
        	board.swap(x2, y2, x1, y1);
        	System.out.println("Move doesn't make a chain");
        	throw new MoveNotValidException("Move doesn't make a chain");
        }
        
        if (!(board.checkMoves())) {  // No more moves left means game over.
          System.out.println("Game over");
          stop();
        }
      }
      else {
        System.out.println("Cells not adjacent");
        throw new MoveNotValidException("Cells not adjacent");
      }
    }
  }
}
