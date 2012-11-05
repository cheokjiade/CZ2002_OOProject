package com.oopj.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.oopj.entities.*;

public class main {

	/**
	 * @param args
	 */
	public static List<Student> studentList;//= new ArrayList<Student>();
	public static List<Course> courseList =  new ArrayList<Course>();
	static Scanner sc;
	static ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded .newConfiguration(), "temp.db");
	public static void main(String[] args) {
		//Person person = new Student("a", "a");
		List <Student> studentsFromDB = db.query(Student.class);
		studentList = new ArrayList(studentsFromDB);
		//studentList = db.query(Student.class);
		int choice;
		do{
			System.out.println("(1)Add Student");
			System.out.println("(2)Add Course");
			System.out.println("(3)Register Student for Course");
			System.out.println("(4)Check Vacancy in a Class");
			System.out.println("(5)Print student list by class type");
			System.out.println("(6)Enter course assessment componnent weightage");
			System.out.println("(7)enter coursework mark inclusive of its components");
			System.out.println("(8)Enter exam mark");
			System.out.println("(9)Print course statastic");
			System.out.println("(10)Print transcript");
			sc = new Scanner(System.in);
			choice = sc.nextInt();
			
			switch(choice){
				case 1:
					addStudent();
					break;
				case 2:
					addCourse();
					break;
				case 3:
					registerStudent();
					break;
				case 20:
					viewAllStudents();
					break;
			}
		}while(choice!=7);
		db.close();

	}
	
	public static void addStudent(){
		System.out.println("Student : Input name followed by id.");
		Student student = new Student(sc.next(), sc.next());
		if(studentList==null) studentList = new ArrayList<Student>();;
		studentList.add(student);
		db.store(student);
		for(Student s: studentList){//int i=0;i<studentList.size();i++
			System.out.println(s.getId() + " " + s.getName());
		}
	}
	public static void addCourse(){
		courseList.add(new Course(sc.next(), sc.next()));
		for(Course c: courseList){//int i=0;i<studentList.size();i++
			System.out.println(c.getId() + " " + c.getName());
		}
	}
	public static void registerStudent(){
		for (int i=0;i<studentList.size();i++){
			System.out.println(Integer.toString(i) + ". " + studentList.get(i).getId() + " " + studentList.get(i).getName());
		}
		System.out.println("Select student to register: ");
		Student s = studentList.get(sc.nextInt());
		System.out.println("Select course to register student into: ");
		for (int i=0;i<courseList.size();i++){
			System.out.println(Integer.toString(i) + ". " + courseList.get(i).getId() + " " + courseList.get(i).getName());
		}
		Course c = courseList.get(sc.nextInt());
		if(c.getStudentList().contains(s)){
			System.out.println("Student is already enrolled.");
			return;
		}
		s.getCourseList().add(c);
		c.getStudentList().add(s);
		System.out.println("Printing course list of student");
		for(Course tempCourse : s.getCourseList()){
			System.out.println(tempCourse.getId() + " " + tempCourse.getName());
		}
	}
	public static void viewAllStudents(){
		for(Student s: studentList){//int i=0;i<studentList.size();i++
			System.out.println("id: " +s.getId() + "\tname: " + s.getName());
		}
	}

}
