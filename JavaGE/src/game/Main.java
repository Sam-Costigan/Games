package game;

import managers.errors.*;
import managers.render.RenderManager;
import managers.render.swing.SwingRenderManager;
import managers.resources.ResourceManager;

public class Main {

	public static void main(String[] args) {
		ErrorManager errM = ErrorManager.getErrorManager();
		ResourceManager resM = new ResourceManager();
		RenderManager rendM = new SwingRenderManager();
		
		rendM.init(800, 800, false, "Yeah boi!");
		errM.create();
		
		try {
			
			resM.loadFromXmlFile("logs/test.xml");
			
		} catch(ErrorException e) {
			e.printStackTrace();
			errM.logException(e);
		}
	}

}