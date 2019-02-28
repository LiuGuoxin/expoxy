package com.guoxin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sql {
	private Connection con;
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/expoxy";
	private String user = "root";
	private String password = "root";

	public Sql() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			if (!con.isClosed())
				System.out.println("Succeeded connecting to the Database!");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Staff search_staff_From_DataBase(String staffNum) {
		ResultSet rs = null;
		Staff staff = null;
		Statement statement =null;
		try {
			statement = con.createStatement();
			rs = statement.executeQuery("select * from staff where staff_num = \"" + staffNum + "\"");
			if (rs.next())
				staff = new Staff(rs.getString("Staff_num"), rs.getString("staff_name"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			rs.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return staff;
	}

	public Expoxy search_expoxy_From_expoxyStorage_by_sierelNum(String sierelNum) {
		ResultSet rs = null;
		Expoxy expoxy = null;
		Statement statement =null;
		try {
			statement = con.createStatement();
			rs = statement.executeQuery("select * from expoxy_storage where expoxy_num = \"" + sierelNum + "\"");
			if (rs.next())
				expoxy = new Expoxy(rs.getString("expoxy_num"), rs.getTimestamp("storage_time"),
						search_staff_From_DataBase(rs.getString("storage_operator")), rs.getInt("expoxy_status"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return expoxy;		
	}
	
	
	public boolean storage(Expoxy expoxy){
		boolean success = true;
		PreparedStatement  statement =null;
			try {
				statement = con.prepareStatement("insert into expoxy_storage values (?,?,?,?)");
				statement.setString(1, expoxy.sierelNum);
				statement.setTimestamp(2, expoxy.strorageDate);
				statement.setString(3,expoxy.storager.staffNum);
				statement.setInt(4, 1);
				statement.executeUpdate();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				success = false;
				try {
					statement.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					success = false;
				}
			}
			return success;
	}
	
	public boolean ufreeze(Expoxy expoxy){
		boolean success = true;
		PreparedStatement  statement =null;
			try {
				statement = con.prepareStatement("insert into expoxy_unfreeze values (?,?,?)");
				statement.setString(1, expoxy.sierelNum);
				statement.setTimestamp(2, expoxy.unfreezeDate);
				statement.setString(3,expoxy.unfreezer.staffNum);
				statement.executeUpdate();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				success = false;
				try {
					statement.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					success = false;
				}
			}
			
			try {
				statement = con.prepareStatement("UPDATE  expoxy_storage SET expoxy_status =2 where expoxy_num = \"" + expoxy.sierelNum + "\"");
				statement.executeUpdate();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				success = false;
				try {
					statement.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					success = false;
				}
			}
			return success;
	}
}
