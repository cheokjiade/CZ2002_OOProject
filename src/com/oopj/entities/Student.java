package com.oopj.entities;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person{
	//private 
	private List<Result> resultList;
	private List<Course> courseList;
	
	public Student (String name, String id){
		this.setName(name);
		this.setId(id);
		this.resultList = new ArrayList<Result>();
		this.courseList = new ArrayList<Course>();
	}

	public List<Result> getResultList() {
		return resultList;
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
	
}
