package TeamOpav.DataBaseActions.DBandTable;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLDatabaseCreator implements DatabaseCreation {
    private String databaseUrl;
    private String username;
    private String password;
    public Connection connection;
    private Statement statement;
    public String databaseName;

    public MySQLDatabaseCreator(String databaseUrl, String username, String password) throws SQLException {
        this.databaseUrl = databaseUrl;
        this.username = username;
        this.password = password;

        this.connection = DriverManager.getConnection(databaseUrl, username, password);
        this.statement = connection.createStatement();
    }

    @Override
    public void createDatabase(String databaseName, String username, String password) throws SQLException {
        statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + databaseName);
        System.out.println("Database " + databaseName + " created successfully!");

    }

    @Override
    public void selectDatabase(String databaseName) throws SQLException {
        try {
            if (connection == null || connection.isClosed()) {
                throw new IllegalStateException("Connection is closed");
            }
            Statement stmt = connection.createStatement();
            stmt.execute("USE " + databaseName);
            System.out.println("Selected database: " + databaseName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void createTable(String tableName, String[] columnNames, String[] columnTypes) throws SQLException {

        StringBuilder sql = new StringBuilder("CREATE TABLE " + tableName + " (");
        sql.append("id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,");
        for (int i = 0; i < columnNames.length; i++) {
            sql.append(columnNames[i]).append(" ").append(columnTypes[i]);
            if (i < columnNames.length - 1) {
                sql.append(", ");
            }
        }
        sql.append(")");

        statement.executeUpdate(sql.toString());
        System.out.println("Table " + tableName + " created successfully!");
    }

    public void updateTable(String tableName, String[] columnNames, String[] columnTypes) throws SQLException {
        //NOT READY YET!!!

        StringBuilder sql = new StringBuilder("CREATE TABLE " + tableName + " (");
        sql.append("\"INSERT INTO location (latitude, longitude, city_name, country_name, region, temperature, pressure, humidity, wind_direction, wind_speed, date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)\";");
        for (int i = 0; i < columnNames.length; i++) {
            sql.append(columnNames[i]).append(" ").append(columnTypes[i]);
            if (i < columnNames.length - 1) {
                sql.append(", ");
            }
        }
        sql.append(")");

        statement.executeUpdate(sql.toString());
        System.out.println("Table " + tableName + " updated successfully!");
    }





    public void close() throws SQLException {
        statement.close();
//            connection.close();

    }
}
