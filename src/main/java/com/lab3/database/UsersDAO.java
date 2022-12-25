package com.lab3.database;

import com.lab3.accounts.UserProfile;

import java.sql.Connection;
import java.sql.SQLException;

public class UsersDAO {
    private final QueryExecutor queryExecutor;

    public UsersDAO(Connection connection) {
        this.queryExecutor = new QueryExecutor(connection);
    }

    public UserProfile getUserByLogin(String login) throws SQLException {
        return queryExecutor.execQuery(
                "select * from users where login = '" + login + "';",
                result -> {
                    result.next();
                    return new UserProfile(
                            result.getString(1),
                            result.getString(2),
                            result.getString(3)
                    );
                });
    }

    public void insertUser(UserProfile userProfile) throws SQLException {
        queryExecutor.execUpdate(String.format("insert into users values ('%s', '%s', '%s');",
                userProfile.getLogin(), userProfile.getEmail(), userProfile.getPass()));
    }

    public void createTable() throws SQLException {
        queryExecutor.execUpdate(
                "create table if not exists users(" +
                        "login varchar(100) primary key,email varchar(100),password varchar(100));");
    }

    public void dropTable() throws SQLException {
        queryExecutor.execUpdate("drop table users;");
    }
}
