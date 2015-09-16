package util.factories;

import java.awt.Color;
import java.awt.Graphics;

import game.Actor;
import util.Globals;
import util.Location;
import util.Terrain;

public class DrawFactory {
	
	public static void drawTerrain(Terrain terr, Graphics g) {
		switch(terr.getType()) {
			case WATER:
				g.setColor(Color.BLUE);
				break;
			case STONE:
				g.setColor(Color.GRAY);
				break;
			case TREE:
				g.setColor(new Color(30, 160, 50));
				break;
			case GRASS:
				g.setColor(Color.GREEN);
				break;
				
		}
		Location pos = terr.getPosition();
		g.fillRect(pos.getX(), pos.getY(), Globals.squareSize, Globals.squareSize);
	}
	
	public static void drawActor(Actor actor, Graphics g) {
		g.setColor(Color.RED);
		Location pos = actor.getLocation().getPosition();
		g.fillRect(pos.getX(), pos.getY(), Globals.squareSize, Globals.squareSize);
	}
	
}
