package TeamOpav.DataBaseActions.DBandTable.Creators;





import java.sql.*;

public class MySQLDatabaseCreator {
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


    public void createDatabase(String databaseName, String username, String password) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SHOW DATABASES LIKE '" + databaseName + "'");
        if(resultSet.next()) {
            System.out.println("Database " + databaseName + " already exists.");
            return;
        }
        statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + databaseName);
        System.out.println("Database " + databaseName + " created successfully!");
    }
    public void useDatabase(String databaseName) throws SQLException {
        statement.execute("USE " + databaseName);
        System.out.println("Using database " + databaseName + ".");
    }


    public void createTable(String tableName, String[] columnNames, String[] columnTypes) throws SQLException {
        // check if table already exists
        ResultSet resultSet = statement.executeQuery("SHOW TABLES LIKE '" + tableName + "'");
        if(resultSet.next()) {
            System.out.println("Table " + tableName + " already exists.");
            return;
        }

        StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS " + tableName + " (");
        sql.append("id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,");
        for (int i = 0; i < columnNames.length; i++) {
            sql.append(columnNames[i]).append(" ").append(columnTypes[i]);
            if (i < columnNames.length - 1) {
                sql.append(", ");
            }
        }
        sql.append(")");


        // initialize statement
        statement.executeUpdate(sql.toString());
        System.out.println("Table " + tableName + " created successfully!");
    }


    public void close() throws SQLException {
        statement.close();
          connection.close();

    }
}
