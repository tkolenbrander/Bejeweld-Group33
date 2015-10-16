package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import main.board.GemType;

/**
 * Test for GemType enumeration.
 * @author Bart van Oort
 *
 */
public class GemTypeTest {
  
  private static final long RANDOMSEED = 1337;
  
  private GemType gemType;
  
  /**
   * Sets the gemType to blue in order to set its random value .
   */
  @Before
  public void setUp() {
    gemType = GemType.BLUE;
    gemType.setRandom(new Random(RANDOMSEED));
  }

  
  /**
   * Tests if the enumeration has the right elements.
   */
  @Test
  public void testCompleteEnum() {
      for (String value : new String[] 
          { "BLUE", "RED", "GREEN", "YELLOW", "WHITE", "PURPLE", "ORANGE" }) {
      assertNotNull(GemType.valueOf(value));
    }
  }
  
  /**
   * Tests if the randomGem method returns a gem, using a seed in the randomiser.
   */
  @Test
  public void testRandomGem() {
    assertEquals(GemType.randomGem(), GemType.PURPLE);
  }

}
