package Treningsdagbok;

import java.sql.*;

public class Connector {

	public String read(String query){
	}

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
			System.out.println(value);
			ResultSet result = state.executeQuery("SELECT * FROM treningsokt");

			int columns = result.getMetaData().getColumnCount();

			StringBuilder message = new StringBuilder();

			while (result.next()) {
			    for (int i = 1; i <= columns; i++) {
			        message.append(result.getString(i) + " ");
			    }
			    message.append("\n");
			}

			System.out.println(message);  // print table contents

			connection.close();
			}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
}