package com.sms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sms.entities.Teacher;
import com.sms.repo.TeacherRepo;
import com.sms.service.TeacherService;

import jakarta.servlet.http.HttpSession;

@Service
public class TeacherServiceImpl implements TeacherService{

	@Autowired
	private TeacherRepo teacherRepo;
	@Override
	public Teacher createTeacher(Teacher teacher) {
		Teacher teacher2 = this.teacherRepo.findByEmail(teacher.getEmail());
		if(teacher2!=null)
		{
			System.out.println("Teacher Already register");
			return null;
		}
		else
		{
			Teacher save = this.teacherRepo.save(teacher);
			return teacher2;
		}
		
	}

	@Override
	public Optional<Teacher> getById(Long empId) {
		Optional<Teacher> teacher = this.teacherRepo.findById(empId);
		return teacher;
	}

	@Override
	public List<Teacher> getAllTeacher() {
		List<Teacher> findAll = this.teacherRepo.findAll();
		return findAll;
	}

	@Override
	public void removeSessionMessage() {
		HttpSession session=((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		session.removeAttribute("message");	
	}

	@Override
	public void acceptTeacherRequest(Long rollNo) {
		Optional<Teacher> teacher = teacherRepo.findById(rollNo);
		Teacher teacher1 = teacher.get();
		teacher1.setStatus("Approved");
		teacherRepo.save(teacher1);
		
		
	}

	@Override
	public void rejectTeacherRequest(Long rollNo) {
		Optional<Teacher> teacher = teacherRepo.findById(rollNo);
		Teacher teacher2 = teacher.get();
		teacher2.setStatus("Rejected");
		teacherRepo.save(teacher2);
		
	}

}
