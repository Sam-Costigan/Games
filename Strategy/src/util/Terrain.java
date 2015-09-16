package util;

import java.util.Arrays;

import util.enums.TerrainType;

public class Terrain {
	
	private Location position;
	private TerrainType type;
	private int x;
	private int y;
	private int z;
	private boolean navigable;
	
	private int[] neighbourIndexes = new int[4];
	
	public Terrain(Location position, TerrainType type, int x, int y, int z) {
		this.position = position;
		this.type = type;
		this.x = x;
		this.y = y;
		this.z = z;
		
		if(this.type == TerrainType.GRASS) {
			navigable = true;
		}
	}
	
	public Location getPosition() {
		return position;
	}

	public TerrainType getType() {
		return type;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}
	
	public int[] getNeighbours() {
		return neighbourIndexes;
	}
	
	public boolean isNavigable() {
		return navigable;
	}

	public void setPosition(Location position) {
		this.position = position;
	}

	public void setType(TerrainType type) {
		this.type = type;
	}

	public void setZ(int z) {
		this.z = z;
	}
	
	public void setNavigable(boolean navigable) {
		this.navigable = navigable;
	}

	@Override
	public String toString() {
		return "Terrain [position=" + position + ", type=" + type + ", z=" + z
				+ ", navigable=" + navigable + ", neighbourIndexes="
				+ Arrays.toString(neighbourIndexes) + "]";
	}
	
}
