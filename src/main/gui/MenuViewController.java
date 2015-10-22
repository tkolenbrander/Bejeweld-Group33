package main.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import main.SwekJeweled;

/**
 * Controller class fot the main menu.
 * 
 * @author Steven Meijer
 */
public class MenuViewController {

	private static final String FILENAME = "main.fxml";

	@FXML private Button newGameButton;
	@FXML private Button loadGameButton;
	@FXML private Button settingsButton;

	@FXML
	private void initialize() {

		newGameButton.setOnAction((event) -> {
			NewGameViewController.show();
		});

		loadGameButton.setOnAction((event) -> {
			//TODO Ability to load game from main menu
		});

		settingsButton.setOnAction((event) -> {
			//TODO Settings menu
		});
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
