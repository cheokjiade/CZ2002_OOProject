package com.oopj.entities;

import java.util.Set;

public abstract class CourseClass {
	private int maxSize;
	private String id;
	private String name;
	private Course parentCourse;
	private Set<Student> studentList;
}
