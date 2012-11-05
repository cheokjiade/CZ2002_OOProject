package com.oopj.entities;

import java.util.Set;

public abstract class CourseClass {
	private int maxSize;
	private String id;
	private String name;
	private Course parentCourse;
	private Set<Student> studentList;
	public CourseClass(int maxSize, String id, String name, Course parentCourse) {
		this.maxSize = maxSize;
		this.id = id;
		this.name = name;
		this.parentCourse = parentCourse;
	}
	
}
