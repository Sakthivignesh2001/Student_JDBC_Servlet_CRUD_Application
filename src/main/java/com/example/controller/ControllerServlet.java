package com.example.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dto.Student;
import com.example.service.StudentService;
import com.example.service.factory.StudentServiceFactory;

@WebServlet("/controller/*")
public class ControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) {

		StudentService studentService = StudentServiceFactory.getStudentService();

		System.out.println("Requested URI :: " + request.getRequestURI());
		System.out.println("Requested path variable :: " + request.getPathInfo());

		if (request.getRequestURI().endsWith("insertStudentDetails")) { // insert fully completed

			String name = request.getParameter("studentName");
			String address = request.getParameter("studentAddress");
			String marks = request.getParameter("studentMarks");

			Student student = new Student();
			student.setName(name);
			student.setAddress(address);
			student.setMarks(Integer.parseInt(marks));

			String status = studentService.insertStudentDetail(student);

			if (status.equalsIgnoreCase("success")) {
				String url = "../insertSuccess.html";
				requestDispather(url, request, response);
			} else {
				String url = "../insertFailure.html";
				requestDispather(url, request, response);
			}

		}

		if (request.getRequestURI().endsWith("deleteStudentById")) { // delete fully completed

			String id = request.getParameter("studentId");
			if (id != null) {

				String status = studentService.deleteStudentDetailById(Integer.parseInt(id));
				if (status.equalsIgnoreCase("success")) {
					String url = "../deleteSuccess.html";
					requestDispather(url, request, response);
				} else if (status.equalsIgnoreCase("not found")) {
					String url = "../not_found.html";
					requestDispather(url, request, response);
				} else {
					String url = "../deleteFailure.html";
					requestDispather(url, request, response);
				}
			}
		}

		if (request.getRequestURI().endsWith("getAllDetails")) { // Get all details fully completed

			List<Student> allStudentDetails = studentService.getAllStudentDetails();

			String status = null;
			if (allStudentDetails.size() >= 1) {
				status = getAllOperationPage(allStudentDetails, request, response);
			} else if (status.equalsIgnoreCase("failure")) {
				String url = "students_not_found.html";
				requestDispather(url, request, response);
			}
		}

		if (request.getRequestURI().endsWith("getStudentById")) { // get student details by id fully completed

			String id = request.getParameter("studentId");

			if (id != null) {

				Student student = studentService.getStudentDetailById(Integer.parseInt(id));

				if (student != null) {

					getStudentByIdPage(student, request, response);

				} else {
					String url = "../not_found.html";
					requestDispather(url, request, response);
				}
			}
		}

		if (request.getRequestURI().endsWith("findStudentById")) { // update in progress

			String id = request.getParameter("studentId");
			if (id != null) {
				Student student = studentService.getStudentDetailById(Integer.parseInt(id));

				if (student != null) {
					updateOperationPage(request, response, student, studentService);
				} else {
					String url = "../not_found.html";
					requestDispather(url, request, response);
				}

			}
		}

