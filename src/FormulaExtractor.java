import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

public class FormulaExtractor {

	public static Map<String, String> extractFormulas(JSONObject json) {
		Map<String, String> formulas = new HashMap<String, String>();

		String rawString = json.getJSONObject("content").getString("rendered");

//		System.out.println(rawString);
		String[] formulaString = rawString.split("\n");

		String name = "";
		String formulaImg = "";
		for (String s : formulaString) {
			if (!s.equals("")) {
				if (s.contains("<h3>")) {
					name = s.substring(4, s.length() - 5);
					name = Jsoup.parse(name).text();
				}

				if (s.contains("<figure")) {
					Pattern p = Pattern.compile("src=\"(.*?)\"");
					Matcher m = p.matcher(s);
					if (m.find()) {
						formulaImg = m.group(1);
						if(!name.equals("")) {
							formulas.put(name, formulaImg);
						}
					}
				}
				
				if(s.contains("<p>")) {
					formulaImg = Jsoup.parse(s).text();
					if(!name.equals("")) {
						formulas.put(name, formulaImg);
					}
				}
				
			}
		}

		return formulas;
	}
	
	public static Map<String, String> getAccountingFormulas() throws JSONException, IOException {
		JSONObject accountingJson = JsonReader.readJsonFromUrl("http://costng.com/wp-json/wp/v2/posts/241");
		return extractFormulas(accountingJson);
	}

	public static Map<String, String> getFinanceFormulas() throws JSONException, IOException {
		JSONObject financeFormulas = JsonReader.readJsonFromUrl("http://costng.com/wp-json/wp/v2/posts/260");
		return extractFormulas(financeFormulas);
	}

	public static void main(String[] args) throws JSONException, IOException {
		Map<String, String> accountingFormulas = getAccountingFormulas();
		
		for(String s: accountingFormulas.keySet()) {
			System.out.println(s + " : " + accountingFormulas.get(s));
		}
		
//		System.out.println(new JSONObject(accountingFormulas));
	}

}
