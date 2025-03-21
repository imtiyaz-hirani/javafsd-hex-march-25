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

	public List<Project> fetchAllProjects() {
		System.out.println(DBUtil.getInstance());
		System.out.println(DBUtil.getInstance());
		Connection con = DBUtil.getInstance().dbConnect();
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
			DBUtil.getInstance().dbClose();
			return list; 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		DBUtil.getInstance().dbClose();
		return null;
	}
	
	
}
