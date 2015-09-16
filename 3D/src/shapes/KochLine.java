package shapes;

import java.awt.Graphics;
import java.awt.Point;

public class KochLine {
	
	private Point start;
	private Point end;
	
	public KochLine(Point start, Point end) {
		this.start = start;
		this.end = end;
	}
	
	public void draw(Graphics g) {
		g.drawLine(start.x, start.y, end.x, end.y);
	}

	public Point getA() {
		return new Point(start.x, start.y);
	}

	public Point getB() {
		int x = (start.x + end.x) / 3;
		return new Point(x, start.y);
	}

	public Point getC() {
		Point b = getB();
		//b.translate(((start.x + end.x) / 3) / 2, ((start.y + end.y) / 2));
		return b;
	}

	public Point getD() {
		int x = (start.x + end.x) * 2/3;
		return new Point(x, start.y);
	}

	public Point getE() {
		return new Point(end.x, end.y);
	}
}
