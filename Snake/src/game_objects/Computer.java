package game_objects;

import user_interface.*;

public class Computer extends Player {
	
	private Goal currentGoal;
	
	public Computer(int posX, int posY, int squareSize, int numSegments, Board board) {
		super(posX, posY, squareSize, numSegments);
	}
	
	public void setGoal(Goal goal) {
		currentGoal = goal;
	}
	
	public void move(int squareSize) {
		
		assessDirection();
		
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
	
	private void assessDirection() {
		int posX = this.posX;
		int posY = this.posY;
		
		int newPosX = this.posX;
		int newPosY = this.posY;
		
		int goalPosX = currentGoal.getPosX();
		int goalPosY = currentGoal.getPosY();
		
		if(direction == Move.UP) {
			posY = posY - squareSize;
		} else if(direction == Move.DOWN) {
			posY = posY + squareSize;
		} else if(direction == Move.LEFT) {
			posX = posX - squareSize;
		} else if(direction == Move.RIGHT) {
			posX = posX + squareSize;
		}
	}
	
}
