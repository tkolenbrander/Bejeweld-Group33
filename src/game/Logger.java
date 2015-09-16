package game;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * Handles the logging of events to an external file.
 * 
 * @author Steven Meijer
 */
public class Logger {

	private static String path = "logfile.txt"; //static path for now
	private static boolean logInfo = true; //TODO: config file?
	private static boolean logWarning = true;

	/**
	 * Logs all info and higher to a logfile.
	 * @param text The text to be written.
	 */
	public static void logInfo(final String text) {
		if(logInfo) {
			writeLog("[INFO]", text);
		}
	}
	
	/**
	 * Logs all warnings and higher to a logfile.
	 * @param text The text to be written.
	 */
	public static void logWarning(final String text) {
		if(logWarning) {
			writeLog("[WARNING]", text);
		}
	}
	
	/**
	 * Makes sure the desired message is written to a logfile.
	 * @param level The severity of the message.
	 * @param text The text to be written.
	 */
	public static void writeLog(final String level, final String text) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		final Date date = new Date();
		
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, true)))) {
			out.println(level + " " + dateFormat.format(date) + " " + text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
