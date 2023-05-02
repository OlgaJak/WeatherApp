package TeamOpav.DataBaseActions.DBClass;

import TeamOpav.DataBaseActions.DBClass.Location;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShowTable {
    private Statement statement;
    public String databaseName;
    public Connection connection;

    public ShowTable(String databaseName, String username, String password) throws SQLException {
        this.databaseName = databaseName;
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost/" + databaseName, username, password);
        this.statement = connection.createStatement();
    }

    public void showDatabaseTable(String tableName) throws SQLException {
        String sql = "SELECT * FROM `" + tableName + "`";

        ResultSet resultSet = statement.executeQuery(sql);
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int numColumns = rsmd.getColumnCount();

        int[] columnWidths = new int[numColumns];
        for (int i = 1; i <= numColumns; i++) {
            columnWidths[i - 1] = Math.max(rsmd.getColumnName(i).length(), 14);// set a minimum column width of 10
            // characters
        }

        // print table header
        for (int i = 1; i <= numColumns; i++) {
            String columnName = rsmd.getColumnName(i);
            System.out.print(String.format("%-" + columnWidths[i - 1] + "s", columnName));
            System.out.print(" | ");
        }
        System.out.println();

        // print table separator
        for (int i = 1; i <= numColumns; i++) {
            System.out.print(String.format("%-" + columnWidths[i - 1] + "s", "-".repeat(columnWidths[i - 1])));
            System.out.print("-+-");
        }
        System.out.println();

        // print table data
        while (resultSet.next()) {
            for (int i = 1; i <= numColumns; i++) {
                String columnValue = resultSet.getString(i);
                System.out.print(String.format("%-" + columnWidths[i - 1] + "s", columnValue != null ? columnValue : "NULL"));
                System.out.print(" | ");
            }
            System.out.println();
        }
    }
}