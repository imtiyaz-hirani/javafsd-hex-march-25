package com.corejavaapp.main.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.corejavaapp.main.model.Address;
import com.corejavaapp.main.model.Employee; //ctrl + shift + O
import com.corejavaapp.main.model.EmployeeProject;
import com.corejavaapp.main.model.Project;
import com.corejavaapp.main.utility.DBUtil;

public class EmployeeRepository {
	
	private String userDb="root";
	private String dbPass="techskillsit";
	private String url="jdbc:mysql://localhost:3306/fsd_java_march_25";
	private String driver = "com.mysql.cj.jdbc.Driver";
	Connection con; 
	
	List<Employee> empList = new ArrayList<>(); 
	
	public void dbConnect() {
		/*Step 1: Load the driver */
		try {
			Class.forName(driver);
			//System.out.println("DRIVER LOADED!!!!");
		} catch (ClassNotFoundException e) {
			System.out.println("DRIVER LOADING FAILED!!!!");
		}
		/* Step 2: Establish connection */
		try {
			con = DriverManager.getConnection(url, userDb, dbPass);
			//System.out.println("CONNECTION ESTABLISHED...");
		} catch (SQLException e) {
			System.out.println("CONNECTION iSSUE...");
		}
	}
	
	public void dbClose() {
		/* Close the connection*/
		try {
			con.close();
			//System.out.println("connection closed...");
		} catch (SQLException e) {
			 System.out.println(e.getMessage());	
		}
	}
	/*  to reach out to DB */
	public void populateRecords() {
		Employee e1 = new Employee(1,"harry potter", "chennai","Finance", 89000); 
		Employee e2 = new Employee(); 
		e2.setId(2);
		e2.setName("ronald weasley");
		e2.setBranch("mumbai");
		e2.setDepartment("IT");
		e2.setSalary(75000);
		
		Employee e3 = new Employee(); 
		 
		empList.add(e1);
		empList.add(e2);
		empList.add(e3);
	}
	
	public List<Employee> getEmployeeList() {
		dbConnect();
		String sql="select * from employee e JOIN address a ON e.address_id=a.id";
		List<Employee> list = new ArrayList<>(); 
		try {
			/* Step A: Prepare the statement */
			PreparedStatement pstmt = con.prepareStatement(sql);
			/*Step B: Execute the statement */
			ResultSet rst =  pstmt.executeQuery();
			while(rst.next()) { //i will stay in the loop as long as the record exists
				int id = rst.getInt("emp_id");
				String name = rst.getString("emp_name");
				String branch = rst.getString("branch");
				String department = rst.getString("department");
				double salary = rst.getDouble("salary");
				String city = rst.getString("city");
				String pincode  = rst.getString("pincode");
				int addressId = rst.getInt("id");
				
				Address address = new Address(
						addressId,
						city,
						pincode);
				
				//create obj of employee and attach above values to it
				Employee e = new Employee(
						id,
						name,
						branch,
						department,
						salary,
						address);
				
				//save this obj e in list before it gets replaced by second record
				list.add(e);
			}
			dbClose();
			return list; 
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		dbClose();
		return empList; 
	}

	public void addEmployee(Employee employee) {
		dbConnect();
		String sql1 = "insert into address values (?,?,?)";
		String sql2 = "insert into employee values (?,?,?,?,?,?)";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql1);
			pstmt.setInt(1, employee.getAddress().getId());
			pstmt.setString(2, employee.getAddress().getCity());
			pstmt.setString(3, employee.getAddress().getPincode());
			pstmt.executeUpdate();
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql2);
			pstmt.setInt(1, employee.getId());
			pstmt.setString(2, employee.getName());
			pstmt.setString(3, employee.getBranch());
			pstmt.setString(4, employee.getDepartment());
			pstmt.setDouble(5, employee.getSalary());
			pstmt.setInt(6, employee.getAddress().getId());
			pstmt.executeUpdate();
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		dbClose();
	}

	public void assignProject(EmployeeProject employeeProject, int empId, int projectId) {
		dbConnect();
		String sql="insert into employee_project values (?,?,?,?)";
		
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, employeeProject.getId());
			pstmt.setInt(2, empId);
			pstmt.setInt(3, projectId);
			//convert date to string as it is varchar in DB
			pstmt.setString(4, employeeProject.getDateOfAssign().toString()); 
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
	}

	public Optional<Employee> getEmployeeById(int eid) {
		dbConnect();
		String sql="select * from employee where emp_id=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, eid);
			
			ResultSet rst = pstmt.executeQuery();
			if(rst.next()) {
				int id = rst.getInt("emp_id");
				String name = rst.getString("emp_name");
				String branch = rst.getString("branch");
				String department = rst.getString("department");
				double salary = rst.getDouble("salary");
				
				Employee e = new Employee(
						id,
						name,
						branch,
						department,
						salary);
				dbClose();
				return Optional.of(e); 
			}
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return null;
	}

	public List<Project> getProjectsByEmployeeId(int eid) {
		dbConnect();
		 String sql="select p.* "
		 		+ " from employee e JOIN employee_project ep ON e.emp_id = ep.employee_id"
		 		+ " JOIN project p ON ep.project_id = p.id"
		 		+ " where e.emp_id=?";
		 List<Project> list = new ArrayList<>();
		 try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, eid);
			
			ResultSet rst = pstmt.executeQuery();
			while(rst.next()) {
				Project project = new Project(
						 rst.getInt("id"),
						 rst.getString("title"),
						 rst.getInt("credits"));
				list.add(project);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 dbClose();
		return list;
	}
}

/*
 * executeQuery(): select --- ResultSet
 * executeUpdate() : insert, update,delete 
 * */
