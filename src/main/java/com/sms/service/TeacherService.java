package com.sms.service;

import java.util.List;
import java.util.Optional;

import com.sms.entities.Teacher;

public interface TeacherService {
	

    public Teacher createTeacher(Teacher teacher);
	
	public Optional<Teacher> getById(Long empId);
	
	public List<Teacher> getAllTeacher();
	
	public void removeSessionMessage();
	
	public void acceptTeacherRequest(Long rollNo);
	
	public void rejectTeacherRequest(Long rollNo);
	
	
}
