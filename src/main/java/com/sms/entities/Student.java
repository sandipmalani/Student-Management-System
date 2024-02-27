package com.sms.entities;



import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Student{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rollNo;
	
	@NotBlank(message = "First name is requied")
	private String firstName;
	
	@NotBlank(message = "Last name is required")
	private String lastName;
	
	@NotBlank(message = "phone Number is required")
	@Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 digits")
	private String phone;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;
	
	@NotBlank(message = "Password is required")
	@Size(min = 6, message = "Password must be at least 6 characters long")	
	private String password;
	
	private String course;
	
	@Transient	
	private String confirmPassword;	
		
	@NotBlank(message = "Please select your date of birth")
	private String dob;
	
	@NotBlank(message = "Please select role")
	private String role;	
	
	private String status;
	
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	private List<Attendance> attendances;
	
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	private List<Grade> grades;

	public Student(Long rollNo, @NotBlank(message = "First name is requied") String firstName,
			@NotBlank(message = "Last name is required") String lastName,
			@NotBlank(message = "phone Number is required") @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 digits") String phone,
			@NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email,
			@NotBlank(message = "Password is required") @Size(min = 6, message = "Password must be at least 6 characters long") String password,
			String course, String confirmPassword, @NotBlank(message = "Please select your date of birth") String dob,
			@NotBlank(message = "Please select role") String role, String status, List<Attendance> attendances,
			List<Grade> grades) {
		super();
		this.rollNo = rollNo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.course = course;
		this.confirmPassword = confirmPassword;
		this.dob = dob;
		this.role = role;
		this.status = status;
		this.attendances = attendances;
		this.grades = grades;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
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

	public List<Attendance> getAttendances() {
		return attendances;
	}

	public void setAttendances(List<Attendance> attendances) {
		this.attendances = attendances;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	@Override
	public String toString() {
		return "Student [rollNo=" + rollNo + ", firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone
				+ ", email=" + email + ", password=" + password + ", course=" + course + ", confirmPassword="
				+ confirmPassword + ", dob=" + dob + ", role=" + role + ", status=" + status + ", attendances="
				+ attendances + ", grades=" + grades + "]";
	}

	
	
	
	
		
}
