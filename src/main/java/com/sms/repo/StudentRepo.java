package com.sms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sms.entities.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long>{
	public Student findByEmail(String email);
	
}
