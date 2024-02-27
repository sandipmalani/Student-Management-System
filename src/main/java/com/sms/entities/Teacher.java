package com.sms.entities;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rollNo;

	private String firstName;

	private String lastName;

	private String email;

	private String phone;

	private String password;

	private String confirmPassword;

	private String dob;

	private String role;

	private String standard;

	private String status;

	private String subject;

	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Teacher(Long rollNo, String firstName, String lastName, String email, String phone, String password,
			String confirmPassword, String dob, String role, String standard, String status, String subject) {
		super();
		this.rollNo = rollNo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.dob = dob;
		this.role = role;
		this.standard = standard;
		this.status = status;
		this.subject = subject;
	}

	public Long getRollNo() {
		return rollNo;
	}

	public void setRollNo(Long rollNo) {
		this.rollNo = rollNo;
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

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "Teacher [rollNo=" + rollNo + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phone=" + phone + ", password=" + password + ", confirmPassword=" + confirmPassword + ", dob="
				+ dob + ", role=" + role + ", standard=" + standard + ", status=" + status + ", subject=" + subject
				+ "]";
	}

	
	

}
