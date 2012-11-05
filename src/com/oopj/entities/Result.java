package com.oopj.entities;

public class Result {
	private String id;
	private int score;
	
	private Student parentStudent;
	
	public Result(String resultid, int score, Student parentStudent) {
		this.id = resultid;
		this.score = score;
		this.parentStudent = parentStudent;
	}
	
	public Student getParentStudent() {
		return parentStudent;
	}
	public void setParentStudent(Student parentStudent) {
		this.parentStudent = parentStudent;
	}

	public void setId (String resultid) {
		this.id = resultid;
	}
	
	public String getId () {
		return id;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}

}