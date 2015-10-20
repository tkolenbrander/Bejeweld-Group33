package main.gui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import main.SwekJeweled;

public class NewGameViewController {

	private static final String fileName = "newgame.fxml";
	
	@FXML
	private void initialize() {
		
		//TODO Add buttons
		
	}
	
	/**
	 * Show this view.
	 */
	public static void show() {
		FXMLLoader l = new FXMLLoader();
		l.setLocation(SwekJeweled.class.getClassLoader().getResource(fileName));
		System.out.println("Fissa");
		try {
			AnchorPane newgame = l.load();
			SwekJeweled.getStage().setScene(new Scene(newgame));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
