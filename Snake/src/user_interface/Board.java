package user_interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import game_objects.*;

public class Board extends JPanel implements ActionListener {
	
	private Timer timer;
	private static final int squareSize = 10;
	private Player player;
	private Goal goal;
	private int score = 0;
	
	private JLabel statusbar;
	
	//private Difficulty diff = new Difficulty();
	
	// Flags
	private boolean setup = false;
	private boolean newGoal = false;
	private boolean hit = false;
	
	public Board(Snake parent) {
		initBoard(parent);
	}
	
	private void initBoard(Snake parent) {
		timer = new Timer(100, this);
		timer.start();
		
		statusbar = parent.getStatusbar();
		
		setFocusable(true);
		setBackground(Color.BLACK);
		addKeyListener(new SAdapter());
		
	}
	
	private void doDrawing(Graphics g) {
		Dimension size = getSize();
		
		if(!setup) {
			setup = setupBoard();
		}
		
		if(newGoal) {
			newGoal = updateBoard();
		}
		
		drawGoal(g);
		drawPlayer(g);
	}
	
	private boolean setupBoard() {
		Dimension size = getSize();
		
		setupPlayer(size);
		setupGoal(size);
		
		return true;
	}
	
	private boolean updateBoard() {
		Dimension size = getSize();
		
		setupGoal(size);
		updateScore();
		player.addSegment();
		statusbar.setText(String.valueOf(score));
		
		return true;
	}
	
	private void setupPlayer(Dimension size) {
		int randX = roundUp((int) (Math.random() * size.getWidth()), 10);
		int randY = roundUp((int) (Math.random() * size.getHeight()), 10);
		player = new Player(randX, randY, squareSize);
	}
	
	private boolean setupGoal(Dimension size) {
		int randX = roundUp((int) (Math.random() * size.getWidth()), 10);
		int randY = roundUp((int) (Math.random() * size.getHeight()), 10);
		goal = new Goal(randX, randY);
		
		return false;
	}
	
	private int roundUp(int num, int round) {
		return (int) Math.ceil(num / round) * round;
	}
	
	private void updateScore() {
		score = score + 1;
	}
	
	private void drawPlayer(Graphics g) {
		g.setColor(new Color(0,110,255));
		for(Segment seg : player.getSegments()) {
			g.fillRect(seg.getPosX(), seg.getPosY(), squareSize, squareSize);
		}
	}
	
	private void drawGoal(Graphics g) {
		g.setColor(new Color(210,0,0));
		g.fillRect(goal.getPosX(), goal.getPosY(), squareSize, squareSize);
	}
	
	public void start() {
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		player.move(squareSize);
		Dimension size = getSize();
		
		newGoal = player.detectGoalCollision(goal);
		hit = player.detectWallCollision(size);
		if(hit != true) {
			hit = player.detectSelfCollision();
		}
		
		if(hit == true) {
			timer.stop();
		}
		
		repaint();
	}
	
	class SAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			Move dir = Move.UP;
			switch(e.getKeyCode()) {
				case 38:
					dir = Move.UP;
					break;
				case 39:
					dir = Move.RIGHT;
					break;
				case 40:
					dir = Move.DOWN;
					break;
				case 37:
					dir = Move.LEFT;
					break;
			}
			player.setDirection(dir);
		}
		
	}

}