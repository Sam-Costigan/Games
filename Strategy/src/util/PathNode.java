package util;

public class PathNode {

	private Terrain location;
	private PathNode from;
	
	public PathNode(Terrain location, PathNode from) {
		this.location = location;
		this.from = from;
	}

	public Terrain getLocation() {
		return location;
	}

	public PathNode getFrom() {
		return from;
	}

	public void setLocation(Terrain location) {
		this.location = location;
	}

	public void setFrom(PathNode from) {
		this.from = from;
	}

	@Override
	public String toString() {
		return "PathNode [location=" + location + ", from=" + from + "]";
	}
	
}
