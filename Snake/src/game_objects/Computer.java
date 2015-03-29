package game_objects;

import java.awt.Dimension;

import user_interface.*;

public class Computer extends Player {
	
	private Goal currentGoal;
	private Board board;
	private Dimension size;
	
	public Computer(int posX, int posY, int squareSize, int numSegments, Board board) {
		super(posX, posY, squareSize, numSegments);
		this.board = board;
		this.size = board.getSize();
	}
	
	public void setGoal(Goal goal) {
		currentGoal = goal;
	}
	
	public void move(int squareSize) {
		
		this.direction = assessDirection(direction, posX, posY);
		
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
	
	private Move assessDirection(Move dir, int posX, int posY) {
		
		int newPosX = posX;
		int newPosY = posY;
		
		int goalPosX = currentGoal.getPosX();
		int goalPosY = currentGoal.getPosY();
		
		int diffX = 0;
		int diffY = 0;
		
		if(dir == Move.UP) {
			newPosX = posY - squareSize;
		} else if(dir == Move.DOWN) {
			newPosX = posY + squareSize;
		} else if(dir == Move.LEFT) {
			newPosY = posX - squareSize;
		} else if(dir == Move.RIGHT) {
			newPosY = posX + squareSize;
		}
		
		while(detectWallCollision(newPosX, newPosY)) {
			dir = reassessDirection(rightTurn(dir), posX, posY);
		}
		
		return dir;
	}
	
	private Move reassessDirection(Move dir, int posX, int posY) {
		int newPosX = posX;
		int newPosY = posY;
		
		int goalPosX = currentGoal.getPosX();
		int goalPosY = currentGoal.getPosY();
		
		int diffX = 0;
		int diffY = 0;
		
		if(dir == Move.UP) {
			newPosX = posY - squareSize;
		} else if(dir == Move.DOWN) {
			newPosX = posY + squareSize;
		} else if(dir == Move.LEFT) {
			newPosY = posX - squareSize;
		} else if(dir == Move.RIGHT) {
			newPosY = posX + squareSize;
		}
		
		if(detectWallCollision(newPosX, newPosY)) {
			dir = rightTurn(dir);
		}
		
		return dir;
	}
	
	private boolean detectWallCollision(int posX, int posY) {
		if(posX > (size.getWidth()) || posY > (size.getWidth())) {
			return true;
		}
		if(posX < 0 || posY < 0) {
			return true;
		}
		return false;
	}
	
	private Move rightTurn(Move dir) {
		if(dir == Move.UP) {
			return Move.RIGHT;
		} else if(dir == Move.RIGHT) {
			return Move.DOWN;
		} else if(dir == Move.DOWN) {
			return Move.LEFT;
		} else {
			return Move.UP;
		}
	}
	
	private Move leftTurn(Move dir) {
		if(dir == Move.UP) {
			return Move.LEFT;
		} else if(dir == Move.LEFT) {
			return Move.DOWN;
		} else if(dir == Move.DOWN) {
			return Move.RIGHT;
		} else {
			return Move.UP;
		}
	}
	
}
