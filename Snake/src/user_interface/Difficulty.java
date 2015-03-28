package user_interface;

public class Difficulty {
	
	private int speedMS;
	private int segmentsStart;
	private String tune;
	
	public Difficulty(int speedMS, int segmentsStart, String tune) {
		super();
		this.speedMS = speedMS;
		this.segmentsStart = segmentsStart;
		this.tune = tune;
	}

	public int getSpeedMS() {
		return speedMS;
	}
	public int getSegmentsStart() {
		return segmentsStart;
	}
	public String getTune() {
		return tune;
	}

	public void setSpeedMS(int speedMS) {
		this.speedMS = speedMS;
	}
	public void setSegmentsStart(int segmentsStart) {
		this.segmentsStart = segmentsStart;
	}
	public void setTune(String tune) {
		this.tune = tune;
	}
	
	
	
	
}
