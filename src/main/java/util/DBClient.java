package util;

import lombok.extern.log4j.Log4j2;

import java.sql.*;

@Log4j2
public class DBClient {

    public static final String DB_URL = "jdbc:mysql://localhost:3306/newdb";
    public static final String USER = "root";
    public static final String PASSWORD = "mySqlServer23";

    private Connection connection = null;
    private Statement statement = null;

    public void connect() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            statement = connection.createStatement();
        } catch (SQLException exception) {
            log.error(exception.getMessage());
        }
    }

    public ResultSet executeQuery(String query) {
        try {
            return statement.executeQuery(query);
        } catch (SQLException exception) {
            log.error(exception.getMessage());
        }
        return null;
    }

    public void executeUpdate(String query) {
        try {
          int i =  statement.executeUpdate(query);
          log.info("Rows number affected is: ");
        } catch (SQLException exception) {
            log.error(exception.getMessage());
        }
    }

    public void executeUpdateWithPreparedStatement(String query) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "Garry");
            preparedStatement.setInt(2, 29);
            preparedStatement.executeUpdate();
            log.info("Rows number affected is: ");
        } catch (SQLException exception) {
            log.error(exception.getMessage());
        }
    }

    public ResultSet selectFrom(String tableName) {
        try {
            return statement.executeQuery(String.format("SELECT * FROM %s", tableName));
        } catch (SQLException exception) {
            log.error(exception.getMessage());
        }
        return null;
    }

    public void close() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException exception) {
            log.error(exception.getMessage());
        }
    }
}
