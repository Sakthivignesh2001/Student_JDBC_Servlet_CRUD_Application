package com.example.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.example.dto.Student;
import com.example.util.JDBCUtil;

public class StudentDaoImpl implements StudentDao {

	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	@Override
	public List<Student> getAllStudentDetails() {

		List<Student> students = null;

		try {
			connection = JDBCUtil.getConnection();

			if (connection != null) {

				String sqlSelectAllQuery = "select id, name, address, marks from students;";

				preparedStatement = connection.prepareStatement(sqlSelectAllQuery);

				if (preparedStatement != null) {

					resultSet = preparedStatement.executeQuery();

					if (resultSet != null) {

						students = new LinkedList<>();

						while (resultSet.next()) {

							Student student = new Student();

							student.setId(resultSet.getInt(1));
							student.setName(resultSet.getString(2));
							student.setAddress(resultSet.getString(3));
							student.setMarks(resultSet.getInt(4));

							students.add(student);
						}

						System.out.println("Students count :: " + students.size()); // For debugging purpose

						if (students.size() != 0)
							return students;
					}
				}
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCUtil.cleanUp(connection, preparedStatement, resultSet);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	@Override
	public Student getStudentDetailById(int id) {

		Student student = null;

		try {
			connection = JDBCUtil.getConnection();

			if (connection != null) {

				String sqlSelectByIdQuery = "select id, name, address, marks from students where id = ?;";

				preparedStatement = connection.prepareStatement(sqlSelectByIdQuery);

				if (preparedStatement != null) {

					preparedStatement.setInt(1, id);

					resultSet = preparedStatement.executeQuery();

					if (resultSet != null) {

						if (resultSet.next()) {
							student = new Student();
							student.setId(resultSet.getInt(1));
							student.setName(resultSet.getString(2));
							student.setAddress(resultSet.getString(3));
							student.setMarks(resultSet.getInt(4));

							return student;
						}
					}
				}
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCUtil.cleanUp(connection, preparedStatement, resultSet);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	@Override
	public String insertStudentDetail(Student student) {

		try {
			connection = JDBCUtil.getConnection();

			if (connection != null) {

				String sqlInsertQuery = "insert into students(name, address, marks) value(?, ?, ?);";

				preparedStatement = connection.prepareStatement(sqlInsertQuery);

				if (preparedStatement != null) {

					preparedStatement.setString(1, student.getName());
					preparedStatement.setString(2, student.getAddress());
					preparedStatement.setInt(3, student.getMarks());

					int updateResult = preparedStatement.executeUpdate();

					System.out.println(
							"Row affected for insertion :: " + updateResult + ", value :: " + student.toString());

					return (updateResult > 0) ? "success" : "failure";

				}
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCUtil.cleanUp(connection, preparedStatement, resultSet);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "failure";
	}

	@Override
	public String updateStudentDetailById(Student student) {

		try {
			connection = JDBCUtil.getConnection();

			if (connection != null) {

				String sqlSelectByIdQuery = "select id, name, address, marks from students where id = ?;";

				preparedStatement = connection.prepareStatement(sqlSelectByIdQuery);

				if (preparedStatement != null) {

					preparedStatement.setInt(1, student.getId());

					resultSet = preparedStatement.executeQuery();

					if (resultSet != null) {

						if (resultSet.next()) {

							String sqlUpdateQuery = "update students set name = ?, address = ? , marks = ? where id = ?;";

							PreparedStatement preparedStatement2 = connection.prepareStatement(sqlUpdateQuery);

							preparedStatement2.setString(1, student.getName());
							preparedStatement2.setString(2, student.getAddress());
							preparedStatement2.setInt(3, student.getMarks());
							preparedStatement2.setInt(4, student.getId());

							int updateResult = preparedStatement2.executeUpdate();

							return (updateResult > 0) ? "success" : "failure";

						} else {
							return "not found";
						}
					}
				}
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCUtil.cleanUp(connection, preparedStatement, resultSet);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "failure";
	}

	@Override
	public String deleteStudentDetailById(int id) {

		try {
			connection = JDBCUtil.getConnection();

			if (connection != null) {

				String sqlDeleteQuery = "delete from students where id = ?";

				preparedStatement = connection.prepareStatement(sqlDeleteQuery);

				if (preparedStatement != null) {

					preparedStatement.setInt(1, id);

					int updateResult = preparedStatement.executeUpdate();

					return (updateResult > 0) ? "success" : "not found";
				}
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCUtil.cleanUp(connection, preparedStatement, resultSet);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "failure";
	}

}
