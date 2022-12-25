package com.lab3.database;

import com.lab3.accounts.UserProfile;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBService {
    private final Connection connection;

    public DBService() {
        this.connection = getMysqlConnection();
    }

    public UserProfile getUserByLogin(String login) {
        try {
            return (new UsersDAO(connection).getUserByLogin(login));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addUser(UserProfile userProfile) {
        try {
            UsersDAO usersDAO = new UsersDAO(connection);
            usersDAO.createTable();
            usersDAO.insertUser(userProfile);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("java_dev?").          //db name
                    append("user=root&").          //login
                    append("password=1234");       //password

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}


