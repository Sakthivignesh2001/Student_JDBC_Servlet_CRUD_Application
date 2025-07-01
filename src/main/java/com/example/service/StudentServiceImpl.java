package com.example.service;

import java.util.List;

import com.example.dao.StudentDao;
import com.example.dao.factory.StudentDaoFactory;
import com.example.dto.Student;

public class StudentServiceImpl implements StudentService {

	StudentDao studentDao;

	@Override
	public List<Student> getAllStudentDetails() {
		studentDao = StudentDaoFactory.getStudentDao();
		return studentDao.getAllStudentDetails();
	}

	@Override
	public Student getStudentDetailById(int id) {
		studentDao = StudentDaoFactory.getStudentDao();
		return studentDao.getStudentDetailById(id);
	}

	@Override
	public String insertStudentDetail(Student student) {
		studentDao = StudentDaoFactory.getStudentDao();
		return studentDao.insertStudentDetail(student);
	}

	@Override
	public String updateStudentDetailById(Student student) {
		studentDao = StudentDaoFactory.getStudentDao();
		return studentDao.updateStudentDetailById(student);
	}

	@Override
	public String deleteStudentDetailById(int id) {
		studentDao = StudentDaoFactory.getStudentDao();
		return studentDao.deleteStudentDetailById(id);
	}

}
