package game;

public class Logger {

  private String path;
  //add filewriter.
  
  public Logger() {
    path = "bejeweled-" + getDateLog() + ".log";
  }
  
  public static String getDateLog() {
    return "";
  }
}
