package TeamOpav;

import TeamOpav.DataBaseActions.DBClass.AddingData;
import TeamOpav.DataBaseActions.DBandTable.Creators.MySQLDatabaseCreator;
import TeamOpav.DataBaseActions.DBClass.ShowTable;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        String databaseUrl = "jdbc:mysql://localhost/";
        String username = "root"; //null-no, error message, can be null in persistence
        String password = "3312"; //empty-no, error message,can be empty in persistence
        String databaseName = "weather_api";


        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome!");


//options presentation:
        System.out.println("If you want to add specific location to your weather database, please enter 1");
        System.out.println("If you want to display all added locations to your datatable, please enter 2");
        System.out.println("If you want to download weather values, please enter 3");
        System.out.println("make your choice: ");


//checking entry:
        int choice;
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
        } else {
            System.out.println("Invalid data!");
            return;
        }

        //switch cases:
        switch (choice) {
            case 1:
                System.out.print("Please enter City: ");
                Scanner scn = new Scanner(System.in);
                String inputCity = scn.nextLine();


                if (inputCity.length() > 0) {


                    try {
                        MySQLDatabaseCreator creator = new MySQLDatabaseCreator(databaseUrl, username, password);
                        creator.createDatabase("weather_api", username, password);
                        creator.useDatabase(databaseName);
                      creator.createTable("location", new String[]{"id", "latitude", "longitude", "city_name",
                                        "country_name",
                                        "region", "temperature", "pressure", "humidity", "wind_direction", "wind_speed", "date"},
                                new String[]{"INT NOT NULL AUTO_INCREMENT PRIMARY KEY", "DOUBLE", "DOUBLE", "VARCHAR(255)", "VARCHAR(255)", "VARCHAR(255)", "DOUBLE", "DOUBLE", "DOUBLE", "VARCHAR(50)", "DOUBLE", "DATE"});
                        System.out.println("Database and table are ready!");
                        creator.close();


                    } catch (SQLException e) {
                        System.out.println("Error creating database and table: " + e.getMessage());

                }

                        AddingData add = new AddingData();
                        add.addCity(inputCity);
                }
                break;


            case 2:
                System.out.println("here is a list of all your added locations to your datatable: ");
                System.out.println();

                try {
                    ShowTable show = new ShowTable("weather_api",username,password);
                    show.showDatabaseTable("location");
                } catch (SQLException e) {
                    System.out.println("Error reading table: " + e.getMessage());
                }

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


