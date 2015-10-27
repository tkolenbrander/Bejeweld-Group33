package main.gui;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import main.SwekJeweled;
import main.game.Game;
import main.game.LoadGame;
import main.game.Logger;

/**
 * Controller class for the main menu.
 * 
 * @author Steven Meijer
 */
public class MenuViewController {

	private static final String FILENAME = "main.fxml";

	private static AnchorPane anchorPane;
	private static boolean bAnimateLogo = true;

	@FXML private Button newGameButton;
	@FXML private Button loadGameButton;
	@FXML private Button helpButton;
	@FXML private Button quitButton;

	@FXML private ImageView logo;

	@FXML
	private void initialize() {
		fadeIn();
		initializeButtons();
	}

	/**
	 * Initialize the on-screen buttons of the main menu.
	 */
	private void initializeButtons() {
		initNewGameButton();
		initLoadGameButton();
		initHelpButton();
		initQuitButton();
	}

	/**
	 * Initialize the new game button.
	 */
	private void initNewGameButton() {
		newGameButton.setOnAction((event) -> {
			fadeOutButtons();

			Timeline timeout = 
					new Timeline(
							new KeyFrame(
									Duration.millis(AnimationController.getFadeIn()), (event2) -> {
										NewGameViewController.show();
									}));

			timeout.play();
		});
	}

	/**
	 * Initialize the load game button.
	 */
	private void initLoadGameButton() {
		loadGameButton.setOnAction((event) -> {
			try {
				Game game = LoadGame.load();
				game.start();

				AnimationController.fadeOut(anchorPane);

				Timeline timeout = 
						new Timeline(
								new KeyFrame(
										Duration.millis(
												AnimationController.getFadeOut()), (event2) -> {
													GameViewController.show(game);
												}));

				timeout.play();

			} catch (Exception e) {
				e.printStackTrace();
			}

		});
	}

	/**
	 * Initialize the help button.
	 */
	private void initHelpButton() {
		helpButton.setOnAction((event) -> {
			fadeOutButtons();

			Timeline timeout = 
					new Timeline(
							new KeyFrame(
									Duration.millis(AnimationController.getFadeIn()), (event2) -> {
										HelpViewController.show();
									}));

			timeout.play();
		});
	}

	/**
	 * Initialize the quit button.
	 */
	private void initQuitButton() {
		quitButton.setOnAction((event) -> {
			Logger.logInfo("Exiting...");
			Logger.close();
			System.exit(0);
		});
	}

	/**
	 * Fade in all nodes.
	 */
	private void fadeIn() {
		newGameButton.setOpacity(0.0);
		loadGameButton.setOpacity(0.0);
		helpButton.setOpacity(0.0);
		quitButton.setOpacity(0.0);

		AnimationController.fadeIn(newGameButton);
		AnimationController.fadeIn(loadGameButton);
		AnimationController.fadeIn(helpButton);
		AnimationController.fadeIn(quitButton);

		if (bAnimateLogo) {
			logo.setOpacity(0.0);
			AnimationController.fadeIn(logo);

			bAnimateLogo = false;
		}
	}

	/**
	 * Fades out all buttons.
	 */
	private void fadeOutButtons() {
		AnimationController.fadeOutNodeLeft(newGameButton);
		AnimationController.fadeOutNodeLeft(loadGameButton);
		AnimationController.fadeOutNodeLeft(helpButton);
		AnimationController.fadeOutNodeLeft(quitButton);
	}

	/**
	 * Show this view.
	 */
	public static void show() {
		FXMLLoader l = new FXMLLoader();
		l.setLocation(SwekJeweled.class.getClassLoader().getResource(FILENAME));

		try {
			AnchorPane menu = l.load();
			anchorPane = menu;
			SwekJeweled.getStage().setScene(new Scene(menu));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Set if the logo should be animated.
	 * @param b True if the logo has to be animated
	 */
	public static void setAnimateLogo(boolean b) {
		bAnimateLogo = b;
	}
}
