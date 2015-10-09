package board;

import javafx.scene.image.Image;

/**
 * A class to represent a special Power Gem
 * @author Ruben
 *
 */
public class PowerGem extends Gem{

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
		return image;
	}

	/**
	 * Return the image of a power gem when it's clicked.
	 * @return The image of a power gem when it's clicked
	 */
	public Image getImageClicked() {
		Image image = new Image("file:assets/textures/gems/gemMissing.png");
		return image;
	}

}
