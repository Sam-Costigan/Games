package user_interface;

public class Difficulty {
	
	private int speedMS;
	private int segmentsStart;
	private String tune;
	private boolean computer = false;
	
	public Difficulty(int speedMS, int segmentsStart, String tune) {
		super();
		this.speedMS = speedMS;
		this.segmentsStart = segmentsStart;
		this.tune = tune;
	}
	
	public Difficulty(int speedMS, int segmentsStart, String tune, boolean computer) {
		super();
		this.speedMS = speedMS;
		this.segmentsStart = segmentsStart;
		this.tune = tune;
		this.computer = computer;
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
	public boolean getComputer() {
		return computer;
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
