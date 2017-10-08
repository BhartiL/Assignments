package Assignment4;

public class Test {
	/**
	 * Problem-4
	 * Design a simple registration system that allows Student to register in a
	 * course using 2 classes: class Student & class Course. Implement the scenarios
	 * in class Test’s main method. Each student has a name and an id variables.
	 * Each object of class Student is initialized using values of name and id
	 * passed to constructor. Class Student has accessor methods for its instance
	 * variables Each Course has a name, and a variable numberOfStudent representing
	 * the number of registered students. A course can have a maximum number of 10
	 * students registered in it. Class Course store the registered students in
	 * students which is an array of type Student. When a student register in a
	 * course, he is added to the array. Each object of class Course is initialized
	 * using the title. Class Course has the following methods: method
	 * getStudents(): return the array of registered students; method boolean
	 * isFull(): return true if the course is full, accessor method for the title
	 * and numberOfStudent field, method registerStudent (Student student): if the
	 * course is not full, register a student in course. (Score 2)
	 * 
	 * 
	 */
	public static void main(String[] args) {

		Course course = new Course("Abc");
		Student student1 = new Student("Bharti", 1);
		Student student2 = new Student("Kirti", 67);
		course.registerStudent(student1);
		course.registerStudent(student2);
		System.out.println("The student1 Id is :" + student1.getStudentId());
		System.out.println("The name of student1 is: " + student1.getStudentName());
		System.out.println("The student2 Id is :" + student2.getStudentId());
		System.out.println("The name of student2 is: " + student2.getStudentName());
		System.out.println("The number of registered students is: " + course.getNumberOfStudent());
		System.out.println("Course Title is: " + course.getCourseName());
		System.out.println("The result of course is full: " + course.isFull());
		System.out.println("The array of registered students " + course.getStudents());
		course.returnRegisteredStudentArrayElements();
	}
}

class Student {
	String studentName;
	int studentId;

	Student(String studentName, int studentID) { // Student class constructor
		this.studentName = studentName; 
		this.studentId = studentID;
	}

	public int getStudentId() {
		return studentId; //accessor method for studentId
	}

	public String getStudentName() {
		return studentName; //accessor method for StudentName
	}
}

class Course {
	String courseName;
	int numberOfStudent = 0;

	public Course(String title) { //Course class constructor
		this.courseName = title;  
	}

	Student[] students = new Student[10]; //declaring students array with maximum capacity of 10
	int studentArrayLength = students.length;

	public Student[] getStudents() {

		return students; // returning students array
	}
/*
 * returnRegisteredStudentArrayElements method returning students array elements
 */
	public void returnRegisteredStudentArrayElements() {
		for (int j = 0; j < studentArrayLength; j++) {
			if (students[j] != null)
				System.out.println("RegistedStudentId: " + students[j].studentId + ", " + "RegisteredStudentName: "
						+ students[j].studentName);
			else
				break;
		}
	}
/*
 * boolean isFull method checking course is full or not
 * 
 */
	public boolean isFull() {
		if (numberOfStudent > 10) {
			return true;
		}
		return false;
	}
/*
 * registerStudent method i registering students into students array
 */
	public void registerStudent(Student student) {
		if (numberOfStudent <= 10) {
			students[numberOfStudent] = student;
			numberOfStudent = numberOfStudent + 1;
		}
	}

	public int getNumberOfStudent() {
		return numberOfStudent; //accessor method for numberOfStudent
	}

	public String getCourseName() {
		return courseName; //accessor method for title
	}

}
