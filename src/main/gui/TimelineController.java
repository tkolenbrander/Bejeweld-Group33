package main.gui;

import java.util.ArrayList;
import java.util.List;

import main.board.Change;
import main.board.Create;
import main.board.Position;
import main.board.Remove;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

/**
 * Class that controls the timeline, used for the animations.
 * @author Thomas & Bart
 *
 */
public class TimelineController {

	/**
	 * Contains all changes.
	 */
	private static List<List<Change<Position>>> list;

	/**
	 * Contains all swap animations.
	 */
	private static List<Timeline> swapList;

	private static final Duration KF_DURATION = Duration.millis(300);

	/**
	 * Simple constructor.
	 */
	public TimelineController() {
		list = new ArrayList<List<Change<Position>>>();
		swapList = new ArrayList<Timeline>();
	}

	/**
	 * Generates animations from the changes.
	 * @return The timeline with animations.
	 */
	public SequentialTransition getTimeline() {
		SequentialTransition sequence = new SequentialTransition();	
		sequence.getChildren().addAll(swapList);
		sequence.getChildren().addAll(generateTimelines());
		list.clear();

		return sequence;
	}

	/**
	 * Generates Timelines from the changes.
	 * @return The different Timelines with animations.
	 */
	public Timeline[] generateTimelines() {
		Timeline[] timelines = new Timeline[list.size()];
		KeyFrame[] keyFrames = new KeyFrame[list.size()];

		for (int i = 0; i < list.size(); i++) {
			List<Change<Position>> changes = list.get(i);
			KeyValue[] keyValues = generateKeyValues(changes);
			KeyFrame keyFrame = new KeyFrame(KF_DURATION, keyValues);
			keyFrames[i] = keyFrame;	
			Timeline t = new Timeline();
			t.getKeyFrames().add(keyFrame);
			timelines[i] = t;
		}

		return timelines;
	}

	/**
	 * Generates key values from the changes.
	 * @param changes The list with all the changes that should occur parallel.
	 * @return The generated key values.
	 */
	public KeyValue[] generateKeyValues(List<Change<Position>> changes) {
		KeyValue[] keyValues = new KeyValue[changes.size()];
		ImageView[][] imageViews = GUI.getBoardPane().getImageViews();

		for (int i = 0; i < changes.size(); i++) {
			Change<Position> change = changes.get(i);
			Position from = change.getFrom();
			Position to = change.getTo();

			if (from != null && to != null) { 
				keyValues[i] = fallDownGem(imageViews, change);
			}
			else if (change instanceof Create<?>) { 			
				keyValues[i] = generateGem(imageViews, change);
			}
			else if (change instanceof Remove<?>) {				
				keyValues[i] = removeGem(imageViews, change);				
			}			
		}

		return keyValues;
	}

	/**
	 * Animation for removing a gem.
	 * @param imageViews The imageviews
	 * @param change The position of the change
	 * @return The keyvalue for the animation
	 */
	public KeyValue removeGem(ImageView[][] imageViews, Change<Position> change) {
		Position from = change.getFrom();
		ImageView ivFrom = imageViews[from.getX()][from.getY()];
		imageViews[from.getX()][from.getY()] = new ImageView();

		return new KeyValue(ivFrom.opacityProperty(), 0, Interpolator.LINEAR);
	}

	/**
	 * Handles the animation when a gem should fall down.
	 * @param imageViews All imageviews of the board
	 * @param change The change that has to be made to the new gem.
	 * @return The keyvalue corresponding to the animation of a gem falling down.
	 */
	public KeyValue fallDownGem(ImageView[][] imageViews, Change<Position> change) {
		Position from = change.getFrom();
		Position to = change.getTo();

		int xDiff = from.deltaX(to);		
		ImageView ivFrom = imageViews[from.getX()][from.getY()];
		imageViews[to.getX()][to.getY()] = imageViews[from.getX()][from.getY()];
		imageViews[from.getX()][from.getY()] = new ImageView();
		if (xDiff == 0) {
			return new KeyValue(ivFrom.yProperty(), to.getY() * 65, Interpolator.LINEAR);
		}
		else {
			return new KeyValue(ivFrom.xProperty(), to.getX() * 65, Interpolator.LINEAR);
		}		
	}

