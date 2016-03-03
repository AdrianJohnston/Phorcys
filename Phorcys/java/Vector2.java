import java.util.ArrayList;
public class Vector2 {
	ArrayList<Double> points;
	String label;
	public Vector2(String label){
		points = new ArrayList<Double>();
		this.label = label;
	}

	public void addPoint(double point){
		points.add(point);
	}
	
	public ArrayList<Double> getPoints(){
		return points;
	}
	
	public double getPointAt(int index){
		if (index > points.size()){
			System.out.println("ERRORL POINT SIZE MISMATCH");
			System.exit(0);
		}
		return points.get(index);
	}
	
	public int size(){
		return points.size();
	}
	
	public String getLabel(){
		return label;
	}
	
	
	@Override
	public String toString(){
		return getX()+","+getY();
	}
}
