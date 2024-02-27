package com.sms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sms.entities.Teacher;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Long>{
	public Teacher findByEmail(String email);
}
