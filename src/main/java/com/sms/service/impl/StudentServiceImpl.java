package com.sms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sms.entities.Student;
import com.sms.repo.StudentRepo;
import com.sms.service.StudentService;

import jakarta.servlet.http.HttpSession;



@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepo studentRepo;
	@Override
	public Student createStudent(Student student) {
		
		Student save = this.studentRepo.findByEmail(student.getEmail());
		if(save!=null)
		{
			System.out.println("Student Already register");
			return null;
		
		}
		else
		{
			Student save2 = this.studentRepo.save(student);
			return save;
		}
		
	}

	@Override
	public Optional<Student> getStudentById(Long rollNo) {
		Optional<Student> student = this.studentRepo.findById(rollNo);
		return student;
	}

	@Override
	public List<Student> getAllStudent() {
		
		return this.studentRepo.findAll();
	}
	
	

	@Override
	public void removeSessionMessage() {
		HttpSession session=((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		session.removeAttribute("message");	
	}

	@Override
	public void acceptRequest(Long rollNo) {
		Optional<Student> student = this.studentRepo.findById(rollNo);
		if (student.isPresent()) {
			Student student2 = student.get();
			student2.setStatus("Approved");
			studentRepo.save(student2);
		}
		
	}

	@Override
	public void RejectStudentRequest(Long rollNo) {
		Optional<Student> student = this.studentRepo.findById(rollNo);
		Student student2 = student.get();
		student2.setStatus("Rejected");
		studentRepo.save(student2);
		
	}


}
