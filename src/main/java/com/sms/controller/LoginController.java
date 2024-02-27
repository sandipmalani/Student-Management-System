package com.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sms.config.CustomUserDetailsService;
import com.sms.entities.Admin;
import com.sms.entities.Student;
import com.sms.entities.Teacher;
import com.sms.helper.Message;
import com.sms.repo.AdminRepo;
import com.sms.repo.StudentRepo;
import com.sms.repo.TeacherRepo;
import com.sms.service.AdminService;
import com.sms.service.StudentService;
import com.sms.service.TeacherService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private StudentService studentService;
    
    @Autowired
    private AdminService adminService;
    
    @Autowired
    private TeacherService teacherService; 
    
    @Autowired
    private AdminRepo adminRepo;
    
    @Autowired
    private TeacherRepo teacherRepo;
    
    @Autowired
    private StudentRepo studentRepo;
    
    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    @GetMapping("/admin-dashboard")
	public String adminDashboard()
	{
		return "admin-dashboard";
	}
	
    
    
//    @Autowired
//    private PasswordEncoder passwordEncoder;
    
   
    
    @RequestMapping("/doLogin")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session)
    {
UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        System.out.println("Before passsword mathchers");
        if (userDetails != null && new BCryptPasswordEncoder().matches(password, userDetails.getPassword())) {
        	System.out.println("after password matches");
            if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            	System.out.println("after role check");
                Admin admin = adminRepo.findByEmail(email);
                if ("Approved".equals(admin.getStatus())) {
                    session.setAttribute("message", new Message("Welcome to Admin Dashboard", "alert-success"));
                    return "redirect:/admin-dashboard";
                } else {
                    session.setAttribute("message", new Message("Your registration request has not been approved yet. Please contact admin.", "alert-danger"));
                    return "redirect:/login";
                }
            } else if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_TEACHER"))) {
                Teacher teacher = teacherRepo.findByEmail(email);
                if ("Approved".equals(teacher.getStatus())) {
                    session.setAttribute("message", new Message("Welcome to Teacher Dashboard", "alert-success"));
                    return "redirect:/teacher-dashboard";
                } else {
                    session.setAttribute("message", new Message("Your registration request has not been approved yet. Please contact admin.", "alert-danger"));
                    return "redirect:/login";
                }
            } else if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_STUDENT"))) {
               Student student = studentRepo.findByEmail(email);
                if ("Approved".equals(student.getStatus())) {
                    session.setAttribute("message", new Message("Welcome to Student Dashboard", "alert-success"));
                    return "redirect:/student-dashboard";
                } else {
                    session.setAttribute("message", new Message("Your registration request has not been approved yet. Please contact admin.", "alert-danger"));
                    return "redirect:/login";
                    
                }
            }
        }
        System.out.println("Authentication Failed");
        session.setAttribute("message", new Message("Email or Password Incorrect", "alert-danger"));
        return "redirect:/login";
    }
}