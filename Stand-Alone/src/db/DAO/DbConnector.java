package src.db.DAO;

import src.db.ScriptRunner;
import src.db.twoPC.TwoPCClientSocket;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

/**
 * Created by vishn_000 on 22.02.14.
 */
public class DbConnector {

    private String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private String DB_URL = "jdbc:mysql://localhost/";
    private String DB_PASSWORD = "root";
    private String DB_USER = "root";

    private Connection connection;
    private Statement statement;
    private TwoPCClientSocket twoPCClientSocket;

    public DbConnector() {
        this.twoPCClientSocket = new TwoPCClientSocket(this);
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
            connection.close();
            return true;

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

    public void openConnectionToDB(String dbName) {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName(JDBC_DRIVER).newInstance();
                connection = DriverManager.getConnection(DB_URL + dbName, DB_USER, DB_PASSWORD);

                //for 2pc: commit or rollback by getting 2pc command
                connection.setAutoCommit(false);
            }

        } catch (InstantiationException | IllegalAccessException
                | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String query) {

        try {

            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean executeUpdate(String query) {

        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);

            statement.close();

            return twoPCClientSocket.writeToServer(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean executeUpdateWithoutSending(String query) {

        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);

            statement.close();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public void closeStatement() {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void commitQuery() {
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollbackQuery() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
