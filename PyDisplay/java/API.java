//TODO: 
	//Import things like JSON reader/writer [√]
	//Make more sensible classes []
	//Make sure all content is JSON (need JSON writer in visualised process) []
	//Actually write docs []
	//Give this a better name []
	//Customisable URL [√]
	//Implement a UID generator [√]

//Dependencies:
	//Uses the unirest http://unirest.io/java.html library. In particular the dependency filled jar
	//JSON library is minimal-json https://github.com/ralfstx/minimal-json
		//this is the ugliest json lib but it's also actually maintained unlike simple-json

//Notes:
	//Untested, but it's now very close to the Python API.
	//A lot of client side JSON generation will need to be done (would like to avoid that...)

import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonValue;

import java.util.UUID;

public class API{
	static String URL = "http://localhost:5000/events";

	public static void setUrl(String url){
		URL = url;
	}
	
	/**
	 * [send description]
	 * @param paneToSend [description]
	 */
	public static boolean send(PaneToSend paneToSend){
		// String command = json.dumps(command);
		System.out.println("Command: " + paneToSend.toString());
		// req = Request(URL, 'POST')
		// req.add_header('Content-Type', 'application/text') 
		// req.data = command.encode('ascii')
		// 
		// I have noooo idea if this works
		try {
			HttpResponse<String> response = Unirest.post(URL)
				.header("Content-Type", "application/text")
				.body(paneToSend.toString())
				.asString();
			return true;
		} catch(UnirestException e){
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * [A UID generator]
	 * @return [A UID]
	 */
	public static String uid(){
		//TODO: Use a UID generator
		return UUID.randomUUID().toString();
				
	}

	public static String pane(String paneType, String uid, String title, String content){
		if (uid.length() == 0){
			uid = uid();
		}
		String uid_a = uid;

		send(new PaneToSend(paneType, uid_a, title, content));

		return uid_a;
	}

	//Methods to call the different types of panes
	//TODO: actually make these correct
	
	/**
	 * Create a text pane
	 * @param uid
	 * @param title
	 * @param content
	 * @return
	 */
	public static String text(String uid, String title, String content){
		return pane("text", uid, title, content);
	}

	public static String mesh(String uid, String title, String content){
		return pane("mesh", uid, title, content);
	}

	public static String image(String uid, String title, String content){
		return pane("image", uid, title, content);
	}

	public static String isosurface(String uid, String title, String content){
		return pane("isosurface", uid, title, content);
	}
	
	public static String plot2D(String uid, String title, String[] labels, String xLabel, String yLabel, Vector2[] dataPoints){
				
	
		return "";
	}
}

class PaneToSend{
	JsonObject json;
	String paneType;
	String uid;
	String title;
	String content;


	PaneToSend(String paneType, String uid, String title, String content) {
		this.paneType = paneType;
		this.uid = uid;
		this.title = title;
		this.content = content;

		json = Json.object().add("type", paneType).add("command","pane").add("id",uid).add("title",title).add("content",content);
	}

	@Override
	public String toString(){
		return json.toString();
	}
}


//This is really lazy, should move into it's own class so it can actually be used
class Vector2{
	double X;
	double Y;
	Vector2(double X, double Y){
		this.X = X;
		this.Y = Y;
	}
	public double getX(){
		return X;
	}
	public double getY(){
		return Y;
	}
}