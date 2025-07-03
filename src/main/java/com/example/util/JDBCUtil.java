package com.example.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {

	private JDBCUtil() {
	}

	public static Connection getConnection() throws SQLException, IOException {

		 String jdbcUrl = "jdbc:mysql://localhost:3306/javafsdcourse";
//		String jdbcUrl = "jdbc:mysql:///javafsdcourse";
		String userName = "root";
		String password = "root";

		System.out.println(jdbcUrl + userName + password);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = DriverManager.getConnection(jdbcUrl, userName, password);
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
