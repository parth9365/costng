import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.jsoup.Jsoup;

public class APIHelper {

	public static void main(String[] args) throws IOException, JSONException {
		JSONArray jsonArray = JsonReader.readJsonArrayFromUrl("http://costng.com/wp-json/wp/v2/posts?categories=17");
		for(int i=0; i<jsonArray.length(); i++) {
			System.out.println(jsonArray.get(i));

			String imageurl = jsonArray.getJSONObject(i).getString("jetpack_featured_media_url");
			System.out.println("\n\n-----------------------------<image>-----------------------------");
			System.out.println(imageurl);
			System.out.println("-----------------------------</image>-----------------------------\n\n");
			
			String title = jsonArray.getJSONObject(i).getJSONObject("title").getString("rendered");
			System.out.println("\n\n-----------------------------<title>-----------------------------");
			System.out.println(title);
			System.out.println("-----------------------------</title>-----------------------------\n\n");
			
			String content = Jsoup.parse(jsonArray.getJSONObject(i).getJSONObject("content").getString("rendered")).text();
			System.out.println("\n\n-----------------------------<content>-----------------------------");
			System.out.println(content);
			System.out.println("-----------------------------</content>-----------------------------\n\n");
			
		}
	}
}
