package org.example.util;

import java.lang.ref.PhantomReference;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public final class ConnectionManager {
    private static final System.Logger logger = System.getLogger(ConnectionManager.class.getName());
    private static final String URL = "db.url";
    private static final String USERNAME = "db.username";
    private static final String PASSWORD = "db.password";

    private ConnectionManager() {
    }

    public static Connection open() {
        try {
            Driver driver = new org.postgresql.Driver();
            return DriverManager.getConnection(PropertiesUtil.get(URL),
                    PropertiesUtil.get(USERNAME),
                    PropertiesUtil.get(PASSWORD));
        } catch (SQLException e) {
            logger.log(System.Logger.Level.ERROR, "Unable to connect to the Database");
            throw new RuntimeException(e);
        }
    }
}
