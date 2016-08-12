package phorcys.java;
public class Edge implements Comparable<Edge>{
	Node start, end;
	private double weight;
	String type;
	
	public Edge(Node start, Node end, String type, double weight){
		this.start = start;
		this.end = end;
		this.setWeight(weight);
		this.type = type; 
	}
	
	public Node getStart(){
		return start;
		
	}
	
	public Node getEnd(){
		return end;
	}
	public double getWeight(){
		return weight;
	}
	
	@Override
	public String toString(){
		String out = start.getName()+"\t"+end.getName()+"\tType: "+type+"\tWeight: "+getWeight();
		return out;
	}
	
	public boolean containsNode(Node n){
		return (start.getName().equals(n.getName()) || end.getName().equals(n.getName()));
	}
	
	public boolean containsNodes(Node n1, Node n2){
		return (containsNode(n1) && containsNode(n2));
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		double diff = getWeight() - o.getWeight();
		if(diff < 0.0){
			return -1;
		} else if (diff > 0.0){
			return 1;
		}
		
		return 0;
		
	}
}