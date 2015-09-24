package managers.scene2d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import managers.EngineObject;

public class SceneManager2D extends EngineObject {

	List<SceneLayer2D> layers = new ArrayList<SceneLayer2D>();
	
	
	public SceneLayer2D addLayer(String name) {
		
		SceneLayer2D layer = findLayer(name);
		
		if(layer == null) {
			layer = new SceneLayer2D();
			layer.name = name;
			layers.add(layer);
		}
		
		return layer;
	}
	
	public SceneLayer2D findLayer(String name) {
		
		for(SceneLayer2D layer : layers) {
			if(layer.name.equals(name)) {
				return layer;
			}
		}
		
		return null;
	}
	
	public void removeLayer(String name) {
		for(SceneLayer2D layer : layers) {
			if(layer.name.equals(name)) {
				layers.remove(layer);
			}
		}
	}
	
	public void sortLayers() {
		Collections.sort(layers);
	}
	
	public boolean loadFromXmlFile(String name) {
		return false;
	}
	
	public void update() {
		
	}
	
}
