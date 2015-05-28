package util;

import game.Unit;

public class Square {
	
	private Location position;
	private Unit unit;
	
	public Square(Location position, Unit unit) {
		this.position = position;
		this.unit = unit;
	}
	
	public Location getPosition() {
		return position;
	}
	
	public Unit getUnit() {
		return unit;
	}
	
	public void setPosition(Location position) {
		this.position = position;
	}
	
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
	
	
}
