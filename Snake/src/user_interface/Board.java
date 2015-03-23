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
	private boolean setup = false;
	
	public Board() {
		initBoard();
	}
	
	private void initBoard() {
		timer = new Timer(200, this);
		timer.start();
		
		setFocusable(true);
		setBackground(Color.BLACK);
		addKeyListener(new SAdapter());
		
	}
	
	private void doDrawing(Graphics g) {
		Dimension size = getSize();
		
		if(!setup) {
			setup = setupBoard();
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
	
	private void setupPlayer(Dimension size) {
		int randX = roundUp((int) (Math.random() * size.getWidth()), 10);
		int randY = roundUp((int) (Math.random() * size.getHeight()), 10);
		player = new Player(randX, randY, squareSize);
	}
	
	private void setupGoal(Dimension size) {
		int randX = roundUp((int) (Math.random() * size.getWidth()), 10);
		int randY = roundUp((int) (Math.random() * size.getHeight()), 10);
		goal = new Goal(randX, randY);
	}
	
	private int roundUp(int num, int round) {
		return (int) Math.ceil(num / round) * round;
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