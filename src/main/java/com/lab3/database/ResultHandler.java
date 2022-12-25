package com.lab3.database;

import java.sql.SQLException;
import java.sql.ResultSet;

public interface ResultHandler<T> {
    T handle(ResultSet resultSet) throws SQLException;
}
