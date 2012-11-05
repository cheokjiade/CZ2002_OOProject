package com.oopj.entities;

import java.util.ArrayList;
import java.util.List;

public class Exam extends ExamComponent {
	
	public Exam(String examid, String name, int totalScore, int weightage) {
		this.setId(examid);
		this.setName(name);
		this.setTotalScore(totalScore);
		this.setWeightage(weightage);
		this.setResultList(new ArrayList<Result>());
	}
	
	
	
	
}
