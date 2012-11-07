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
	public static List<CourseClass> courseClassList;
	static Scanner sc;
	static ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded .newConfiguration(), "temp.db");
	public static void main(String[] args) {
		//Person person = new Student("a", "a");
		List <Student> studentsFromDB = db.query(Student.class);
		List <Result> resultList = new ArrayList(db.query(Result.class));
		studentList = new ArrayList(studentsFromDB);
		courseList = new ArrayList(db.query(Course.class));
		courseClassList = new ArrayList(db.query(CourseClass.class));

		//db.query(CourseClass.class);
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
			case 4:
				printVacancy();
				break;
			case 5:
				printStudentList();
				break;
			case 6:
				editAssessmentComponentWeightage();
				break;
			case 7:
				addCourseWorkMark();
				break;
			case 8:
				addExamResult();
				break;
			case 9:
				printStatistics();
				break;
			case 10:
				printTranscript();
				break;
			case 11:
				Student tempS = (Student)chooseChoosable((ArrayList)studentList);
				if(tempS!=null) System.out.println(tempS.getId() + " " + tempS.getName());
				break;
			case 12:
				Course tempC = (Course)chooseChoosable((ArrayList)courseList);
				if(tempC!=null) System.out.println(tempC.getId() + " " + tempC.getName());
				break;
			case 13:
				Student testC = (Student)chooseChoosable((ArrayList)studentList);
				if(testC!=null) System.out.println(testC.getId() + " " + testC.getName());
				break;
			case 20:
				viewAllStudents();
				viewAllCourses();
				break;

			}
		}while(choice!=21);
		db.close();

	}

	public static void addStudent(){
		System.out.println("Student : Input name followed by id.");
		Student student = new Student(sc.next(), sc.next());
		if(studentList==null) studentList = new ArrayList<Student>();
		studentList.add(student);
		System.out.println(student.getId() + " - " + student.getName() + " has been added successfully!\n");
		db.store(student);

		System.out.println("List of students:");
		for(Student s: studentList){//int i=0;i<studentList.size();i++
			System.out.println(s.getId() + " - " + s.getName());
		}
	}

	public static void addCourse(){
		System.out.println("Course: Input name followed by id.");
		Course course = new Course(sc.next(), sc.next());
		courseList.add(course);
		System.out.println(course.getId() + " - " + course.getName() + " has been added successfully!\n");

		System.out.println("List of courses:");
		for(Course c: courseList){//int i=0;i<studentList.size();i++
			System.out.println(c.getId() + " - " + c.getName());
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
			System.out.println("Adding lectures...");
			addClass(course, 1);
			System.out.println("Adding tutorials...");
			addClass(course, 2);
			System.out.println("Adding laboratory...");
			addClass(course, 3);
			break;
		case 2:
			System.out.println("Adding lectures...");
			addClass(course, 1);
			System.out.println("Adding tutorials...");
			addClass(course, 2);
			break;
		case 3:
			System.out.println("Adding lectures...");
			addClass(course, 1);
			break;
		}
	}

	public static void addClass(Course course, int type){
		System.out.println("How many of classes?");
		int classAmt = sc.nextInt();
		for(int i =0;i<classAmt;i++){
			System.out.println("Please enter Class Name, Class Index, Class Intake Size.");
			CourseClass tempCourseClass = new CourseClass(sc.next(), sc.next(), sc.nextInt(), type, course);
			course.getCourseClassList().add(tempCourseClass);
		}
		String classType = type==1?"Lecture":type==2?"Tutorial":"Laboratory";
		System.out.println(classAmt + " " + classType + " has been added successfully!\n");
	}

	public static void editAssessmentComponentWeightage(){
		System.out.println("Choose course to edit assessment componnent weightage");
		Course tempCourse = (Course)chooseChoosable((ArrayList)courseList);
		System.out.printf("Exam total and weightage has %sbeen set.\n",tempCourse.getExam()==null?"not ":"");
		System.out.printf("Coursework total and weightage has %sbeen set.\n",tempCourse.getCourseWork()==null?"not ":"");
		int choice;
		do{
			System.out.println("Press 1 to edit Exam and 2 to edit Coursework. Press any other number to exit.");
			choice = sc.nextInt();
			if(choice==1) editExam(tempCourse);
			else if (choice==2)editCourseWork(tempCourse);
		} while (choice == 1 || choice == 2);

	}

	public static void editExam(Course tempCourse){
		if(tempCourse.getExam()==null){
			System.out.print("Please enter the total score of the exam followed by its weighted percentage : ");
			Exam tempExam = new Exam(tempCourse.getId(), tempCourse.getName(), sc.nextInt(), sc.nextInt(), tempCourse);
			tempCourse.setExam(tempExam);
			System.out.println("Total score of the exam is " + Integer.toString(tempCourse.getExam().getTotalScore()) + " and its weighted percentage is " + Integer.toString(tempCourse.getExam().getWeightage()));
		}else{
			System.out.println("Current total score of the exam is " + Integer.toString(tempCourse.getExam().getTotalScore()) + " and its weighted percentage is " + Integer.toString(tempCourse.getExam().getWeightage()));
			System.out.print("Enter 1 to edit the Exam total marks and weightage and any other number to quit: ");
			int choice = sc.nextInt();
			if(choice == 1){
				System.out.println("Please enter the total score of the exam followed by its weighted percentage ");
				tempCourse.getExam().setTotalScore(sc.nextInt());
				tempCourse.getExam().setWeightage(sc.nextInt());
				System.out.println("Total score of the exam is " + Integer.toString(tempCourse.getExam().getTotalScore()) + " and its weighted percentage is" + Integer.toString(tempCourse.getExam().getWeightage()));
			}	
		}
		db.store(tempCourse);
		//int
	}

	public static void editCourseWork(Course tempCourse){
		if(tempCourse.getCourseWork()==null){
			System.out.print("Please enter the total weighted percentage of the coursework : ");
			CourseWork tempCourseWork = new CourseWork(tempCourse.getId(), tempCourse.getName(), sc.nextInt(), tempCourse);
			tempCourse.setCourseWork(tempCourseWork);
			System.out.println("Weighted percentage is " + Integer.toString(tempCourse.getCourseWork().getWeightage()));
			editCourseWorkComponent(tempCourse);
		}else{
			System.out.println("Current weighted percentage is" + Integer.toString(tempCourse.getCourseWork().getWeightage()));
			System.out.print("Enter 1 to edit coursework total weightage or 2 to edit coursework components : ");
			int choice = sc.nextInt();
			if(choice == 1){
				System.out.print("Please enter the total weighted percentage of the coursework : ");
				tempCourse.getCourseWork().setWeightage(sc.nextInt());
				System.out.println("Weighted percentage is " + Integer.toString(tempCourse.getCourseWork().getWeightage()));
			}else if(choice == 2){
				editCourseWorkComponent(tempCourse);
			}
		}
		db.store(tempCourse);
	}

	public static void editCourseWorkComponent(Course tempCourse){
		CourseWork tempCourseWork = tempCourse.getCourseWork();
		int choice;
		do{
			System.out.printf("There are currently %d components.\nDo you want to 1. Add a new component%s?\n",tempCourseWork.getComponent().size(),tempCourseWork.getComponent().size()==0?"":" or 2. Edit a component");
			choice = sc.nextInt();
			if(choice==1){
				System.out.print("Please enter component name, total mark and weighted percentage. ");
				Component c = new Component(tempCourse.getId(), sc.next(), sc.nextInt(), sc.nextInt(), tempCourseWork);
				tempCourseWork.getComponent().add(c);
				db.store(tempCourse);
			} else if (choice ==2 && tempCourseWork.getComponent().size()>0){
				Component c = (Component)chooseChoosable((ArrayList)tempCourseWork.getComponent());
				if(c==null) continue;
				System.out.print("Please enter updated component name, total mark and weighted percentage. ");
				c.setName(sc.next());
				c.setTotalScore(sc.nextInt());
				c.setWeightage(sc.nextInt());
				db.store(tempCourse);
			}

		}while(choice==1||choice==2);

	}

	public static void addExamResult(){
		System.out.println("Please select the course to add exam results for:");
		Course c = (Course)chooseChoosable((ArrayList)courseList);
		if(c==null)return;
		if(c.getExam()==null)return;
		System.out.println("Please select the student to add exam results for:");
		Student s = (Student)chooseChoosable((ArrayList)c.getStudentList());
		if(s==null)return;
		addResult(s, c.getExam());
	}

	public static void addCourseWorkMark(){
		System.out.println("Please select the course to add coursework marks for:");
		Course c = (Course)chooseChoosable((ArrayList)courseList);
		if(c==null)return;
		System.out.println("Please select the student to add coursework results for:");
		Student s = (Student)chooseChoosable((ArrayList)c.getStudentList());
		if(s==null)return;
		System.out.println("Please select the component to add marks for:");
		Component com = (Component)chooseChoosable((ArrayList)c.getCourseWork().getComponent());
		addResult(s, com);
		db.store(s);
		db.store(c);
	}

	public static boolean addResult(Student s, ExamComponent ec){
		for(Result r : s.getResultList()){
			if(r.getParentExamComponent().equals(ec)) return false;
		}
		System.out.printf("Maximum grade for %s is %d. Please enter grade for %s : ",ec.getName(),ec.getTotalScore(),s.getName());
		int grade = sc.nextInt();
		if(grade>ec.getTotalScore()) grade = ec.getTotalScore();
		else if (grade<0) grade = 0;
		Result r = new Result(ec.getId(), grade, s, ec);
		s.getResultList().add(r);
		ec.getResultList().add(r);
		db.store(s);
		db.store(s.getResultList());
		return true;
	}

	public static void printStatistics(){
		System.out.println("Please select the course to view statistics for:");
		Course c = (Course)chooseChoosable((ArrayList)courseList);
		int actualExam, actualCourseWork, total, totalExam=0;
		total = c.getExam().getWeightage() + c.getCourseWork().getWeightage();
		for(Result r:c.getExam().getResultList()) totalExam+=r.getScore();
		System.out.printf("Average actual mark for exam is %d/%d. Average percentage is %d. Average weighted percentage is %d .\n",(int)(totalExam/c.getStudentList().size()),(int)(c.getExam().getTotalScore()),(int)(totalExam/c.getStudentList().size()*c.getExam().getWeightage()/100),(int)(totalExam/c.getStudentList().size()*c.getExam().getWeightage()/total));
	}

	public static Choosable chooseChoosable(ArrayList<Choosable> choosableList){
		int choice,pageCount=0,i;
		boolean lastPage = false;
		do{
			for(i=1; pageCount*10+i-1<choosableList.size()&&i<=10;i++)
				System.out.println("("+ Integer.toString(i) +") " + (choosableList.get((pageCount*10+i-1))).printString());
			if(i>=10&&((pageCount*10+i-1)<=choosableList.size())) System.out.println("Enter 11 to see the next 10 courses");
			else{
				System.out.println("End of course list. Enter 0 to restart the list or -1 to exit");
				lastPage = true;
			}
			choice = sc.nextInt();
			if(choice>0&&choice<=i-1) return choosableList.get((pageCount*10)+choice-1);
			else if (choice==0){
				pageCount=0;
				lastPage=false;
			}else if(choice>10){
				if(lastPage){
					pageCount=0;
					lastPage=false;
				}else pageCount++;
			}
		}while(choice>-1);
		return null;
	}

	public static void registerStudent(){

		System.out.println("Select student to register: ");
		Student s = (Student)chooseChoosable((ArrayList)studentList); 
		if(s==null)return;
		System.out.println("Select course to register student into: ");
		Course c = (Course)chooseChoosable((ArrayList)courseList);
		if(c==null)return;
		if(c.getStudentList().contains(s)){
			System.out.println("Student is already enrolled.\n");
			return;
		}

		//add student to course
		s.getCourseList().add(c);
		c.getStudentList().add(s);

		for(int i=1;i<=3;i++){
			ArrayList<CourseClass> tempCCList = new ArrayList<CourseClass>();
			for(CourseClass tempCC: c.getCourseClassList()){ 
				if(tempCC.getType()==i) {
					tempCCList.add(tempCC);
				}
			}
			if(tempCCList.size()>0){
				System.out.println("Please select the class to register for...");
				CourseClass cc = (CourseClass)chooseChoosable((ArrayList)tempCCList);
				if(cc==null) 
					return;
				if(cc.getMaxSize() - cc.getStudentList().size() == 0) //how to prompt again, while loop?
					System.out.println(c.getId() + " " + c.getName() + " - " + cc.getName() + " " + cc.getId() + " has no more vacancy, please choose another class!\n");
				else {
					s.getCourseClassList().add(cc);
					cc.getStudentList().add(s);
					System.out.println("Student " + s.getName() + " of matriculation number " + s.getId() + " has successfully been enrolled to the following course:");
					db.store(cc);
					for(CourseClass cc1: s.getCourseClassList()){
						String classType = cc1.getType()==1?"Lecture":cc1.getType()==2?"Tutorial":"Laboratory";
						System.out.println(c.getId() + " " + c.getName() + " - " + classType + " " + cc1.getName() + "\n");

					}
				}
			}
		}
		System.out.println("Student " + s.getName() + " of matriculation number " + s.getId() + " has successfully been enrolled to the following course:");
		for(CourseClass cc1: s.getCourseClassList()){
			String classType = cc1.getType()==1?"Lecture":cc1.getType()==2?"Tutorial":"Laboratory";
			System.out.println(c.getId() + " " + c.getName() + " - " + classType + " " + cc1.getName());
		}
		System.out.println("\n");

		db.store(s);
		db.store(c);
		db.store(c.getStudentList());
		db.store(c.getCourseClassList());
	}


	public static void printVacancy(){
		System.out.println("Choose course to view vacancy. ");
		Course c = (Course)chooseChoosable((ArrayList)courseList);
		if(c==null)return;

		for(int i=1;i<=3;i++){
			ArrayList<CourseClass> tempCCList = new ArrayList<CourseClass>();
			for(CourseClass tempCC: c.getCourseClassList()){ 
				if(tempCC.getType()==i) {
					tempCCList.add(tempCC);
					int vac = tempCC.getMaxSize() - tempCC.getStudentList().size();
					String classType = tempCC.getType()==1?"Lecture":tempCC.getType()==2?"Tutorial":"Laboratory";
					System.out.println(classType + " index: "+ tempCC.getId() + " name: " + tempCC.getName() + " vacancy:" + vac + "/" + tempCC.getMaxSize());

				}
			}
		}
	}


	public static void printStudentList() {
		System.out.println("Please select the course to display: ");
		Course c = (Course)chooseChoosable((ArrayList)courseList);
		if(c==null)return;
		System.out.println("Please select the course class to display: ");
		CourseClass cc = (CourseClass)chooseChoosable((ArrayList)c.getCourseClassList());
		if(cc==null)return;
		System.out.println("Printing course list of student in this course class");

		for (Student s: cc.getStudentList())
			System.out.println("Id: " + s.getId() +"\tName: " + s.getName());

	}

	public static void printTranscript() {

		System.out.println("Please select student ID to display transcript: ");
		Student s = (Student)chooseChoosable((ArrayList)studentList);

		double totalCourseGrade = 0.00;

		for (Course c: s.getCourseList()) {
			System.out.println("Results for course: " + c.getName() + "...");

			for (Result r: s.getResultList()) {
				int score = r.getScore();
				double weightage = r.getParentExamComponent().getWeightage();
				double totalScore = r.getParentExamComponent().getTotalScore();
				
				System.out.println("Score for " + r.getParentExamComponent().getName() + " " + r.getScore() + " out of " + r.getParentExamComponent().getTotalScore() +  " with weightage " + weightage);
				
				totalCourseGrade += ((score/totalScore) * (weightage));
			}

			computeGrade(totalCourseGrade);

		}

	}

	public static void computeGrade(double totalCourseGrade) {
		String grade;

		if (totalCourseGrade > 80)
			grade = "A";
		else if (totalCourseGrade < 80 && totalCourseGrade > 60)
			grade = "B";
		else if (totalCourseGrade < 60 && totalCourseGrade > 50)
			grade = "C";
		else
			grade = "D";
		System.out.print("Grade: " + grade);
	} 

	/*		for (Course c: s.getCourseList()) {

			for (CourseClass cc: c.getCourseClassList()) {
				for (Result r: s.getResultList()
			}
		}

	 */		
	//		System.out.println(s.getResultList());

	//		System.out.println("Name: " + s.getName());
	//		System.out.println("ID: " + s.getId());


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