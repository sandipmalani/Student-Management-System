package com.sms.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.sms.entities.Student;
import com.sms.entities.Teacher;
import com.sms.repo.StudentRepo;
import com.sms.service.StudentService;

import jakarta.servlet.http.HttpSession;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudentRepo studentRepo;
	
	@GetMapping("/student")
	public String openStudent()
	{
		return "student";
	}
	
	
	@GetMapping("/student-data")
	public String openStudentData(Model model)	{
		List<Student> allStudent = this.studentService.getAllStudent();
		List<Student> students1=new ArrayList<>();
		for(Student students:allStudent)
		{
			if(students.getStatus().equals("Approved"))
			{
				students1.add(students);
			}
		}
		model.addAttribute("students",students1);
		return "student-data";
	}
	
	@GetMapping("/manage-student")
	public String manageStudent(Model model)
	{
		List<Student> allStudent = this.studentService.getAllStudent();
		List<Student> students1=new ArrayList<>();
		for(Student students:allStudent)
		{
			if(students.getStatus().equals("Approved"))
			{
				students1.add(students);
			}
		}
		model.addAttribute("students",students1);
		return "manage-student";
	}
	
	@PostMapping("/add-student")
	public String addStudent(@ModelAttribute Student student)
	{
			
			student.setStatus("Approved");
			Student createStudent = this.studentService.createStudent(student);
		
		return "redirect:/manage-student";
	
	}
	
	@GetMapping("/student/edit/{rollNo}")
	public String updateForm(@PathVariable("rollNo") Long rollNo, Model model, HttpSession session) {
	    Optional<Student> studentOptional = this.studentService.getStudentById(rollNo);
	    	Student student = (Student)session.getAttribute("student");
	        //Student student = studentOptional.get();
	        System.out.println("student" +student);
	        model.addAttribute("student", student);
	        session.setAttribute("student", student);
	        return "editStudentModal"; // Return the view for managing student
	    
	}
	@PostMapping("/student/delete/{rollNo}")
	public String deleteStudent(@PathVariable("rollNo")Long rollNo)
	{
		this.studentRepo.deleteById(rollNo);
		return "redirect:/manage-student";
	}
	
}
