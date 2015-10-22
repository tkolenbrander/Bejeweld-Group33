package main.board.gems;

import javafx.scene.image.Image;

/**
 * A class to represent a special Power Gem.
 * @author Ruben
 *
 */
public class PowerGem extends Gem {

	/**
	 * Creates a PowerGem object with GemType t.
	 * @param t The GemType of this PowerGem object.
	 */
	public PowerGem(GemType t) {
		super(t);
		onDestroy = new PowerDestroyBehaviour();
	}

	/**
	 * Return the image of a power gem.
	 * @return The image of a power gem
	 */
	public Image getImage() {
		Image image = new Image("file:assets/textures/gems/gemMissing.png");
		switch (type) {
		case BLUE:
			image = new Image("file:assets/textures/powergems/gemBlueBomb.png");
			break;
		case RED:
			image = new Image("file:assets/textures/powergems/gemRedBomb.png");
			break;
		case GREEN:
			image = new Image("file:assets/textures/powergems/gemGreenBomb.png");
			break;
		case YELLOW:
			image = new Image("file:assets/textures/powergems/gemYellowBomb.png");
			break;
		case WHITE:
			image = new Image("file:assets/textures/powergems/gemWhiteBomb.png");
			break;
		case PURPLE:
			image = new Image("file:assets/textures/powergems/gemPurpleBomb.png");
			break;
		case ORANGE:
			image = new Image("file:assets/textures/powergems/gemOrangeBomb.png");
			break;
		default:
		  break;
		}
		return image;
	}

	/**
	 * Return the image of a power gem when it's clicked.
	 * @return The image of a power gem when it's clicked
	 */
	public Image getImageClicked() {
		Image image = new Image("file:assets/textures/gems/gemMissing.png"); 	
		switch (type) {
		case BLUE:
			image = new Image("file:assets/textures/powergems/gemBlueBombClicked.png");
			break;
		case RED:
			image = new Image("file:assets/textures/powergems/gemRedBombClicked.png");
			break;
		case GREEN:
			image = new Image("file:assets/textures/powergems/gemGreenBombClicked.png");
			break;
		case YELLOW:
			image = new Image("file:assets/textures/powergems/gemYellowBombClicked.png");
			break;
		case WHITE:
			image = new Image("file:assets/textures/powergems/gemWhiteBombClicked.png");
			break;
		case PURPLE:
			image = new Image("file:assets/textures/powergems/gemPurpleBombClicked.png");
			break;
		case ORANGE:
			image = new Image("file:assets/textures/powergems/gemOrangeBombClicked.png");
			break;
		default:
		  break;
		}	
		return image;
	}

}
