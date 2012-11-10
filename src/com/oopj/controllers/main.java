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
	public static List<Professor> professorList;
	static Scanner sc;
	static ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded .newConfiguration(), "temp.db");
	public static void main(String[] args) {
		//Person person = new Student("a", "a");
		List <Student> studentsFromDB = db.query(Student.class);
		List <Result> resultList = new ArrayList(db.query(Result.class));
		List <ExamComponent> examComponentList = new ArrayList(db.query(ExamComponent.class));
		studentList = new ArrayList(studentsFromDB);
		courseList = new ArrayList(db.query(Course.class));
		courseClassList = new ArrayList(db.query(CourseClass.class));
		professorList = new ArrayList(db.query(Professor.class));

		//db.query(CourseClass.class);
		//studentList = db.query(Student.class);
		int choice;
		do{
			System.out.println("\n*******************************************************************");
			System.out.println("* STUDENT COURSE REGISTRATION AND MARK ENTRY Application (SCRAME) *");
			System.out.println("*******************************************************************");
			System.out.println("\t\t      Please select a choice:");
			System.out.println("(1) Add Student");
			System.out.println("(2) Add Course");
			System.out.println("(3) Register Student for Course");
			System.out.println("(4) Check Vacancy in a Class");
			System.out.println("(5) Print Student List by Class Type");
			System.out.println("(6) Enter Course Assessment Component Weightage");
			System.out.println("(7) Enter Coursework Marks inclusive of its Components");
			System.out.println("(8) Enter Exam Marks");
			System.out.println("(9) Print Course Statistic");
			System.out.println("(10) Print Transcript\n");
			System.out.print("Choice made:");
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
		System.out.print("Add a Student : Input Student's Name and Matriculation No. - ");
		Student student = new Student(sc.next(), sc.next());
		for(Student s: studentList) 
			if(s.getId().equals(student.getId())){
				System.out.println("Student not added! Matriculation No. already exists!");
				return;
			}
		if(studentList==null) studentList = new ArrayList<Student>();
		studentList.add(student);
		System.out.println("New Student : " + student.getId() + " - " + student.getName() + " has been added successfully!\n");
		db.store(student);

		System.out.println("List of Students Registered:");
		for(Student s: studentList){//int i=0;i<studentList.size();i++
			System.out.println(s.getId() + " - " + s.getName());
		}
	}
	
	public static void addProfessor(){
		System.out.print("Add a Professor : Input Professor's Name and Staff No. - ");
		Professor professor = new Professor(sc.next(), sc.next());
		for(Professor p: professorList) 
			if(p.getId().equals(professor.getId())){
				System.out.println("Professor not added! Staff No. already exists!");
				return;
			}
		if(professorList==null) professorList = new ArrayList<Professor>();
		professorList.add(professor);
		System.out.println("Professor " + professor.getName() + ", Staff No. " + professor.getId() +  " has been added successfully!\n");
		db.store(professor);

	}

	public static void addCourse(){
		System.out.print("Add a Course : Input Course Name and Course Index - ");
		Course course = new Course(sc.next(), sc.next());
		for(Course c: courseList) 
			if(c.getId().equals(course.getId())){
				System.out.println("Course not added! Course Index already exists!");
				return;
			}
		Professor testP = null;
		while(testP==null){
			System.out.print("Please select Professor-in-Charge for this course:\n");
			if (professorList.size() ==0){
				System.out.print("No professor in list yet. Please add a new professor!\n");
				addProfessor();
			}else{
				System.out.print("Select a professor or cancel to add a new professor:\n");
			}
			testP = (Professor)chooseChoosable((ArrayList)professorList);
			if(testP==null){
				addProfessor();
			}
		}
		
		course.setProfessor(testP);
		courseList.add(course);
		System.out.println("New Course : " + course.getId() + " - " + course.getName() + "\tProfessor-in-Charge : " + course.getProfessor().getName() + " has been added successfully!\n");
		System.out.println("List of courses:");
		for(Course c: courseList){//int i=0;i<studentList.size();i++
			System.out.println("Course Index: " + c.getId() + " \tCourse Name: " + c.getName() + " \tProfessor-in-charge: " + c.getProfessor().getName());
		}
		System.out.print("\n");
		addCourseClass(course);
		db.store(course);
	}

	public static void addCourseClass(Course course){
		System.out.println("Add Course Class Types : ");
		System.out.println("(1) Lectures, Laboratory, Tutorials");
		System.out.println("(2) Lectures & Tutorials ONLY");
		System.out.println("(3) Lectures ONLY\n");
		System.out.println("Choice made : ");
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
		String classType = type==1?"lecture":type==2?"tutorial":"laboratory";
		System.out.println("How many classes for " + course.getName() + " - " + classType + " ?");
		int classAmt = sc.nextInt();
		for(int i =0;i<classAmt;i++){
			System.out.println("Add a Course Class : Input Class Name, Class Index, Class Intake Size.");
			CourseClass tempCourseClass = new CourseClass(sc.next(), sc.next(), sc.nextInt(), type, course);
			course.getCourseClassList().add(tempCourseClass);
		}
		System.out.println("New Course Class : " + classAmt + " " + classType + " has been added successfully!\n");
	}

	public static void editAssessmentComponentWeightage(){
		System.out.println("Choose Course to Update Assessment Component Weightage :");
		Course tempCourse = (Course)chooseChoosable((ArrayList)courseList);
		System.out.printf("Exam total and weightage has %sbeen set.\n",tempCourse.getExam()==null?"not ":"");
		System.out.printf("Coursework total and weightage has %sbeen set.\n",tempCourse.getCourseWork()==null?"not ":"");
		int choice;
		do{
			System.out.println("Press 1 to Update Exam and 2 to Update Coursework. Press Any Other Number to Quit.");
			choice = sc.nextInt();
			if(choice==1) editExam(tempCourse);
			else if (choice==2)editCourseWork(tempCourse);
		} while (choice == 1 || choice == 2);

	}

	public static void editExam(Course tempCourse){
		if(tempCourse.getExam()==null){
			System.out.print("Please Input Total Score of the Exam and its Weighted Percentage : ");
			Exam tempExam = new Exam(tempCourse.getId(), tempCourse.getName(), sc.nextInt(), sc.nextInt(), tempCourse);
			tempCourse.setExam(tempExam);
			System.out.println("Total Score of the Exam is " + Integer.toString(tempCourse.getExam().getTotalScore()) + " and its Weighted Percentage is " + Integer.toString(tempCourse.getExam().getWeightage()));
		}else{
			System.out.println("Current Total Score of the Exam is " + Integer.toString(tempCourse.getExam().getTotalScore()) + " and its Weighted Percentage is " + Integer.toString(tempCourse.getExam().getWeightage()));
			System.out.print("Enter 1 to Update the Exam Total Marks and Weightage. Press Any Other Number to Quit. ");
			int choice = sc.nextInt();
			if(choice == 1){
				System.out.println("Please Input Total Score of the Exam and its Weighted Percentage ");
				tempCourse.getExam().setTotalScore(sc.nextInt());
				tempCourse.getExam().setWeightage(sc.nextInt());
				System.out.println("Total score of the Exam is " + Integer.toString(tempCourse.getExam().getTotalScore()) + " and its Weighted Percentage is" + Integer.toString(tempCourse.getExam().getWeightage()));
			}	
		}
		db.store(tempCourse);
		//int
	}

	public static void editCourseWork(Course tempCourse){
		if(tempCourse.getCourseWork()==null){
			System.out.print("Please Input Total Weighted Percentage of the Coursework : ");
			CourseWork tempCourseWork = new CourseWork(tempCourse.getId(), tempCourse.getName(), sc.nextInt(), tempCourse);
			tempCourse.setCourseWork(tempCourseWork);
			System.out.println("Weighted Percentage is " + Integer.toString(tempCourse.getCourseWork().getWeightage()));
			editCourseWorkComponent(tempCourse);
		}else{
			System.out.println("Current Weighted Percentage is" + Integer.toString(tempCourse.getCourseWork().getWeightage()));
			System.out.print("Enter 1 to Update Coursework Total Weightage or 2 to Update Coursework Components : ");
			int choice = sc.nextInt();
			if(choice == 1){
				System.out.print("Please Input Total Weighted Percentage of Coursework : ");
				tempCourse.getCourseWork().setWeightage(sc.nextInt());
				System.out.println("Weighted Percentage is " + Integer.toString(tempCourse.getCourseWork().getWeightage()));
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
			System.out.printf("There are currently %d Components.\nDo you want to 1. Add a new component%s?\n",tempCourseWork.getComponent().size(),tempCourseWork.getComponent().size()==0?"":" or 2. Update a component");
			choice = sc.nextInt();
			if(choice==1){
				System.out.print("Please Input Component's Name, Total Marks and Weighted Percentage. ");
				Component c = new Component(tempCourse.getId(), sc.next(), sc.nextInt(), sc.nextInt(), tempCourseWork);
				tempCourseWork.getComponent().add(c);
				db.store(tempCourse);
			} else if (choice ==2 && tempCourseWork.getComponent().size()>0){
				Component c = (Component)chooseChoosable((ArrayList)tempCourseWork.getComponent());
				if(c==null) continue;
				System.out.print("Please Input Updated Component's Name, Total Marks and Weighted Percentage. ");
				c.setName(sc.next());
				c.setTotalScore(sc.nextInt());
				c.setWeightage(sc.nextInt());
				db.store(tempCourse);
			}

		}while(choice==1||choice==2);

	}

	public static void addExamResult(){
		System.out.println("Select Course to Add Exam Results For :");
		Course c = (Course)chooseChoosable((ArrayList)courseList);
		if(c==null)return;
		if(c.getExam()==null)return;
		System.out.println("Select Student to Add Exam Results For :");
		Student s = (Student)chooseChoosable((ArrayList)c.getStudentList());
		if(s==null)return;
		addResult(s, c.getExam());
	}

	public static void addCourseWorkMark(){
		System.out.println("Select Course to Add Coursework Marks For :");
		Course c = (Course)chooseChoosable((ArrayList)courseList);
		if(c==null)return;
		System.out.println("Select Student to Add Coursework Results For :");
		Student s = (Student)chooseChoosable((ArrayList)c.getStudentList());
		if(s==null)return;
		System.out.println("Select Component to Add Marks For :");
		Component com = (Component)chooseChoosable((ArrayList)c.getCourseWork().getComponent());
		addResult(s, com);
		db.store(s);
		db.store(c);
	}

	public static boolean addResult(Student s, ExamComponent ec){
		for(Result r : s.getResultList()){
			if(r.getParentExamComponent().equals(ec)) return false;
		}
		System.out.printf("Maximum Grade for %s is %d. Please Input Grade for %s : ",ec.getName(),ec.getTotalScore(),s.getName());
		int grade = sc.nextInt();
		if(grade>ec.getTotalScore()) grade = ec.getTotalScore();
		else if (grade<0) grade = 0;
		Result r = new Result(ec.getId(), grade, s, ec);
		s.getResultList().add(r);
		ec.getResultList().add(r);
		db.store(s);
		db.store(s.getResultList());
		db.store(ec.getResultList());
		return true;
	}

	public static void printStatistics(){
		System.out.println("Select Course to View Statistics :");
		Course c = (Course)chooseChoosable((ArrayList)courseList);
		int actualExam, actualCourseWork, total, totalExam=0, totalCourseWorkWeight=0;
		for(Component com:c.getCourseWork().getComponent()) totalCourseWorkWeight+= com.getWeightage();
		total = c.getExam().getWeightage() + c.getCourseWork().getWeightage();
		for(Result r:c.getExam().getResultList()) totalExam+=r.getScore();
		int avgExam = totalExam/c.getExam().getResultList().size();
		int avgExamPercentage = totalExam/c.getExam().getResultList().size()*c.getExam().getWeightage()/100;
		int avgExamWeightedPercentage = totalExam/c.getStudentList().size()*c.getExam().getWeightage()/total;
		System.out.printf("Average Actual Mark for Exam is %d/%d. Average Percentage is %d. Average Weighted Percentage is %d .\n",avgExam,c.getExam().getTotalScore(),avgExamPercentage,avgExamWeightedPercentage);
		for(Component com: c.getCourseWork().getComponent()){
			int totalComponent=0;
			for(Result r : com.getResultList()) totalComponent+=r.getScore();
			int avgComponent = totalComponent/com.getResultList().size();
			int avgComponentPercentage = totalComponent/com.getResultList().size()*com.getWeightage()/100;
			int avgComponentWeightedPercentage = totalComponent/com.getResultList().size()*com.getWeightage()/totalCourseWorkWeight*c.getCourseWork().getWeightage()/total;
			System.out.printf("Average Actual Mark for %s is %d/%d. Average Percentage is %d. Average Weighted Percentage is %d .\n",com.getName(),avgComponent,com.getTotalScore(),avgComponentPercentage,avgComponentWeightedPercentage);
		}
	}

	public static Choosable chooseChoosable(ArrayList<Choosable> choosableList){
		int choice,pageCount=0,i;
		boolean lastPage = false;
		do{
			for(i=1; pageCount*10+i-1<choosableList.size()&&i<=10;i++)
				System.out.println("("+ Integer.toString(i) +") " + (choosableList.get((pageCount*10+i-1))).printString());
			if(i>=10&&((pageCount*10+i-1)<=choosableList.size())) System.out.println("Enter 11 to see the Next 10 "+ choosableList.get(0).getClass().getSimpleName());
			else{
				System.out.println("End of "+ choosableList.get(0).getClass().getSimpleName()+" List. Enter 0 to Restart the List or -1 to Quit");
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

		System.out.println("Select Student to Register: ");
		Student s = (Student)chooseChoosable((ArrayList)studentList); 
		if(s==null)return;
		System.out.println("Select Course to Register Student into: ");
		Course c = (Course)chooseChoosable((ArrayList)courseList);
		if(c==null)return;
		if(c.getStudentList().contains(s)){
			System.out.println("Student is Already Enrolled in this Course.\n");
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
				System.out.println("Please Select the Class to Register For :");
				CourseClass cc = (CourseClass)chooseChoosable((ArrayList)tempCCList);
				if(cc==null) return;
				if(cc.getMaxSize() - cc.getStudentList().size() == 0) //how to prompt again, while loop?
					System.out.println("Course " + c.getId() + " - " + c.getName() + " - " + cc.getName() + " " + cc.getId() + " Has No More Vacancy. Please Select Another Class!\n");
				else {
					s.getCourseClassList().add(cc);
					cc.getStudentList().add(s);
					System.out.println("Student " + s.getName() + ", Matriculation No. " + s.getId() + " Has Successfully been Enrolled to the Following Course:");
					db.store(cc);
					for(CourseClass cc1: s.getCourseClassList()){
						String classType = cc1.getType()==1?"Lecture":cc1.getType()==2?"Tutorial":"Laboratory";
						System.out.println(c.getId() + " " + c.getName() + " - " + classType + " " + cc1.getName() + "\n");

					}
				}
			}
		}
		System.out.println("Student " + s.getName() + ", Matriculation No. " + s.getId() + " Has Successfully Been Enrolled to the Following Course:");
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
		System.out.println("Select Course To View Vacancy : ");
		Course c = (Course)chooseChoosable((ArrayList)courseList);
		if(c==null)return;

		for(int i=1;i<=3;i++){
			ArrayList<CourseClass> tempCCList = new ArrayList<CourseClass>();
			for(CourseClass tempCC: c.getCourseClassList()){ 
				if(tempCC.getType()==i) {
					tempCCList.add(tempCC);
					int vac = tempCC.getMaxSize() - tempCC.getStudentList().size();
					String classType = tempCC.getType()==1?"Lecture":tempCC.getType()==2?"Tutorial":"Laboratory";
					System.out.println(classType + " : \tIndex : "+ tempCC.getId() + " \tName : " + tempCC.getName() + " \tVacancy :" + vac + "/" + tempCC.getMaxSize());

				}
			}
		}
	}


	public static void printStudentList() {
		System.out.println("Please Select the Course to Display: ");
		Course c = (Course)chooseChoosable((ArrayList)courseList);
		if(c==null)return;
		System.out.println("Please Select the Course Class to Display: ");
		CourseClass cc = (CourseClass)chooseChoosable((ArrayList)c.getCourseClassList());
		if(cc==null)return;
		System.out.println("Printing Course List of Students in this Course Class");

		for (Student s: cc.getStudentList())
			System.out.println("Student's Matriculation No. : " + s.getId() +"\tStudent's Name : " + s.getName());

	}

	public static void printTranscript() {

		System.out.println("Please Select Student Matriculation No. to Display Transcript : ");
		Student s = (Student)chooseChoosable((ArrayList)studentList);

		double totalCourseGrade = 0.00;

		for (Course c: s.getCourseList()) {
			int actualExam, actualCourseWork, total, totalExam=0, totalCourseWorkWeight=0, studentCourseTotalMark=0;
			for(Component com:c.getCourseWork().getComponent()) totalCourseWorkWeight+= com.getWeightage();
			total = c.getExam().getWeightage() + c.getCourseWork().getWeightage();
			System.out.println("Results for Course: " + c.getName() + "...");
			Result examResult = null;
			for(Result r:c.getExam().getResultList())
				if(r.getParentStudent().equals(s)){
					examResult=r;
					break;
				}
			if(examResult==null)return;
			int adjustedExam = (int)((double)examResult.getScore()/(double)c.getExam().getTotalScore()*(double)c.getExam().getWeightage()/(double)total*(double)100);
			System.out.printf("Exam:\t\tActual: %d/%d\tAdjusted:%d/%d\n",examResult.getScore(),c.getExam().getTotalScore(),adjustedExam,(int)((double)c.getExam().getWeightage()/(double)total*100));
			System.out.println("\nCoursework Breakdown:");
			for(Component com:c.getCourseWork().getComponent()){
				Result comResult = null;
				for(Result r:com.getResultList())
					if(r.getParentStudent().equals(s)){
						comResult=r;
						break;
					}
				int adjustedResult = (int)((double)comResult.getScore()/(double)com.getTotalScore() * (double)com.getWeightage()/(double)totalCourseWorkWeight * (double)c.getCourseWork().getWeightage() /(double)total * (double)100);
				studentCourseTotalMark += adjustedResult;
				int adjustedTotal = (int)((double)com.getWeightage()/(double)totalCourseWorkWeight * (double)c.getCourseWork().getWeightage() /(double)total * (double)100);
				System.out.printf("%s:\t\tActual: %d/%d\tAdjusted:%d/%d\n",com.getName(),comResult.getScore(),com.getTotalScore(),adjustedResult,adjustedTotal);
			}
			studentCourseTotalMark += adjustedExam;
			System.out.printf("Final Mark: %d/100 Final Grade: %s",studentCourseTotalMark, computeGrade(studentCourseTotalMark));
			System.out.println();
			System.out.println();

		}

	}

	public static String computeGrade(double totalCourseGrade) {
		String grade;

		if (totalCourseGrade > 80)
			grade = "A";
		else if (totalCourseGrade < 80 && totalCourseGrade > 60)
			grade = "B";
		else if (totalCourseGrade < 60 && totalCourseGrade > 50)
			grade = "C";
		else
			grade = "D";
		return grade;

	} 

	public static void viewAllStudents(){
		for(Student s: studentList){
			System.out.println("Student's Matriculation No. : " +s.getId() + "\tStudent's Name: " + s.getName());
		}
	}
	public static void viewAllCourses(){
		for(Course c: courseList){
			System.out.println("Course Index: " +c.getId() + "\tCourse Name: " + c.getName());
			for(CourseClass cc : c.getCourseClassList()){
				System.out.println(cc.getName() + " " + cc.getId() + " " + Integer.toString(cc.getMaxSize()) + " " + cc.getType());
			}
		}
	}

}