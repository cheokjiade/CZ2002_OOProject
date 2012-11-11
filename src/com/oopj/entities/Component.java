package com.oopj.entities;

import java.util.ArrayList;
import java.util.List;

public class Component extends ExamComponent implements Choosable{
	
	
	private CourseWork parentCourseWork;
	
	public Component(String id, String name, int totalScore, int weightage, CourseWork parentCourseWork){
		super(name, id);
		this.setTotalScore(totalScore);
		this.setWeightage(weightage);
		this.parentCourseWork = parentCourseWork;
		this.setResultList(new ArrayList<Result>());

	}

	public CourseWork getCourseWork() {
		return parentCourseWork;
	}

	public void setCourseWork(CourseWork courseWork) {
		this.parentCourseWork = courseWork;
	}

	@Override
	public String printString() {
		return "Component name: "+ getName() + " \tTotal Score: " + Integer.toString(getTotalScore()) + " \tWeightage: " + Integer.toString(getWeightage());
	}

}