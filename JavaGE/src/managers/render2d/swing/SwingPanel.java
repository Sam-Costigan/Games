package managers.render2d.swing;

import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import managers.render2d.RenderObject2D;

public class SwingPanel extends JPanel {

	List<RenderObject2D> resources;
	
	public SwingPanel(List<RenderObject2D> resources) {
		this.resources = resources;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		for(RenderObject2D render : resources) {
			render.draw(g);
		}
	}
	
}
