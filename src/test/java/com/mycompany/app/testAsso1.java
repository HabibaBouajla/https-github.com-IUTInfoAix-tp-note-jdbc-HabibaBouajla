package com.mycompany.app;
import java.sql.*;
import java.util.ArrayList;

public class testAsso1 {

	static final String req1 = "SELECT NUM_PROF, NOM_PROF, PRENOM_PROF, MAT_SPEC "
							 + "FROM PROF ";

	static ArrayList<Prof> listeProf = new ArrayList<Prof>();

	public static void main(String[] args) throws SQLException 
	{
		Connection myConnection = ConnexionUnique.getInstance().getConnexion();
		try 
		{
			Statement stmt = myConnection.createStatement();
			
			//Exécution de la requête
			ResultSet rset = stmt.executeQuery(req1);
			while (rset.next())
			{
				Prof prof = new Prof();

				prof.setNomProf(rset.getString("NOM_PROF"));
				prof.setPrenomProf(rset.getString("PRENOM_PROF"));
				prof.setSpecialite(new Module(rset.getString("MAT_SPEC"), null, 0, 0, 0, 0, null, 0, 0, null, null));
				
				listeProf.add(prof);
			}
			
			for(Prof prof : listeProf){System.out.println(prof.toString());}
			
			stmt.close();
			
		} catch (SQLException e)
		{
			e.printStackTrace();
			System.out.print(e.getMessage());
		} finally 
		{
			//Fermeture de la connexion (si déjà ouverte)
			if(myConnection != null)
				myConnection.close();
		}
	}
	
	

}
