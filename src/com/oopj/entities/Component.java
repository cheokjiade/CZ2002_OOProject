package com.oopj.entities;

import java.util.ArrayList;
import java.util.List;

public class Component {
	private String id;
	private String name;
	private int totalScore;
	private int weightage;
	
	private CourseWork parentCourseWork;
	private List<Result> resultList;
	
	public Component(String id, String name, int totalScore, int weightage){
		this.setId(id);
		this.setName(name);
		this.setTotalScore(totalScore);
		this.setWeightage(weightage);
		this.resultList = new ArrayList<Result>();

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

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public int getWeightage() {
		return weightage;
	}

	public void setWeightage(int weightage) {
		this.weightage = weightage;
	}

	public CourseWork getCourseWork() {
		return parentCourseWork;
	}

	public void setCourseWork(CourseWork courseWork) {
		this.parentCourseWork = courseWork;
	}

	public List<Result> getResult() {
		return resultList;
	}

	public void setResult(List<Result> resultList) {
		this.resultList = resultList;
	}


}
