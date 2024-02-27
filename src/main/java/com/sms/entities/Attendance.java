package com.sms.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Attendance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Student student;
	
	private LocalDate date;
	
	private boolean present;

	public Attendance(Long id, Student student, LocalDate date, boolean present) {
		super();
		this.id = id;
		this.student = student;
		this.date = date;
		this.present = present;
	}

	public Attendance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public boolean isPresent() {
		return present;
	}

	public void setPresent(boolean present) {
		this.present = present;
	}

	@Override
	public String toString() {
		return "Attendance [id=" + id + ", student=" + student + ", date=" + date + ", present=" + present + "]";
	}

	
	
}
