package user_interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

import game_objects.*;

public class Board extends JPanel implements ActionListener {
	
	private Timer timer;
	private static final int squareSize = 10;
	private Player player;
	private Goal goal;
	private int score = 0;
	
	private Snake parent;
	private JLabel statusbar;
	
	//private Difficulty diff = new Difficulty();
	
	// Flags
	private boolean setup = false;
	private boolean newGoal = false;
	private boolean hit = false;
	private boolean isRunning = false;
	
	public Board(Snake parent) {
		this.parent = parent;
	}
	
	private void initBoard() {
		timer = new Timer(100, this);
		timer.start();
		
		statusbar = parent.getStatusbar();
		
		setFocusable(true);
		setBackground(Color.BLACK);
		addKeyListener(new SAdapter());
		
	}
	
	private void drawStart(Graphics g) {
		
	}
	
	private void drawGame(Graphics g) {
		
		if(!setup) {
			setup = setupBoard();
		}
		
		if(newGoal) {
			newGoal = updateBoard();
		}
		
		drawGoal(g);
		drawPlayer(g);
	}
	
	private void drawEnd(Graphics g) {
		Dimension size = getSize();
		Font font =  new Font("Verdana", Font.PLAIN, 16);
		g.setFont(font);
		g.setColor(Color.WHITE);
		
		FontMetrics fontMetric = g.getFontMetrics();
		
		String overStr = "Game Over";
		String scoreStr = "Your score: " + score;
		
		Rectangle2D overRect = fontMetric.getStringBounds(overStr, g);
		Rectangle2D scoreRect = fontMetric.getStringBounds(scoreStr, g);
		
		g.drawString(overStr, (int) (size.getWidth() - overRect.getWidth()) / 2, (int) (size.getHeight() - overRect.getHeight()) / 2);
		g.drawString(scoreStr, (int) (size.getWidth() - scoreRect.getWidth()) / 2, (int) ((size.getHeight() - scoreRect.getHeight()) / 2) + (int) (overRect.getHeight() + 10));
	}
	
	private boolean setupBoard() {
		Dimension size = getSize();
		
		setupPlayer(size);
		setupGoal(size);
		
		return true;
	}
	
	private boolean updateBoard() {
		Dimension size = getSize();
		
		System.out.println("yes");
		
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
		initBoard();
		//setupBoard();
		isRunning = true;
		repaint();
		
	}
	
	public void end() {
		timer.stop();
		isRunning = false;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(isRunning) {
			drawGame(g);
		} else {
			drawEnd(g);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(isRunning) {
			if(player != null) {
				player.move(squareSize);
				Dimension size = getSize();
				
				newGoal = player.detectGoalCollision(goal);
				hit = player.detectWallCollision(size);
				if(hit != true) {
					hit = player.detectSelfCollision();
				}
				
				if(hit == true) {
					end();
				}
			}
			
			repaint();
		}
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