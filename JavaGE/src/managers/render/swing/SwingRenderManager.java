package managers.render.swing;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import managers.EngineObject;
import managers.render.RenderManager;
import managers.render.RenderObject;
import managers.render.RenderResource;
import managers.resources.Resource;
import managers.resources.ResourceType;

public class SwingRenderManager extends EngineObject implements RenderManager {
	
	SwingFrame frame;
	List<RenderObject> resources = new ArrayList<RenderObject>();

	@Override
	public boolean init(int width, int height, boolean fullscreen, String title) {
		
		frame = new SwingFrame(resources);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(title);
		frame.setResizable(false);
		
		if(fullscreen) {
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		} else {
			frame.setSize(width, height);
		}
		
		frame.setVisible(true);
		
		return true;
	}
	
	@Override
	public Resource loadRenderResource(int id, String type, String filename) {
		RenderResource res = new RenderResource();
		
		res.setId(id);
		res.setType(ResourceType.RESOURCE_GRAPHIC);
		res.setFilename(filename);
		
		return res;
	}
	
	@Override
	public void update() {
		frame.repaint();
	}
	
}
