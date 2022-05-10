package com.indraInterview.db;

import com.indraInterview.utils.Utils;

import java.sql.*;

public class DBHandler {

    private final String JDBC_DRIVER = "org.h2.Driver";
    private final String DB_URL = "jdbc:h2:~/database/localdb";

    private Connection conn = null;
    private Statement stmt = null;

    private void connect() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        Utils.printSystemMessage("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL,"","");
    }

    private void disconnect() throws SQLException {
        stmt.close();
        conn.close();
    }

    private void execute(String query) {
        try {
            connect();
            Utils.printSystemMessage("Executing query");
            stmt = conn.createStatement();
            if (query.toLowerCase().contains("select")) {
                doSelectQuery(query);
            } else {
                doQuery(query);
            }
            Utils.printSystemMessage("Query executed");
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
        Utils.printSystemMessage("Execute method end");
    }

    private void doSelectQuery(String query) throws SQLException {
        printOutSelectResults(stmt.executeQuery(query));
    }

    private void printOutSelectResults(ResultSet resultSet) {
        try {
            Boolean rowsNotFound = true;
            while (resultSet.next()) {
                if (rowsNotFound) rowsNotFound = false;

                System.out.print("USER_ID: " + resultSet.getInt("USER_ID"));
                System.out.print(", USER_GUID: " + resultSet.getString("USER_GUID"));
                System.out.println(", USER_NAME: " + resultSet.getString("USER_NAME"));
            }
            if (rowsNotFound) {
                System.out.println("Zero rows found");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void doQuery(String query) throws SQLException {
        stmt.executeUpdate(query);
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

    public void printAll() {
        execute("SELECT * FROM SUSERS;");
    }

    public void deleteAll() {
        execute("TRUNCATE TABLE SUSERS;");
    }
}
