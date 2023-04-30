package TeamOpav.DataBaseActions.DBandTable;

import java.sql.SQLException;

public interface DatabaseCreation {

    void createDatabase(String databaseName, String username, String password) throws SQLException;

    void selectDatabase(String weatherApi) throws SQLException;

    void createTable(String tableName, String[] columnNames, String[] columnTypes) throws SQLException;



}

