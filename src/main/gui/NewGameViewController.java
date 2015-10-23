package main.gui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import main.SwekJeweled;

/**
 * Controller class for the New Game selection screen.
 * 
 * @author Steven Meijer
 */
public class NewGameViewController {

	private static final String FILENAME = "main/resources/newgame.fxml";

	@FXML private Button newNormalButton;
	@FXML private Button newTimeTrialButton;
	@FXML private Button newBackButton;

	@FXML
	private void initialize() {

		newNormalButton.setOnAction((event) -> {
			GameViewController.show();
		});

		newTimeTrialButton.setOnAction((event) -> {
			//TODO Add Time Trial game to menu
		});

		newBackButton.setOnAction((event) -> {
			MenuViewController.show();
		});

	}

	/**
	 * Show this view.
	 */
	public static void show() {
		FXMLLoader l = new FXMLLoader();
		l.setLocation(SwekJeweled.class.getClassLoader().getResource(FILENAME));

		try {
			AnchorPane newgame = l.load();
			SwekJeweled.getStage().setScene(new Scene(newgame));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
