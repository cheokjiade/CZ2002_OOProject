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
	public static List<Course> courseList;// =  new ArrayList<Course>();
	static Scanner sc;
	static ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded .newConfiguration(), "temp.db");
	public static void main(String[] args) {
		//Person person = new Student("a", "a");
		List <Student> studentsFromDB = db.query(Student.class);
		studentList = new ArrayList(studentsFromDB);
		courseList = new ArrayList(db.query(Course.class));
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
					viewAllCourses();
					break;
					
			}
		}while(choice!=7);
		db.close();

	}
	
	public static void addStudent(){
		System.out.println("Student : Input name followed by id.");
		Student student = new Student(sc.next(), sc.next());
		if(studentList==null) studentList = new ArrayList<Student>();
		studentList.add(student);
		db.store(student);
		for(Student s: studentList){//int i=0;i<studentList.size();i++
			System.out.println(s.getId() + " " + s.getName());
		}
	}
	public static void addCourse(){
		System.out.println("Course: Input name followed by id.");
		Course course = new Course(sc.next(), sc.next());
		courseList.add(course);
		
		for(Course c: courseList){//int i=0;i<studentList.size();i++
			System.out.println(c.getId() + " " + c.getName());
		}
		addCourseClass(course);
		db.store(course);
	}
	
	public static void addCourseClass(Course course){
		System.out.println("Choose Course Type");
		System.out.println("(1) Lectures, Laboratory, Tutorials");
		System.out.println("(2) Lectures & Tutorials ONLY");
		System.out.println("(3) Lectures ONLY");
		sc = new Scanner(System.in);
		int choiceOfClass = sc.nextInt();
		
		switch(choiceOfClass){
			case 1:
				System.out.println("Inserting lectures...");
				addClass(course, 1);
				System.out.println("Inserting tutorials...");
				addClass(course, 2);
				System.out.println("Inserting laboratory...");
				addClass(course, 3);
				break;
			case 2:
				System.out.println("Inserting lectures...");
				addClass(course, 1);
				System.out.println("Inserting tutorials...");
				addClass(course, 2);
				break;
			case 3:
				System.out.println("Inserting lectures...");
				addClass(course, 1);
				break;
		}
	}
	
	public static void addClass(Course course, int type){
		System.out.println("How many of this class type do you want to create?");
		int classAmt = sc.nextInt();
		for(int i =0;i<classAmt;i++){
			System.out.println("Please enter class name, class id and class size.");
			CourseClass tempCourseClass = new CourseClass(sc.next(), sc.next(), sc.nextInt(), type, course);
			course.getCourseClassList().add(tempCourseClass);
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
	public static void viewAllCourses(){
		for(Course c: courseList){//int i=0;i<studentList.size();i++
			System.out.println("Course id: " +c.getId() + "\tname: " + c.getName());
			for(CourseClass cc : c.getCourseClassList()){
				System.out.println(cc.getName() + "" + cc.getId() + " " + Integer.toString(cc.getMaxSize()) + " " + cc.getType());
			}
		}
	}

}
