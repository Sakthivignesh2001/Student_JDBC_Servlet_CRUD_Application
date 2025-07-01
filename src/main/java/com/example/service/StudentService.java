package com.example.service;

import java.util.List;

import com.example.dto.Student;

public interface StudentService {

	public List<Student> getAllStudentDetails();

	public Student getStudentDetailById(int id);

	public String insertStudentDetail(Student student);

	public String updateStudentDetailById(Student student);

	public String deleteStudentDetailById(int id);

}
