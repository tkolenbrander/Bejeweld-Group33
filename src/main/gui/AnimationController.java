package main.gui;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * Controller class for the animations of the GUI.
 * 
 * @author Steven Meijer
 */
abstract class AnimationController {

	private static final int DURATION_FADEIN = 400;
	private static final int DURATION_FADEOUT = 300;

	private static final double BUTTON_OFFSET = 50.0;

	/**
	 * Fades in a node.
	 * 
	 * @param node The node to fade in
	 */
	protected static void fadeIn(Node node) {
		FadeTransition ft = new FadeTransition(Duration.millis(DURATION_FADEIN), node);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();
	}

	/**
	 * Fades in a node.
	 * 
	 * @param node The node to fade in
	 * @param duration The duration of the fade in in milliseconds
	 */
	protected static void fadeIn(Node node, double duration) {
		FadeTransition ft = new FadeTransition(Duration.millis(duration), node);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();
	}

	/**
	 * Fades out a node.
	 * 
	 * @param node The node to fade out
	 */
	protected static void fadeOut(Node node) {
		FadeTransition ft = new FadeTransition(Duration.millis(DURATION_FADEOUT), node);
		ft.setToValue(0.0);
		ft.play();
	}

	/**
	 * Fades out a node, with a longer duration.
	 * 
	 * @param node The node to fade out
	 * @param duration The duration of the fade out in milliseconds
	 */
	protected static void fadeOut(Node node, double duration) {
		FadeTransition ft = new FadeTransition(Duration.millis(duration), node);
		ft.setToValue(0.0);
		ft.play();
	}

	/**
	 * Fade out animation for a node.
	 * Node slides to the right and fades out.
	 * 
	 * @param node The button to fade out.
	 */
	protected static void fadeOutNodeRight(Node node) {
		TranslateTransition tt = new TranslateTransition(Duration.millis(DURATION_FADEOUT), node);
		tt.setByX(BUTTON_OFFSET);

		FadeTransition ft = new FadeTransition(Duration.millis(DURATION_FADEOUT), node);
		ft.setToValue(0.0);

		tt.play();
		ft.play();
	}

	/**
	 * Fade out animation for a node.
	 * Node slides to the left and fades out.
	 * 
	 * @param node The button to fade out.
	 */
	protected static void fadeOutNodeLeft(Node node) {
		TranslateTransition tt = new TranslateTransition(Duration.millis(DURATION_FADEOUT), node);
		tt.setByX(-BUTTON_OFFSET);

		FadeTransition ft = new FadeTransition(Duration.millis(DURATION_FADEOUT), node);
		ft.setToValue(0.0);

		tt.play();
		ft.play();
	}

	/**
	 * @return DURATION_FADEIN
	 */
	protected static int getFadeIn() {
		return DURATION_FADEIN;
	}

	/**
	 * @return DURATION_FADEOUT
	 */
	protected static int getFadeOut() {
		return DURATION_FADEOUT;
	}
}
