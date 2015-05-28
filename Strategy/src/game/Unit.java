package game;

import util.Location;
import util.enums.UnitType;

public class Unit {
	
	private int strength;
	private int speed;
	private Location position;
	private UnitType type;
	private boolean active = false;
	
	public Unit() {
		
	}
	
	public Unit(int strength, int speed, Location position, UnitType type) {
		this.strength = strength;
		this.speed = speed;
		this.position = position;
		this.type = type;
	}
	
	public int getStrength() {
		return strength;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public Location getPosition() {
		return position;
	}
	
	public UnitType getType() {
		return type;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setStrength(int strength) {
		this.strength = strength;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void setPosition(Location pos) {
		this.position = pos;
	}
	
	public void setType(UnitType type) {
		this.type = type;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Unit [strength=" + strength + ", speed=" + speed
				+ ", position=" + position + ", type=" + type + ", active="
				+ active + "]";
	}
	
}