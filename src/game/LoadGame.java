package game;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import board.Board;
import board.Cell;
import board.GemType;
import board.RegularGem;

/**
 * Utility class for loading the game from a saved game.
 * @author Ruben
 *
 */
public final class LoadGame {
  
    private static final Object SYNC = new Object();
	  private static final String PATH = "savefile.bjw";
 
	  /**
	   * Since this is a utility class, we don't want this instantiated.
	   */
	  private LoadGame() {
	    throw new AssertionError("Instantiating utility class...");
	  }
	  
	  /**
	   * Loads the game from the last saved game.
	   * @throws FileNotFoundException Which is handled in the GUI.
	   * @return a new Game with the saved score and Board.
	   */
	  public static Game load() throws FileNotFoundException {
	    synchronized (SYNC) {
	      Cell[][] cells = new Cell[Board.BOARDSIZE][Board.BOARDSIZE];
	      Scanner in = null;
	      try {
	        in = new Scanner(new FileReader(PATH));
	        Player player = new Player(in.nextInt());
		  
	        for (int y = 0; y < Board.BOARDSIZE; y++) {
	          for (int x = 0; x < Board.BOARDSIZE; x++) {
	            GemType type = GemType.valueOf(in.next());
	            cells[y][x] = new Cell(new RegularGem(type));
	          }
	        }
	        return new Game(new Board(cells), player);
	      }
	      finally {
	        in.close();
	      }
	    }
	  }
}
