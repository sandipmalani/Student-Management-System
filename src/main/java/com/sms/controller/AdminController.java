package com.sms.controller;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sms.entities.Admin;
import com.sms.entities.Student;
import com.sms.entities.Teacher;
import com.sms.repo.AdminRepo;
import com.sms.service.AdminService;
import com.sms.service.StudentService;
import com.sms.service.TeacherService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

	@Autowired
	public AdminService adminService;
	
	@Autowired
	public TeacherService teacherService;
	
	@Autowired
	public StudentService studentService;
	
	
	
	
	@GetMapping("/pendingRequest")
    public String openAdminDashboard(Model model)
    {
		List<Admin> admins = this.adminService.getAllAdmin();
		List<Teacher> teachers = this.teacherService.getAllTeacher();
		List<Student> students = this.studentService.getAllStudent();
		
		List<Object> pendingRequest =new ArrayList<>();
		
		List<Admin> pendingAdmin=new ArrayList<>();
		for(Admin admin:admins)
		{
			if(admin.getStatus().equals("Pending"))
			{
				pendingAdmin.add(admin);
			}
		}
		
		pendingRequest.add(pendingAdmin);
		
		List<Teacher> pendingTeacher=new ArrayList<>();
		for(Teacher teacher:teachers)
		{
			if(teacher.getStatus().equals("Pending"))
			{
				pendingTeacher.add(teacher);
			}
		}
		pendingRequest.add(pendingTeacher);
		
		List<Student> pendingStudent=new ArrayList<>();
		for(Student student:students)
		{
			if(student.getStatus().equals("Pending"))
			{
				pendingStudent.add(student);
			}
		}
		pendingRequest.add(pendingStudent);
		System.out.println(pendingRequest);
		model.addAttribute("pendingRequest",pendingRequest);
    	return "pendingRequest";
    }
	
	
	
	@PostMapping("/student/accept/{rollNo}")
	public String acceptStudentRequest(@PathVariable("rollNo") Long rollNo, HttpSession session)
	{
		studentService.acceptRequest(rollNo);
		session.setAttribute("message", new com.sms.helper.Message("Student Registration Request accepted", "alert-success"));
		return "redirect:/admin-dashboard";
	}
	
	
	@PostMapping("/admin/accept/{empId}")
	public String acceptAdminRequest(@PathVariable("empId") Long empId, HttpSession session)
	{
		adminService.acceptAdminRequest(empId);
		session.setAttribute("message", new com.sms.helper.Message("Admin Registration Request accepted", "alert-success"));
		return "redirect:/admin-dashboard";
	}
	
	
	@PostMapping("/teacher/accept/{rollNo}")
	public String acceptTeacherRequest(@PathVariable("rollNo") Long rollNo, HttpSession session)
	{
		teacherService.acceptTeacherRequest(rollNo);
		session.setAttribute("message", new com.sms.helper.Message("Teacher Registration Request accepted", "alert-success"));
		return "redirect:/admin-dashboard";
	}
	
	@PostMapping("/student/reject/{rollNo}")
	public String rejectStudentRequest(@PathVariable("rollNo") Long rollNo, HttpSession session)
	{
		studentService.RejectStudentRequest(rollNo);
		session.setAttribute("message", new com.sms.helper.Message("Student Registration Request Rejected", "alert-danger"));
		return "redirect:/admin-dashboard";
	}
	
	@PostMapping("/teacher/reject/{rollNo}")
	public String rejectTeacherRequest(@PathVariable("rollNo") Long rollNo, HttpSession session)
	{
		teacherService.rejectTeacherRequest(rollNo);
		session.setAttribute("message", new com.sms.helper.Message("Teacher Registration Request Rejected", "alert-danger"));
		return "redirect:/admin-dashboard";
	}
	
	@PostMapping("/admin/reject/{rollNo}")
	public String rejectAdminRequest(@PathVariable("rollNo") Long rollNo, HttpSession session)
	{
		adminService.rejectAdminRequest(rollNo);
		session.setAttribute("message", new com.sms.helper.Message("Admin Registration Request Rejected", "alert-danger"));
		return "redirect:/admin-dashboard";
	}
	
	
	
}
