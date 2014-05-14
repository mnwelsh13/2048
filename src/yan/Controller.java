package yan;

import java.util.LinkedList;

/**
 * A controller of the game.
 * @author yanzhang
 *
 */
public class Controller {
	// a queue to hold the cells which have a value
	private LinkedList<Cell> cellQueue = new LinkedList<Cell>();
	// a queue to hold the cells which dont have a value;
	private LinkedList<Integer> idleQueue = new LinkedList<Integer>();

	/** 
	 * Initialization, put all the cells into idleQueue;
	 */
	public Controller() {
		for(int i = 0; i < 8; i++) {
			idleQueue.add(i);
		}
	}
	
	/**
	 * Create the game with two cells
	 */
	public 
}
