package TeamOpav;

import TeamOpav.Connection.HTTPWeatherCurrentHist;
import TeamOpav.DataBaseActions.DBandTable.MySQLDatabaseCreator;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String databaseUrl = "jdbc:mysql://localhost/";
        String username = "root"; //null-no, error message, can be null in persistence
        String password = "3312"; //empty-no, error message,can be empty in persistence
        String databaseName = "weather_api";
        JSONObject retrievedData;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome!");


//creating database and table if not exist:
        try {
            MySQLDatabaseCreator creator = new MySQLDatabaseCreator(databaseUrl, username, password);

            creator.createDatabase("weather_api", username, password);

            creator.selectDatabase(databaseName);

            creator.createTable("location", new String[]{"id", "latitude", "longitude", "city_name", "country_name",
                            "region", "temperature", "pressure", "humidity", "wind_direction", "wind_speed", "date"},
                    new String[]{"INT NOT NULL AUTO_INCREMENT PRIMARY KEY", "DOUBLE", "DOUBLE", "VARCHAR(255)", "VARCHAR(255)", "VARCHAR(255)", "DOUBLE", "DOUBLE", "DOUBLE", "VARCHAR(50)", "DOUBLE", "DATE"});

            System.out.println("Database and table are ready!");
            creator.close();

        } catch (SQLException e) {
            System.out.println("Error creating database and table: " + e.getMessage());
        }


//options presentation:
        System.out.println("If you want to add specific location to your weather database, please enter 1");
        System.out.println("If you want to display all added locations tp ypu datatable, please enter 2");
        System.out.println("If you want to download weather values, please enter 3");
        System.out.println("make your choice: ");



        int choice;
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
        } else {
            System.out.println("Invalid data!");
            return;
        }

        switch (choice) {
            case 1:
                System.out.print("Please enter City: ");
                Scanner scn = new Scanner(System.in);
                String city = scn.nextLine();

                if (city.length() > 0) {
                    HTTPWeatherCurrentHist.HTTPWeatherCurrent request = new HTTPWeatherCurrentHist.HTTPWeatherCurrent(
                            "372e85960410a8f82e15c5353e020e2e", city);

                    StringBuilder data = request.getData();

                    retrievedData = new JSONObject(data.toString());
                    System.out.println(retrievedData);
                }

                break;
            case 2:
                System.out.println("-");
                break;
            case 3:
                System.out.println("-");
                break;
            case 4:
                System.exit(0);
                break;
        }


        }

    }
