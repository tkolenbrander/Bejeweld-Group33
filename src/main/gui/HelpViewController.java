package main.gui;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import main.SwekJeweled;

/**
 * Controller class for the Help screen.
 * 
 * @author Steven Meijer
 */
public class HelpViewController {

	private static final String FILENAME = "help.fxml";

	@FXML private Button backButton;

	@FXML private Text text1;
	@FXML private Text text2;
	@FXML private Text text3;
	@FXML private Text text4;
	@FXML private Text text5;

	@FXML
	private void initialize() {
		fadeIn();
		
		backButton.setOnAction((event) -> {
			fadeOut();
			
			Timeline timeout = 
					new Timeline(
							new KeyFrame(
									Duration.millis(AnimationController.getFadeOut()), (event2) -> {
										MenuViewController.show();
									}));

			timeout.play();
		});
	}
	
	/**
	 * Fade in all nodes.
	 */
	@SuppressWarnings("magicnumber")
	private void fadeIn() {
		text1.setOpacity(0.0);
		text2.setOpacity(0.0);
		text3.setOpacity(0.0);
		text4.setOpacity(0.0);
		text5.setOpacity(0.0);
		backButton.setOpacity(0.0);
		
		AnimationController.fadeIn(text1, 300);
		AnimationController.fadeIn(text2, 400);
		AnimationController.fadeIn(text3, 500);
		AnimationController.fadeIn(text4, 600);
		AnimationController.fadeIn(text5, 700);
		AnimationController.fadeIn(backButton, 900);
	}
	
	/**
	 * Fade out all nodes.
	 */
	private void fadeOut() {
		AnimationController.fadeOutNodeRight(text1);
		AnimationController.fadeOutNodeRight(text2);
		AnimationController.fadeOutNodeRight(text3);
		AnimationController.fadeOutNodeRight(text4);
		AnimationController.fadeOutNodeRight(text5);
		AnimationController.fadeOutNodeRight(backButton);
	}

	/**
	 * Show this view.
	 */
	public static void show() {
		FXMLLoader l = new FXMLLoader();
		l.setLocation(SwekJeweled.class.getClassLoader().getResource(FILENAME));

		try {
			AnchorPane menu = l.load();
			SwekJeweled.getStage().setScene(new Scene(menu));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
