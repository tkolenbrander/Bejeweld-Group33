package game;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import board.Board;
import board.Cell;
import board.Gem;
import board.GemType;

/**
 * Utility class for loading the game from a saved game.
 * @author Ruben
 *
 */
public final class LoadGame {

	  private static final String PATH = "savefile.bjw";
	  private static Scanner in;
	  static {
		  try {
		    in = new Scanner(new FileReader(PATH));
		  } catch (FileNotFoundException e) {
		    e.printStackTrace();
		  }
	  }
	  
	  /**
	   * Since this is a utility class, we don't want this instantiated.
	   */
	  private LoadGame() {
	    throw new AssertionError("Instantiating utility class...");
	  }
	  
	  /**
	   * Loads the game from the last saved game
	   * @return a new Game with the saved score and Board
	   */
	  public static Game load() {
		  Player player = new Player(in.nextInt());
		  
		  Cell[][] cells = new Cell[Board.BOARDSIZE][Board.BOARDSIZE];
		  for (int y = 0; y < Board.BOARDSIZE; y++) {
				for (int x = 0; x < Board.BOARDSIZE; x++) {
					GemType type = GemType.valueOf(in.next());
					cells[y][x] = new Cell(new Gem(type));
				}
		  }
		  in.reset();
		  return new Game(new Board(cells), player);
	  }
	  
	  /**
	   * Closes the Scanner used in this class.
	   */
	  public static void close() {
	    in.close();
	  }
	
}
