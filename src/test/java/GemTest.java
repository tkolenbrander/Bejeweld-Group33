package test.java;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.board.Gem;
import main.board.GemType;
import main.board.RegularGem;

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
    Gem gem1 = new RegularGem(GemType.BLUE);
    Gem gem2 = new RegularGem(GemType.BLUE);
    assertTrue(gem1.equals(gem2));
  }
  
  /**
   * Equals method should return false, because the GemType is different.
   */
  @Test
  public void testEqualsFalse() {
    Gem gem1 = new RegularGem(GemType.BLUE);
    Gem gem2 = new RegularGem(GemType.GREEN);
    assertFalse(gem1.equals(gem2));
  }
  
  /**
   * Equals method should return false, because the other object is not a Gem object.
   */
  @Test
  public void testEqualsOtherClass() {
    Gem gem1 = new RegularGem(GemType.BLUE);
    String lol = "Doesn't make sense";
    assertFalse(gem1.equals(lol));
  }

  /**
   * getType should return the correct type.
   */
  @Test
  public void testGetType() {
    assertTrue((new RegularGem(GemType.BLUE)).getType().equals(GemType.BLUE));
  }
}