		if (request.getRequestURI().endsWith("updateStudentById")) {
			Student student = new Student();
			String id = request.getParameter("updatedStudentId");
			System.out.println(id);
			student.setId(Integer.parseInt(request.getParameter("updatedStudentId")));
			student.setName(request.getParameter("updatedStudentName"));
			student.setAddress(request.getParameter("updatedStudentAddress"));
			student.setMarks(Integer.parseInt(request.getParameter("updatedStudentMarks")));

			String status = studentService.updateStudentDetailById(student);

			if (status.equalsIgnoreCase("success")) {
				String url = "../updateSuccess.html";
				requestDispather(url, request, response);
			} else {
				String url = "../updateFailure.html";
				requestDispather(url, request, response);
			}

		}

	}

	private void getStudentByIdPage(Student student, HttpServletRequest request, HttpServletResponse response) {

		PrintWriter writer = null;

		try {

			writer = response.getWriter();

			writer.println("<!DOCTYPE html><html><head><title>Get details page</title><style>"
					+ "body { margin: 0; height: 100vh; display: flex; flex-direction: column;background-color: skyblue; font-family: Arial, sans-serif;}"
					+ "header, footer { background-color: #003366; color: white; text-align: center; padding: 15px 0; }"
					+ "main {flex: 1; display: flex; flex-direction: column; align-items: center; justify-content: center; }"
					+ "h1 { color: red; text-align: center; }"
					+ "table { border: 1px solid black; border-collapse: collapse; background-color: white; }"
					+ "th, td {	padding: 10px 15px; border: 1px solid black; text-align: center; }"
					+ "input[type='submit']  padding: 5px 10px; }" + "</style> </head> <body>"
					+ "	<header> <h2>Welcome to the Student Management Portal</h2> </header>");

			writer.println("<main><h1>Students Details by ID</h1>");

			writer.println("<table>");

			writer.println("<tr><th>Student Id</th><td>" + student.getId() + "</td></tr>");
			writer.println("<tr><th>Student Name</th><td>" + student.getName() + "</td></tr>");
			writer.println("<tr><th>Student Address</th><td>" + student.getAddress() + "</td></tr>");
			writer.println("<tr><th>Student Marks</th><td>" + student.getMarks() + "</td></tr>");

			writer.println("</table>");
			writer.println("</main> <footer>"
					+ " <p>&copy; 2025 Student Management Inc. | Contact: studentmanagement@gmail.com</p>"
					+ "	</footer> </body> </html> ");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String getAllOperationPage(List<Student> allStudentDetails, HttpServletRequest request,
			HttpServletResponse response) {

		PrintWriter writer = null;

		try {

			writer = response.getWriter();

			writer.println("<!DOCTYPE html><html><head><title>Get all details page</title><style>"
					+ "body { margin: 0; height: 100vh; display: flex; flex-direction: column;background-color: skyblue; font-family: Arial, sans-serif;}"
					+ "header, footer { background-color: #003366; color: white; text-align: center; padding: 15px 0; }"
					+ "main {flex: 1; display: flex; flex-direction: column; align-items: center; justify-content: center; }"
					+ "h1 { color: red; text-align: center; }"
					+ "table { border: 1px solid black; border-collapse: collapse; background-color: white; }"
					+ "th, td {	padding: 10px 15px; border: 1px solid black; text-align: center; }"
					+ "input[type='submit']  padding: 5px 10px; }" + "</style> </head> <body>"
					+ "	<header> <h2>Welcome to the Student Management Portal</h2> </header>");

			writer.println("<main><h1>All Students Details</h1>");

			writer.println("<table>");

			writer.println(
					"<tr><th>Student_Id</th><th>Student_Name</th><th>Student_Address</th><th>Student_Marks</th></tr>");

			for (Student student : allStudentDetails) {
				writer.println("<tr><th>" + student.getId() + "</th><th>" + student.getName() + "</th><th>"
						+ student.getAddress() + "</th><th>" + student.getMarks() + "</th></tr>");
			}

			writer.println("</table>");
			writer.println("</main> <footer>"
					+ " <p>&copy; 2025 Student Management Inc. | Contact: studentmanagement@gmail.com</p>"
					+ "	</footer> </body> </html> ");

			return "success";

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "failure";
	}

	private void updateOperationPage(HttpServletRequest request, HttpServletResponse response, Student student,
			StudentService studentService) {

		try {

			int id = student.getId();
			String name = student.getName();
			String address = student.getAddress();
			int marks = student.getMarks();

			PrintWriter writer = response.getWriter();

			writer.println("<!DOCTYPE html><html><head><title>Update page</title><style>"
					+ "body { margin: 0; height: 100vh; display: flex; flex-direction: column;background-color: skyblue; font-family: Arial, sans-serif;}"
					+ "header, footer { background-color: #003366; color: white; text-align: center; padding: 15px 0; }"
					+ "main {flex: 1; display: flex; flex-direction: column; align-items: center; justify-content: center; }"
					+ "h1 { color: red; text-align: center; }"
					+ "table { border: 1px solid black; border-collapse: collapse; background-color: white; }"
					+ "th, td {	padding: 10px 15px; border: 1px solid black; text-align: center; }"
					+ "input[type='submit']  padding: 5px 10px; }" + "</style> </head> <body>"
					+ "	<header> <h2>Welcome to the Student Management Portal</h2> </header>");

			writer.println("<main><h1>Update student details</h1>");
			writer.println("<form action='./updateStudentById' method='post'>");

			writer.println("<table>");

			writer.println("<tr><th>Student Id</th><td>" + "<input type = 'text' name='updatedStudentId' value = '" + id
					+ "' />" + "</td></tr>");
			writer.println("<tr><th>Student Name</th><td>" + "<input type = 'text' name='updatedStudentName' value = '"
					+ name + "'/>" + "</td></tr>");
			writer.println("<tr><th>Student Address</th><td>"
					+ "<input type = 'text' name='updatedStudentAddress' value = '" + address + "'/>" + "</td></tr>");
			writer.println("<tr><th>Student Marks</th><td>"
					+ "<input type = 'number' name='updatedStudentMarks' value = '" + marks + "'/>" + "</td></tr>");

			writer.println("<tr><td colspan='2' style='text-align: center;'>"
					+ "<input type='submit' value='Update' /></td></tr>");

			writer.println("</table>");
			writer.println("</form>");
			writer.println("</main> <footer>"
					+ " <p>&copy; 2025 Student Management Inc. | Contact: studentmanagement@gmail.com</p>"
					+ "	</footer> </body> </html> ");

//			requestDispather("./controller/updateStudentById", request, response);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void requestDispather(String url, HttpServletRequest request, HttpServletResponse response) {

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);

		try {
			requestDispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
