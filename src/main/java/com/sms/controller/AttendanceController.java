package com.sms.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sms.entities.Attendance;
import com.sms.entities.Student;
import com.sms.repo.AttendanceRepo;
import com.sms.repo.StudentRepo;

@Controller
public class AttendanceController {

	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private AttendanceRepo attendanceRepo;
	
	
	@GetMapping("/attendance")
	public String showAttendanceForm(Model model)
	{
		List<Student> students = studentRepo.findAll();
		model.addAttribute("students", students);
		model.addAttribute("attendance", new Attendance());
		return "attendance";
	}
	
	@PostMapping("/attendance")
	public String markAttendance(@RequestParam("studentId") Long studentId,
	                             @RequestParam(value = "present", required = false) boolean present,
	                             RedirectAttributes redirectAttributes) {
	    Student student = studentRepo.findById(studentId)
	                                 .orElseThrow(() -> new IllegalArgumentException("Invalid student roll number"));
	    
	    // Check if the student has already been marked present for today
	    LocalDate today = LocalDate.now();
	    boolean alreadyPresentToday = student.getAttendances().stream()
	                                         .anyMatch(attendance -> attendance.getDate().equals(today) && attendance.isPresent());
	    
	    if (alreadyPresentToday) {
	        redirectAttributes.addFlashAttribute("message", "Attendance already marked for today.");
	    } else {
	        Attendance attendance = new Attendance();
	        
	        attendance.setStudent(student);
	        attendance.setDate(today);
	        attendance.setPresent(present);
//	        List<Attendance> att=new ArrayList<>();
//	        att.add(attendance);
//	        student.setAttendances(att);
	        attendanceRepo.save(attendance);
	        redirectAttributes.addFlashAttribute("message", "Attendance Present Successfully.");
	    }
	    
	    return "redirect:/attendance";
	}
	
	@GetMapping("/student-attendance")
    public String getStudentAttendance(@RequestParam("studentId") Long studentId, Model model) {
        // Retrieve the student by ID
        Optional<Student> studentOptional = studentRepo.findById(studentId);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            // Retrieve attendance records for the selected student
            List<Attendance> attendances = attendanceRepo.findByStudent(student);
            List<Student> students = studentRepo.findAll();
    		model.addAttribute("students", students);
            model.addAttribute("attendances", attendances);
            return "attendance"; // Return the view to display attendance records
        } else {
            // If the student is not found, return an error message
            model.addAttribute("errorMessage", "Student not found");
            return "attendance"; // Return the error view
        }
    }
	
}
