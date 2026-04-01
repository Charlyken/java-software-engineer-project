package com.esigelec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String DB_URL = "jdbc:oracle:thin:@//oracle.esigelec.fr:1521/orcl.intranet.int";
    private static final String DB_USER = "C##BDD6_16";
    private static final String DB_PASSWORD = "BDD616";
    private static Connection connection;

    private DataBaseConnection (){}

    public static synchronized Connection getConnection() throws SQLException { //une seule connexion à la fois 'synchronized'
        if (connection == null || connection.isClosed()){
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        }
        return connection;
    }



}
