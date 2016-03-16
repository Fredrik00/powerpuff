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
			state = mcon.createStatement();
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

			System.out.println(message);
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

	public void exit(){
		try {
			mcon.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String compare(int ID, int days){
		try {
			Statement tempstate = mcon.createStatement();
			ResultSet specific = tempstate.executeQuery("select Resultat, Ovelse_Navn from treningsokt join resultater where treningsokt.ID = resultater.Okt_ID and resultater.ID = " + ID);
			specific.next();
			int resultat = specific.getInt(1);
			String navn = specific.getString(2);
			tempstate.close();

			ResultSet periode = state.executeQuery("select dato, Resultat, Maal, Ovelse_Navn from treningsokt join resultater where treningsokt.ID = resultater.Okt_ID and Ovelse_Navn = '" + navn + "' and dato > NOW() - INTERVAL " + days + " DAY");
			int lowest = 0;
			int maal = 0;

			while (periode.next()) {
					if (lowest == 0 || lowest > periode.getInt(2)){
						lowest = periode.getInt(2);
					}

					if (maal == 0 || maal > periode.getInt(3)){
						maal = periode.getInt(3);
					}
				}

			String maaltekst = ".";

			if (lowest == 0){
				return "Ikke nok testdata for perioden.";
			}

			if (maal == 0){
				maaltekst = ". Ikke noe mål for perioden.";
			}

			else if (maal < resultat){
				maaltekst = ". Mål ikke oppnådd, med en differanse på " + (resultat - maal) + ".";
			}

			else if (maal > resultat){
				maaltekst = ". Mål oppnåd, med en differanse på " + (resultat - maal) + ".";
			}

			if (lowest < resultat){
				return "Resultat dårligere enn beste i perioden, med en differanse på " + (resultat - lowest) + maaltekst;
			}

			else if (lowest > resultat){
				return "Resultat bedre enn beste i perioden, med en differanse på " + (resultat - lowest) + maaltekst;
			}

			else if (lowest == resultat){
				return "Resultat like godt som det beste i perioden" + maaltekst;
			}
		}

			catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "Det har skjedd en feil";

	}
}