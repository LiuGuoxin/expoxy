package com.guoxin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sql {
	private Connection con;
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/expoxy";
	private String user = "root";
	private String password = "root";
	private Statement statement;
	public Sql(){
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,password);
			if(!con.isClosed())
				System.out.println("Succeeded connecting to the Database!");
			statement = con.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	public  Staff search_staff_From_DataBase(String staffNum){
		ResultSet rs;
		Staff staff = null;
		try {
			rs = statement.executeQuery("select * from staff where staff_num = \""+ staffNum+"\"");
			if(rs.next())
			staff = new Staff(rs.getString("Staff_num"), rs.getString("Staff_name"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return staff;
	}
	
}
