package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * Class for a player. Currently only used for keeping track of his / her
 * score, but in light of possible future developments, it is good to have 
 * a good structure.
 * @author Bart van Oort
 *
 */
public class Player implements Score {

	private int score;

	private List<ScoreObserver> observers;
	private String message;
	private boolean changed;
	private final Object MUTEX = new Object();

	/**
	 * Creates a new player with 0 as a score.
	 */
	public Player() {
		this.observers = new ArrayList<>();
		score = 0;
	}

	/**
	 * Creates a new player with the given score.
	 */
	public Player(int newScore) {
		this.observers = new ArrayList<>();
		score = newScore;
	}

	/**
	 * Returns the score of the player.
	 * @return score of the player.
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Adds score to the player's score.
	 * @param add score to be added to the player's score.
	 */
	public void addScore(int add) {
		score += add;
	}

	/**
	 * Resets the player to its initial state, that is, score = 0..
	 */
	public void reset() {
		score = 0;
	}

	@Override
	public void register(Observer obj) {
		if(obj == null) throw new NullPointerException("Null Observer");
		synchronized (MUTEX) {
			if(!observers.contains(obj)) {
				observers.add((ScoreObserver) obj);
			};
		}
	}

	@Override
	public void unregister(Observer obj) {
		synchronized (MUTEX) {
			observers.remove(obj);
		}
	}

	@Override
	public void notifyObservers() {
		List<ScoreObserver> observersLocal = null;

		synchronized (MUTEX) {
			if (!changed) {
				return;
			}
			
			observersLocal = new ArrayList<>(this.observers);
			this.changed = false;
		}
		
		for (ScoreObserver obj : observersLocal) {
			obj.update();
		}

	}

	@Override
	public Object getUpdate(Observer obj) {
		return this.message;
	}

}
