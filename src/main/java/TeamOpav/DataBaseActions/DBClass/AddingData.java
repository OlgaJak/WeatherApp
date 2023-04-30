package TeamOpav.DataBaseActions.DBClass;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class AddingData {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("weather_app");

    public static void addLocation(String city, String country, String region, double longitude, double latitude,
                                   double temperature,
                                   double pressure, double humidity, String wind_direction, double wind_speed) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();

            Location loc = new Location();
            loc.setCityName(city);
            loc.setCountryName(country);
            loc.setRegion(region);
            loc.setLongitude(longitude);
            loc.setLatitude(latitude);
            loc.setTemperature(temperature);
            loc.setPressure(pressure);
            loc.setHumidity(humidity);
            loc.setWindDirection(wind_direction);
            loc.setWindSpeed(wind_speed);


            em.persist(loc);
            et.commit();
        } catch (Exception e) {
            if (et != null) {
                et.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();

        }

    }
}
