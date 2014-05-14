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
	 * Create the game with two cells.
	 */
	public void create() {
		// add two cells to the cellQueue
		cellQueue.add(createNewCell());
		cellQueue.add(createNewCell());
	}
	
	/**
	 * Create a new cell.
	 */
	public Cell createNewCell() {
		if(idleQueue.size() == 0) {
			return null;
		}
		
		// get a position randomly
		int random = (int) (Math.random() * (idleQueue.size() - 1));		
		int position = idleQueue.get(random);
		// remove this position from idle
		idleQueue.remove(random);
		// if random is bigger than 5, set value 4, else set 2
		int value = (int) (Math.random() * 10) > 5 ? 4 : 2;
		
		Cell cell = new Cell(value);
		cell.setRow(position / 4);
		cell.setCol(position % 4);
		
		return cell;
	}
}
