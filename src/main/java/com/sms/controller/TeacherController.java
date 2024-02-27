package com.sms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sms.entities.Teacher;
import com.sms.repo.TeacherRepo;
import com.sms.service.AdminService;
import com.sms.service.StudentService;
import com.sms.service.TeacherService;

@Controller
public class TeacherController {

	@Autowired
	public AdminService adminService;
	
	@Autowired
	public TeacherService teacherService;
	
	@Autowired
	public StudentService studentService;
	
	@Autowired
	public TeacherRepo teacherRepo;
	
	@GetMapping("/teacher-data")
	public String openTeacherData(Teacher teacher ,Model model)
	{
		List<Teacher> allTeacher = this.teacherService.getAllTeacher();
		List<Teacher> teachers=new ArrayList<>();
		for(Teacher teacher1:allTeacher)
		{
			if(teacher1.getStatus().equals("Approved"))
			{
				teachers.add(teacher1);
			}
		}
		model.addAttribute("teachers",teachers);
		return "teacher-data";
	}
	
	@PostMapping("/teacher/delete/{rollNo}")
	public String deleteTeacher(@PathVariable("rollNo")Long rollNo)
	{
		this.teacherRepo.deleteById(rollNo);
		return "redirect:/teacher-dashboard";
	}
//	@GetMapping("/manage-student")
//	public String openTeacherDashboard()
//	{
//		return "student";
//	}
}
