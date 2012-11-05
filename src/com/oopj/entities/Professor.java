package com.oopj.entities;

import java.util.ArrayList;
import java.util.List;

public class Professor extends Person {
	private List<Course> courseList;

	public Professor(String name, String id) {
		this.setName(name);
		this.setId(id);
		this.courseList = new ArrayList<Course>();
	}
	
	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}
	
	

}
