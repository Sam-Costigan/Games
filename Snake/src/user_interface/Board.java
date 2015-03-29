package user_interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.ArrayList;

import sun.audio.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import game_objects.*;

public class Board extends JPanel implements ActionListener {
	
	private Timer timer;
	private static final int squareSize = 20;
	private Player player;
	private Goal goal;
	private int score = 0;
	private Difficulty diff;
	
	private Snake parent;
	private JLabel statusbar;
	
	// Flags
	private boolean setup = false;
	private boolean newGoal = false;
	private boolean hit = false;
	private boolean isRunning = false;
	
	private String imageDir = "resources/images/";
	
	public Board(Snake parent, Difficulty diff) {
		this.parent = parent;
		this.diff = diff;
	}
	
	private void initBoard() {
		timer = new Timer(diff.getSpeedMS(), this);
		timer.start();
		
		statusbar = parent.getStatusbar();
		
		setFocusable(true);
		setBackground(Color.BLACK);
		addKeyListener(new SAdapter());
		
		loopSound(diff.getTune());
	}
	
	private void drawGame(Graphics g) {
		
		if(!setup) {
			setup = setupBoard();
		}
		
		try {
			Image img = ImageIO.read(new File(imageDir + "background.png"));
			g.drawImage(img, 0, 0, null);
		} catch(IOException e) {
			System.out.println(e);
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
		
		setupGoal(size);
		updateScore();
		player.addSegment();
		statusbar.setText(String.valueOf(score));
		
		return true;
	}
	
	private void setupPlayer(Dimension size) {
		int randX = roundUp((int) (Math.random() * size.getWidth()), squareSize);
		int randY = roundUp((int) (Math.random() * size.getHeight()), squareSize);
		player = new Player(randX, randY, squareSize, diff.getSegmentsStart());
	}
	
	private boolean setupGoal(Dimension size) {
		int randX = roundUp((int) (Math.random() * size.getWidth()), squareSize);
		int randY = roundUp((int) (Math.random() * size.getHeight()), squareSize);
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
		int count = 0;
		ArrayList<Segment> segments = player.getSegments();
		for(Segment seg : segments) {
			if(count == 0) {
				try {
					Move direction = player.getDirection();
					Image img = null;
					if(direction.equals(Move.UP)) {
						img = ImageIO.read(new File(imageDir + "head-up.png"));
					} else if(direction.equals(Move.DOWN)) {
						img = ImageIO.read(new File(imageDir + "head-down.png"));
					} else if(direction.equals(Move.LEFT)) {
						img = ImageIO.read(new File(imageDir + "head-left.png"));
					} else {
						img = ImageIO.read(new File(imageDir + "head-right.png"));
					}
					g.drawImage(img, seg.getPosX(), seg.getPosY(), null);
				} catch(IOException e) {
					System.out.println(e);
				}
			} else {
				try {
					if((count + 1) < segments.size()) {
						Segment prevSegment = segments.get(count - 1);
						Segment nextSegment = segments.get(count + 1);
						Image img = null;
						
						if(prevSegment.getPosX() == seg.getPosX() && nextSegment.getPosX() == seg.getPosX()) {
							if(count % 2 != 0) {
								img = ImageIO.read(new File(imageDir + "body-up.png"));
							} else {
								img = ImageIO.read(new File(imageDir + "body-down.png"));
							}
						} else if(prevSegment.getPosY() == seg.getPosY() && nextSegment.getPosY() == seg.getPosY()) {
							if(count % 2 != 0) {
								img = ImageIO.read(new File(imageDir + "body-right.png"));
							} else {
								img = ImageIO.read(new File(imageDir + "body-left.png"));
							}
						} else {
							if((prevSegment.getPosX() < seg.getPosX()
									&& prevSegment.getPosY() == seg.getPosY()
									&& nextSegment.getPosY() > seg.getPosY()
									&& nextSegment.getPosX() == seg.getPosX())
									||
									(nextSegment.getPosX() < seg.getPosX()
									&& nextSegment.getPosY() == seg.getPosY()
									&& prevSegment.getPosY() > seg.getPosY()
									&& prevSegment.getPosX() == seg.getPosX())) {
								img = ImageIO.read(new File(imageDir + "corner-left-down.png"));
							} else if((prevSegment.getPosX() == seg.getPosX()
									&& prevSegment.getPosY() < seg.getPosY()
									&& nextSegment.getPosY() == seg.getPosY()
									&& nextSegment.getPosX() > seg.getPosX())
									||
									(nextSegment.getPosX() == seg.getPosX()
									&& nextSegment.getPosY() < seg.getPosY()
									&& prevSegment.getPosY() == seg.getPosY()
									&& prevSegment.getPosX() > seg.getPosX())) {
								img = ImageIO.read(new File(imageDir + "corner-right-up.png"));
							} else if((prevSegment.getPosX() > seg.getPosX()
									&& prevSegment.getPosY() == seg.getPosY()
									&& nextSegment.getPosY() > seg.getPosY()
									&& nextSegment.getPosX() == seg.getPosX())
									||
									(nextSegment.getPosX() > seg.getPosX()
									&& nextSegment.getPosY() == seg.getPosY()
									&& prevSegment.getPosY() > seg.getPosY()
									&& prevSegment.getPosX() == seg.getPosX())) {
								img = ImageIO.read(new File(imageDir + "corner-down-right.png"));
							} else if((prevSegment.getPosX() == seg.getPosX()
									&& prevSegment.getPosY() < seg.getPosY()
									&& nextSegment.getPosY() == seg.getPosY()
									&& nextSegment.getPosX() < seg.getPosX())
									||
									(nextSegment.getPosX() == seg.getPosX()
									&& nextSegment.getPosY() < seg.getPosY()
									&& prevSegment.getPosY() == seg.getPosY()
									&& prevSegment.getPosX() < seg.getPosX())) {
								img = ImageIO.read(new File(imageDir + "corner-up-left.png"));
							}
						}
						
						g.drawImage(img, seg.getPosX(), seg.getPosY(), null);
					} else {
						Segment prevSegment = segments.get(count - 1);
						Image img = null;
						
						if(prevSegment.getPosY() == seg.getPosY() && prevSegment.getPosX() > seg.getPosX()) {
							img = ImageIO.read(new File(imageDir + "tail-right.png"));
						} else if(prevSegment.getPosY() == seg.getPosY() && prevSegment.getPosX() < seg.getPosX()) {
							img = ImageIO.read(new File(imageDir + "tail-left.png"));
						} else if(prevSegment.getPosY() > seg.getPosY() && prevSegment.getPosX() == seg.getPosX()) {
							img = ImageIO.read(new File(imageDir + "tail-down.png"));
						} else {
							img = ImageIO.read(new File(imageDir + "tail-up.png"));
						}
						
						g.drawImage(img, seg.getPosX(), seg.getPosY(), null);
					}
				} catch(IOException e) {
					System.out.println(e);
				}
			}
			count++;
		}
	}
	
	private void drawGoal(Graphics g) {
		g.setColor(new Color(210,0,0));
		try {
			Image img = ImageIO.read(new File("resources/images/goal.png"));
			g.drawImage(img, goal.getPosX(), goal.getPosY(), null);
		} catch(IOException e) {
			System.out.println(e);
		}
	}
	
	private void playSound(String file) {
		try {
			Clip clip = AudioSystem.getClip();
			File soundFile = new File("resources/sounds/" + file);
	        AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundFile);
			clip.open(inputStream);
			clip.start();
	        
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	private void loopSound(String file) {
		try {
			Clip clip = AudioSystem.getClip();
			File soundFile = new File("resources/sounds/" + file);
	        AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundFile);
			clip.open(inputStream);
			clip.loop(Integer.MAX_VALUE);
	        
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void start() {
		initBoard();
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
				//AudioPlayer.player.stop(eat);
				
				player.move(squareSize);
				Dimension size = getSize();
				
				newGoal = player.detectGoalCollision(goal);
				hit = player.detectWallCollision(size);
				if(hit != true) {
					hit = player.detectSelfCollision();
				}
				
				if(newGoal) {
					playSound("eat.wav");
					updateBoard();
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