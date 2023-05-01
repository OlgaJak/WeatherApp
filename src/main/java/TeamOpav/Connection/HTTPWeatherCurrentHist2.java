package TeamOpav.Connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class HTTPWeatherCurrentHist2 {
    public static StringBuffer getWeatherData(String location) throws Exception {
        String apiKey = "05e961f66c1f360264381097f750ec46";
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + apiKey);
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


//historical link: https://history.openweathermap.org/data/2.5/history/city?q={city name},{country code}&type=hour&start={start}&end={end}&appid={API key}

}



