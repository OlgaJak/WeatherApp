package TeamOpav.Connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class HTTPWeatherCurrentHist {
    public static StringBuffer getWeatherData(String location) throws Exception {
        String apiKey = "372e85960410a8f82e15c5353e020e2e";
        URL url = new URL("http://api.weatherstack.com/current?access_key=" + apiKey + "&query=" + location + "&units=m");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        con.disconnect();

        return response;
    }

    public static StringBuffer getHistoricalData(String location, Date date) throws Exception {
        String apiKey = "372e85960410a8f82e15c5353e020e2e";
        URL url = new URL("https://api.weatherstack.com/historical\n" +
                "    ? access_key" + apiKey +
                "    & query =" + location +
                "    & historical_date =" + date +
                "    & hourly = 0");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        con.disconnect();

        return response;
    }
}


