package util.factories;

import java.awt.Dimension;

import util.Globals;
import util.Terrain;
import game.Actor;
import game.WorldMap;

public class ActorFactory {
	
	public static Actor generateRandomActor(WorldMap map) {
		Actor randActor = new Actor();
		
		randActor.setStrength(Globals.generateRandomRange(Globals.minStr, Globals.maxStr));
		randActor.setSpeed(Globals.generateRandomRange(Globals.minSpeed, Globals.maxSpeed));
		
		int mapWidth = map.getTerrain().length;
		int mapHeight = map.getTerrain()[0].length;
		Terrain location = map.getSquare((int)(Math.random() * mapWidth), (int)(Math.random() * mapHeight));
		
		randActor.setLocation(location);
		randActor.setMap(map);
		
		return randActor;
	}
	
}
