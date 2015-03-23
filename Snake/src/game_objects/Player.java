package game_objects;

import java.util.ArrayList;

import user_interface.Move;

public class Player {
	
	private int posX;
	private int posY;
	private int numSegments = 10;
	private Move direction = Move.UP;
	private ArrayList<Segment> segments = new ArrayList<Segment>();
	
	public Player(int posX, int posY, int squareSize) {
		this.posX = posX;
		this.posY = posY;
		
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
		this.direction = direction;
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

	@Override
	public String toString() {
		return "Player [posX=" + posX + ", posY=" + posY + ", numSegments="
				+ numSegments + "]";
	}
	
}