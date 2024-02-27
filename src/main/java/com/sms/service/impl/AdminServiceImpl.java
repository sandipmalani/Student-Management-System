package com.sms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sms.entities.Admin;
import com.sms.repo.AdminRepo;
import com.sms.service.AdminService;

import jakarta.servlet.http.HttpSession;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminRepo adminRepo;
	
	
	@Override
	public Admin createAdmin(Admin admin) {
		Admin admin1 = this.adminRepo.findByEmail(admin.getEmail());
		System.out.println("from adminServiceImpl "+admin);
		if(admin1!=null)
		{
			System.out.println("Admin Already register");
			return null;
		}
		else
		{
			
			Admin save = this.adminRepo.save(admin);
			return save;
		}
		
		
	}

	@Override
	public Optional<Admin> getById(Long empId) {
		Optional<Admin> admin = this.adminRepo.findById(empId);
		return admin;
	}

	@Override
	public List<Admin> getAllAdmin() {
		List<Admin> admins = this.adminRepo.findAll();
		return admins;
	}

	@Override
	public void removeSessionMessage() {
		HttpSession session=((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		session.removeAttribute("message");	
	}

	@Override
	public void acceptAdminRequest(Long empId) {
		Optional<Admin> admin = this.adminRepo.findById(empId);
		Admin admin2 = admin.get();
		admin2.setStatus("Approved");
		adminRepo.save(admin2);
		
	}

	@Override
	public void rejectAdminRequest(Long empId) {
		Optional<Admin> admin = this.adminRepo.findById(empId);
		Admin admin2 = admin.get();
		admin2.setStatus("Rejected");
		adminRepo.save(admin2);
		
	}
	
}
