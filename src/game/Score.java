package game;

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
    public void register(Observer obj);
    
    /**
     * Unregisters an observer.
     * @param obj The observer.
     */
    public void unregister(Observer obj);
    
    /**
     * Notifies all observers.
     */
    public void notifyObservers();
    
    /**
     * Gets updates from the observed object.
     * @param obj The observer.
     * @return The update.
     */
    public Object getUpdate(Observer obj);
	
}
