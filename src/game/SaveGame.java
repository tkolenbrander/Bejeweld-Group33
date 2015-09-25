package game;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Utility class for saving the game to a save game.
 * @author Bart van Oort
 *
 */
public final class SaveGame {
  private static final Object SYNC = new Object();
  private static final String PATH = "savefile.bjw";
  private static PrintWriter out;
  static {
    try {
      out = new PrintWriter(new BufferedWriter(new FileWriter(PATH, false)));
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Since this is a utility class, we don't want this instantiated.
   */
  private SaveGame() {
    throw new AssertionError("Instantiating utility class...");
  }
  
  /**
   * Saves the game to the save game.
   * @param game Game to be saved.
   */
  public static void save(Game game) {
    synchronized (SYNC) {
      out.println(game.getPlayer().getScore());
      out.print(game.getBoard().toString());
      out.flush();
      reset();
    }
  }
  
  private static void reset() {
    out.flush();
    try {
      out = new PrintWriter(new BufferedWriter(new FileWriter(PATH, false)));
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Closes the PrintWriter used in this class.
   */
  public static void close() {
    out.close();
  }
}
