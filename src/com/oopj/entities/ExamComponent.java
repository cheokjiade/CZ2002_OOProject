package com.oopj.entities;

import java.util.List;

public abstract class ExamComponent extends UniqueObject{
	
	private int totalScore;
	private int weightage;
	private List<Result> resultList;
	
	public ExamComponent(String name, String id) {
		super(name, id);
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
	
	public List<Result> getResultList() {
		return resultList;
	}

	public void setResultList(List<Result> resultList) {
		this.resultList = resultList;
	}
	
	
}
