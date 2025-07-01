package com.example.service.factory;

import com.example.service.StudentService;
import com.example.service.StudentServiceImpl;

public class StudentServiceFactory {

	private static StudentService studentService = null;

	private StudentServiceFactory() {
		// Making constructor as private to avoid object creation
	}

	public static StudentService getStudentService() {

		// Singleton pattern
		if (studentService == null)
			studentService = new StudentServiceImpl();

		return studentService;
	}
}