	/**
	 * This method generates a new gem and its animation.
	 * @param imageViews All imageviews of the board
	 * @param change The change that has to be made to the new gem.
	 * @return The keyvalue corresponding to the animation of a new gem appearing.
	 */
	public KeyValue generateGem(ImageView[][] imageViews, Change<Position> change) {
		Position from = change.getFrom();
		Position to = change.getTo();

		Create<Position> create = (Create<Position>) change;
		ImageView newImage = new ImageView(create.getGem().getImage());
		imageViews[to.getX()][to.getY()] = newImage;
		newImage.setX(to.getX() * 65);
		newImage.setY(-65);
		addMousePressed(newImage);		

		GUI.getgui().boxToFront();
		newImage.toBack();      
		GUI.getBoardPane().getPane().getChildren().add(newImage);
		ImageView ivTo = imageViews[to.getX()][to.getY()];

		return new KeyValue(ivTo.yProperty(), to.getY() * 65, Interpolator.LINEAR);
	}

	/**
	 * Adds an EventHandler to an ImageView.
	 * @param newImage The ImageView to add the EventHandler to.
	 */
	public void addMousePressed(ImageView newImage) {
		newImage.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				System.out.println(me.getSceneX() + ", " + (me.getSceneY() - 55));
				int x = (int) me.getSceneX() / 65;
				int y = (int) (me.getSceneY() - 55) / 65;
				GUI.getBoardPane().clicked(x, y, newImage);
			}
		});
	}

	/**
	 * Generates the animation of a swap action.
	 * @param one The position of the first cell to swap.
	 * @param two The position of the second cell to swap with.
	 */
	public static void swap(Position one, Position two) {
		ImageView[][] imageViews = GUI.getBoardPane().getImageViews();
		SequentialTransition sequence = new SequentialTransition();
		Timeline t = new Timeline();		

		KeyFrame keyFrame = new KeyFrame(KF_DURATION, swapKeyValues(imageViews, one, two));
		t.getKeyFrames().add(keyFrame);
		sequence.getChildren().add(t);
		swapList.add(t);

		ImageView temp = imageViews[one.getX()][one.getY()];
		imageViews[one.getX()][one.getY()] = imageViews[two.getX()][two.getY()];
		imageViews[two.getX()][two.getY()] = temp;
	}

	/**
	 * Generates the keyvalues of a swap animation.
	 * @param imageViews All imageviews of the board
	 * @param one The position of the first cell to swap.
	 * @param two The position of the second cell to swap with.
	 * @return The keyvalues corresponding with a swap animation.
	 */
	public static KeyValue[] swapKeyValues(ImageView[][] imageViews, Position one, Position two) {
		KeyValue[] keyValues = new KeyValue[2];

		int xDiff = one.deltaX(two);
		ImageView ivOne = imageViews[one.getX()][one.getY()];
		ImageView ivTwo = imageViews[two.getX()][two.getY()];

		if (xDiff == 0) {
			keyValues[0] = new KeyValue(ivOne.yProperty(), two.getY() * 65, Interpolator.LINEAR);
			keyValues[1] = new KeyValue(ivTwo.yProperty(), one.getY() * 65, Interpolator.LINEAR);
		}
		else {
			keyValues[0] = new KeyValue(ivOne.xProperty(), two.getX() * 65, Interpolator.LINEAR);
			keyValues[1] = new KeyValue(ivTwo.xProperty(), one.getX() * 65, Interpolator.LINEAR);
		}

		return keyValues;
	}

	/**
	 * Sets the list of current changes.
	 * @param l A list of changes.
	 */
	public static void setList(List<List<Change<Position>>> l) {
		list = l;
	}

	/**
	 * Clears the swaplist.
	 */
	public static void clearSwapList() {
		swapList = new ArrayList<Timeline>();
	}

}
