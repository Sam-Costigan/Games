package managers.scene2d;

import java.util.ArrayList;
import java.util.List;

import managers.EngineObject;

public class SceneLayer2D extends EngineObject implements Comparable {
	
	public boolean visible;
	public int z;
	public int x;
	public int y;
	public String name;
	
	List<SceneObject2D> objects = new ArrayList<SceneObject2D>();
	
	public void update() {
		
	}

	@Override
	public int compareTo(Object obj) {
		if(obj instanceof SceneLayer2D) {
			SceneLayer2D scene = (SceneLayer2D) obj;
			return this.z - scene.z;
		}
		
		return 0;
	}
	
}
