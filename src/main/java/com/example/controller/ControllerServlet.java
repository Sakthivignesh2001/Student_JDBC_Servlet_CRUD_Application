package com.example.controller;

import java.io.IOException;
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

		if (request.getRequestURI().endsWith("addForm")) {

			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String marks = request.getParameter("marks");

			Student student = new Student();
			student.setName(name);
			student.setAddress(address);
			student.setMarks(Integer.parseInt(marks));

			String status = studentService.insertStudentDetail(student);
			
			// not completed
			// redirect result

		}

	}

}
