package gui;

import game.Game;
import game.LoadGame;
import game.Logger;
import game.SaveGame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
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
	private static final int WINDOW_Y = 620;
	
	protected static Game game;
	private BoardPane boardPane;
	private static Label scoreLabel;
	private static Label errorLabel;
	protected static GUI gui;

	/**
	 * Main class to launch the application.
	 * 
	 * @param args Launch arguments.
	 */
	public static void main(String[] args) {
		game = new Game();
		game.start();
		gui = new GUI();
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
		
		scoreLabel = new Label("Score: ");
		scoreLabel.setFont(new Font("Arial", 22));
			
		errorLabel = new Label("");
		errorLabel.setFont(new Font("Arial", 22));

		boardPane = new BoardPane();
		HBox hbox = addScorePanel();
		
		HBox errorPanel = addErrorPanel();
		
		borderPane.setTop(hbox);
		borderPane.setCenter(boardPane.getBoardPane());		
		borderPane.setBottom(errorPanel);

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
		hbox.setStyle("-fx-background-color: #C0C0C0;");

		Button buttonSave = new Button("Save Game");
		buttonSave.setPrefSize(100, 20);

		Button buttonLoad = new Button("Load Game");
		buttonLoad.setPrefSize(100, 20);
		
		buttonSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SaveGame.save(game);
            }
        });
		
		buttonLoad.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LoadGame.load();
            }
        });

		hbox.getChildren().addAll(scoreLabel, buttonSave, buttonLoad);
		return hbox;
	}
	
	public HBox addErrorPanel() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 12, 15, 150));
		hbox.setSpacing(10);
		hbox.setStyle("-fx-background-color: #C0C0C0;");
		
		Button buttonRestart = new Button("Restart Game");
		buttonRestart.setPrefSize(100, 20);
		
		buttonRestart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	game.reset();
            }
        });
		
		hbox.getChildren().addAll(errorLabel, buttonRestart);
		return hbox;
	}
	
	public void setError(String s) {
		errorLabel.setText(s);
	}
	
	public void setScore(int score) {
		String s = "Score: " + score;
		scoreLabel.setText(s);
	}
	
}