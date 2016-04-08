import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import java.util.ArrayList;

public class Mesh {
	//Information about the scene
	ArrayList<MeshEntity> entities;
	public Mesh(){
		entities = new ArrayList<MeshEntity>();
	}
	
	public void addEntity(String name, double x, double y, double z){
		entities.add(new MeshEntity(name, x, y, z));
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
	
	//DEBUG
	public void shiftEntity(double min, double max){
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
	public MeshEntity(String id, double x, double y, double z){
		this.id = id;
		this.x = x;
		this.y = y;
		this.z = z;
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
	
	
}
