package com.example.controller;

import java.util.List;

import com.example.dao.StudentDao;
import com.example.dao.factory.StudentDaoFactory;
import com.example.dto.Student;
import com.example.service.StudentService;
import com.example.service.factory.StudentServiceFactory;

public class TestApp {

	public static void main(String[] args) {
		TestApp app = new TestApp();
		app.getAllStudentDetails();

	}

	public String getAllStudentDetails() {
		StudentDao studentService = StudentDaoFactory.getStudentDao();
		return studentService.deleteStudentDetailById(10);
	}
}
