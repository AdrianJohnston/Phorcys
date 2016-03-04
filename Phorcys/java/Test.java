import java.util.Random;

public class Test {
	
	static Random rand = new Random();
	public static void main(String[] args){
		
		//Text box testing
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
		
		
		//Image testing
		//This loads an image from file and sends it to the server
		//Load image from args[0]
		PNG png = new PNG(args[0]);
		System.out.println(args[0]);
		png.setLabels("testsss");
		String img_win = API.image(null, "IMAGE TEST YO", png.toJson());
	}
}