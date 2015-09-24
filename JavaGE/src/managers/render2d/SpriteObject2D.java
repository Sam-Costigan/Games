package managers.render2d;

import java.awt.Image;

public class SpriteObject2D extends RenderObject2D {

	public long timeLastFrame;
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
		
		timeLastFrame = System.currentTimeMillis();
	}
	
	public void update() {
		long timeSinceLastFrame = System.currentTimeMillis() - timeLastFrame;
		
		if(timeSinceLastFrame >= speed) {
			//update
		}
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
