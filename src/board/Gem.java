package board;

import javax.swing.ImageIcon;

import javafx.scene.image.Image;

/**
 * Gem class to represent the gems in Bejeweled.
 * 
 * @author Bart van Oort
 */
public class Gem {
	
	private GemType type;
	
	/**
	 * Creates a Gem object with GemType t.
	 * @param t GemType of the Gem.
	 */
	public Gem(GemType t) {
		type = t;
	}
	
	/**
	 * Returns the type of gem.
	 * @return type of gem.
	 */
	public GemType getType() {
		return type;
	}
	
	/**
	 * Return the image of a gem
	 * @return The image of a gem
	 */
	public Image getImage() {
		Image image = new Image("file:assets/textures/gems/gemMissing.png");

		switch (type) {
		case BLUE:
			image = new Image("file:assets/textures/gems/gemBlue.png");
			break;
		case RED:
			image = new Image("file:assets/textures/gems/gemRed.png");
			break;
		case GREEN:
			image = new Image("file:assets/textures/gems/gemGreen.png");
			break;
		case YELLOW:
			image = new Image("file:assets/textures/gems/gemYellow.png");
			break;
		case WHITE:
			image = new Image("file:assets/textures/gems/gemWhite.png");
			break;
		case PURPLE:
			image = new Image("file:assets/textures/gems/gemPurple.png");
			break;
		case ORANGE:
			image = new Image("file:assets/textures/gems/gemOrange.png");
			break;
		}
		
		return image;
	}
	
	/**
	 * Return the image of a gem when it's clicked
	 * @return The image of a gem when it's clicked
	 */
	public Image getImageClicked() {
		Image image = new Image("file:assets/textures/gems/gemMissing.png"); 

		switch (type) {
		case BLUE:
			image = new Image("assets/textures/gems/gemBlueClicked.png");
			break;
		case RED:
			image = new Image("assets/textures/gems/gemRedClicked.png");
			break;
		case GREEN:
			image = new Image("assets/textures/gems/gemGreenClicked.png");
			break;
		case YELLOW:
			image = new Image("assets/textures/gems/gemYellowClicked.png");
			break;
		case WHITE:
			image = new Image("assets/textures/gems/gemWhiteClicked.png");
			break;
		case PURPLE:
			image = new Image("assets/textures/gems/gemPurpleClicked.png");
			break;
		case ORANGE:
			image = new Image("assets/textures/gems/gemOrangeClicked.png");
			break;
		}
		
		return image;
	}
	
	/**
   * Checks if two gems are equal.
   * @param other gem to check
   * @return true iff the two gems are equal.
   */
	@Override
	public boolean equals(Object other) {
	  if (other instanceof Gem) {
	    return this.type == ((Gem) other).type;
	  }
	  return false;
	}
}
