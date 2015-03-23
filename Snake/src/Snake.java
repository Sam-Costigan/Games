import java.awt.Color;

import javax.swing.*;

/**
 * 
 * @author Sam Costigan
 *
 */
public class Snake extends JFrame {
	
	public Snake() {
		initUI();
	}
	
	private void initUI() {
		Board board = new Board();
		add(board);
		board.start();
		
		setSize(416,439);
		setTitle("Snake");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {	
		Snake game = new Snake();
		game.setVisible(true);
	}

}
