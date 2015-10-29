package main.game;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import main.board.Board;
import main.board.Position;
import main.exceptions.MoveNotValidException;
import main.gui.GameViewController;

/**
 * Implementation of a Time Trial game. In this game mode, the player gets a
 * certain amount of time to get a score as high as possible. 
 * 
 * @author Bart van Oort
 * @since 23/10/2015
 */
public class TimeTrialGame extends Game {

	/**
	 * Time limit in seconds.
	 */
	private static final int TIMELIMIT = 120;

	/**
	 * Time added per newly generated powergem.
	 */
	private static final int TIME_PER_POWERGEM = 30;
	
	/**
	 * The time delay.
	 */
	private static final int DELAY = 1000;

	/**
	 * Timer to keep track of the time.
	 */
	private Timer timer;

	/**
	 * Time remaining.
	 */
	private int remainingTime;

	/**
	 * Creates a new game object, with a freshly initialized player and a new board.
	 * Initializes the timer to the time limit specified in the field TIMELIMIT.
	 * The game is paused at the start.
	 */
	public TimeTrialGame() {
		super();
		timer = new Timer();
		remainingTime = TIMELIMIT;
	}

	/**
	 * Creates a new game object, with the given board and player.
	 * 
	 * Initializes the timer to the time limit specified in the field TIMELIMIT.
	 * The game is paused at the start.
	 * 
	 * @param newBoard Board to be loaded into the game.
	 * @param newPlayer Player to be loaded into the game.
	 */
	public TimeTrialGame(Board newBoard, Player newPlayer) {
		super(newBoard, newPlayer);
		timer = new Timer();
		remainingTime = TIMELIMIT;
	}

	@Override
	public void start() {
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						remainingTime--;
						if (remainingTime == 0) {
							stop();
						}
						GameViewController.getGVC().setTimer(remainingTime);
					}
				});
			}
		}, DELAY, DELAY);
		super.start();
	}

	@Override
	public void stop() {
		timer.cancel();
		super.stop();
	}

	@Override
	public void reset() {
		timer.cancel();
		getPlayer().reset();
		resetBoard();
		timer = new Timer();
		remainingTime = TIMELIMIT;
	}

	@Override
	public void makeMove(Position one, Position two) throws MoveNotValidException {
		getBoard().resetNewPowerGems();
		super.makeMove(one, two);
		remainingTime += getBoard().getNewPowerGems() * TIME_PER_POWERGEM;
	}

	@Override
	public boolean moveAllowed(Position one, Position two) throws MoveNotValidException {
		if (remainingTime > 0) {
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
	 * Checks if the game is over. Also regenerates the board if there are no more moves left.
	 * 
	 * In this game, the game is over when there is no more time left.
	 */
	@Override
	public void checkGameOver() {
		boardNeedsReset();
		if (remainingTime == 0) {
			stop();
		}
	}

	@Override
	public void close() {
		super.close();
		timer.cancel();
		timer = null;
		remainingTime = 0;
	}

	/**
	 * Checks if the board has any moves left and if not, resets the board.
	 */
	private void boardNeedsReset() {
		if (!getBoard().checkMoves()) {
			resetBoard();
			GameViewController.getGVC().refreshBoard("No more moves left, Board refreshed!");
		}
	}
	
	/**
	 * @return TIMELIMIT
	 */
	public static int getTimeLimit() {
		return TIMELIMIT;

	}
}
