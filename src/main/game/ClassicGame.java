package main.game;

import main.board.Board;
import main.board.Position;
import main.exceptions.MoveNotValidException;

/**
 * Implementation for a classic game, where the game is over when there
 * are no more moves left on the board.
 * 
 * @author Bart
 *
 */
public class ClassicGame extends Game {
  
  /**
   * Creates a new ClassicGame-object, with a freshly initialised player and 
   * a new board.
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
  
  /**
   * Starts the game.
   */
  public void start() {
    setInProgress(true);
  }

  /**
   * Pauses the game.
   */
  public void stop() {
    setInProgress(false);
  }
  
  /**
   * Resets the game to how it was when it was just initialised.
   * Ahhh, I remember it like it was yesterday. He was just such a cute little game.
   * Wait, it was yesterday...
   */
  public void reset() {
    getPlayer().reset();
    resetBoard();
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
    if (inProgress()) {
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
    if (!(getBoard().checkMoves())) {
      Logger.logInfo("Game over! Score was " + getPlayer().getScore());
      stop();
      return true;
    }
    return false;
  }
}
