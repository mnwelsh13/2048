package yan;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * A controller of the game.
 * @author yanzhang
 *
 */
public class Controller {
	// the board of the game to hold the cells
	private Cell[][] board;
	// a queue to hold the cells which don't have a value;
	private LinkedList<Integer> idles = new LinkedList<Integer>();

	/** 
	 * Initialization, put all the cells into idleQueue;
	 */
	public Controller() {
		board = new Cell[4][4];
		
		for(int i = 0; i < 16; i++) {
			idles.add(i);
		}
	}
	
	/**
	 * Create the game with two cells.
	 */
	public void newGame() {
		board = new Cell[4][4];
		
		for(int i = 0; i < 16; i++) {
			idles.add(i);
		}
		// add two cells to the board
		createNewCell();
		createNewCell();
	}
	
	/**
	 * Create a new cell.
	 */
	private void createNewCell() {
		if(idles.size() != 0) {
			// get a position randomly
			int random = (int) (Math.random() * (idles.size() - 1));		
			int position = idles.get(random);
			// remove this position from idle
			idles.remove(random);
			// if random is bigger than 5, set value 4, else set 2
			int value = (int) (Math.random() * 10) > 5 ? 4 : 2;
			
			// create a new cell and set it's attributes
			Cell cell = new Cell(value);
			cell.setRow(position / 4);
			cell.setCol(position % 4);
			
			board[cell.getRow()][cell.getCol()] = cell;
		}
	}
	
	/**
	 * Start the game by read input from user.
	 */
	public void start() {
		newGame();
		
		Scanner input = new Scanner(System.in);
		System.out.println("Please input the direction you want to move: /n");
		boolean fail = false;

		// keep read command from user
		while(!fail) {
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 4; j++) {
					if(board[i][j] == null) {
						System.out.print("[ ] ");
					} else {
						System.out.print("[" + board[i][j].getValue() + "] ");
					}
				}
				
				System.out.println("");
			}
			
