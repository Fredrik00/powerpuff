package Treningsdagbok;

import java.sql.*;

public class Connector {


	private Connection mcon;
	private Statement state;
	private String userName = "DB Admin";
	private String db = "jdbc:mysql://localhost:3306/treningsdagbok?useSSL=false";
	private String driver = "com.mysql.jdbc.Driver";
	private String password = "secretpassword";

	public Connector() {
		try {
			Class.forName(driver).newInstance();
			mcon = DriverManager.getConnection(db,userName,password);
			Statement state = mcon.createStatement();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void Read(String s) {
		try {
			ResultSet result = state.executeQuery(s);
			int columns = result.getMetaData().getColumnCount();

			StringBuilder message = new StringBuilder();

			while (result.next()) {
				for (int i = 1; i <= columns; i++) {
					message.append(result.getString(i) + " ");
				}
				message.append("\n");
			}

			System.out.println(message);  // print table contents
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean Write(String s) {
		try {
			return state.executeUpdate(s) == 1;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}