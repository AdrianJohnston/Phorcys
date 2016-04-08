import java.awt.Color;
import java.util.Random;

public class Test {
	
	static Random rand = new Random();
	public static void main(String[] args){
		
//		//Text box testing
//		try {
//			String txt_win = API.text("", "JAVA TEXT BOX", "HELLO BANANA FACE");
//			Thread.sleep(5000);
//			API.text(txt_win, "JAVA TEXT BOX", "HELLO BANANA1 FACE");
//			Thread.sleep(5000);
//			API.text(txt_win, "JAVA TEXT BOX", "HELLO BANANA2 FACE");
//		} catch (Exception e){
//			e.printStackTrace();
//		}
//		
//		
//		//Plot2D testing
//		try {
//			Plot2D testPlot = new Plot2D("position","a","b"); //mimic the example.py
//			testPlot.setXLabel("Position");
//			for (int i = 0; i < 15; i++){
//				testPlot.addPoints(i,rand.nextDouble(),rand.nextDouble()*2);
//			}
//			String txt_plot2d = API.plot2D(null, "JAVA PLOT 2D Test",testPlot);
//			for (int i = 15; i < 30; i++){
//				testPlot.addPoints(i,rand.nextDouble(),rand.nextDouble()*2);
//			}
//			Thread.sleep(5000);
//			API.plot2D(txt_plot2d, "JAVA PLOT 2D Test",testPlot);
//		} catch(Exception e){
//			e.printStackTrace();
//		}
//		
//		
//		//Image testing
//		//This loads an image from file and sends it to the server
//		//Load image from args[0]
//		PNG png = new PNG(args[0]);
//		System.out.println(args[0]);
//		png.setLabels("testsss");
//		String img_win = API.image(null, "IMAGE TEST YO", png.toJson());
		
		//Generated Image Testing
//		PNG png = new PNG(30, 30);
//		png.createImage();
//		png.setLabels("OLOLOLO");
//		String imgg_win = API.image(null,"GEN IMAGE",png.toJson());
//		for (int i = 0; i < 1000; i++){
//			png.golTEST();
//			API.image(imgg_win,"Small Game Of Life",png.toJson());
//		}
		
		
		
		
		
		
//		//Graph3D testing
//		try {
//			Graph3D testPlot = new Graph3D("position","a","b"); //mimic the example.py
//			testPlot.setXLabel("Position");
//			for (int i = 0; i < 500; i++){
//				testPlot.addPoints(i,rand.nextDouble()*100,rand.nextDouble()*25);
//			}
//			String txt_graph3D = API.graph3D(null, "JAVA GRAPH3d",testPlot);
//			for (int i = 500; i < 40; i++){
//				testPlot.addPoints(i,rand.nextDouble()*87,rand.nextDouble()*35);
//			}
//			Thread.sleep(5000);
//			API.graph3D(txt_graph3D, "JAVA GRAPH3d Test",testPlot);
//		} catch(Exception e){
//			e.printStackTrace();
//		}
		
		//Network Graph testing
//		Graph testGraph = new Graph();
//		//Add test nodes
//		for (int i = 0; i < 10; i++){
//			testGraph.addNewNode(new Node("Node"+i,0,0,i));
//		}
//		
//		//Add test edges
//		testGraph.connectRandomNodes(50);
//		String ntwrk_win = API.networkGraph(null, "network test", testGraph.toJson());
		
		
		//Mesh testing
		Mesh mesh = new Mesh();
		int dummyEntities = 10000;
		for (int i = 0; i < dummyEntities; i++){
			mesh.addEntity("ent_"+i, Utilities.generateRandomDouble()-0.5, Utilities.generateRandomDouble()-0.5, 0);
		}
		
		String mesh_win = API.mesh(null, "mesh test", mesh.toJson());
		String count_win = API.text(null, "Current Step", "");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
		for (int i = 0; i < 1000; i++){
			mesh.shiftEntities(-0.05, 0.05);
			API.mesh(mesh_win, "mesh test", mesh.toJson());
			API.text(count_win, "Current Step", "Step "+i);
//			try {
//				Thread.sleep(800);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		

		
	}
}