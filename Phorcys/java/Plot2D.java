import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

public class Plot2D {
	String[] labels; 
	String xLabel;
	String yLabel;
	Vector2[] points;	
	int numberOfLines = 0;
	
	public Plot2D(String... strings){
		labels = strings;
		points = new Vector2[strings.length];
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
	
	public void addLine(String label, Vector2 vector){
		points.put(label,vector);
	}
	
	public void setHeaderLabel(String x){
		labels[0] = x;
	}
	
	public void setXLabel(String x){
		xLabel = 0;
	}
	
	public void setYLabel(String x){
		yLabel = x;
	}
	
	public String toJson(){
		JsonArray mainJson = new JsonArray();
		int max = 0;
		for (int i = 0; i < points.length; i++){
			if (points[i].size() > max) {
				points[i].size()
			}
		}
		JsonArray tmpJsonArray;
		for (int i = 0; i < max; i++){
			tmpJsonArray = new JsonArray();
			for (int j = 0; j < points.length; j++){
				tmpJsonArray = Json.add(tmpJsonArray).points[j].getPointAt(i));	
			}
			mainJson = Json.add(mainJson).add(tmpJsonArray);
		}
		
		return mainJson.toString();
	}
}