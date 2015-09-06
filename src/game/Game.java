package game;

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
   */
  public void makeMove(int x1, int y1, int x2, int y2) {
    if (inProgress) {
      if (x1 - x2 == -1 || x1 - x2 == 1 || y1 - y2 == -1 || y1 - y2 == 1) {
        board.swap(x1, y1, x2, y2);
        //check if the board has any chains
        if (board.hasChain()){
        	
        }
      //if no new chains, then swap back using board.swap(x2, y2, x1, y1);
        else{
        	board.swap(x2, y2, x1, y1);
        }
        //player.addScore(score);
        //check if there are any possible moves left.
      }
      else {
        //move is not allowed, what now? Wanna play throw and catch with custom exceptions?
      }
    }
  }
}
