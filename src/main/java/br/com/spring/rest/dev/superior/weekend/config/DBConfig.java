package br.com.spring.rest.dev.superior.weekend.config;

import br.com.spring.rest.dev.superior.weekend.util.exceptions.DbException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Properties;

public final class DBConfig {

    private DBConfig() {

    }

    private static final String PROPERTY_URL = "dburl";
    private static Connection connection = null;

    public static Connection getConnection() {
        if (Objects.isNull(connection)) {
            try {
                Properties properties = loadProperties();
                String url = Objects.requireNonNull(properties).getProperty(PROPERTY_URL);
                connection = DriverManager.getConnection(url, properties);
            } catch (SQLException exception) {
                throw new DbException(exception.getMessage());
            }
        }
        return connection;
    }

    private static Properties loadProperties() {
        try (FileInputStream fileInputStream = new FileInputStream("db.properties")) {
            Properties properties = new Properties();
            properties.load(fileInputStream);
            return properties;
        } catch (IOException e) {
            throw new DbException(e.getLocalizedMessage());
        }
    }

    public static void closeConnection() {
        try {
            if (Objects.nonNull(connection)) {
                connection.close();
            }
        } catch (SQLException exception) {
            throw new DbException(exception.getMessage());
        }
    }

    public static void closeStatement(Statement statement) {
        try {
            if (Objects.nonNull(statement)) {
                statement.close();
            }
        } catch (SQLException exception) {
            throw new DbException(exception.getMessage());
        }
    }
}
