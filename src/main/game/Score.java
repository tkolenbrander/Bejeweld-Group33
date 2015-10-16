package main.game;

/**
 * Interface for the Score of the game.
 * 
 * @author Steven Meijer
 */
public interface Score {

	/**
	 * Registers an observer.
	 * @param obj The observer
	 */
	void register(Observer obj);

	/**
	 * Unregisters an observer.
	 * @param obj The observer.
	 */
	void unregister(Observer obj);

	/**
	 * Notifies all observers.
	 */
	void notifyObservers();

	/**
	 * Gets updates from the observed object.
	 * @param obj The observer.
	 * @return The update.
	 */
	Object getUpdate(Observer obj);

}
