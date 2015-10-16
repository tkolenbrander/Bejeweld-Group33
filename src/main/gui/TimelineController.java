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
		Timeline[] timelines = new Timeline[list.size()];
		SequentialTransition sequence = new SequentialTransition();	
		sequence.getChildren().addAll(swapList);
		KeyFrame[] keyFrames = new KeyFrame[list.size()];
		for (int i = 0; i < list.size(); i++) {
			List<Change<Position>> changes = list.get(i);
			KeyValue[] keyValues = generateKeyValues(changes);
			KeyFrame keyFrame = new KeyFrame(KF_DURATION, keyValues);
			keyFrames[i] = keyFrame;	
			Timeline t = new Timeline();
			t.getKeyFrames().add(keyFrame);
			timelines[i] = t;
			System.out.println(t.getKeyFrames());
		}
		//System.out.println(swapList.get(0).getKeyFrames());
		//System.out.println(swapList.get(1).getKeyFrames());
		sequence.getChildren().addAll(timelines);
		list.clear();
		return sequence;
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
			
			if (from != null && to != null) { //means this comes from falldown() or swap()
			  int xDiff = from.deltaX(to);		
			  ImageView ivFrom = imageViews[from.getX()][from.getY()];
			  if (xDiff == 0) {
			    keyValues[i] = new KeyValue(ivFrom.yProperty(), to.getY() * 65, Interpolator.LINEAR);
			  }
			  else {
			    keyValues[i] = new KeyValue(ivFrom.xProperty(), to.getX() * 65, Interpolator.LINEAR);
			  }
			  imageViews[to.getX()][to.getY()] = imageViews[from.getX()][from.getY()];
			  imageViews[from.getX()][from.getY()] = new ImageView();
			}
			else if (change instanceof Create<?>) { //from is out of bounds, to is in bounds, comes from fillEmptyCells()
				Create<Position> create = (Create<Position>) change;
        ImageView newImage = new ImageView(create.getGem().getImage());
        imageViews[to.getX()][to.getY()] = newImage;
        newImage.setX(to.getX()*65);
        newImage.setY(-65);
        newImage.setOnMousePressed(new EventHandler<MouseEvent>() {
                      public void handle(MouseEvent me) {
                              System.out.println(me.getSceneX() + ", " + (me.getSceneY() - 55));
                              int x = (int) me.getSceneX() / 65;
                              int y = (int) (me.getSceneY() - 55) / 65;
                              GUI.getBoardPane().clicked(x, y, newImage);
                      }
              });
        GUI.getgui().boxToFront();
        newImage.toBack();      
        GUI.getBoardPane().getPane().getChildren().add(newImage);
        ImageView ivTo = imageViews[to.getX()][to.getY()];
        keyValues[i] = new KeyValue(ivTo.yProperty(), to.getY()*65, Interpolator.LINEAR);
      }
			else if (change instanceof Remove<?>) { //from is in bounds, to is out of bounds, comes from removeChains().
			  ImageView ivFrom = imageViews[from.getX()][from.getY()];
			  keyValues[i] = new KeyValue(ivFrom.opacityProperty(), 0, Interpolator.LINEAR);
			  imageViews[from.getX()][from.getY()] = new ImageView();
			}			
		}
		GUI.getBoardPane().setImageViews(imageViews);
		return keyValues;
	}
	
	public static void swap(Position one, Position two) {
	  ImageView[][] imageViews = GUI.getBoardPane().getImageViews();
	  SequentialTransition sequence = new SequentialTransition();
	  Timeline t = new Timeline();
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
	  
	  ImageView temp = imageViews[one.getX()][one.getY()];
	  imageViews[one.getX()][one.getY()] = imageViews[two.getX()][two.getY()];
	  imageViews[two.getX()][two.getY()] = temp;
	  
	  KeyFrame keyFrame = new KeyFrame(KF_DURATION, keyValues);
	  t.getKeyFrames().add(keyFrame);
	  sequence.getChildren().add(t);
	  swapList.add(t);
	  GUI.getBoardPane().setImageViews(imageViews);
	}

	/**
	 * Sets the list of current changes.
	 * @param l A list of changes.
	 */
	public static void setList(List<List<Change<Position>>> l) {
		list = l;
	}
	
	public static void clearSwapList() {
		swapList = new ArrayList<Timeline>();
	}

}
