package TeamOpav.DataBaseActions.DBandTable;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadJSON {
    public static void seeForecast(String jsonString) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(jsonString);

            // Extract the location data: city, temperature, pressure, humidity, wind direction and speed
            String cityName = jsonNode.get("location").get("name").asText();
            String country = jsonNode.get("location").get("country").asText();
            double temperature = jsonNode.get("current").get("temperature").asDouble();
            double pressure = jsonNode.get("current").get("pressure").asDouble();
            double humidity = jsonNode.get("current").get("humidity").asDouble();
            String windDir = jsonNode.get("current").get("wind_dir").asText();
            double windSpeed = jsonNode.get("current").get("wind_speed").asDouble();

            // Print data
            System.out.println(cityName + ", " + country + ":");
            System.out.println("Current temperature: " + temperature + "°C");
            System.out.println("Pressure: " + pressure + "hPa");
            System.out.println("Humidity: " + humidity + "%");
            System.out.println("Wind: " + + windSpeed + " m/s (" + windDir + ")");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
