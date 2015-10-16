package main.board;

import javafx.scene.image.Image;

/**
 * A class to represent a regular gem on the board.
 * @author Ruben
 *
 */
public class RegularGem extends Gem{

	/**
	 * Creates a RegularGem object with GemType t.
	 * @param t GemType of the RegularGem.
	 */
	public RegularGem(GemType t) {
		super(t);
		onDestroy = new RegularDestroyBehaviour();
	}
	
	/**
	 * Return the image of a regular gem.
	 * @return The image of a regular gem
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
		default:
		  break;
		}
		return image;
	}
	
	/**
	 * Return the image of a regular gem when it's clicked.
	 * @return The image of a regular gem when it's clicked
	 */
	public Image getImageClicked() {
		Image image = new Image("file:assets/textures/gems/gemMissing.png"); 	
		switch (type) {
		case BLUE:
			image = new Image("file:assets/textures/gems/gemBlueClicked.png");
			break;
		case RED:
			image = new Image("file:assets/textures/gems/gemRedClicked.png");
			break;
		case GREEN:
			image = new Image("file:assets/textures/gems/gemGreenClicked.png");
			break;
		case YELLOW:
			image = new Image("file:assets/textures/gems/gemYellowClicked.png");
			break;
		case WHITE:
			image = new Image("file:assets/textures/gems/gemWhiteClicked.png");
			break;
		case PURPLE:
			image = new Image("file:assets/textures/gems/gemPurpleClicked.png");
			break;
		case ORANGE:
			image = new Image("file:assets/textures/gems/gemOrangeClicked.png");
			break;
		default:
		  break;
		}	
		return image;
	}

}
