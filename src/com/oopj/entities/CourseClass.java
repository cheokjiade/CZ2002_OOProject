package com.oopj.entities;

import java.util.ArrayList;
import java.util.List;

public class CourseClass extends UniqueObject implements Choosable  {
	private int maxSize;
	private int type;
	private Course parentCourse;
	private List<Student> studentList;
	public CourseClass(String name, String id, int maxSize, int type, Course parentCourse) {
		super(name, id);
		this.maxSize = maxSize;
		this.type = type;
		this.parentCourse = parentCourse;
		studentList = new ArrayList<Student>();
	}
	public int getMaxSize() {
		return maxSize;
	}
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
	public Course getParentCourse() {
		return parentCourse;
	}
	public void setParentCourse(Course parentCourse) {
		this.parentCourse = parentCourse;
	}
	public List<Student> getStudentList() {
		return studentList;
	}
	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String printString() {
		return "ID: " + this.getId() + " Name: " + this.getName() + " Vacancies: " + Integer.toString(maxSize - (this.getStudentList()==null?0:this.getStudentList().size()));
	}
	
}
