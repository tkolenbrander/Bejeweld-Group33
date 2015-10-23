package main.gui;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import main.SwekJeweled;
import main.game.ClassicGame;
import main.game.Game;
import main.game.GameOver;
import main.game.GameState;
import main.game.LoadGame;
import main.game.Logger;
import main.game.Observer;
import main.game.SaveGame;
import main.game.TimeTrialGame;

/**
 * Controller class for the game.
 * This class takes care of correctly displaying the game and 
 * 	the elements that belong in the in-game view.
 * 
 * @author Steven Meijer
 */
public class GameViewController implements Observer {

	private static final String FILENAME = "game.fxml";

	private static GameViewController gameViewController;

	private static GameState game;
	private static BoardPane boardPane;

	@FXML private BorderPane borderPane;
	@FXML private AnchorPane gameOverPane;
	@FXML private Pane topPane;
	@FXML private Pane bottomPane;
	@FXML private Pane topMidPane;
	@FXML private Pane bottomMidPane;

	@FXML private Button saveGameButton;
	@FXML private Button loadGameButton;
	@FXML private Button restartGameButton;
	@FXML private Button exitGameButton;
	@FXML private Button gameOverRestartButton;
	@FXML private Button gameOverMenuButton;

	@FXML private Label scoreLabel;
	@FXML private Label errorLabel;
	@FXML private Label remainingTime;
	@FXML private Label scoreLabelGO;

	@FXML
	private void initialize() {

		game.start();
		game.getPlayer().register(this);

		boardPane = new BoardPane();
		borderPane.setCenter(boardPane.getBoardPane());
		borderPane.getCenter().setStyle("-fx-background-image: url(\"/gemback.png\");");

		//Make sure the top pane is over the falling gems
		borderPane.getChildren().remove(topPane);
		borderPane.setTop(topPane);
		scoreLabel.setText("Score: 0");

		//TODO Make this work
		//Boxblur cannot be applied to a pane, Gaben pls fix
		BoxBlur boxBlur = new BoxBlur(10, 30, 3);
		topMidPane.setEffect(boxBlur);
		bottomMidPane.setEffect(boxBlur);

		initializeButtons();

		if (game instanceof TimeTrialGame) {
			saveGameButton.setVisible(false);
			loadGameButton.setVisible(false);
			remainingTime.setVisible(true);
			Logger.logInfo("New Time Trial game started");
		} else {
			Logger.logInfo("New Normal game started");
		}
	}

	/**
	 * Initializes the on-screen buttons.
	 */
	private void initializeButtons() {
		initSaveGameButton();
		initLoadGameButton();
		initRestartGameButton();
		initExitGameButton();
		initGORestartButton();
		initGOMenuButton();
	}

	/**
	 * Initialize the save game button.
	 */
	private void initSaveGameButton() {
		saveGameButton.setOnAction((event) -> {
			SaveGame.save(game);
			setError("Game saved!");
			Logger.logInfo("Game saved");
		});
	}

	/**
	 * Initialize the load game button.
	 */
	private void initLoadGameButton() {
		loadGameButton.setOnAction((event) -> {
			try {
				game = LoadGame.load();
				game.start();
				boardPane.refresh();
				setScore(GameViewController.game.getPlayer().getScore());
				game.getPlayer().register(this);
				setError("Game loaded!");
				Logger.logInfo("Loaded game from file");
			} catch (Exception e) {
				setError("Cannot load game!");
				Logger.logWarning("Failed to load game from file with error: " + e);
			} 
		});
	}

	/**
	 * Initialize the restart game button.
	 */
	private void initRestartGameButton() {
		restartGameButton.setOnAction((event) -> {
			game.reset();
			game.start();
			setScore(0);
			boardPane.refresh();
		});
	}

	/**
	 * Initialize the exit game button.
	 */
	private void initExitGameButton() {
		exitGameButton.setOnAction((event) -> {
			game.getPlayer().unregister(this);
			game.close();
			MenuViewController.show();
		});
	}

	/**
	 * Initialize the restart game button on the game over screen.
	 */
	private void initGORestartButton() {
		gameOverRestartButton.setOnAction((event) -> {
			game.reset();
		});
	}

	/**
	 * Initialize the menu button on the game over screen.
	 */
	private void initGOMenuButton() {
		gameOverMenuButton.setOnAction((event) -> {
			MenuViewController.show();
		});
	}

	/**
	 * Show this view.
	 * 
	 * @param type The type of game. Either Classic or Time Trial.
	 */
	public static void show(Game type) {
		game = type;

		FXMLLoader l = new FXMLLoader();
		l.setLocation(SwekJeweled.class.getClassLoader().getResource(FILENAME));

		try {
			AnchorPane game = l.load();
			GameViewController.gameViewController = l.getController();
			SwekJeweled.getStage().setScene(new Scene(game));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Update method for observer.
	 */
	@Override
	public void update() {
		int newScore = (int) game.getPlayer().getUpdate(this);
		setScore(newScore);
	}

	/**
	 * Enables the game over screen.
	 */
	public void setGameOver() {
		scoreLabelGO.setText(scoreLabel.getText());
		gameOverPane.setVisible(true);
		game = new GameOver(game);
	}

	/**
	 * Sets a message for the error label.
	 * 
	 * @param s the message to be displayed
	 */
	protected void setError(String s) {
		//TODO Fix overwriting animation on duplicate call
		errorLabel.setText(s);
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						FadeTransition ft = new FadeTransition(Duration.millis(2000), errorLabel);
						ft.setFromValue(1.0);
						ft.setToValue(0.0);
						ft.play();
					}
				});
			}
		}, 4000);
		
		errorLabel.setOpacity(1.0);
	}

	/**
	 * Sets a number for the score label.
	 * 
	 * @param score number to be displayed
	 */
	protected void setScore(int score) {
		String s = "Score: " + score;
		scoreLabel.setText(s);
	}

	/**
	 * Sets the timer.
	 * 
	 * @param timer number to be displayed
	 */
	public void setTimer(int timer) {
		String s = "Time: " + timer;
		remainingTime.setText(s);
	}

	/**
	 * @return The game.
	 */
	protected static GameState getGame() {
		return game;
	}

	/**
	 * @return The boardpane.
	 */
	protected static BoardPane getBoardPane() {
		return boardPane;
	}

	/**
	 * @return The GameViewController.
	 */
	public static GameViewController getGVC() {
		return gameViewController;
	}
	
	/**
	 * Refreshes the current board and posts a message to the user with it.
	 * @param message the message to be posted.
	 */
	public void refreshBoard(String message) {
	  boardPane.getBoardPane().getChildren().clear();
	  boardPane.initBoard();
	  setError(message);
	}
}
