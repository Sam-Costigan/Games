package managers.render2d;

import managers.resources.Resource;

public interface RenderManager2D {
	
	public boolean init(int width, int height, boolean fullscreen, String title);
	
	public Resource loadRenderResource(int id, String type, String filename);
	
	public void update();
	
}
