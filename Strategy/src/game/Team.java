package game;

import util.enums.TeamPersonality;
import util.enums.TeamPosition;

public class Team {

	private Army army;
	private TeamPosition position;
	private TeamPersonality personality;
	
	public Team() {
		
	}
	
	public Army getArmy() {
		return army;
	}

	public TeamPosition getPosition() {
		return position;
	}
	
	public TeamPersonality getPersonality() {
		return personality;
	}
	
	public void setArmy(Army army) {
		this.army = army;
	}
	
	public void setPosition(TeamPosition position) {
		this.position = position;
	}
	
	public void setPersonality(TeamPersonality personality) {
		this.personality = personality;
	}
	
}