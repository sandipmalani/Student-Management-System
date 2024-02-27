package com.sms.entities;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class Admin{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private Long empId;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String phone;
	
	private String password;
	@Transient
	private String confirmPassword;	
		
	private String dob;
	
	private String role;	
	
	private String status;

	public Admin(Long empId, String firstName, String lastName, String email, String phone, String password,
			String confirmPassword, String dob, String role, String status) {
		super();
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.dob = dob;
		this.role = role;
		this.status = status;
	}

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Admin [empId=" + empId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phone=" + phone + ", password=" + password + ", confirmPassword=" + confirmPassword + ", dob="
				+ dob + ", role=" + role + ", status=" + status + "]";
	}
	
	
	
}
