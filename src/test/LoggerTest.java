package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.PrintWriter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import main.game.Logger;

/**
 * 
 * Tests the Logger class.
 * 
 * @author Steven Meijer
 */
@RunWith(MockitoJUnitRunner.class)
public class LoggerTest {
  
  @Mock private PrintWriter print;
  
  /**
   * Sets up the Logger with a mocked PrintWriter, to improve observability
   * and to make sure it does not actually write to the log.
   */
  @Before
  public void setUp() {
    Logger.setWriter(print);
  }

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
	
	/**
	 * Tests if the logger actually calls the writer.
	 * It should not be called when the log level is 0.
	 */
	@Test
	//verify is the oracle of this test, no assert() or fail() needed.
	@SuppressWarnings("PMD.JUnitTestsShouldIncludeAssert")
  public void testWriteInfoLevel0() {
	  Logger.setLogLevel(0);
	  Logger.logInfo("WriteInfoLevel0");
	  Logger.logWarning("WriteInfoLevel0");
	  verify(print, never()).println();
	}
	
  /**
   * Tests if the logger actually calls the writer.
   * With the log level at 1, it should only be called once, 
   * when logging the warning.
   */
	@Test
	//verify is the oracle of this test, no assert() or fail() needed.
	@SuppressWarnings("PMD.JUnitTestsShouldIncludeAssert")
  public void testWriteInfoLevel1() {
	  Logger.setLogLevel(1);
	  Logger.logInfo("WriteInfoLevel1");
	  Logger.logWarning("WriteInfoLevel1");
	  verify(print, times(1)).println("[WARNING]" + anyString());
	}
	
  /**
   * Tests if the logger actually calls the writer.
   * With the log level at default, it should be called twice,
   * both for the warning and the info.
   */
  @Test
  //verify is the oracle of this test, no assert() or fail() needed.
  @SuppressWarnings("PMD.JUnitTestsShouldIncludeAssert")
  public void testWriteInfoLevelDefault() {
    Logger.setLogLevel(2);
    Logger.logInfo("WriteInfoLevel2");
    Logger.logWarning("WriteInfoLevel2");
    verify(print, times(2)).println(anyString());
  }
}
