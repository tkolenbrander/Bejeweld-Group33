package gui;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import board.Cell;
import board.Change;
import board.Position;

public class TimelineController {

	/**
	 * Contains all changes.
	 */
	private static List<List<Change<Position>>> list;
	
	private static final Duration KF_DURATION = Duration.millis(500);

	/**
	 * Simple constructor.
	 */
	public TimelineController() {
		list = new ArrayList<List<Change<Position>>>();
	}

	/**
	 * Generates animations from the changes.
	 * @return The timeline with animations.
	 */
	public SequentialTransition getTimeline() {
		//Timeline t = new Timeline();
		Timeline[] timelines = new Timeline[list.size()];
		SequentialTransition sequence = new SequentialTransition();	
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
		//t.getKeyFrames().addAll(keyFrames);
		//return t;
		sequence.getChildren().addAll(timelines);
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
			Position from =  changes.get(i).getFrom();
			Position to = changes.get(i).getTo();
			int xDiff = from.deltaX(to);
			int yDiff = from.deltaY(to);			
			
			if (from.isInBoard() && to.isInBoard()) { //means this comes from falldown() or swap()
			  ImageView ivFrom = imageViews[from.getX()][from.getY()];
			  if (xDiff == 0) {
			    keyValues[i] = new KeyValue(ivFrom.yProperty(), yDiff * 65, Interpolator.LINEAR);
			  }
			  else {
			    keyValues[i] = new KeyValue(ivFrom.xProperty(), xDiff * 65, Interpolator.LINEAR);
			  }
			  imageViews[to.getX()][to.getY()] = imageViews[from.getX()][from.getY()];
			  imageViews[from.getX()][from.getY()] = new ImageView();
			}
			else if (!from.isInBoard()) { //from is out of bounds, to is in bounds, comes from fillEmptyCells()
			  ImageView ivTo = imageViews[to.getX()][to.getY()];
			  keyValues[i] = new KeyValue(ivTo.yProperty(), 0, Interpolator.LINEAR);
			  imageViews[to.getX()][to.getY()] = new ImageView(GUI.getGame().getBoard().getCells()[to.getY()][to.getX()].getGem().getImage());
			}
			else { //from is in bounds, to is out of bounds, comes from removeChains().
			  ImageView ivFrom = imageViews[from.getX()][from.getY()];
			  keyValues[i] = new KeyValue(ivFrom.opacityProperty(), 0, Interpolator.LINEAR);
			  imageViews[from.getX()][from.getY()] = new ImageView();
			}			
		}
		GUI.getBoardPane().setImageViews(imageViews);
		return keyValues;
	}
	
	public void updateImageViews() {
	  ImageView[][] views = GUI.getBoardPane().getImageViews();
	  final Cell[][] cells = GUI.getGame().getBoard().getCells();
	  for (List<Change<Position>> changes : list) {
	    for (int i = 0; i < changes.size(); i++) {
	      Position from =  changes.get(i).getFrom();
	      Position to = changes.get(i).getTo();
	      int xDiff = from.deltaX(to);
	      int yDiff = from.deltaY(to);
	      
	      if (from.isInBoard() && to.isInBoard()) {
	        ImageView ivFrom = views[from.getX()][from.getY()];
	        if (xDiff == 0) {
	          
	        }
	        else {
	          
	        }
	      }
	      else if (!from.isInBoard()) { //from is out of bounds, to is in bounds
	        ImageView ivTo = views[to.getX()][to.getY()];
	        
	      }
	      else { //from is in bounds, to is out of bounds.
	        ImageView ivFrom = views[from.getX()][from.getY()];
	        
	      }
	    }
	  }
	}

	/**
	 * Sets the list of current changes.
	 * @param l A list of changes.
	 */
	public static void setList(List<List<Change<Position>>> l) {
		list = l;
	}

}
