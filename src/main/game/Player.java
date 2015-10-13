package main.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for a player. Currently only used for keeping track of his / her
 * score, but in light of possible future developments, it is good to have 
 * a good structure.
 * 
 * @author Bart van Oort
 */
public class Player implements Score {

	private int score;

	private List<Observer> observers;
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
	 * @param newScore The new score of the player.
	 */
	public Player(final int newScore) {
		this.observers = new ArrayList<>();
		score = newScore;
	}

	/**
	 * Returns the score of the player.
	 * @return score of the player.
	 */
	public final int getScore() {
		return score;
	}

	/**
	 * Adds score to the player's score.
	 * @param add score to be added to the player's score.
	 */
	public final void addScore(final int add) {
		this.score += add;
		this.changed = true;
		notifyObservers();
	}

	/**
	 * Resets the player to its initial state, that is, score = 0..
	 */
	public final void reset() {
		score = 0;
	}

	/**
	 * Registers this object to an observer.
	 * @param obj The observer.
	 */
	@Override
	public final void register(final Observer obj) {
		if (obj == null) { 
			throw new NullPointerException("Null Observer");
		}
		
		synchronized (MUTEX) {
			if (!observers.contains(obj)) {
				observers.add((Observer) obj);
			}
		}
	}

	/**
	 * Unregisters this object from an observer.
	 * @param obj The observer
	 */
	@Override
	public final void unregister(final Observer obj) {
		synchronized (MUTEX) {
			observers.remove(obj);
		}
	}

	/**
	 * Notifies all observers from the observers ArrayList
	 * 	if the observed object has changed.
	 */
	@Override
	public final void notifyObservers() {
		List<Observer> observersLocal = null;

		synchronized (MUTEX) {
			if (!changed) {
				return;
			}
			
			observersLocal = new ArrayList<>(this.observers);
			this.changed = false;
		}
		
		for (Observer obj : observersLocal) {
			obj.update();
		}

	}
	
	/**
	 * Gets the updated score from the player.
	 */
	@Override
	public final Object getUpdate(final Observer obj) {
		return score;
	}

}
