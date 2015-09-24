package managers.render2d.swing;

import java.util.List;

import javax.swing.JFrame;

import managers.render2d.RenderObject2D;
import managers.render2d.RenderResource2D;

public class SwingFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private SwingPanel panel;

	public SwingFrame(List<RenderObject2D> resources) {
		panel = new SwingPanel(resources);
		add(panel);
	}
	
}
