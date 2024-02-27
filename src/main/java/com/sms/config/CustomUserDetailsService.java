package com.sms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.sms.entities.Admin;
import com.sms.entities.Student;
import com.sms.entities.Teacher;
import com.sms.repo.AdminRepo;
import com.sms.repo.StudentRepo;
import com.sms.repo.TeacherRepo;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Check if the username exists in Admins
        Admin admin = adminRepo.findByEmail(username);
        if (admin != null) {
            return CustomUserDetails.buildAdmin(admin);
        }

        // Check if the username exists in Teachers
        Teacher teacher = teacherRepo.findByEmail(username);
        if (teacher != null) {
            return CustomUserDetails.buildTeacher(teacher);
        }

        // Check if the username exists in Students
        Student student = studentRepo.findByEmail(username);
        if (student != null) {
            return CustomUserDetails.buildStudent(student);
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}

