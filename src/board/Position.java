package board;

/**
 * Class to represent a position in a 2d grid
 * 
 * @author Ruben
 *
 */
public class Position {
	
	private int x;
	private int y;
	
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public boolean equals(Object other){
		if (other instanceof Position){
			Position that = (Position)other;
			return this.x == that.x && this.y == that.y;
		}
		return false;
	}
	
}
