package yan;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI {
	private JFrame frame;
	private JPanel boardPanel, controlPanel, arrowPanel;
	private JTextField scoreLabel, score;
	private JButton newGame, up, down, left, right;
	private JTextField[] cells;
	
	Controller controller;
	
	public GUI() {
		init();
		controller = new Controller();
	}
	
	/**
	 * initialize the components.
	 */
	public void init() {
		frame = new JFrame();
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		
		boardPanel = new JPanel(new GridLayout(4, 4));
		boardPanel.setSize(400, 500);
		
		cells = new JTextField[16];
		
		for(int i = 0; i < 16; i++) {
			JTextField text = new JTextField();
			//text.setEditable(false);
			cells[i] = text;
			boardPanel.add(text);
		}
		
		mainPanel.add(boardPanel);
		
		controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
		
		scoreLabel = new JTextField("SCORE");
		scoreLabel.setEditable(false);
		controlPanel.add(scoreLabel);
		
		score = new JTextField("0");
		score.setEditable(false);
		controlPanel.add(score);
		
		newGame = new JButton("New Game");
		newGame.addActionListener(new NewGameListener());
		controlPanel.add(newGame);
		
		arrowPanel = new JPanel(new GridLayout(2, 2));
		up = new JButton("UP");
		
		DirectionListener dListener = new DirectionListener();
		up.addActionListener(dListener);
		down = new JButton("DOWN");
		down.addActionListener(dListener);
		left = new JButton("LEFT");
		left.addActionListener(dListener);
		right = new JButton("RIGHT");
		right.addActionListener(dListener);
		
		arrowPanel.add(up);
		arrowPanel.add(left);
		arrowPanel.add(down);
		arrowPanel.add(right);
		
		controlPanel.add(arrowPanel);
		
		mainPanel.add(controlPanel);
		
		frame.add(mainPanel);
		frame.setSize(600, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void refresh() {
		Cell[][] board = controller.getBoard();
		for(int i = 0; i < 16; i++) {
			if(board[i / 4][i % 4] == null) continue;			
			cells[i].setText(Integer.toString(board[i / 4][i % 4].getValue()));
		}
	}

	public static void main(String[] args) {
		GUI gui = new GUI();
	}
	
	class DirectionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == up) {
				controller.move("u");
				refresh();
			} else if(e.getSource() == down) {
				controller.move("d");
				refresh();
			} else if(e.getSource() == left) {
				controller.move("l");
				refresh();
			} else if(e.getSource() == right) {
				controller.move("r");
				refresh();
			}		
		}
	}
	
	class NewGameListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(newGame)) {
				controller.newGame();
				refresh();
			}
		}
		
	}

}
