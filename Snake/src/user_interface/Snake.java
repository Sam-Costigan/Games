package user_interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * 
 * @author Sam Costigan
 *
 */
public class Snake extends JFrame implements ActionListener {
	
	private JLabel statusbar;
	private JButton startBtn;
	
	private JPanel menu;
	private Board game;
	
	public Snake() {
		initUI();
	}
	
	private void initUI() {
		
		statusbar = new JLabel(" 0");
		statusbar.setOpaque(false);
		statusbar.setForeground(Color.WHITE);
		
		createMenuBar();
		
		setSize(416,438);
		setTitle("Snake");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	private void createMenuBar() {
		menu = new JPanel();
		
		startBtn = new JButton("Start Game");
		startBtn.setActionCommand("Start");
		startBtn.addActionListener(this);
		
		menu.add(startBtn);
		
		add(menu, BorderLayout.NORTH);
	}
	
	private void startGame() {
		game = new Board(this);
		add(game);
		game.add(statusbar, BorderLayout.NORTH);
		game.start();
		
		menu.setVisible(false);
	}
	
	public JLabel getStatusbar() {
		return statusbar;
	}
	
	public static void main(String[] args) {	
		Snake game = new Snake();
		game.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Start")) {
			startGame();
		}
	}

}
