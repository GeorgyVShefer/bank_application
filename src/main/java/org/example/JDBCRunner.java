package org.example;

import org.example.util.ConnectionManager;
import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCRunner {


    public static void main(String[] args) {
        try(Connection open = ConnectionManager.open()){
            System.out.println("Ok");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
