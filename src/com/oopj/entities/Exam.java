package com.oopj.entities;

import java.util.ArrayList;
import java.util.List;

public class Exam extends ExamComponent {
	
	private Course parentCourse;
	public Exam(String examid, String name, int totalScore, int weightage, Course parentCourse) {
		this.setId(examid);
		this.setName(name);
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
