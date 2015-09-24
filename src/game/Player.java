package game;

/**
 * Class for a player. Currently only used for keeping track of his / her
 * score, but in light of possible future developments, it is good to have 
 * a good structure.
 * @author Bart van Oort
 *
 */
public class Player {

  private int score;
  
  /**
   * Creates a new player with 0 as a score.
   */
  public Player() {
    score = 0;
  }
  
  /**
   * Creates a new player with the given score.
   */
  public Player(int newScore) {
    score = newScore;
  }
  
  /**
   * Returns the score of the player.
   * @return score of the player.
   */
  public int getScore() {
    return score;
  }
  
  /**
   * Adds score to the player's score.
   * @param add score to be added to the player's score.
   */
  public void addScore(int add) {
    score += add;
  }
  
  /**
   * Resets the player to its initial state, that is, score = 0..
   */
  public void reset() {
    score = 0;
  }
}
