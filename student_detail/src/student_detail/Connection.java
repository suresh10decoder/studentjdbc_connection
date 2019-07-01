package student_detail;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Connection {
	public java.sql.Connection con;
	// Test test=new Test();
	Student students = new Student();

	public Connection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?useSSL=false", "root", "root");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void adds(Student student) {
		try {
			String query = " insert into list (ID, Name, Cgpa)" + " values (?, ?, ? )";
			PreparedStatement pStmt = con.prepareStatement(query);
			pStmt.setInt(1, student.getId());
			pStmt.setString(2, student.getName());
			pStmt.setFloat(3, student.getCgpa());
			pStmt.execute();
		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}

	public ArrayList<Student> show() {
		ArrayList<Student> some = new ArrayList<Student>();
		try {

			String query = "select * from list";
			PreparedStatement pStmt = con.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery(query);
			while (rs.next()) {
//				System.out.println (rs.getInt(1));
//				System.out.println(rs.getString(2));
//			System.out.println(rs.getFloat(3));
				some.add(new Student(rs.getInt(1), rs.getString(2), rs.getFloat(3)));
				/*
				 * for (int i = 0; i < someStudent.size(); i++) {
				 * System.out.println(someStudent.size());
				 * 
				 * 
				 * System.out.println(someStudent.get(i)); }
				 */
			}
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return some;

	}

	public void modify(int id) {
		try {
			// String query = "select * from list where id=1";
			PreparedStatement ps = con.prepareStatement("select * from list where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				PreparedStatement pst = con.prepareStatement("update list set id=?, name=? , cgpa=?");
				Scanner scanner = new Scanner(System.in);

				System.out.print("enter student name: ");
				String Name = scanner.next();
				System.out.print("enter student cgpa: ");
				float Cgpa = scanner.nextFloat();
				ps.setInt(1, id);
				pst.setString(2, Name);
				pst.setFloat(3, Cgpa);
				pst.executeUpdate();
				System.out.println("records updated");
				scanner.close();

			}

		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}

	public void del(int i) {
		try {
			PreparedStatement ps = con.prepareStatement("delete from list" + " where id=?");
			ps.setInt(1, i);
			ps.executeUpdate();
		} catch (Exception e) {
			System.err.println("Got an Exception ");
			System.err.println(e.getMessage());
		}
	}
}
