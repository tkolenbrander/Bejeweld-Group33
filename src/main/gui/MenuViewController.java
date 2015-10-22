package main.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import main.SwekJeweled;
import main.game.Game;
import main.game.LoadGame;

public class MenuViewController {

	private static final String fileName = "main.fxml";

	@FXML private Button newGameButton;
	@FXML private Button loadGameButton;
	@FXML private Button settingsButton;

	@FXML
	private void initialize() {

		newGameButton.setOnAction((event) -> {
			NewGameViewController.show();
		});

		loadGameButton.setOnAction((event) -> {
			//
		});

		settingsButton.setOnAction((event) -> {
			//
		});
	}

	/**
	 * Show this view.
	 */
	public static void show() {
		FXMLLoader l = new FXMLLoader();
		l.setLocation(SwekJeweled.class.getClassLoader().getResource(fileName));

		try {
			AnchorPane menu = l.load();
			SwekJeweled.getStage().setScene(new Scene(menu));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
