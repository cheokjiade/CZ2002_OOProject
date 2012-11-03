package com.oopj.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.oopj.entities.*;

public class main {

	/**
	 * @param args
	 */
	public List<Student> studentList= new ArrayList<Student>();
	public List<Course> courseList =  new ArrayList<Course>();
	static Scanner sc;
	public static void main(String[] args) {
		Person person = new Student("a", "a");
		int choice;
		do{
			System.out.println("(1)Add Student");
			System.out.println("(2)Add Course");
			sc = new Scanner(System.in);
			choice = sc.nextInt();
			
			switch(choice){
				case 1:
					
					break;
				case 2:
					break;
			}
		}while(choice!=7);

	}
	
	public void addStudent(){
		
	}

}
