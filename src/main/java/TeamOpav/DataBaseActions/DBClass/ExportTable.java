package TeamOpav.DataBaseActions.DBClass;

import au.com.bytecode.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class ExportTable {



        private Connection connection;

        public ExportTable(String databaseName, String username, String password) throws SQLException {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost/" + databaseName, username, password);
        }

        public void saveTableDataToFile(String tableName, String fileName, String fileType) throws SQLException, IOException {

            String sql = "SELECT * FROM `" + tableName + "`";

            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int numColumns = rsmd.getColumnCount();
            List<String[]> rows = new ArrayList<>();

            // Get the maximum length of the values in each column
            int[] columnWidths = new int[numColumns];
            for (int i = 1; i <= numColumns; i++) {
                columnWidths[i - 1] = Math.max(rsmd.getColumnName(i).length(), 1); // Set a minimum column width of
                // 10 characters
            }
            while (resultSet.next()) {
                String[] row = new String[numColumns];
                for (int i = 1; i <= numColumns; i++) {
                    String columnValue = resultSet.getString(i);
                    row[i - 1] = columnValue != null ? columnValue : "NULL";
                    columnWidths[i - 1] = Math.max(columnWidths[i - 1], row[i - 1].length()); // Update column width if necessary
                }
                rows.add(row);
            }


            String filePath = fileName + "." + fileType;
            try (Writer writer = new FileWriter(filePath);
                 CSVWriter csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.DEFAULT_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END)) {


                // Write column headers
                String[] headers = new String[numColumns];
                for (int i = 1; i <= numColumns; i++) {
                    headers[i - 1] = rsmd.getColumnName(i);
                }
                csvWriter.writeNext(headers);

                // Write table data
                for (String[] row : rows) {
                    String[] formattedRow = new String[numColumns];
                    for (int i = 0; i < numColumns; i++) {
                        formattedRow[i] = String.format("%-" + columnWidths[i] + "s", row[i]); // Format the row with the appropriate column width
                    }
                    csvWriter.writeNext(formattedRow);
                }
            }
            System.out.println("Table data written to file: " + filePath);
        }



}


