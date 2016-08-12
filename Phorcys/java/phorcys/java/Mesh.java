package phorcys.java;
import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Mesh {
	//Information about the scene
	ArrayList<MeshEntity> entities;
	HashMap<String, MeshEntity> mapOfEntities;
	public Mesh(){
		entities = new ArrayList<MeshEntity>();
		mapOfEntities = new HashMap<String, MeshEntity>();
	}
	
	public void addEntity(String name, double x, double y, double z){
		entities.add(new MeshEntity(name, x, y, z));
	}
	
	public void addEntityToMap(String name, double x, double y, double z){
		MeshEntity me = mapOfEntities.get(name);
		if (me == null){
			mapOfEntities.put(name, new MeshEntity(name, x, y, z));
		} else {
			me.setXYZ(x, y, z);			
			mapOfEntities.put(name,me);
		}
	}
	
	public void addEntityToMap(String name, double x, double y, double z, String colour){
		MeshEntity me = mapOfEntities.get(name);
		if (me == null){
			mapOfEntities.put(name, new MeshEntity(name, x, y, z, colour));
		} else {
			me.setXYZ(x, y, z);
			me.setColour(colour);
			mapOfEntities.put(name,me);
		}
	}
	
	public void updateEntity(String name, double x, double y, double z){
		MeshEntity me = mapOfEntities.get(name);
		me.setXYZ(x, y, z);
		mapOfEntities.put(name,me);
	}
	
	
	public JsonValue toJson(){
		JsonObject jobj = new JsonObject();
		JsonArray mainJson = new JsonArray();
		
		JsonArray tmpArray = new JsonArray();
		for (MeshEntity me : entities){
			tmpArray = new JsonArray();
			tmpArray.add(me.getId()).add(me.getX()).add(me.getY()).add(me.getZ());
			mainJson.add(tmpArray);
		}		
		
		
		jobj.add("file", mainJson);
		
		return jobj;
	}
	
	public JsonValue toJsonFromMap(){
		JsonObject jobj = new JsonObject();
		JsonArray mainJson = new JsonArray();
		
		JsonArray tmpArray = new JsonArray();
		
		Iterator<Entry<String, MeshEntity>> it = mapOfEntities.entrySet().iterator();
		while (it.hasNext()){
			Map.Entry<String,MeshEntity> pair = (Map.Entry<String,MeshEntity>)it.next();
			MeshEntity me = pair.getValue();
			tmpArray = new JsonArray();
			tmpArray.add(me.getId()).add(me.getX()).add(me.getY()).add(me.getZ()).add(me.getColour());
			mainJson.add(tmpArray);
		}		
		
		jobj.add("file", mainJson);
		
		return jobj;
	}
	
	//DEBUG
	public void shiftEntities(double min, double max){
		for (MeshEntity me : entities){
			me.setX(me.getX() + Utilities.generateRandomRangeDouble(min, max));
			me.setY(me.getY() + Utilities.generateRandomRangeDouble(min, max));
			me.setZ(me.getZ() + Utilities.generateRandomRangeDouble(min, max));
		}
	}
}

class MeshEntity{
	String id = "";
	double x;
	double y;
	double z;
	String colour;
	public MeshEntity(String id, double x, double y, double z){
		this.id = id;
		this.x = x;
		this.y = y;
		this.z = z;
		colour = "0xffffff";
	}
	public MeshEntity(String id, double x, double y, double z, String colour){
		this.id = id;
		this.x = x;
		this.y = y;
		this.z = z;
		this.colour = colour;
	}
	
	public void setColour(String colourString){
		colour = colourString;
	}
	public String getColour(){
		return colour;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getZ() {
		return z;
	}
	public void setZ(double z) {
		this.z = z;
	}
	public void setXYZ(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
		
	}
	
	
}
