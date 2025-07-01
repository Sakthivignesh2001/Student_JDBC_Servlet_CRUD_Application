package com.example.dao.factory;

import com.example.dao.StudentDao;
import com.example.dao.StudentDaoImpl;

public class StudentDaoFactory {

	private static StudentDao studentDao = null;

	private StudentDaoFactory() {
		// Making constructor as private to avoid object creation.
	}

	public static StudentDao getStudentDao() {

		// Singleton Pattern
		if (studentDao == null) {
			studentDao = new StudentDaoImpl();
		}
		
		return studentDao;
	}
}
