package src.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

/**
 * Created by vishn_000 on 22.02.14.
 */
public class DbConnector {

    String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    String DB_URL = "jdbc:mysql://localhost/";
    String DB_PASSWORD = "root";
    String DB_USER = "root";

    public void openConnection() {
        try {
            Class.forName(JDBC_DRIVER).newInstance();
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement statement = connection.createStatement();
            connection.close();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public boolean isDBExists(String dbName) {
        try {
            Class.forName(JDBC_DRIVER).newInstance();
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            ResultSet resultSet = connection.getMetaData().getCatalogs();

            while (resultSet.next()) {
                String databaseName = resultSet.getString(1);
                if (dbName.equals(databaseName)) {
                    return true;
                }
            }
            resultSet.close();
            connection.close();

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean createDBIfNotExists(String dbName) {
        if (!isDBExists(dbName)) {
            try {
                Class.forName(JDBC_DRIVER).newInstance();
                Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement();
                statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + dbName);
                statement.close();
                connection.close();
            } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    public boolean executeDump(String pathToDump, String dbName) {
        try {
            System.out.println("LOADING DUMP OF " + pathToDump);
            createDBIfNotExists(dbName);

            Class.forName(JDBC_DRIVER).newInstance();
            Connection connection = DriverManager.getConnection(DB_URL + dbName, DB_USER, DB_PASSWORD);

            ScriptRunner runner = new ScriptRunner(connection, false, false);
            runner.runScript(new BufferedReader(new FileReader(pathToDump)));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }


}
