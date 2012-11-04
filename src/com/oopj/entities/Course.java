package com.oopj.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Course {
	private String name;
	private String id;
	private Set<Student> studentList;
	private Professor professor;
	public Course(String name, String id) {
		this.name = name;
		this.id = id;
		studentList = new HashSet<Student>();
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Set<Student> getStudentList() {
		return studentList;
	}
	public void setStudentList(Set<Student> studentList) {
		this.studentList = studentList;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	
}
