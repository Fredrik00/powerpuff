package Treningsdagbok;

import java.sql.*;

public class Connector {

	public static void main(String[] args) {
		String db = "jdbc:mysql://localhost:3306/treningsdagbok?useSSL=false";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "DB Admin";
		String password = "secretpassword";
		try {
			Class.forName(driver).newInstance();
			Connection connection = DriverManager.getConnection(db,userName,password);
			Statement state = connection.createStatement();
			int value = state.executeUpdate("INSERT into treningsokt VALUES ('2016-03-15','10:00', 2, null, null, null, null)");
			ResultSet result = state.executeQuery("SELECT * FROM treningsokt");
			connection.close();
			}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
}