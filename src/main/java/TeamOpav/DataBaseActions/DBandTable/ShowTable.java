package TeamOpav.DataBaseActions.DBandTable;

import TeamOpav.DataBaseActions.DBClass.Location;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ShowTable {
    public static void displayTable() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();

        TypedQuery<Location> query = em.createQuery("SELECT l FROM Functions.Location l", Location.class);
        List<Location> locations = query.getResultList();
        System.out.println(String.format("%15S","id | cityName | longitude | latitude | region | countryName"));
        for (Location location : locations) {
            System.out.println(String.format("%15s",
                    location.getId() + " | " + location.getCityName() + " | " + location.getLatitude() + "| " + location.getLongitude() + " | " + location.getRegion() + " | " + location.getCountryName()));
        }

        em.close();
        emf.close();
    }
}
