package main.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import main.SwekJeweled;

public class MenuViewController implements Initializable {

	private static final String fileName = "main.fxml";
	
	@FXML private Button newGameButton;
	@FXML private Button loadGameButton;
	@FXML private Button settingsButton;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Set button action
		
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
