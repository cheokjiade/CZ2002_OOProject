package com.oopj.entities;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person implements Choosable{
	//private 
	private List<Result> resultList;
	private List<Course> courseList;
	private List<CourseClass> courseClassList;
	
	public Student (String name, String id){
		this.setName(name);
		this.setId(id);
		this.resultList = new ArrayList<Result>();
		courseList = new ArrayList<Course>();
		courseClassList = new ArrayList<CourseClass>();
		
		//courseClassList = new ArrayList<CourseClass>();
	}

	public List<Result> getResultList() {
		return resultList;
	}

	public List<CourseClass> getCourseClassList() {
		return courseClassList;
	}

	public void setCourseClassList(List<CourseClass> courseClassList) {
		this.courseClassList = courseClassList;
	}

	public void setResultList(List<Result> resultList) {
		this.resultList = resultList;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	@Override
	public String printString() {
		return "ID: " + this.getId() + " Name: " + this.getName();
		
	}
	
}
