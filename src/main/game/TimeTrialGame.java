package main.game;

import java.util.Timer;
import java.util.TimerTask;

import main.board.Board;
import main.board.Position;
import main.exceptions.MoveNotValidException;

public class TimeTrialGame extends Game {
  
  /**
   * Time limit in seconds.
   */
  private static int timelimit = 10;
  
  /**
   * Timer to keep track of the time.
   */
  private Timer timer;
  
  /**
   * Time remaining.
   */
  private int remainingTime;
  
  public TimeTrialGame() {
    super();
    timer = new Timer();
    remainingTime = timelimit;
  }
  
  public TimeTrialGame(Board newBoard, Player newPlayer) {
    super(newBoard, newPlayer);
    timer = new Timer();
    remainingTime = timelimit;
  }

  @Override
  public void start() {
    setInProgress(true);
    timer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        remainingTime--;
        if (remainingTime == 0) {
          stop();
        }
        System.out.println(remainingTime);
      }
    }, 0, 1000);
  }

  @Override
  public void stop() {
    setInProgress(false);
    timer.cancel();
  }

  @Override
  public void reset() {
    getPlayer().reset();
    getBoard().reset();
    timer.cancel();
  }

  @Override
  public boolean moveAllowed(Position one, Position two) throws MoveNotValidException {
    if (inProgress() && remainingTime > 0) {
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

  @Override
  public boolean isGameOver() {
    if (remainingTime == 0 || !(getBoard().checkMoves())) {
      Logger.logInfo("Game over! Score was " + getPlayer().getScore());
      stop();
      return true;
    }
    return false;
  }

}
