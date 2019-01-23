import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

public class APIHelper {

	public static void main(String[] args) throws IOException, JSONException {
		JSONArray jsonArray = JsonReader.readJsonArrayFromUrl("http://costng.com/wp-json/wp/v2/posts?categories=44");
		for(int i=0; i<jsonArray.length(); i++) {
			
			System.out.println("======================================== POST ID: " + jsonArray.getJSONObject(i).getInt("id") + "======================================== ");
			
			System.out.println(jsonArray.get(i));

			String imageurl = jsonArray.getJSONObject(i).getString("jetpack_featured_media_url");
			System.out.println("\n-----------------------------<image>-----------------------------");
			System.out.println(imageurl);
			System.out.println("-----------------------------</image>-----------------------------\n");
			
			String title = jsonArray.getJSONObject(i).getJSONObject("title").getString("rendered");
			System.out.println("\n-----------------------------<title>-----------------------------");
			System.out.println(title);
			System.out.println("-----------------------------</title>-----------------------------\n");
			
			String content = Jsoup.parse(jsonArray.getJSONObject(i).getJSONObject("content").getString("rendered")).text();
			System.out.println("\n-----------------------------<content>-----------------------------");
			System.out.println(content);
			System.out.println("-----------------------------</content>-----------------------------\n");
			
			String timestamp = jsonArray.getJSONObject(i).getString("date");
			System.out.println("\n-----------------------------<timestamp>-----------------------------");
			System.out.println(timestamp);
			System.out.println("-----------------------------</timestamp>-----------------------------\n");
			
			Integer authorId = jsonArray.getJSONObject(i).getInt("author");
			JSONObject authorJson = JsonReader.readJsonFromUrl("http://costng.com/wp-json/wp/v2/users/"+ authorId);
			String author = authorJson.getString("name");
			System.out.println("\n-----------------------------<author>-----------------------------");
			System.out.println(author);
			System.out.println("-----------------------------</author>-----------------------------\n");
			
			
		}
	}
}
