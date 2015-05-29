package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.swing.JPanel;

import util.ArmyGenerator;
import util.Location;
import util.Square;
import util.enums.TeamPosition;
import util.enums.UnitType;

public class StrategyGame extends JPanel {

	public static final int squareSize = 30;
	public static final int width = 600;
	public static final int height = 600;
	
	private List<Team> teams = new ArrayList<Team>();
	private Map<Location, Square> squares = new HashMap<Location, Square>();
	private Unit activeUnit = null;
	private Square activeSquare = null;
	
	public StrategyGame() {
		Team topTeam = new Team();
		topTeam.setPosition(TeamPosition.TOP);
		topTeam.setArmy(ArmyGenerator.getStandardArmy(topTeam));
		
		teams.add(topTeam);
		
		for(Team team : teams) {
			for(Unit unit : team.getArmy().getUnits()) {
				Square square = new Square(unit.getPosition(), unit);
				squares.put(unit.getPosition(), square);
			}
		}
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Location position = new Location(e.getX(), e.getY());
				Square target = squares.get(position);
				if(target != null) {
					
					for(Team team : teams) {
						for(Unit unit : team.getArmy().getUnits()) {
							unit.setActive(false);
						}
					}
					target.getUnit().setActive(true);
					activeUnit = target.getUnit();
					activeSquare = target;
				} else if(activeUnit != null) {
					Location newPos = position.makeRoundedLocation();
					target = new Square(newPos, activeUnit);
					activeUnit.setPosition(newPos);
					squares.remove(activeSquare.getPosition());
					squares.put(newPos, target);
				}
				repaint();
			}
			
		});
	}
	
	private void initGame() {
		setFocusable(true);
		setBackground(Color.GRAY);
	}
	
	public void start() {
		initGame();
		repaint();
	}
	
	private void drawGame(Graphics g) {
		g.setColor(Color.BLACK);
		for(int x = 0; x < width; x = x + squareSize) {
			for(int y = 0; y < height; y = y + squareSize) {
				g.drawRect(x, y, squareSize, squareSize);
			}
		}
		
		for(Team team : teams) {
			for(Unit unit : team.getArmy().getUnits()) {
				int posX = unit.getPosition().getX();
				int posY = unit.getPosition().getY();
				
				if(unit.isActive()) {
					g.setColor(Color.GREEN);
				} else {
					g.setColor(Color.RED);
				}
				
				if(unit.getType() == UnitType.INFANTRY) {
					g.fillRect(posX, posY, squareSize, squareSize);
				} else if(unit.getType() == UnitType.ARTILLERY) {
					g.fillOval(posX, posY, squareSize, squareSize);
				} else {
					Polygon shape = new Polygon();
					shape.addPoint(posX + (squareSize / 2), posY);
					shape.addPoint(posX, posY + squareSize);
					shape.addPoint(posX + squareSize, posY + squareSize);
					g.fillPolygon(shape);
				}
				
				if(unit.isActive()) {
					
				}
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawGame(g);
	}
	
}
