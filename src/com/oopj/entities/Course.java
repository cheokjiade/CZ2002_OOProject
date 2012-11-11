package com.oopj.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Course extends UniqueObject implements Choosable{
	private List<Student> studentList;
	private List<CourseClass> courseClassList;
	private Exam exam;
	private CourseWork courseWork;
	private Professor professor;
	public Course(String name, String id) {
		super(name, id);
		studentList = new ArrayList<Student>();
		courseClassList = new ArrayList<CourseClass>();
	}
	public List<CourseClass> getCourseClassList() {
		return courseClassList;
	}
	public void setCourseClassList(List<CourseClass> courseClassList) {
		this.courseClassList = courseClassList;
	}
	public List<Student> getStudentList() {
		return studentList;
	}
	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	public Exam getExam() {
		return exam;
	}
	public void setExam(Exam exam) {
		this.exam = exam;
	}
	public CourseWork getCourseWork() {
		return courseWork;
	}
	public void setCourseWork(CourseWork courseWork) {
		this.courseWork = courseWork;
	}
	@Override
	public String printString() {
		return "ID: " + this.getId() + " Name: " + this.getName();
	}
	
}
