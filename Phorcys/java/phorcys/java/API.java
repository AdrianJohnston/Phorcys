package phorcys.java;

//TODO: 
	//Import things like JSON reader/writer [√]
	//Make more sensible classes []
	//Make sure all content is JSON (need JSON writer in visualised process) []
	//Actually write docs []
	//Give this a better name []
	//Customisable URL [√]
	//Implement a UID generator [√]
	/**Panes**/
	//Text [√]
	//Image [√]
	//Plot2D [√]
	//Plot3D []
	//Mesh []
	//IsoSurface []

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
	static String URL = "http://localhost:8000/events";

	public static void setUrl(String url){
		URL = url;
	}
	
	/**
	 * [send description]
	 * @param paneToSend [description]
	 */
	public static boolean send(PaneToSend paneToSend){
//		System.out.println("Command: " + paneToSend.toString().replace("\\",""));
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
		return "pane_"+UUID.randomUUID().toString();
				
	}

	public static String pane(String paneType, String uid, String title, JsonValue content){
		if (uid == null){
			uid = uid();
		} else if (uid.length() == 0){
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
		return pane("text", uid, title, Json.value(content));
	}
	
	/**
	 * Create a Plot2D pane
	 * @param uid
	 * @param title
	 * @param plot2d
	 * @return
	 */
	public static String plot2D(String uid, String title, Plot2D plot2d){				
		return pane("plot",uid, title, plot2d.getJson());
	}
	
	public static String image(String uid, String title, JsonValue content){
		return pane("image", uid, title, content);
	}
	
	public static String graph3D(String uid, String title, Graph3D graph3d){				
		return pane("graph3d",uid, title, graph3d.getJson());
	}
	
	public static String networkGraph(String uid, String title, JsonValue content){
		return pane("network",uid,title,content);
	}

	public static String mesh(String uid, String title, JsonValue content){
		return pane("mesh", uid, title, content);
	}
//

//
//	public static String isosurface(String uid, String title, String content){
//		return pane("isosurface", uid, title, content);
//	}
	

}

class PaneToSend{
	JsonObject json;
	String paneType;
	String uid;
	String title;
	JsonValue content;


	PaneToSend(String paneType, String uid, String title, JsonValue content) {
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