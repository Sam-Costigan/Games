package managers.render;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import managers.resources.Resource;

public class RenderResource extends Resource {
	
	private Image image;
	
	@Override
	public void load() {
		unload();
		
		File resource = new File(filename);
		
		if(resource.exists()) {
			try {
				image = ImageIO.read(resource);
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}

	}

	@Override
	public void unload() {
		image.flush();
		image = null;
	}
	
	public Image getImage() {
		return image;
	}

	@Override
	public String toString() {
		return "RenderResource [image=" + image + ", filename=" + filename
				+ ", type=" + type + "]";
	}

}
