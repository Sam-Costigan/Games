package managers.resources;

import managers.EngineObject;

public abstract class Resource extends EngineObject {

	private int resourceID;
	
	protected String filename;
	protected ResourceType type;
	
	public Resource() {
		resourceID = 0;
		type = null;
	}
	
	public int getResourceID() {
		return resourceID;
	}
	
	public String getFilename() {
		return filename;
	}

	public ResourceType getType() {
		return type;
	}

	public void setResourceID(int resourceID) {
		this.resourceID = resourceID;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void setType(ResourceType type) {
		this.type = type;
	}
	
	public abstract void load();
	
	public abstract void unload();
	
}