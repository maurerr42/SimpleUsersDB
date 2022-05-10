package com.indraInterview.db;

import com.indraInterview.utils.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHandler {

    private final String JDBC_DRIVER = "org.h2.Driver";
    private final String DB_URL = "jdbc:h2:~/database/localdb";

    private Connection conn = null;
    private Statement stmt = null;

    private void connect() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        Utils.printDBHandlerMessage("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL,"","");
    }

    private void disconnect() throws SQLException {
        stmt.close();
        conn.close();
    }

    private String execute(String query) {
        String result = "";

        try {
            connect();
            Utils.printDBHandlerMessage("Executing query");
            stmt = conn.createStatement();
            if (query.toLowerCase().contains("select")) {
                result = doSelectQuery(query);
            } else {
                doQuery(query);
            }
            Utils.printDBHandlerMessage("Query executed");
            disconnect();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(stmt!=null) stmt.close();
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
        Utils.printDBHandlerMessage("Execute method end");

        return result;
    }

    private void doQuery(String query) throws SQLException {
        stmt.executeUpdate(query);
    }

    private String doSelectQuery(String query) throws SQLException {
        return selectResultsToString(stmt.executeQuery(query));
    }

    private String selectResultsToString(ResultSet resultSet) {
        StringBuilder resultBuilder = new StringBuilder();

        try {
            Boolean rowsNotFound = true;
            while (resultSet.next()) {
                if (rowsNotFound) rowsNotFound = false;

                resultBuilder.append("USER_ID: ");
                resultBuilder.append(resultSet.getInt("USER_ID"));
                resultBuilder.append(", USER_GUID: ");
                resultBuilder.append(resultSet.getString("USER_GUID"));
                resultBuilder.append(", USER_NAME: ");
                resultBuilder.append(resultSet.getString("USER_NAME"));
                if (!resultSet.isLast()) {
                    resultBuilder.append("\n");
                }
            }
            if (rowsNotFound) {
                resultBuilder.append("Zero rows found");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return resultBuilder.toString();
    }

    /**
     * Creates the table for this task in DB, use it if the table wasn't created manually
     */
    public void createTable() {
        execute("CREATE TABLE SUSERS "+
                "( USER_ID int, USER_GUID varchar(20), USER_NAME varchar(20));");
    }

    public void add(int userId, String userGuid, String userName) {
        if ((userGuid == null) || userGuid.isEmpty()) {
            System.out.println("Missing userGuid parameter in DBHandler.add(), query will be not executed.");
            return;
        }
        if ((userName == null) || userName.isEmpty()) {
            System.out.println("Missing userName parameter in DBHandler.add(), query will be not executed.");
            return;
        }
        execute("INSERT INTO SUSERS (USER_ID, USER_GUID, USER_NAME)\n" +
                "VALUES (" + userId + ", '" + userGuid + "', '" + userName + "');");
    }

    public String printAll() {
        return execute("SELECT * FROM SUSERS;");
    }

    public void deleteAll() {
        execute("TRUNCATE TABLE SUSERS;");
    }
}
