package com.oopj.entities;

import java.util.ArrayList;
import java.util.List;

public class Exam {
	private String id;
	private String name;
	private int totalScore;
	private int weightage;
	private List<Result> resultList;
	
	public Exam(String examid, String name, int totalScore, int weightage) {
		this.id = examid;
		this.name = name;
		this.totalScore = totalScore;
		this.weightage = weightage;
		this.resultList = new ArrayList<Result>();
	}
	
	public List<Result> getResultList() {
		return resultList;
	}

	public void setResultList(List<Result> resultList) {
		this.resultList = resultList;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String examid) {
		this.id = examid;
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
	
}
