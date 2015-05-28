package util;

import game.StrategyGame;

public class Location {
	
	private int x;
	private int y;
	
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Location [x=" + x + ", y=" + y + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + round(x, StrategyGame.squareSize);
		result = prime * result + round(y, StrategyGame.squareSize);
		return result;
	}
	
	private int round(double num, int multipleOf) {
		return (int) Math.round((num + multipleOf/2) / multipleOf) * multipleOf;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if ((x < other.x) || (x > other.x + StrategyGame.squareSize))
			return false;
		if ((y < other.y) || (y > other.y + StrategyGame.squareSize))
			return false;
		return true;
	}
	
	
	
}
