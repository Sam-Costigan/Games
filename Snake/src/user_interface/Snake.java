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
	
	private JPanel menu;
	private JPanel endGame;
	private Board game;
	
	private Difficulty easy = new Difficulty(200, 3, "tune-easy.wav");
	private Difficulty medium = new Difficulty(100, 5, "tune-med.wav");
	private Difficulty hard  = new Difficulty(50, 8, "tune-hard.wav");
	private Difficulty hardComputer  = new Difficulty(50, 8, "tune-hard.wav", true);
	
	public Snake() {
		initUI();
	}
	
	private void initUI() {
		
		statusbar = new JLabel(" 0");
		statusbar.setOpaque(false);
		statusbar.setForeground(Color.WHITE);
		
		createMenuBar();
		
		setSize(606,628);
		setTitle("Snake");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	private void createMenuBar() {
		menu = new JPanel();
		
		JButton easyBtn = new JButton("Easy");
		easyBtn.setActionCommand("Easy");
		easyBtn.addActionListener(this);
		
		JButton medBtn = new JButton("Medium");
		medBtn.setActionCommand("Medium");
		medBtn.addActionListener(this);
		
		JButton hardBtn = new JButton("Hard");
		hardBtn.setActionCommand("Hard");
		hardBtn.addActionListener(this);
		
		JButton computerBtn = new JButton("Watch Computer");
		computerBtn.setActionCommand("Hard");
		computerBtn.addActionListener(this);
		
		menu.add(easyBtn);
		menu.add(medBtn);
		menu.add(hardBtn);
		menu.add(computerBtn);
		
		add(menu, BorderLayout.CENTER);
	}
	
	private void startGame(Difficulty diff) {
		game = new Board(this, diff);
		add(game);
		game.add(statusbar, BorderLayout.NORTH);
		game.start();
		
		menu.setVisible(false);
	}
	
	public void endGame(int score) {
		game.setVisible(false);
		menu.setVisible(true);
		
		endGame = new JPanel();
		
		JLabel overText = new JLabel("Game Over!");
		JLabel scoreText = new JLabel("Your score: " + score);
		
		endGame.add(overText);
		endGame.add(scoreText);
		
		endGame.setVisible(true);
		add(endGame, BorderLayout.CENTER);
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
		if(e.getActionCommand().equals("Easy")) {
			startGame(easy);
		} else if(e.getActionCommand().equals("Medium")) {
			startGame(medium);
		} else if(e.getActionCommand().equals("Hard")) {
			startGame(hard);
		}
	}

}
