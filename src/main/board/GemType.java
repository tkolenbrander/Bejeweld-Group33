package main.board;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Enumeration of gem types. As of now, they are just the possible 6 colours, 
 * but using an enumeration like this does allow for easier implementation of 
 * special gem types in the future.
 * 
 * @author Bart van Oort
 *
 */
public enum GemType {
  
	BLUE, 
	RED, 
	GREEN, 
	YELLOW, 
	WHITE, 
	PURPLE,
	ORANGE;
	
	private static final List<GemType> GEMTYPES = 
	    Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = GEMTYPES.size();
	private static Random random = new Random(); //TODO: allow for seed.
	
	/**
	 * Chooses a random gem type and returns it.
	 * @return a random gem type
	 */
	public static GemType randomGem() {
	  return GEMTYPES.get(random.nextInt(SIZE));
	}
	
	/**
	 * Used to set random with a seed.
	 * @param randomWithSeed Random object, preferably with a seed.
	 */
	public void setRandom(Random randomWithSeed) {
	  random = randomWithSeed;
	}
}
