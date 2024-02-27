package com.sms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sms.entities.Grade;
import com.sms.entities.Student;

@Repository
public interface GradeRepo extends JpaRepository<Grade, Long>{

	
	
	public List<Grade> findAllByStudent(Student student);
}
