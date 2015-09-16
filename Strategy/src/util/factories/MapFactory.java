package util.factories;

import game.WorldMap;

import java.awt.Dimension;
import java.util.Arrays;

import util.Globals;
import util.Location;
import util.Terrain;
import util.enums.TerrainType;

public class MapFactory {

	public static WorldMap generateRandomMap() {
		WorldMap randMap = new WorldMap();
		
		int min = Globals.minMapSize;
		int max = Globals.maxMapSize;
		
		int randWidth = generateRandomLength(min, max);
		int randHeight = generateRandomLength(min, max);
		randMap.setSize(new Dimension(randWidth, randHeight));
		
		for(int x = 0; x < (randWidth / Globals.squareSize); x++) {
			for(int y = 0; y < (randHeight / Globals.squareSize); y++) {
				Terrain terrain = generateRandomTerrain(x * Globals.squareSize, y * Globals.squareSize);
				int[] neighbours = terrain.getNeighbours();
				
				int left = x - 1;
				int right = x + 1;
				int top = y - 1;
				int bottom = y + 1;
				
				if(left >= 0) {
					neighbours[0] = left;
				} else {
					neighbours[0] = -1;
				}
				
				if(right < (randWidth / Globals.squareSize)) {
					neighbours[1] = right;
				} else {
					neighbours[1] = -1;
				}
				
				if(top >= 0) {
					neighbours[2] = top;
				} else {
					neighbours[2] = -1;
				}
				
				if(bottom < (randHeight / Globals.squareSize)) {
					neighbours[3] = bottom;
				} else {
					neighbours[3] = -1;
				}
				
				//System.out.println(x + "," + y + ", " + Arrays.toString(neighbours));
				
				
				randMap.getTerrain()[x][y] = terrain;
			}
		}
		
		return randMap;
	}
	
	public static int generateRandomLength(int min, int max) {
		int rand = Globals.round(Globals.generateRandomRange(min, max), Globals.squareSize);
		
		return rand;
	}
	
	public static Terrain generateRandomTerrain(int x, int y) {
		Location loc = new Location(x, y);
		TerrainType type = generateRandomTerrainType();
		
		return new Terrain(loc, type, (x / Globals.squareSize), (y / Globals.squareSize), 0);
	}
	
	public static TerrainType generateRandomTerrainType() {
		TerrainType terrain = null;
		
		int randTerrain = Globals.generateRandomRange(0, 30);
		
		switch(randTerrain) {
			case 0:
				terrain = TerrainType.WATER;
				break;
			case 1:
				terrain = TerrainType.TREE;
				break;
			case 2:
				terrain = TerrainType.STONE;
				break;
			default:
				terrain = TerrainType.GRASS;
				break;
		}
		return terrain;
	}
	
}
