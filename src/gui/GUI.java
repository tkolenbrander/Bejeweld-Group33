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
	
	private static Game game;
	private static BoardPane boardPane;
	private static Label scoreLabel;
	private static Label errorLabel;
	private static GUI gui;

	/**
	 * Returns the game.
	 * @return Game
	 */
	protected static Game getGame() {
		return game;
	}
	
	/**
	 * Returns the GUI.
	 * @return gui
	 */
	protected static GUI getgui() {
		return gui;
	}
	
	/**
	 * Returns the boardpane.
	 * @return Boardpane.
	 */
	protected static BoardPane getBoardPane() {
	  return boardPane;
	}
	
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
	@SuppressWarnings("magicnumber")
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
		stage.setTitle("SwekJeweled");
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
                setError("Game saved!"); } });
		buttonLoad.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              try {
                game = LoadGame.load();
                game.start();
                boardPane.refresh();
                setScore(GUI.game.getPlayer().getScore());
                setScore(GUI.game.getPlayer().getScore());
                setError("Game loaded!"); }
              catch (Exception e) {
                setError("Cannot load game!"); } } });
		setScore(0);
		hbox.getChildren().addAll(scoreLabel, buttonSave, buttonLoad);
		return hbox;  }

	/**
	 * Adds the error panel at the bottom of the screen.
	 * @return The HBox containing all that is needed for the error panel
	 */
	@SuppressWarnings("magicnumber")
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
            	game.start();
            	boardPane.refresh();
            }
        });
		
		hbox.getChildren().addAll(errorLabel, buttonRestart);
		return hbox;
	}
	/**
	 * sets a message for the error label.
	 * @param s the message to be displayed
	 */
	public void setError(String s) {
		errorLabel.setText(s);
	}
	/**
	 * sets a number for the score label.
	 * @param score number to be displayed
	 */
	public void setScore(int score) {
		String s = "Score: " + score;
		scoreLabel.setText(s);
	}
	
}