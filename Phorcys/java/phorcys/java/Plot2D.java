package phorcys.java;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

public class Plot2D {
	String[] labels; 
	String xLabel;
	String yLabel;
	Vector[] points;	
	int numberOfLines = 0;
	
	public Plot2D(String... strings){
		labels = strings;
		points = new Vector[strings.length];
		for (int i = 0; i < strings.length; i++){
			points[i] = new Vector(strings[i]);
		}
	}
	
	public void addPoints(double... newPoints){
		if (newPoints.length > points.length){
			System.out.println("ERRORL POINT SIZE MISMATCH");
			System.exit(0);
		}
		
		for (int i = 0; i < newPoints.length; i++){
			points[i].addPoint(newPoints[i]);
		}
	}
	
	public void setHeaderLabel(String x){
		labels[0] = x;
	}
	
	public void setXLabel(String x){
		xLabel = x;
	}
	
	public void setYLabel(String x){
		yLabel = x;
	}
	
	public String toJsonString(){
		return getJson().toString();
	}
	
	public JsonValue getJson(){
		JsonObject jobj = new JsonObject();
		JsonArray mainJson = new JsonArray();
		JsonArray jsonLabels = new JsonArray();
		int max = 0;
		for (int i = 0; i < points.length; i++){
			jsonLabels.add(points[i].getLabel());
			if (points[i].size() > max) {
				max = points[i].size();
			}
		}
		
		JsonArray tmpJsonArray;
		for (int i = 0; i < max; i++){
			tmpJsonArray = new JsonArray();
			for (int j = 0; j < points.length; j++){
				tmpJsonArray.add(points[j].getPointAt(i));
			}
			mainJson.add(new JsonArray(tmpJsonArray));
		}
		
		jobj.add("labels",jsonLabels);
		jobj.add("xlabel", xLabel);
		jobj.add("file", mainJson);
		
		return jobj;
	}
}