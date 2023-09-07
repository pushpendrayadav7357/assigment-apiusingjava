import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {

        URL url = null;
        HttpURLConnection connection = null;
        int responseCode = 0;
        String urlString = "https://api.chucknorris.io/jokes/random";

        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            System.out.println("Problem in URL");
        }

        // Connection

        try {
            connection = (HttpURLConnection) url.openConnection();
            responseCode = connection.getResponseCode();
        } catch (Exception e) {
            System.out.println("Connection problem");
        }



        if (responseCode == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder apiData = new StringBuilder();
            String readLine;

            while ((readLine = in.readLine()) != null) {
                apiData.append(readLine);
            }


            try {
                in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println(apiData.toString());
            JSONObject jsonAPIResponse = new JSONObject(apiData.toString());
            String categories = jsonAPIResponse.getJSONArray("categories").toString();

//            String categories = jsonAPIResponse.getString("categories").toString();
            String created_at = jsonAPIResponse.getString("created_at");
            String icon_url = jsonAPIResponse.getString("icon_url");
            String id = jsonAPIResponse.getString("id");
            String updated_at = jsonAPIResponse.getString("updated_at");
            String url1 = jsonAPIResponse.getString("url");
            String value = jsonAPIResponse.getString("value");



            System.out.println("Categories: " + categories);
            System.out.println("Created_at: " + created_at);
            System.out.println("Icon_url: " + icon_url);
            System.out.println("Id: " + id);
            System.out.println("Updated_at: " + updated_at);
            System.out.println("Url1: " + url1);
            System.out.println("Value: " + value);

        } else {
            System.out.println("API call could not be made!!!");
        }
    }
}
