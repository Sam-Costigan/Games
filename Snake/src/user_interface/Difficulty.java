package user_interface;

public class Difficulty {
	
	private int speedMS;
	private int segmentsStart;
	
	public Difficulty(int speedMS, int segmentsStart) {
		super();
		this.speedMS = speedMS;
		this.segmentsStart = segmentsStart;
	}

	public int getSpeedMS() {
		return speedMS;
	}
	public int getSegmentsStart() {
		return segmentsStart;
	}

	public void setSpeedMS(int speedMS) {
		this.speedMS = speedMS;
	}
	public void setSegmentsStart(int segmentsStart) {
		this.segmentsStart = segmentsStart;
	}
	
	
	
	
}
