package com.mycompany.app;
import java.sql.*;

public class testJBDC {

	static final String req1 = "SELECT NUM_ET, NOM_ET, PRENOM_ET "
							 + "FROM ETUDIANT "
							 + "WHERE VILLE_ET ='AIX-EN-PROVENCE'";

	public static void main(String[] args) throws SQLException 
	{
		Connection myConnection = ConnexionUnique.getInstance().getConnexion();
		try 
		{	
			Statement stmt = myConnection.createStatement();
			ResultSet rset = stmt.executeQuery(req1);
			
			while (rset.next())
			{
				System.out.print(rset.getInt("NUM_ET") + "\t\t");
				System.out.print(rset.getString("NOM_ET") + "\t\t");
				System.out.println(rset.getString("PRENOM_ET"));
			}
			
			stmt.close();
			
		} catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.print(e.getMessage());
		} finally 
		{
			if(myConnection != null)
				myConnection.close();
		}
	}

}
