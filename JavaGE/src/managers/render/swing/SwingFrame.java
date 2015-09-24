package managers.render.swing;

import java.util.List;

import javax.swing.JFrame;

import managers.render.RenderObject;
import managers.render.RenderResource;

public class SwingFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private SwingPanel panel;

	public SwingFrame(List<RenderObject> resources) {
		panel = new SwingPanel(resources);
		add(panel);
	}
	
}
