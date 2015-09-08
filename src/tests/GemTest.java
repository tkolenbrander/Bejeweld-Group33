package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import board.Gem;
import board.GemType;

/**
 * Tests the Gem class.
 * @author Bart van Oort
 *
 */
public class GemTest {

  /**
   * Equals method should return true.
   */
  @Test
  public void testEqualsTrue() {
    Gem gem1 = new Gem(GemType.BLUE);
    Gem gem2 = new Gem(GemType.BLUE);
    assertTrue(gem1.equals(gem2));
  }
  
  /**
   * Equals method should return false, because the GemType is different.
   */
  @Test
  public void testEqualsFalse() {
    Gem gem1 = new Gem(GemType.BLUE);
    Gem gem2 = new Gem(GemType.GREEN);
    assertFalse(gem1.equals(gem2));
  }
  
  /**
   * Equals method should return false, because the other object is not a Gem object.
   */
  @Test
  public void testEqualsOtherClass() {
    Gem gem1 = new Gem(GemType.BLUE);
    String lol = "Doesn't make sense";
    assertFalse(gem1.equals(lol));
  }

  /**
   * getType should return the correct type.
   */
  @Test
  public void testGetType() {
    assertTrue((new Gem(GemType.BLUE)).getType().equals(GemType.BLUE));
  }
}
