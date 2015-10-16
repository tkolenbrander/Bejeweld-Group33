package main.game;

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
public final class Logger {

	private static final Object SYNC = new Object();
	private static final String PATH = "logfile.txt"; //static path for now
	private static boolean logInfo = true; //TODO: config options in GUI
	private static boolean logWarning = true;

	private static PrintWriter out;
	static {
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(PATH, true)));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This is a utility class, we don't want it to be instantiated.
	 */
	private Logger() {
		throw new AssertionError("Instantiating utility class...");
	}

	/**
	 * Sets the PrintWriter-object that this class uses to write away to the files.
	 * This method is purely used for testing purposes.
	 * 
	 * @param print PrintWriter object.
	 */
	public static void setWriter(PrintWriter print) {
		out = print;
	}

	/**
	 * Returns the PrintWriter-object that this class uses to write away to the files.
	 * This method is also purely used for testing purposes.
	 * 
	 * @return PrintWriter object.
	 */
	public static PrintWriter getWriter() {
		return out;
	}

	/**
	 * Allows the level of messages to be logged to be changed.
	 * <br><br>
	 * 
	 * Possible log levels: <br>
	 * 0: No logging whatsoever <br>
	 * 1: Only warnings are logged <br>
	 * Anything else: Warnings and Info messages are both logged <br>
	 * @param level level of messages to be logged.
	 * 
	 */
	public static void setLogLevel(int level) {
		switch (level) {
		case 0:
			logInfo = false;
			logWarning = false;
			break;
		case 1:
			logInfo = false;
			logWarning = true;
			break;
		default:
			logInfo = true;
			logWarning = true;
		}
	}

	/**
	 * Logs all info and higher to a log file.
	 * @param text The text to be written.
	 */
	public static void logInfo(final String text) {
		if (logInfo) {
			writeLog("[INFO]", text);
		}
	}

	/**
	 * Logs all warnings and higher to a log file.
	 * @param text The text to be written.
	 */
	public static void logWarning(final String text) {
		if (logWarning) {
			writeLog("[WARNING]", text);
		}
	}

	/**
	 * Makes sure the desired message is written to a log file.
	 * @param level The severity of the message.
	 * @param text The text to be written.
	 */
	private static void writeLog(final String level, final String text) {
		synchronized (SYNC) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			out.println(level + " " + dateFormat.format(new Date()) + " " + text);
			out.flush();
		}
	}

	/**
	 * @return If info logs are written to a file.
	 */
	public static boolean getLogInfo() {
		return logInfo;
	}

	/**
	 * @return If warning logs are written to a file.
	 */
	public static boolean getLogWarning() {
		return logWarning;
	}

	/**
	 * Closes the PrintWriter.
	 */
	public static void close() {
		out.close();
	}
}
