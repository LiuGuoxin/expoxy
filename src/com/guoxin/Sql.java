package com.guoxin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;

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
		Statement statement = null;
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
		Statement statement = null;
		try {
			statement = con.createStatement();
			rs = statement.executeQuery("select * from expoxy_storage where expoxy_num = \"" + sierelNum + "\"");
			if (rs.next())
				expoxy = new Expoxy(rs.getString("expoxy_num"), rs.getTimestamp("storage_time"),
						search_staff_From_DataBase(rs.getString("storage_operator")), rs.getInt("expoxy_status"));

			if(expoxy!= null)
			{
			if(expoxy.status >= 2)
				getUnfreezeInfo(expoxy);
			if(expoxy.status >= 3)
				getUseInfo(expoxy);
			if(expoxy.status >= 4)
				getCallBackInfo(expoxy);
			}
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

	private void getCallBackInfo(Expoxy expoxy) {
		// TODO 自动生成的方法存根
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = con.createStatement();
			rs = statement
					.executeQuery("select * from expoxy_callback where expoxy_num = \"" + expoxy.sierelNum + "\"");
			if (rs.next()) {
				expoxy.callBack(search_staff_From_DataBase(rs.getString("callbacker")), rs.getTimestamp("callbak_time"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getUnfreezeInfo(Expoxy expoxy) {
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = con.createStatement();
			rs = statement
					.executeQuery("select * from expoxy_unfreeze where expoxy_num = \"" + expoxy.sierelNum + "\"");
			if (rs.next()) {
				expoxy.unfreeze(search_staff_From_DataBase(rs.getString("unfreeze_operator")),
						rs.getTimestamp("unfreeze_time"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean storage(Expoxy expoxy) {
		boolean success = true;
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement("insert into expoxy_storage values (?,?,?,?)");
			statement.setString(1, expoxy.sierelNum);
			statement.setTimestamp(2, expoxy.strorageDate);
			statement.setString(3, expoxy.storager.staffNum);
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

	public boolean ufreeze(Expoxy expoxy) {
		boolean success = true;
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement("insert into expoxy_unfreeze values (?,?,?)");
			statement.setString(1, expoxy.sierelNum);
			statement.setTimestamp(2, expoxy.unfreezeDate);
			statement.setString(3, expoxy.unfreezer.staffNum);
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
		if (!success){
			return success;
		}
		try {
			statement = con.prepareStatement(
					"UPDATE  expoxy_storage SET expoxy_status =2 where expoxy_num = \"" + expoxy.sierelNum + "\"");
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

	public boolean use(Expoxy expoxy) {
		// TODO Auto-generated method stub
		boolean success = true;
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement("insert into expoxy_use values (?,?,?,?)");
			statement.setString(1, expoxy.sierelNum);
			statement.setTimestamp(2, expoxy.useDate);
			statement.setString(3, expoxy.unfreezer.staffNum);
			statement.setString(4, expoxy.place);
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
		if (!success){
			return success;
		}
		try {
			statement = con.prepareStatement(
					"UPDATE  expoxy_storage SET expoxy_status =3 where expoxy_num = \"" + expoxy.sierelNum + "\"");
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

	public void getUseInfo(Expoxy expoxy) {
		// TODO Auto-generated method stub
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = con.createStatement();
			rs = statement
					.executeQuery("select * from expoxy_use where expoxy_num = \"" + expoxy.sierelNum + "\"");
			if (rs.next()) {
				expoxy.use(search_staff_From_DataBase(rs.getString("user")),
						rs.getTimestamp("use_time"), rs.getString("place"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean callBack(Expoxy expoxy) {
		// TODO Auto-generated method stub
		boolean success = true;
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement("insert into expoxy_callback values (?,?,?)");
			statement.setString(1, expoxy.sierelNum);
			statement.setTimestamp(2, expoxy.callbackDate);
			statement.setString(3, expoxy.user.staffNum);
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
		if (!success){
			return success;
		}
		try {
			statement = con.prepareStatement(
					"UPDATE  expoxy_storage SET expoxy_status =4 where expoxy_num = \"" + expoxy.sierelNum + "\"");
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

	public void search_All_Unused_Expoxy(ArrayList<Expoxy> expoxies){
		expoxies.clear();
		ResultSet rs = null;
		Expoxy expoxy = null;
		Statement statement = null;
		try {
			statement = con.createStatement();
			rs = statement.executeQuery("select * from expoxy_storage where expoxy_status = \"" + 3 + "\"");
			while (rs.next()){
				expoxy = new Expoxy(rs.getString("expoxy_num"), rs.getTimestamp("storage_time"),
						search_staff_From_DataBase(rs.getString("storage_operator")), rs.getInt("expoxy_status"));
				getUnfreezeInfo(expoxy);
				getUseInfo(expoxy);
				expoxies.add(expoxy);
			}
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


		try {
			statement = con.createStatement();
			rs = statement.executeQuery("select * from expoxy_storage where expoxy_status = \"" + 2 + "\"");
			while (rs.next()){
				expoxy = new Expoxy(rs.getString("expoxy_num"), rs.getTimestamp("storage_time"),
						search_staff_From_DataBase(rs.getString("storage_operator")), rs.getInt("expoxy_status"));
				getUnfreezeInfo(expoxy);
				//				getUseInfo(expoxy);
				expoxies.add(expoxy);
			}
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



		expoxies.sort(new Comparator<Expoxy>() {

			@Override
			public int compare(Expoxy o1, Expoxy o2) {
				// TODO Auto-generated method stub
				return (int)(o1.unfreezeDate.getTime() - o2.unfreezeDate.getTime());
			}
		});



	}


	public void search_Expoxy_by_siearaNum(ArrayList<Expoxy> expoxies){
		expoxies.clear();
		ResultSet rs = null;
		Expoxy expoxy = null;
		Statement statement = null;
		try {
			statement = con.createStatement();
			rs = statement.executeQuery("select * from expoxy_storage where expoxy_status = \"" + 3 + "\"");
			while (rs.next()){
				expoxy = new Expoxy(rs.getString("expoxy_num"), rs.getTimestamp("storage_time"),
						search_staff_From_DataBase(rs.getString("storage_operator")), rs.getInt("expoxy_status"));
				getUnfreezeInfo(expoxy);
				getUseInfo(expoxy);
				expoxies.add(expoxy);
			}
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


		try {
			statement = con.createStatement();
			rs = statement.executeQuery("select * from expoxy_storage where expoxy_status = \"" + 2 + "\"");
			while (rs.next()){
				expoxy = new Expoxy(rs.getString("expoxy_num"), rs.getTimestamp("storage_time"),
						search_staff_From_DataBase(rs.getString("storage_operator")), rs.getInt("expoxy_status"));
				getUnfreezeInfo(expoxy);
				//				getUseInfo(expoxy);
				expoxies.add(expoxy);
			}
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



		expoxies.sort(new Comparator<Expoxy>() {

			@Override
			public int compare(Expoxy o1, Expoxy o2) {
				// TODO Auto-generated method stub
				return (int)(o1.unfreezeDate.getTime() - o2.unfreezeDate.getTime());
			}
		});



	}
}
