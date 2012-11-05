package com.oopj.entities;

import java.util.ArrayList;
import java.util.List;

public class Component extends ExamComponent {
	
	
	private CourseWork parentCourseWork;
	
	public Component(String id, String name, int totalScore, int weightage){
		this.setId(id);
		this.setName(name);
		this.setTotalScore(totalScore);
		this.setWeightage(weightage);
		this.setResultList(new ArrayList<Result>());

	}

	public CourseWork getCourseWork() {
		return parentCourseWork;
	}

	public void setCourseWork(CourseWork courseWork) {
		this.parentCourseWork = courseWork;
	}

}
