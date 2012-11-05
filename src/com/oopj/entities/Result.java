package com.oopj.entities;

public class Result {
	private String id;
	private int score;
	
	private Student parentStudent;
	private ExamComponent parentExamComponent;
	
	public Result(String resultid, int score, Student parentStudent, ExamComponent parentExamComponent) {
		this.id = resultid;
		this.score = score;
		this.parentStudent = parentStudent;
		this.parentExamComponent = parentExamComponent;
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

	public ExamComponent getParentExamComponent() {
		return parentExamComponent;
	}

	public void setParentExamComponent(ExamComponent parentExamComponent) {
		this.parentExamComponent = parentExamComponent;
	}

}