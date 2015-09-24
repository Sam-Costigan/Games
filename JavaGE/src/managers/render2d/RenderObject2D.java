package managers.render2d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import managers.EngineObject;

public class RenderObject2D extends EngineObject {

	protected RenderResource2D resource;
	public Rectangle bounding;
	public int x;
	public int y;
	public boolean visible;
	public Color colorKey;
	public boolean colorKeyEnabled;
	
	public void setResource(RenderResource2D resource) {
		if(resource != null) {
			this.resource = resource;
		}
	}
	
	public RenderResource2D getResource() {
		return resource;
	}
	
	public void draw(Graphics g) {
		g.fillRect(x, y, 10, 10);
	}
	
}
