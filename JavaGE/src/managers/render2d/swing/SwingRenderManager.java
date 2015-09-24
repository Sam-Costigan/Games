package managers.render2d.swing;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import managers.EngineObject;
import managers.render2d.RenderManager2D;
import managers.render2d.RenderObject2D;
import managers.render2d.RenderResource2D;
import managers.resources.Resource;
import managers.resources.ResourceType;

public class SwingRenderManager extends EngineObject implements RenderManager2D {
	
	private static SwingRenderManager manager = new SwingRenderManager();
	
	private SwingFrame frame;
	private List<RenderObject2D> resources = new ArrayList<RenderObject2D>();
	
	protected SwingRenderManager() {}
	
	public static RenderManager2D getRenderManager() {
		return manager;
	}
	
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
		RenderResource2D res = new RenderResource2D();
		
		res.setId(id);
		res.setType(ResourceType.RESOURCE_GRAPHIC);
		res.setFilename(filename);
		
		RenderObject2D render = new RenderObject2D();
		render.setResource(res);
		
		resources.add(render);
		
		return res;
	}
	
	@Override
	public void update() {
		frame.repaint();
	}
	
}
