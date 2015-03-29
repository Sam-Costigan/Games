package game_objects;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;

import user_interface.Move;

public class Player {
	
	protected int posX;
	protected int posY;
	protected int numSegments = 3;
	protected Move direction = Move.UP;
	protected ArrayList<Segment> segments = new ArrayList<Segment>();
	protected int squareSize = 20;
	
	private final static HashMap<Move, Move> oppositeDirection = new HashMap<Move, Move>();
	static {
		oppositeDirection.put(Move.UP, Move.DOWN);
		oppositeDirection.put(Move.DOWN, Move.UP);
		oppositeDirection.put(Move.LEFT, Move.RIGHT);
		oppositeDirection.put(Move.RIGHT, Move.LEFT);
	}
	
	public Player(int posX, int posY, int squareSize) {
		this.posX = posX;
		this.posY = posY;
		this.squareSize = squareSize;
		this.numSegments = numSegments;
		
		for(int i = 0; i < numSegments; i++) {
			Segment seg = new Segment(posX + (i * squareSize), posY);
			segments.add(i, seg);
		}
	}
	
	public Player(int posX, int posY, int squareSize, int numSegments) {
		this.posX = posX;
		this.posY = posY;
		this.squareSize = squareSize;
		this.numSegments = numSegments;
		
		for(int i = 0; i < numSegments; i++) {
			Segment seg = new Segment(posX + (i * squareSize), posY);
			segments.add(i, seg);
		}
	}

	public int getPosX() {
		return posX;
	}
	public int getPosY() {
		return posY;
	}
	public int getNumSegments() {
		return numSegments;
	}
	public ArrayList<Segment> getSegments() {
		return segments;
	}
	public Move getDirection() {
		return this.direction;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public void setNumSegments(int numSegments) {
		this.numSegments = numSegments;
	}
	public void setDirection(Move direction) {
		if(!direction.equals(oppositeDirection.get(this.direction))) {
			this.direction = direction;
		}
	}
	
	public void move(int squareSize) {
		if(direction == Move.UP) {
			posY = posY - squareSize;
		} else if(direction == Move.DOWN) {
			posY = posY + squareSize;
		} else if(direction == Move.LEFT) {
			posX = posX - squareSize;
		} else if(direction == Move.RIGHT) {
			posX = posX + squareSize;
		}
		
		Segment currentSeg = segments.get(0);
		Segment replacedSeg = null;
		
		for(int i = 0; i < segments.size() - 1; i++) {
			replacedSeg = segments.get(i+1);
			segments.set(i+1, currentSeg);
			currentSeg = replacedSeg;
		}
		
		segments.set(0, new Segment(posX, posY));
	}
	
	public void addSegment() {
		numSegments = numSegments + 1;
		Segment lastSeg = segments.get(segments.size() - 1);
		int newSegX = (direction.equals(Move.LEFT) || direction.equals(Move.RIGHT)) ? lastSeg.getPosX() + squareSize : lastSeg.getPosX();
		int newSegY = (direction.equals(Move.UP) || direction.equals(Move.DOWN)) ? lastSeg.getPosY() + squareSize : lastSeg.getPosY();
		
		Segment newSeg = new Segment(newSegX, newSegY);
		segments.add(newSeg);
	}

	public boolean detectGoalCollision(Goal goal) {
		if(posX == goal.getPosX() && posY == goal.getPosY()) {
			return true;
		}
		return false;
	}
	
	public boolean detectWallCollision(Dimension size) {
		if(posX > (size.getWidth()) || posY > (size.getWidth())) {
			return true;
		}
		if(posX < 0 || posY < 0) {
			return true;
		}
		return false;
	}
	
	public boolean detectSelfCollision() {
		for(Segment seg : segments) {
			if(seg != segments.get(0) && (seg.getPosX() == posX && seg.getPosY() == posY)) {
				return true;
			}
		}
		return false;
	}
	
	public void setGoal(Goal goal) {
	}
	
	@Override
	public String toString() {
		return "Player [posX=" + posX + ", posY=" + posY + ", numSegments="
				+ numSegments + "]";
	}
	
}