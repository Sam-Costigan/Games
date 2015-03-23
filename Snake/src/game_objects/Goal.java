package game_objects;

public class Goal {
	
	private int posX;
	private int posY;
	
	public Goal(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public int getPosX() {
		return posX;
	}
	public int getPosY() {
		return posY;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}

	@Override
	public String toString() {
		return "Goal [posX=" + posX + ", posY=" + posY + "]";
	}
	
}
