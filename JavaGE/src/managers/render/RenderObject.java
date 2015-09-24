package managers.render;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import managers.EngineObject;

public class RenderObject extends EngineObject {

	protected RenderResource resource;
	public Rectangle bounding;
	public float x;
	public float y;
	public boolean visible;
	public Color colorKey;
	public boolean colorKeyEnabled;
	
	public void setResource(RenderResource resource) {
		if(resource != null) {
			this.resource = resource;
		}
	}
	
	public RenderResource getResource() {
		return resource;
	}
	
	public void draw(Graphics g) {
		
	}
	
}
