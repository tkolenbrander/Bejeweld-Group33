package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Logger;

/**
 * 
 * Tests the Logger class
 * 
 * @author Steven Meijer
 */
public class LoggerTest {

	/**
	 * Tests if all possible log settings are correctly passed on.
	 * Tests log level 0.
	 */
	@Test
	public void testSetLogLevel0() {
		Logger.setLogLevel(0);
		assertFalse(Logger.getLogInfo());
		assertFalse(Logger.getLogWarning());
	}
	
	/**
	 * Tests if all possible log settings are correctly passed on.
	 * Tests log level 1.
	 */
	@Test
	public void testSetLogLevel1() {
		Logger.setLogLevel(1);
		assertFalse(Logger.getLogInfo());
		assertTrue(Logger.getLogWarning());
	}
	
	/**
	 * Tests if all possible log settings are correctly passed on.
	 * Tests the default log level
	 */
	@Test
	public void testSetLogLevelDefault() {
		Logger.setLogLevel(2);
		assertTrue(Logger.getLogInfo());
		assertTrue(Logger.getLogWarning());
	}
}
