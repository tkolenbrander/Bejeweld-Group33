package game;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public final class SaveGame {
  
  private static final String PATH = "savefile.bjw";
  private static PrintWriter out;
  static {
    try {
      out = new PrintWriter(new BufferedWriter(new FileWriter(PATH, true)));
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public static void save() {
    
  }
}
