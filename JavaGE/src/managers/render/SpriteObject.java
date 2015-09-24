package managers.render;

import java.awt.Image;

public class SpriteObject extends RenderObject {

	public int totalFrames;
	public int framesPerRow;
	public int framesPerColumn;
	public int currentFrame;
	public int startFrame;
	
	public float speed;
	
	public int frameHeight;
	public int frameWidth;
	
	public void play() {
		Image play = resource.getImage();
	}
	
	public void setFrameRectangle(int frameNumber) {
		int row = (int) Math.floor(frameNumber / framesPerRow);
		int column = frameNumber;
		
		if(row > 0) {
			column = frameNumber - (row *framesPerRow);
		}
		
		bounding.x = column * frameWidth;
		bounding.y = row * frameHeight;
		bounding.width = frameWidth;
		bounding.height = frameHeight;
	}
	
}
