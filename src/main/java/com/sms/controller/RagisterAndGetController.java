package com.sms.controller;

import java.net.http.HttpRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sms.entities.Admin;
import com.sms.entities.Student;
import com.sms.entities.Teacher;
import com.sms.helper.Message;
import com.sms.service.AdminService;
import com.sms.service.StudentService;
import com.sms.service.TeacherService;


import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;

@Controller
public class RagisterAndGetController {

	@Autowired
	private StudentService studentService;
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;

	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private TeacherService teacherService;
	
	@GetMapping("/registerForm")
	public String openRegister(Model model)
	{
		return "register";
	}
	
	
	@GetMapping("/home")
	public String openHome(Model model)
	{
		return "index";
	}
	
	@GetMapping("/login")
	public String openLogin()
	{
		return "login";
	}
	
	@PostMapping("/register")
	public String ragister(@ModelAttribute Teacher teacher, HttpSession session, org.springframework.ui.Model model)	
	{
		Admin admin=new Admin();
		Student student=new Student();
		System.out.println(teacher);
		String role = teacher.getRole();
		System.out.println("role "+role);
		
		try {
			
			if("Admin".equals(role))
			{
				System.out.println("teacher data "+teacher);
				admin.setFirstName(teacher.getFirstName());
				admin.setLastName(teacher.getLastName());
				admin.setEmail(teacher.getEmail());
				admin.setDob(teacher.getDob());
				admin.setPhone(teacher.getPhone());
				admin.setPassword(teacher.getPassword());
				admin.setRole(teacher.getRole());
				admin.setStatus("Pending");	
				System.out.println("teacher data ------"+teacher);
				
					Admin createAdmin = this.adminService.createAdmin(admin);
					System.out.println("admin "+admin);
					session.setAttribute("message", new com.sms.helper.Message("Registeration Successfully", "alert-success"));
					
				
					System.out.println("teacher data under if  ------"+teacher);
					System.out.println("admin is empty");			
					
				
				return "redirect:/login";
			}
			else if("Student".equals(role))
			{
				
				
				student.setFirstName(teacher.getFirstName());
				student.setLastName(teacher.getLastName());
				student.setEmail(teacher.getEmail());
				student.setDob(teacher.getDob());
				student.setPhone(teacher.getPhone());
				student.setPassword(teacher.getPassword());
				student.setRole(teacher.getRole());
				student.setStatus("Pending");
				this.studentService.createStudent(student);
				session.setAttribute("message", new com.sms.helper.Message("Registeration Successfully", "alert-success"));
				
				return "redirect:/login";
			}
			else if("Teacher".equals(role))
			{
//				Teacher teacher=new Teacher();
//				teacher.setFirstName(teacher.getFirstName());
//				teacher.setLastName(teacher.getLastName());
//				teacher.setEmail(teacher.getEmail());
//				teacher.setPhone(teacher.getPhone());
//				teacher.setPassword(teacher.getPassword());
//				teacher.setDob(teacher.getDob());
//				teacher.setRole(teacher.getRole());
//				teacher.setStandard(teacher.getStandard());
//				teacher.setCourse(teacher.getCourse());
//				teacher.setSubject()
				teacher.setPassword(teacher.getPassword());
				teacher.setStatus("Pending");
				this.teacherService.createTeacher(teacher);
				session.setAttribute("message", new com.sms.helper.Message("Registeration Successfully", "alert-success"));
				
				return "redirect:/login";
			}
			else
			{
				System.out.println("invalid role please select right role");
				session.setAttribute("message", new com.sms.helper.Message("Invalid Role please select right role", "alert-danger"));
				return "redirect:/ragister";
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();			
			return "redirect:/register";
			
		}
		
	
	}
	

}