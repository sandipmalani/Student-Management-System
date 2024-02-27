package com.sms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sms.entities.Grade;
import com.sms.entities.Student;
import com.sms.repo.GradeRepo;
import com.sms.repo.StudentRepo;
import com.sms.service.StudentService;

@Controller
public class GradeController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private GradeRepo gradeRepo;
	
	@GetMapping("/grade")
	public String showGradeFrom(Model model)
	{
		List<Student> students = studentService.getAllStudent();
		model.addAttribute("students",students);
		return "grade";
	}
	
	@PostMapping("/grade")
	public String saveGrade(@RequestParam("rollNo")Long studentId, @RequestParam("grade")String grade, Model model)
	{
		Optional<Student> studentOptional = studentService.getStudentById(studentId);
		if(studentOptional.isPresent())
		{
			Student student = studentOptional.get();
			Grade grade2=new Grade();
			grade2.setStudent(student);
			grade2.setGrade(grade);
			gradeRepo.save(grade2);
			model.addAttribute("message","Grade saved successfully");
			return "redirect:grade";
			
		}
		else
		{
			model.addAttribute("message", "Student Not Found");
		}
		return "redirect:/grade";
	}
	
	@GetMapping("/view-grades/{studentId}")
    public String viewGrades(@PathVariable("studentId") Long studentId, Model model) {
        Optional<Student> studentOptional = studentService.getStudentById(studentId);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            List<Grade> grades = gradeRepo.findAllByStudent(student);
            model.addAttribute("student", student);
            model.addAttribute("grades", grades);
            return "grade"; // Return the view to display student grades
        } else {
            model.addAttribute("errorMessage", "Student not found.");
            return "redirect:/grade";
        }
    }
}
