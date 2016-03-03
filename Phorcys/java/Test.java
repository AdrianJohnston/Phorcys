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
		
		
		//Plot2D testing
		Plot2D testPlot = new Plot2D("position","a","b"); //mimic the example.py
		testPlot.setXLabel("Position");
		for (int i = 0; i < 15; i++){
			testPlot.addPoints(i,rand.nextDouble(),rand.nextDouble()*2);
		}
		System.out.println(testPlot.toJson());
	}
}