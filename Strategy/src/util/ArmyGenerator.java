package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import game.Army;
import game.StrategyGame;
import game.Team;
import game.Unit;
import util.enums.TeamPosition;
import util.enums.UnitType;

public class ArmyGenerator {

	private static Map<TeamPosition, Location> positionMap = new HashMap<TeamPosition, Location>();
	static {
		positionMap.put(TeamPosition.TOP, new Location(0, 0));
		positionMap.put(TeamPosition.BOTTOM, new Location(0, 0));
		positionMap.put(TeamPosition.LEFT, new Location(0, 0));
		positionMap.put(TeamPosition.RIGHT, new Location(0, 0));
	}
	
	public static Army getStandardArmy(Team team) {
		List<Unit> army = new ArrayList<Unit>();
		
		Location startPos = positionMap.get(team.getPosition());
		int squareSize = StrategyGame.squareSize;
		int width = StrategyGame.width;
		int height = StrategyGame.height;
		
		// First row, creates artillery
		int y = startPos.getY() + squareSize;
		int start = ((width / 2) - (2 * squareSize));
		int end = ((width / 2) + (2 * squareSize));
		int increment = (2 * squareSize);
		for(int x = startPos.getX() + start; x < end; x = x + increment) {
			Location pos = new Location(x, y);
			Unit cannon = new Unit(60, 1, pos, UnitType.ARTILLERY);
			army.add(cannon);
		}
		
		y += (2 * squareSize);
		start = ((width / 2) - (4 * (2 * squareSize)));
		end = ((width / 2) + (3 * (2 * squareSize)));
		
		
		for(int x = startPos.getX() + start; x <= end; x = x + increment) {
			Location pos = new Location(x, y);
			Unit unit = null;
			
			if(x == startPos.getX() + start || x == startPos.getX() + end) {
				unit = new Unit(80, 5, pos, UnitType.CAVALRY);
			} else {
				if((x + increment) < end) {
					x = x - (increment / 2);
				}
				unit = new Unit(120, 2, pos, UnitType.INFANTRY);
			}
			
			army.add(unit);
		}
		
		return new Army(army);
	}
	
}
