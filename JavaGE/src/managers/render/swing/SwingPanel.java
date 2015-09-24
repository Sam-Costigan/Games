package managers.render.swing;

import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import managers.render.RenderObject;
import managers.render.RenderResource;

public class SwingPanel extends JPanel {

	List<RenderObject> resources;
	
	public SwingPanel(List<RenderObject> resources) {
		this.resources = resources;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		System.out.println("hey!");
		
		
	}
	
}
