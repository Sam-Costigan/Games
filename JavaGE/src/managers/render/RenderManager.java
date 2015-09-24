package managers.render;

import managers.resources.Resource;

public interface RenderManager {

	public boolean init(int width, int height, boolean fullscreen, String title);
	
	public Resource loadRenderResource(int id, String type, String filename);
	
	public void update();
	
}
