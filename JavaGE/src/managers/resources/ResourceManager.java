package managers.resources;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import managers.EngineObject;
import managers.errors.*;
import managers.render2d.RenderManager2D;
import managers.render2d.swing.SwingRenderManager;

public class ResourceManager extends EngineObject {
	
	private ErrorManager errM = ErrorManager.getErrorManager();
	private RenderManager2D rendM = SwingRenderManager.getRenderManager();
	private int currentScope;
	private int resourceCount;
	
	private Map<Integer, ArrayList<Resource>> resources;
	
	public ResourceManager() {
		currentScope = 0;
		resourceCount = 0;
	}
	
	private void loadScopes(NodeList scopes) {
		for(int i = 0; i < scopes.getLength(); i++) {
			Node scope = scopes.item(i);
			
			if(scope.getNodeType() == Node.ELEMENT_NODE) {
				Element scopeEl = (Element) scope;
				
				int scopeVal = Integer.parseInt(scopeEl.getAttribute("id"));
				
				NodeList resourcesList = scopeEl.getElementsByTagName("resource");
				
				ArrayList<Resource> list = loadResources(resourcesList);
				
				resources.put(scopeVal, list);
			}
		}
	}
	
	private ArrayList<Resource> loadResources(NodeList resources) {
		
		ArrayList<Resource> list = new ArrayList<Resource>();
		
		for(int j = 0; j < resources.getLength(); j++) {
			Node resource = resources.item(j);
			
			if(resource.getNodeType() == Node.ELEMENT_NODE) {
				
				Element resourceEl = (Element) resource;
				
				int id = Integer.parseInt(resourceEl.getAttribute("id"));
				String type = resourceEl.getAttribute("type");
				String filename = resourceEl.getAttribute("filename");
				
				Resource res = loadResource(id, type, filename);
				
				
				
				list.add(res);
			}
		}
		
		return list;
	}
	
	private Resource loadResource(int id, String type, String filename) {
		
		Resource res = null;
		
		if(type.equals("graphic")) {
			res = rendM.loadRenderResource(id, type, filename);
		} else if(type.equals("audio")) {
			
		} else if(type.equals("text")) {
			
		}
		
		
		
		return res;
	}
	
	public Resource findResourceByID(int id) {
		
		for(Integer scope : resources.keySet()) {
			if(!resources.get(scope).isEmpty()) {
				for(Resource res : resources.get(scope)) {
					if(res.getResourceID() == id) {
						return res;
					}
				}
			}
		}
		
		return null;
	}
	
	public void clear() {
		for(Integer scope : resources.keySet()) {
			if(!resources.get(scope).isEmpty()) {
				for(Resource res : resources.get(scope)) {
					res.unload();
				}
			}
		}
		
		resources.clear();
	}
	
	public boolean loadFromXmlFile(String filename) throws ErrorException {
		try {
			resources = new HashMap<Integer, ArrayList<Resource>>();
			
			File file = new File(filename);
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList scopes = doc.getElementsByTagName("scope");
			
			loadScopes(scopes);
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			new ErrorException(errM.getCount(), e.getMessage());
		}
		
		return false;
	}
	
	public void setCurrentScope(int scope) {
		if(currentScope != 0) {
			for(Resource res : resources.get(currentScope)) {
				res.unload();
			}
		}
		
		currentScope = scope;
		
		for(Resource res : resources.get(currentScope)) {
			res.load();
		}
	}
	
	public int getResourceCount() {
		return resourceCount;
	}
	
}
