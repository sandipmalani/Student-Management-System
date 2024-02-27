package com.sms.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sms.entities.Attendance;
import com.sms.entities.Student;

public interface AttendanceRepo extends JpaRepository<Attendance, Long>{

	
	// Find attendance records by student and date
    List<Attendance> findByStudentAndDate(Student student, LocalDate date);
    
    // Find attendance records by student
    List<Attendance> findByStudent(Student student);
}
