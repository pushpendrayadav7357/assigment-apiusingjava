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
        String urlString = "https://api.zippopotam.us/us/33162";

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


            String postCode = jsonAPIResponse.getString("post code");
            String country = jsonAPIResponse.getString("country");
            String countryAbbreviation = jsonAPIResponse.getString("country abbreviation");

            System.out.println("Post Code: " + postCode);
            System.out.println("Country: " + country);
            System.out.println("Country Abbreviation: " + countryAbbreviation);


            JSONArray placesArray = jsonAPIResponse.getJSONArray("places");
            for (int i = 0; i < placesArray.length(); i++) {
                JSONObject place = placesArray.getJSONObject(i);
                String placeName = place.getString("place name");
                String state = place.getString("state");
                String stateAbbreviation = place.getString("state abbreviation");
                String longitude = place.getString("longitude");
                String latitude = place.getString("latitude");

                System.out.println("Place Name: " + placeName);
                System.out.println("State: " + state);
                System.out.println("State Abbreviation: " + stateAbbreviation);
                System.out.println("Longitude: " + longitude);
                System.out.println("Latitude: " + latitude);
            }
        } else {
            System.out.println("API call could not be made!!!");
        }
    }
}
