import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

public class Graph {
	
	HashMap<String, Node> nodes;
	ArrayList<Edge> edges;
	
	public Graph(){
		nodes = new HashMap<String, Node>();
		edges = new ArrayList<Edge>();
	}
	
	
	public boolean addNewNode(Node n){
		if (!checkIfNodeExists(n)){
			nodes.put(n.getName(), n);
			return true;
		} else {
			return false;
		}
	}
	
	public Node getNodeByName(String str){
		if (checkIfNodeExists(str)){
			return nodes.get(str);
		} else {
			return null;
		}
	}
	
	public boolean checkIfNodeExists(Node n){
		if (nodes.get(n.getName()) == null){
			return false;
		} else {
			return true;
		}
	}
	public boolean checkIfNodeExists(String n){
		if (nodes.get(n) == null){
			return false;
		} else {
			return true;
		}
	}
	
	public boolean addNewEdge(Node from, Node to, String type, double weight){
		if (checkIfNodeExists(from) && checkIfNodeExists(to)){
			edges.add(new Edge(from, to, type, weight));
			return true;
		} else {
			return false;
		}
	}
	
	public boolean addNewEdge(String from, String to, String type, double weight){
		if (checkIfNodeExists(from) && checkIfNodeExists(to)){
			edges.add(new Edge(getNodeByName(from), getNodeByName(to), type, weight));
			return true;
		} else {
			return false;
		}
	}
	
	public JsonValue toJson(){
		JsonObject jobj = new JsonObject();
		
		//Nodes
		JsonArray jnodes = new JsonArray();
		Iterator it = nodes.entrySet().iterator();
		while (it.hasNext()){
			Map.Entry pair = (Map.Entry)it.next();
			Node node = (Node) pair.getValue();
			jnodes.add(Json.object().add("id", node.getID()).add("label",node.getName()));
		}
		jobj.add("nodes", jnodes);
		
		//Edges
		JsonArray jedges = new JsonArray();
		for (Edge edge : edges){
			jedges.add(Json.object().add("from", edge.getStart().getID()).add("to", edge.getEnd().getID()));
		}
		
		jobj.add("edges", jedges);
		return jobj;
	}
	
	//TESTING
	public void connectRandomNodes(int num){
		HashSet<String> hashSet = new HashSet<String>(nodes.keySet());
		String[] keys = hashSet.toArray(new String[hashSet.size()]);
		for (int i = 0; i < num; i++){
			int indA = Utilities.generateRandomRangeInteger(0, keys.length-1);
			int indB = Utilities.generateRandomRangeInteger(0, keys.length-1);
			addNewEdge(getNodeByName(keys[indA]), getNodeByName(keys[indB]),"lala",1092);
		}
	}
	

}
