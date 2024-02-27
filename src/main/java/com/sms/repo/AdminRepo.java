package com.sms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sms.entities.Admin;


@Repository
public interface AdminRepo extends JpaRepository<Admin, Long>{

	public Admin findByEmail(String email);
	
}
