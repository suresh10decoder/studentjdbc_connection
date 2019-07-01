package student_detail;

import java.util.ArrayList;
import java.util.Scanner;

public class Test extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Student> someStudent = new ArrayList<Student>();
	Scanner scan = new Scanner(System.in);
	// Student student = new Student();
	Connection connection = new Connection();

	public static void main(String[] args) {
		Test test = new Test();

		Scanner sc = new Scanner(System.in);
		int op;
		do {
			System.out.println("Enter your choice");
			System.out.println("1.Add stuent detail");
			System.out.println("2.remove student detail ");
			System.out.println("3.update student detail");
			System.out.println("4.display");
			System.out.println("5.exit");
			op = sc.nextInt();
			switch (op) {
			case 1:
				test.add1();
				break;
			case 2:
				test.remove();
				break;
			case 3:
			    test.update();
				break;
			case 4:
				test.display();
				break;
			case 5:
				System.exit(0);
				break;
			}
		} while (op > 0 && op <= 4);
		sc.close();
	}

	public void add1() throws Invalidcgpaexception {

		System.out.print(" ID: ");
		int id = scan.nextInt();
		System.out.print(" Name: ");
		String name = scan.next();
		float cgpa;
		try {
			System.out.print(" CGPA: ");
			cgpa = scan.nextFloat();

			if (cgpa >= 5)

				System.out.println("valid");
			else
				throw new Exception();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			throw new Invalidcgpaexception("Invalid Cgpa entered");
		}
		someStudent.add(new Student(id, name, cgpa));
		connection.adds(new Student(id, name, cgpa));
	}

	public void remove() {

		System.out.print(" Enter student id to delete");
		int delete = scan.nextInt();
				connection.del(delete);
	}

	public void update() {
		
		System.out.print("enter student id to update: ");
		int id=scan.nextInt();
		connection.modify(id);

	}

	public void display() {
		someStudent = connection.show();
		for (Student student : someStudent)
		{
			System.out.println(student.getId());
		    System.out.println(student.getName());
		    System.out.println(student.getCgpa());

	}
	}
}
