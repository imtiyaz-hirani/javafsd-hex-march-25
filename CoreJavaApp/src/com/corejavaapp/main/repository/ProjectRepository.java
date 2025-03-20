package com.corejavaapp.main.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.corejavaapp.main.model.Project;
import com.corejavaapp.main.utility.DBUtil;

public class ProjectRepository {

	DBUtil dbUtil = new DBUtil();

	public List<Project> fetchAllProjects() {
		Connection con = dbUtil.dbConnect();
		String sql="select * from project";
		List<Project> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rst =  pstmt.executeQuery();
			while(rst.next()) {
				Project project = new Project(
						 rst.getInt("id"),
						 rst.getString("title"),
						 rst.getInt("credits"));
				list.add(project);
				
			}
			dbUtil.dbClose();
			return list; 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dbUtil.dbClose();
		return null;
	}
	
	
}
