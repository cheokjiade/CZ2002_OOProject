package com.oopj.entities;

import java.util.ArrayList;
import java.util.List;

public class CourseWork {
	private String id;
	private String name;
	private int weightage;
	
	private Course parentCourse;
	private List<Component> componentList;
	public CourseWork(String id, String name, int weightage, Course parentCourse) {
		super();
		this.id = id;
		this.name = name;
		this.weightage = weightage;
		this.parentCourse = parentCourse;
		this.componentList = new ArrayList<Component>();
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

	public int getWeightage() {
		return weightage;
	}

	public void setWeightage(int weightage) {
		this.weightage = weightage;
	}

	public List<Component> getComponent() {
		return componentList;
	}

	public void setComponent(List<Component> componentList) {
		this.componentList = componentList;
	}

	

}
