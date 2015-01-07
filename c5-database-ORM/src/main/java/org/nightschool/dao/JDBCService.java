package org.nightschool.dao;

import java.sql.*;

public class JDBCService
{
    public ResultSet select(String sql) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException
    {
        Class.forName("org.postgresql.Driver").newInstance();

        Connection connection = getConnection();

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);

        connection.close();

        return resultSet;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/shopping_mall", "twer", "123456");
    }

    public boolean insert(String sql) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException
    {
        Class.forName("org.postgresql.Driver").newInstance();

        Connection connection = getConnection();

        Statement statement = connection.createStatement();

        statement.executeUpdate(sql);
        connection.close();

        return true;
    }


}
