package main.gui;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import main.SwekJeweled;
import main.game.ClassicGame;
import main.game.Game;
import main.game.TimeTrialGame;

/**
 * Controller class for the New Game selection screen.
 * 
 * @author Steven Meijer
 */
public class NewGameViewController {

	private static final String FILENAME = "newgame.fxml";
	private static final int DURATION_TIMEOUT = 350;

	private static AnchorPane anchorPane;

	@FXML private Button newNormalButton;
	@FXML private Button newTimeTrialButton;
	@FXML private Button newBackButton;

	@FXML
	private void initialize() {

		fadeInButtons();

		newNormalButton.setOnAction((event) -> {
			newGame(new ClassicGame());
		});

		newTimeTrialButton.setOnAction((event) -> {
			newGame(new TimeTrialGame());
		});

		newBackButton.setOnAction((event) -> {
			fadeOutButtons();

			Timeline timeout = 
					new Timeline(new KeyFrame(Duration.millis(DURATION_TIMEOUT), (event2) -> {
						MenuViewController.show();
					}));

			timeout.play();
		});

	}

	/**
	 * Fade in all buttons.
	 */
	private void fadeInButtons() {
		newNormalButton.setOpacity(0.0);
		newTimeTrialButton.setOpacity(0.0);
		newBackButton.setOpacity(0.0);

		AnimationController.fadeIn(newNormalButton);
		AnimationController.fadeIn(newTimeTrialButton);
		AnimationController.fadeIn(newBackButton);
	}

	/**
	 * Fade out all buttons.
	 */
	private void fadeOutButtons() {
		AnimationController.fadeOutNodeRight(newNormalButton);
		AnimationController.fadeOutNodeRight(newTimeTrialButton);
		AnimationController.fadeOutNodeRight(newBackButton);
	}

	/**
	 * Creates a new game and makes sure the current screen fades out nicely.
	 * @param game The kind of game to start.
	 */
	private void newGame(Game game) {
		AnimationController.fadeOut(anchorPane);

		Timeline timeout = 
				new Timeline(new KeyFrame(Duration.millis(DURATION_TIMEOUT), (event2) -> {
					GameViewController.show(game);
				}));

		timeout.play();
	}

	/**
	 * Show this view.
	 */
	public static void show() {
		FXMLLoader l = new FXMLLoader();
		l.setLocation(SwekJeweled.class.getClassLoader().getResource(FILENAME));

		try {
			AnchorPane newgame = l.load();
			anchorPane = newgame;
			SwekJeweled.getStage().setScene(new Scene(newgame));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
