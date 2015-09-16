package game;

import java.awt.Dimension;
import java.util.List;
import java.util.ArrayList;

import util.*;
import util.enums.*;

public class WorldMap {
	
	private Dimension size;
	private Terrain[][] terrain;

	public Dimension getSize() {
		return size;
	}

	public Terrain[][] getTerrain() {
		return terrain;
	}
	
	public Terrain getSquare(int x, int y) {
		return terrain[x][y];
	}

	public void setSize(Dimension size) {
		this.size = size;
		
		terrain = new Terrain[size.width / Globals.squareSize][size.height / Globals.squareSize];
	}

	public void setTerrain(Terrain[][] terrain) {
		this.terrain = terrain;
	}
	
}
