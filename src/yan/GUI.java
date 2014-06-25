package yan;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel boardPanel, controlPanel, arrowPanel;
	private JTextField scoreLabel, score;
	private JButton newGame, up, down, left, right;
	private JButton[] cells;
	
	ControllerV2 controller;
	
	public GUI() {
		init();
		controller = new ControllerV2();
	}
	
	/**
	 * initialize the components.
	 */
	public void init() {	
		boardPanel = new JPanel(new GridLayout(4, 4));
		boardPanel.setPreferredSize(new Dimension(500, 500));
		
		cells = new JButton[16];
		
		for(int i = 0; i < 16; i++) {
			cells[i] = new JButton();
			cells[i].setFont(new Font("Serief", Font.BOLD, 35));
			boardPanel.add(cells[i]);
		}
		
		this.getContentPane().add(BorderLayout.WEST, boardPanel);
		
		controlPanel = new JPanel();
		controlPanel.setPreferredSize(new Dimension(200, 500));
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
		
		scoreLabel = new JTextField("SCORE");
		scoreLabel.setFont(new Font("Serief", Font.BOLD, 35));
		scoreLabel.setPreferredSize(new Dimension(200, 50));
		scoreLabel.setEditable(false);
		controlPanel.add(scoreLabel);
		
		score = new JTextField("0");
		score.setPreferredSize(new Dimension(200, 200));
		score.setFont(new Font("Serief", Font.BOLD, 30));
		score.setEditable(false);
		controlPanel.add(score);
		
		JPanel newButtonPanel = new JPanel();
		newGame = new JButton("New Game");
		newGame.setPreferredSize(new Dimension(200, 70));
		newGame.addActionListener(new NewGameListener());
		newButtonPanel.add(newGame);
		controlPanel.add(newButtonPanel);
		
		
		arrowPanel = new JPanel(new GridLayout(2, 2));
		arrowPanel.setPreferredSize(new Dimension(100, 150));
		DirectionListener dListener = new DirectionListener();
		up = new JButton("UP");
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
		
		this.getContentPane().add(BorderLayout.EAST, controlPanel);
		
		this.setSize(700, 500);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void refreshUI() {
		int[][] board = controller.getBoard();
		for(int i = 0; i < 16; i++) {
			if(board[i / 4][i % 4] == 0) {
				cells[i].setText("");
			} else {
				int value = board[i / 4][i % 4];
				cells[i].setText(Integer.toString(value));
			}
		}
		
		score.setText(Integer.toString(controller.getScore()));
	}
	
	public void gameOver() {
		cells[0].setText("");
		cells[1].setText("");
		cells[2].setText("");
		cells[3].setText("");
		cells[4].setText("G");
		cells[5].setText("A");
		cells[6].setText("M");
		cells[7].setText("E");
		cells[8].setText("O");
		cells[9].setText("V");
		cells[10].setText("E");
		cells[11].setText("R");
		cells[12].setText("");
		cells[13].setText("");
		cells[14].setText("");
		cells[15].setText("");
	}

	public static void main(String[] args) {
		GUI gui = new GUI();
	}
	
	class DirectionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(up)) {
				controller.move(1);
			} else if(e.getSource().equals(down)) {
				controller.move(2);
			} else if(e.getSource().equals(left)) {
				controller.move(3);
			} else if(e.getSource().equals(right)) {
				controller.move(4);
			}
			
			if(!controller.isOver()) {
				refreshUI();
			} else {
				gameOver();
			}	
		}
	}
	
	class NewGameListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			controller.newGame();
			refreshUI();
		}		
	}
}
