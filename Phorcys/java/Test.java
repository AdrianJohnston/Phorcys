
public class Test {
	public static void main(String[] args){
		
		//Text box testing
		try {
			String txt_win = API.text("", "JAVA TEXT BOX", "HELLO BANANA FACE");
			Thread.sleep(5000);
			API.text(txt_win, "JAVA TEXT BOX", "HELLO BANANA1 FACE");
			Thread.sleep(5000);
			API.text(txt_win, "JAVA TEXT BOX", "HELLO BANANA2 FACE");
		} catch (Exception e){
			e.printStackTrace();
		}
		
		
		//Plot2D testing
		
	}
}