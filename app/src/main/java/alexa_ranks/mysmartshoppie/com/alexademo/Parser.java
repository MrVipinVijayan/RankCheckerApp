package alexa_ranks.mysmartshoppie.com.alexademo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class Parser {

    private static final String TAG = "Parser";

    public Rank parse(String response) {

        Rank rank = new Rank();
        try {
            JSONObject xmlJSONObj = XML.toJSONObject(response);
            JSONObject alexa = xmlJSONObj.getJSONObject("ALEXA");
            JSONArray sd = alexa.getJSONArray("SD");
            JSONObject info = sd.getJSONObject(1);

            JSONObject popularity = info.getJSONObject("POPULARITY");
            rank.setWebsiteUrl(popularity.getString("URL"));
            rank.setGlobalRank(popularity.getString("TEXT"));

            JSONObject country = info.getJSONObject("COUNTRY");
            rank.setCountry(country.getString("NAME"));
            rank.setCountryRank(country.getString("RANK"));
        } catch (JSONException je) {
            System.out.println(je.toString());
        }

        return rank;
    }
}
