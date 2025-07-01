package com.example.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtil {

	private JDBCUtil() {
	}

	public static Connection getConnection() throws SQLException, IOException {

		String filePath = "src\\main\\java\\com\\example\\properties\\application.properties";
		File file = new File(filePath);
		FileInputStream inputStream = new FileInputStream(file);

		Properties properties = new Properties();
		properties.load(inputStream);

		Connection connection = DriverManager.getConnection(properties.getProperty("url"),
				properties.getProperty("userName"), properties.getProperty("password"));

		return connection;
	}

	public static void cleanUp(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {

		if (connection != null)
			connection.close();

		if (statement != null)
			statement.close();

		if (resultSet != null)
			resultSet.close();
	}
}
