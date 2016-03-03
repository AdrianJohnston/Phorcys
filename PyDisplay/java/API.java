//TODO: 
	//Import things like JSON reader/writer []
	//Make more sensible classes []
	//Make sure all content is JSON (need JSON writer in visualised process) []
	//Actually write docs []
	//Give this a better name []
	//Customisable URL []
	// 

//Dependencies:
	//Uses the unirest http://unirest.io/java.html library. In particular the dependency filled jar
	//JSON library is minimal-json https://github.com/ralfstx/minimal-json
		//this is the ugliest json lib but it's also actually maintained

//Notes:


import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonValue;


public class API{
	static String URL = "http://localhost:5000/events";

	/**
	 * [send description]
	 * @param paneToSend [description]
	 */
	public static boolean send(PaneToSend paneToSend){
		//TODO: this
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

		// try:
		//   resp = urlopen(req)
		//   return resp != None
		// except:
		//   raise
		//   return False
	}

	/**
	 * [A UID generator]
	 * @return [A UID]
	 */
	public static long uid(){
		//Use a UID generator
		return 0;
	}

	public static long pane(String paneType, long uid, String title, String content){
		if (uid != 0){
			uid = uid();
		}
		long uid_a = uid;

		send(new PaneToSend(paneType, uid_a, title, content));

		return uid_a;
	}

	//Methods to call the different types of panes
	//TODO: actually make these correct
	public long text(long uid, String title, String content){
		return pane("text", uid, title, content);
	}

	public long mesh(long uid, String title, String content){
		return pane("mesh", uid, title, content);
	}

	public long image(long uid, String title, String content){
		return pane("image", uid, title, content);
	}

	public long isosurface(long uid, String title, String content){
		return pane("isosurface", uid, title, content);
	}
}

class PaneToSend{
	JsonObject json;
	String paneType;
	long uid;
	String title;
	String content;


	PaneToSend(String paneType, long uid, String title, String content) {
		this.paneType = paneType;
		this.uid = uid;
		this.title = title;
		this.content = content;

		json = Json.object().add("type", paneType);
		json = Json.object().add("command","pane");
		json = Json.object().add("id",uid);
		json = Json.object().add("title",title);
		json = Json.object().add("content",content);
	}

	@Override
	public String toString(){
		return json.toString();
	}
}