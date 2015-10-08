package game;

import java.util.Observer;

public interface Score {

    public void register(Observer obj);
    public void unregister(Observer obj);
     
    public void notifyObservers();
     
    public Object getUpdate(Observer obj);
	
}
