package game;

import managers.errors.*;
import managers.render2d.RenderManager2D;
import managers.render2d.swing.SwingRenderManager;
import managers.resources.ResourceManager;

public class Main {

	public static void main(String[] args) {
		ErrorManager errM = ErrorManager.getErrorManager();
		ResourceManager resM = new ResourceManager();
		RenderManager2D rendM = SwingRenderManager.getRenderManager();
		
		errM.create();
		
		try {
			
			resM.loadFromXmlFile("logs/test.xml");
			
			rendM.init(800, 800, false, "Yeah boi!");
			
		} catch(ErrorException e) {
			e.printStackTrace();
			errM.logException(e);
		}
	}

}