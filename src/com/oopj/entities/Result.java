package com.oopj.entities;

public class Result {
	private String id;
	private int score;
	
	public Result(String resultid, int score) {
		this.id = resultid;
		this.score = score;
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