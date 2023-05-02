package TeamOpav.DataBaseActions.DBClass;

import TeamOpav.Connection.HTTPWeatherCurrentHist;
import TeamOpav.Connection.HTTPWeatherCurrentHist2;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddingData {
    public static void addCity(String inputCity) throws Exception {

        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;

        Location newLocation = new Location();

        //API connection1
        String jsonString1 = String.valueOf(HTTPWeatherCurrentHist.getWeatherData(inputCity));
        ObjectMapper mapper1 = new ObjectMapper();
        JsonNode jsonNode1 = mapper1.readTree(jsonString1);
        String city = jsonNode1.get("location").get("name").asText();
        double lon = jsonNode1.get("location").get("lon").asDouble();
        double lat = jsonNode1.get("location").get("lat").asDouble();
        String region = jsonNode1.get("location").get("region").asText();
        String country = jsonNode1.get("location").get("country").asText();
        double temperature1 = jsonNode1.get("current").get("temperature").asDouble();
        double pressure1 = jsonNode1.get("current").get("pressure").asDouble();
        double humidity1 = jsonNode1.get("current").get("humidity").asDouble();
        String windDir = jsonNode1.get("current").get("wind_dir").asText();
        double windSpeed1 = jsonNode1.get("current").get("wind_speed").asDouble();


        //API connection2
        String jsonString2 = String.valueOf(HTTPWeatherCurrentHist2.getWeatherData(inputCity));
        ObjectMapper mapper2 = new ObjectMapper();
        JsonNode jsonNode2 = mapper2.readTree(jsonString2);

        double temperature2 = jsonNode2.get("main").get("temp").doubleValue();
        double pressure2 = jsonNode2.get("main").get("pressure").doubleValue();
        double humidity2 = jsonNode2.get("main").get("humidity").doubleValue();
        double windSpeed2 = jsonNode2.get("wind").get("speed").doubleValue();

        //To retrieve data dd-MM-yyyy format
        long unixTimestamp = jsonNode2.get("dt").asLong();
        Date rawDate = new Date(unixTimestamp * 1000L); // Convert Unix timestamp to Java Date object
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); // Set the desired date format
        String date = sdf.format(rawDate); // Convert Date object to formatted date string


        //Average of APIs values;

        double temp = (temperature1 + temperature2) / 2;
        String formattedTemp = String.format("%.2f", temp);

        double pressure = (pressure1 + pressure2) / 2;
        double humidity = (humidity1 + humidity2) / 2;
        double windSpeed = (windSpeed1 + windSpeed2) / 2;


        //Save average data to database
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            String sql = "INSERT INTO location (latitude, longitude, city_name, country_name, region, temperature, pressure, humidity, wind_direction, wind_speed, date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            newLocation.setCityName(city);
            newLocation.setDate(date);
            newLocation.setLongitude(lon);
            newLocation.setLatitude(lat);
            newLocation.setRegion(region);
            newLocation.setCountryName(country);
            newLocation.setTemperature(temp);
            newLocation.setPressure(pressure);
            newLocation.setHumidity(humidity);
            newLocation.setWindDirection(windDir);
            newLocation.setWindSpeed(windSpeed);

            entityManager.persist(newLocation);
            entityManager.getTransaction().commit();
            System.out.println(city + ", " + country + " - was added to database successfully!");

            entityManager.persist(newLocation);
            entityManager.getTransaction().commit();
            System.out.println("Location added successfully!");
        } catch (Exception e) {

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
            if (entityManagerFactory != null) {
                entityManagerFactory.close();
            }
        }
    }
}
