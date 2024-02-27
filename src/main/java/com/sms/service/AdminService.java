package com.sms.service;

import java.util.List;
import java.util.Optional;

import com.sms.entities.Admin;

public interface AdminService {

	public Admin createAdmin(Admin admin);
	
	public Optional<Admin> getById(Long empId);
	
	public List<Admin> getAllAdmin();
	
	public void removeSessionMessage();
	
	public void acceptAdminRequest(Long empId);
	
	public void rejectAdminRequest(Long empId);
}
