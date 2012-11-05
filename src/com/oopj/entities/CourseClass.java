package com.oopj.entities;

import java.util.List;

public class CourseClass {
	private int maxSize;
	private String id;
	private String name;
	private int type;
	private Course parentCourse;
	private List<Student> studentList;
	public CourseClass(String name, String id, int maxSize, int type, Course parentCourse) {
		this.maxSize = maxSize;
		this.id = id;
		this.name = name;
		this.type = type;
		this.parentCourse = parentCourse;
	}
	public int getMaxSize() {
		return maxSize;
	}
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
}
