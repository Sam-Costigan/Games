package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import util.*;
import util.factories.*;
import util.enums.*;

public class StrategyGame extends JPanel implements ActionListener {
	
	private Timer timer;
	private WorldMap map;
	private Actor actor;
	private PathNode path;
	
	public StrategyGame() {
		
	}
	
	private void initGame() {
		timer = new Timer(200, this);
		timer.start();
		
		setFocusable(true);
		setBackground(Color.GRAY);
	}
	
	public void start() {
		initGame();
		
		map = MapFactory.generateRandomMap();
		actor = ActorFactory.generateRandomActor(map);
		
		repaint();
	}
	
	private void drawGame(Graphics g) {
		Dimension size = getSize();
		
		g.setColor(Color.BLACK);
		
		Terrain[][] mapArray = map.getTerrain();
		
		for(int i = 0; i < mapArray.length; i++) {
			for(int j = 0; j < mapArray[0].length; j++) {
				Terrain terr = mapArray[i][j];
				DrawFactory.drawTerrain(terr, g);
			}
		}
		
		DrawFactory.drawActor(actor, g);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawGame(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(path == null) {
			path = actor.findPathToGoal();
		} else {
			path = actor.followPath(path);
		}
		
		repaint();
	}
	
}
