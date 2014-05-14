package yan;

/**
 * A simple cell to represent the cell.
 * @author yanzhang
 *
 */
public class Cell {
	// pointer to the next node
	private Cell next;
	// value of the cell
	private int value;
	// row of the board
	private int row;
	// col of the board
	private int col;
	
	// getters and setters
	public Cell getNext() {
		return next;
	}
	
	public void setNext(Cell next) {
		this.next = next;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
}
