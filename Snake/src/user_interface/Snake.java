package user_interface;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

/**
 * 
 * @author Sam Costigan
 *
 */
public class Snake extends JFrame {
	
	private JLabel statusbar;
	
	public Snake() {
		initUI();
	}
	
	private void initUI() {
		
		statusbar = new JLabel(" 0");
		statusbar.setOpaque(true);
		statusbar.setBackground(Color.GRAY);
		statusbar.setForeground(Color.WHITE);
		add(statusbar, BorderLayout.NORTH);
		
		Board board = new Board(this);
		add(board);
		board.start();
		
		setSize(416,454);
		setTitle("Snake");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public JLabel getStatusbar() {
		return statusbar;
	}
	
	public static void main(String[] args) {	
		Snake game = new Snake();
		game.setVisible(true);
	}

}