			fail = move(input.next());
		}
		
		System.out.println("Game is over!");
		
		input.close();
	}
	
	/**
	 * Move the cells to the direction that the user chose
	 */
	public boolean move(String direction) {
		LinkedList<Cell> copy = new LinkedList<Cell>();
		
		for(Cell[] cells : board) {
			for(Cell cell : cells) {
				copy.add(cell);
			}
		}
		
		// based on the direction move the cells
		switch(direction.toUpperCase()) {
		case "U" : moveUp(); break;
		case "D" : moveDown(); break;
		case "L" : moveLeft(); break;
		case "R" : moveRight(); break;
		default : System.out.println("Invaild Command!");
		}
		
		boolean isChanged = false;
		
		int count = 0;
		for(Cell cell : copy) {
			if(cell != board[count / 4][count % 4]) {
				isChanged = true;
				break;
			}
			count++;
		}
		
		if(isChanged) {
			setIdle();
			// add a new node to the list
			createNewCell();
		}
		
		return isFail(board);
	}
	
	/**
	 * Move the cells up.
	 */
	public void moveUp() {
		
		for(int i = 0; i < 4; i++) {
			LinkedList<Cell> queue = new LinkedList<Cell>();
			
			for(int j = 0; j < 4; j++) {
				if(board[j][i] != null) {
					queue.add(board[j][i]);
					if(queue.size() > 0) {
						queue.getLast().setNext(board[j][i]);
					}
					board[j][i] = null;
				}
			}
			
			if(queue.size() > 1) {
				int m = 0;
				while(m < (queue.size() - 1)) {
					if(queue.get(m).getValue() == queue.get(m + 1).getValue()) {
						queue.get(m).doubleValue();
						int postion = queue.get(m + 1).getRow() * 4 + queue.get(m + 1).getCol();
						idles.add(postion);
						queue.remove(m + 1);
					}
				
					m++;
				}
			}
			
			for(int n = 0; n < queue.size(); n++) {
				board[n][i] = queue.get(n);
			}
		}
	}
	
	/**
	 * Move the cells down.
	 */
	public void moveDown() {
		
		
		for(int i = 0; i < 4; i++) {
			LinkedList<Cell> queue = new LinkedList<Cell>();
			
			for(int j = 3; j >= 0; j--) {
				if(board[j][i] != null) {
					queue.add(board[j][i]);
					if(queue.size() > 0) {
						queue.getLast().setNext(board[j][i]);
					}
					board[j][i] = null;
				}
			}
			
			if(queue.size() > 1) {
				int m = 0;
				while(m < (queue.size() - 1)) {
					if(queue.get(m).getValue() == queue.get(m + 1).getValue()) {
						queue.get(m).doubleValue();
						int postion = queue.get(m + 1).getRow() * 4 + queue.get(m + 1).getCol();
						idles.add(postion);
						queue.remove(m + 1);
					}
				
					m++;
				}
			}
			
			for(int n = 0; n < queue.size(); n++) {
				board[3 - n][i] = queue.get(n);
			}
		}
	}
	
	/**
	 * Move the cells left.
	 */
	public void moveLeft() {
		
		
		for(int i = 0; i < 4; i++) {
			LinkedList<Cell> queue = new LinkedList<Cell>();
			
			for(int j = 0; j < 4; j++) {
				if(board[i][j] != null) {
					queue.add(board[i][j]);
					if(queue.size() > 0) {
						queue.getLast().setNext(board[i][j]);
					}
					board[i][j] = null;
				}
			}
			
			if(queue.size() > 1) {
				int m = 0;
				while(m < (queue.size() - 1)) {
					if(queue.get(m).getValue() == queue.get(m + 1).getValue()) {
						queue.get(m).doubleValue();
						int postion = queue.get(m + 1).getRow() * 4 + queue.get(m + 1).getCol();
						idles.add(postion);
						queue.remove(m + 1);
					}
				
					m++;
				}
			}
			
			for(int n = 0; n < queue.size(); n++) {
				board[i][n] = queue.get(n);
			}
		}
	}
	
	/**
	 * Move the cells right.
	 */
	public void moveRight() {
		
		
		for(int i = 0; i < 4; i++) {
			LinkedList<Cell> queue = new LinkedList<Cell>();
			
			for(int j = 3; j >= 0; j--) {
				if(board[i][j] != null) {
					queue.add(board[i][j]);
					if(queue.size() > 0) {
						queue.getLast().setNext(board[i][j]);
					}
					board[i][j] = null;
				}
			}
			
			if(queue.size() > 1) {
				int m = 0;
				while(m < (queue.size() - 1)) {
					if(queue.get(m).getValue() == queue.get(m + 1).getValue()) {
						queue.get(m).doubleValue();
						int postion = queue.get(m + 1).getRow() * 4 + queue.get(m + 1).getCol();
						idles.add(postion);
						queue.remove(m + 1);
					}
				
					m++;
				}
			}
			
			for(int n = 0; n < queue.size(); n++) {
				board[i][3 - n] = queue.get(n);
			}
		}
	}
	
	/**
	 * To see if the game is over
	 */
	public boolean isFail(Cell[][] board) {
		// if the board is full
		if(idles.size() == 0) {
			// for each cell
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 4; j++) {
					// if not top line
					if((i - 1) >= 0) {
						if(board[i - 1][j].getValue() == board [i][j].getValue()) 
							return false;
					} else if((i + 1) <= 3) {
						if(board[i][j].getValue() == board[i + 1][j].getValue()) 
							return false;
					} else if((j - 1) >= 0) {
						if(board[i][j].getValue() == board[i][j - 1].getValue()) 
							return false;
					} else if((j + 1 <= 3)) {
						if(board[i][j].getValue() == board[i][j + 1].getValue()) 
							return false;
					}
				}
			}
			
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Set the idle cells to the list
	 */
	public void setIdle() {
		idles.clear();
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if(board[i][j] == null) {
					idles.add(i * 4 + j);
				}
			}
		}
	}

	public LinkedList<Integer> getIdleQueue() {
		return idles;
	}
	
	public Cell[][] getBoard() {
		return board;
	}
	
	public static void main(String[] args) {
		Controller con = new Controller();
		con.start();
	}
}
