package gui;

import game.Game;
import game.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Base class of the GUI.
 * 
 * @author Steven Meijer & Thomas Kolenbrander
 */
public class GUI extends Application {

	/**
	 * X and Y dimensions of the board.
	 */
	private static final int WINDOW_X = 520;
	private static final int WINDOW_Y = 600;
	
	protected static Game game;
	private BoardPane boardPane;

	/**
	 * Main class to launch the application.
	 * 
	 * @param args Launch arguments.
	 */
	public static void main(String[] args) {
		game = new Game();
		game.start();
		launch(args);
	}

	/**
	 * JavaFX start class, that sets the properties of the GUI.
	 * 
	 * @param stage The stage.
	 */
	public void start(Stage stage) {
		Logger.logInfo("Game started");
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(GUI.class.getResource("/assets/config/gui.fxml"));

		BorderPane borderPane = new BorderPane();
		Scene scene = new Scene(borderPane, WINDOW_X, WINDOW_Y);

		boardPane = new BoardPane();
		HBox hbox = addScorePanel();
		
		borderPane.setTop(hbox);
		//TODO Fix board being loaded from BoardPane class, into the center of the borderPane
		borderPane.setCenter(boardPane.getBoardPane());

		// Properties of the stage
		stage.setTitle("SwekJeweld");
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.setScene(scene);
		stage.show();
		
		Logger.logInfo("Game succesfully initialized");
	}

	/**
	 * Adds a score panel to the top of the screen.
	 * @return The HBox containing all that is needed for the score panel.
	 */
	@SuppressWarnings("magicnumber")
	public HBox addScorePanel() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 12, 15, 150));
		hbox.setSpacing(10);
		hbox.setStyle("-fx-background-color: #3366ff;");

		Button buttonPause = new Button("Pause Game");
		buttonPause.setPrefSize(100, 20);

		Button buttonRestart = new Button("Restart Game");
		buttonRestart.setPrefSize(100, 20);

		hbox.getChildren().addAll(buttonPause, buttonRestart);

		return hbox;
	}
	
}