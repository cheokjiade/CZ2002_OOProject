package com.oopj.entities;

import java.util.ArrayList;
import java.util.List;

public class Exam extends ExamComponent {
	
	private Course parentCourse;
	public Exam(String id, String name, int totalScore, int weightage, Course parentCourse) {
		super(name, id);
		this.setTotalScore(totalScore);
		this.setWeightage(weightage);
		this.setResultList(new ArrayList<Result>());
		this.parentCourse = parentCourse;
	}
	public Course getParentCourse() {
		return parentCourse;
	}
	public void setParentCourse(Course parentCourse) {
		this.parentCourse = parentCourse;
	}
	
	
	
	
}
