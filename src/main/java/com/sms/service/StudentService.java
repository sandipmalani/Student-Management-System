package com.sms.service;

import java.util.List;
import java.util.Optional;

import com.sms.entities.Student;

public interface StudentService {

	public Student createStudent(Student student);
	
	public Optional<Student> getStudentById(Long rollNo);
	
	public List<Student> getAllStudent();
	
	public void removeSessionMessage();
	
	public void acceptRequest(Long rollNo);
	
	public void RejectStudentRequest(Long rollNo);
	
}
