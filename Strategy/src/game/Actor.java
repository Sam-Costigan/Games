package game;

import java.util.HashMap;
import java.util.Map;

import util.PathNode;
import util.Terrain;
import util.enums.*;

public class Actor {
	
	// Actors statistics
	private int strength;
	private int speed;
	private ActorType type;
	
	// Actors current location and goal information
	private Terrain location;
	private TerrainType currentGoal = TerrainType.WATER;
	
	private WorldMap map;
	
	// Actors inventory!
	//private Inventory inventory = new Inventory();
	
	public Actor() {
		
	}
	
	public Actor(int strength, int speed, ActorType type) {
		this.strength = strength;
		this.speed = speed;
		this.type = type;
	}
	
	public PathNode findPathToGoal() {
		PathNode start = new PathNode(location, null);
		Map<Terrain, Boolean> visitedMap = new HashMap<Terrain, Boolean>();
		Terrain[][] mapArray = map.getTerrain();
		
		for(int i = 0; i < mapArray.length; i++) {
			for(int j = 0; j < mapArray[0].length; j++) {
				Terrain terr = mapArray[i][j];
				visitedMap.put(terr, false);
			}
		}
		
		PathNode path = findPath(start, visitedMap);
		
		if(path != null) {
			return path;
		}
		
		return null;
	}
	
	public PathNode followPath(PathNode path) {
		Terrain next = path.getLocation();
		PathNode from = path.getFrom();
		
		if(location.getType() == currentGoal) {
			if(currentGoal == TerrainType.WATER) {
				currentGoal = TerrainType.STONE;
			} else if(currentGoal == TerrainType.STONE) {
				currentGoal = TerrainType.TREE;
			} else {
				currentGoal = TerrainType.WATER;
			}
		}
		
		location = next;
		
		return from;
	}

	private PathNode findPath(PathNode node, Map<Terrain, Boolean> visitedMap) {
		Terrain terr = node.getLocation();
		PathNode path = null;
		if(visitedMap.get(terr) == false) {
			visitedMap.put(terr, true);
			for(int i = 0; i < 4; i++) {
				int neigh = terr.getNeighbours()[i];
				if(neigh != -1) {
					int x;
					int y;
					if(i < 2) {
						x = neigh;
						y = terr.getY();
					} else {
						x = terr.getX();
						y = neigh;
					}
					Terrain neighbour = map.getSquare(x, y);
					
					if(visitedMap.get(neighbour) == false && (neighbour.isNavigable() || neighbour.getType() == currentGoal)) {
						PathNode newNode = new PathNode(neighbour, node);
						
						if(neighbour.getType() == currentGoal) {
							path = newNode;
							return path;
						} else {
							path = findPath(newNode, visitedMap);
						}
					}
				}
			}
		}
		return path;
	}

	public int getStrength() {
		return strength;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public ActorType getType() {
		return type;
	}
	
	public Terrain getLocation() {
		return location;
	}
	
	public void setStrength(int strength) {
		this.strength = strength;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void setType(ActorType type) {
		this.type = type;
	}
	
	public void setLocation(Terrain location) {
		this.location = location;
	}
	
	public void setMap(WorldMap map) {
		this.map = map;
	}

	@Override
	public String toString() {
		return "Actor [strength=" + strength + ", speed=" + speed
				+ ", type=" + type + "]";
	}
	
}