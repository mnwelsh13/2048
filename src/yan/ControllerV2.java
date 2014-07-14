package yan;

public class ControllerV2 {
	// the board of the game to hold the cells
	private int[][] board = new int[4][4];
	// the number of idle cells
	private int idles = 16;
	//state of the game
	private boolean isChanged = false;
	// score of the game
	private int score = 0;
	
	/**
	 * Create the game with two cells.
	 */
	public void newGame() {
		board = new int[4][4];
		idles = 16;
		isChanged = false;
		score = 0;
		
		// add two cells to the board
		addNumber();
		addNumber();
	}
	
	/**
	 * Add a new number to .
	 */
	private void addNumber() {
		if(idles > 0) {
			// get a position randomly
			int position = (int) (Math.random() * 16);
			
			// while position is 16 or position is not available, re-roll
			while(position == 16 || board[position / 4][position % 4] != 0) {
				position = (int) (Math.random() * 16);
			}

			// get a position randomly if random is bigger than 5, set value 4, else set 2
			int value = (int) (Math.random() * 10) > 5 ? 4 : 2;
			
			board[position / 4][position % 4] = value;
			idles--;
		}
	}
	
	/**
	 * Spread different situations
	 * @param direction
	 */
	public void move(int direction) {
		switch(direction) {
			case 1 : isChanged = moveUp(); break;
			case 2 : isChanged = moveDown(); break;
			case 3 : isChanged = moveLeft(); break;
			case 4 : isChanged = moveRight(); break;
			default : System.out.println("Invaild Command!");
		}
		
		if(isChanged) {
			addNumber();
		}
	}
	
	/**
	 * Move the cells up.
	 */
	public boolean moveUp() {
		boolean result = false;
		// for each cell in the board merge the same numbers
		for(int i = 0; i < 4; i++) { // each col		
			int limit = -1; // the last number which is not merged
			for(int j = 1; j < 4; j++) { // each row
				if(board[j][i] != 0) { // not blank
					int m = j - 1; // the cell up to board[j][i]
					while(m >= 0) { // cell exist
						if(board[m][i] == 0) { // cell is blank
							if(m == 0) { // if the cell is the top
								// exchange the value of board[j][i] and the top
								board[m][i] = board[j][i];
								board[j][i] = 0;
								limit = m; // the last number that not merged is the top
								result = true; // the board is changed
								break;
							} else {
								m--; // else, move to the upper one
								continue;
							}
						} else if(board[m][i] == board[j][i] && m > limit){ // the value is the same
							// merge the two cells, add scores
							board[j][i] = 0;
							board[m][i] *= 2;
							limit = m;
							idles++;
							result = true;
							score += board[m][i];
							break;
						} else if((m + 1) != j){ // move the value of board[j][i] to [m + 1][i]
							int temp = board[j][i];
							board[j][i] = 0;
							board[m + 1][i] = temp;
							result = true;
							break;
						} else {
							break;
						}
					}
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Move the cells down.
	 */
	public boolean moveDown() {
		boolean result = false;
		// for each cell in the board merge the same
		for(int i = 0; i < 4; i++) {
			int limit = 4;
			for(int j = 2; j >= 0; j--) {				
				if(board[j][i] != 0) {
					int m = j + 1;
					while(m < 4) {
						if(board[m][i] == 0) {
							if(m == 3) {
								board[m][i] = board[j][i];
								board[j][i] = 0;
								limit = m;
								result = true;
								break;
							} else {
								m++;
								continue;
							}
						} else if(board[m][i] == board[j][i] && m < limit) {
							board[j][i] = 0;
							board[m][i] *= 2;
							limit = m;
							idles++;
							result = true;
							score += board[m][i];
							break;
						} else if((m - 1) != j) {
							int temp = board[j][i];
							board[j][i] = 0;
							board[m - 1][i] = temp;
							result = true;
							break;
						} else {
							break;
						}
					}
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Move the cells left.
	 */
	public boolean moveLeft() {
		boolean result = false;
		// for each cell in the board merge the same
		for(int i = 0; i < 4; i++) {
			int limit = -1;
			for(int j = 1; j < 4; j++) {				
				if(board[i][j] != 0) {
					int m = j - 1;
					while(m >= 0) {
						if(board[i][m] == 0) {
							if(m == 0) {
								board[i][m] = board[i][j];
								board[i][j] = 0;
								limit = m;
								result = true;
								break;
							} else {
								m--;
								continue;
							}
						} else if(board[i][m] == board[i][j] && m > limit){
							board[i][j] = 0;
							board[i][m] *= 2;
							limit = m;
							idles++;
							result = true;
							score += board[m][i];
							break;
						} else if((m + 1) != j) {
							int temp = board[i][j];
							board[i][j] = 0;
							board[i][m + 1] = temp;
							result = true;
							break;
						} else {
							break;
						}
					}
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Move the cells right.
	 */
	public boolean moveRight() {
		boolean result = false;
		// for each cell in the board merge the same
		for(int i = 0; i < 4; i++) {
			int limit = 4;
			for(int j = 2; j >= 0; j--) {				
				if(board[i][j] != 0) {
					int m = j + 1;
					while(m < 4) {
						if(board[i][m] == 0) {
							if(m == 3) {
								board[i][m] = board[i][j];
								board[i][j] = 0;
								limit = m;
								result = true;
								break;
							} else {
								m++;
								continue;
							}
						} else if(board[i][m] == board[i][j] && m < limit){
							board[i][j] = 0;
							board[i][m] *= 2;
							limit = m;
							idles++;
							result = true;
							score += board[m][i];
							break;
						} else if((m - 1) != j) {
							int temp = board[i][j];
							board[i][j] = 0;
							board[i][m - 1] = temp;
							result = true;
							break;
						} else {
							break;
						}
					}
				}
			}
		}
		
		return result;
	}
	
	public void display() {
		for(int[] line : board) {
			for(int value : line) {
				System.out.print(value + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public int[][] getBoard() {
		return board;
	}

	public int getIdles() {
		return idles;
	}

	public boolean isOver() {
		if(idles <= 0 && isChanged == false) {
			return true;
		}
		
		return false;
	}
	
	public boolean isChanged() {
		return isChanged;
	}

	public int getScore() {
		return score;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}
	
	public static void main(String[] args) {
		ControllerV2 c = new ControllerV2();
		int[][] board = new int[4][4];
		for(int i = 0; i < 16; i++) {
			if(i == 0) {
				board[i / 4][i % 4] = 1;
			} else if(i == 1) {
				board[i / 4][i % 4] = 2;
			} else if(i == 2) {
				board[i / 4][i % 4] = 3;
			} else if(i == 3) {
				board[i / 4][i % 4] = 4;
			}
		}
		c.setBoard(board);
		c.display();
		c.moveRight();
		c.display();		
	}
}
