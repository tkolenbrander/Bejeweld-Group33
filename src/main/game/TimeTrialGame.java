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
 * @version 23/10/2015
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
	 * Timer to keep track of the time.
	 */
	private Timer timer;

	/**
	 * Time remaining.
	 */
	private int remainingTime;

	/**
	 * Creates a new game object, with a freshly initialised player and a new board.
	 * Initialises the timer to the time limit specified in the field TIMELIMIT.
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
	 * Initialises the timer to the time limit specified in the field TIMELIMIT.
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

	@SuppressWarnings("magicnumber")
	@Override
	public void start() {
		setInProgress(true);
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
		}, 1000, 1000);
	}

	@Override
	public void stop() {
		setInProgress(false);
		timer.cancel();
	}

	@Override
	public void reset() {
		timer.cancel();
		getPlayer().reset();
		getBoard().reset();
		timer = new Timer();
		remainingTime = TIMELIMIT;
	}

	@Override
	public void makeMove(Position one, Position two) throws MoveNotValidException {
		getBoard().resetNewPowerGems();
		super.makeMove(one, two);
		remainingTime += getBoard().getNewPowerGems() * TIME_PER_POWERGEM;
	}

	/**
	 * Checks if the move you want to make is allowed.
	 * 
	 * In this game, a move is allowed when the game is in progress and there still
	 * is time remaining.
	 * 
	 * @param one Position on the board of one selected gem
	 * @param two Position on the board of the other selected gem
	 * @return if the move is allowed
	 * @throws MoveNotValidException When the move is not allowed, because the cells 
	 * are not adjacent.
	 */
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

	/**
	 * Checks if the game is over. Also regenerates the board if there are no more moves left.
	 * 
	 * In this game, the game is over when there is no more time left.
	 * @return if the game is over.
	 */
	@Override
	public boolean isGameOver() {
		boardNeedsReset();
		if (remainingTime == 0) {
			GameViewController.getGVC().setGameOver();
			Logger.logInfo("Game over! Score was " + getPlayer().getScore());
			stop();
			return true;
		}
		return false;
	}

	@Override
	public void close() {
		super.close();
		timer = null;
		remainingTime = 0;
	}

	/**
	 * Checks if the board has any moves left and if not, resets the board.
	 */
	private void boardNeedsReset() {
		if (!getBoard().checkMoves()) {
			getBoard().reset();
		}
	}
}
