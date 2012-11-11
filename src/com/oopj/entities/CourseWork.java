package com.oopj.entities;

import java.util.ArrayList;
import java.util.List;

public class CourseWork extends UniqueObject implements Choosable{
	private int weightage;
	
	private Course parentCourse;
	private List<Component> componentList;
	public CourseWork(String id, String name, int weightage, Course parentCourse) {
		super(name, id);
		this.weightage = weightage;
		this.parentCourse = parentCourse;
		this.componentList = new ArrayList<Component>();
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


	@Override
	public String printString() {
		return "Component name: " + this.getName() + " \tWeightage:" + Integer.toString(weightage);
	}

	

}