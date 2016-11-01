package com.mycompany.app;
import java.sql.*;
import java.util.ArrayList;

public class testEntite 
{
	static final String req1 = "SELECT NUM_ET, NOM_ET, PRENOM_ET "
							 + "FROM ETUDIANT "
							 + "WHERE VILLE_ET ='AIX-EN-PROVENCE'";
	
	static ArrayList<Etudiant> listeEtu = new ArrayList<Etudiant>();
	static ArrayList<Prof> listeProf = new ArrayList<Prof>();
	static ArrayList<Module> listeMod = new ArrayList<Module>();

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
				Etudiant etu = new Etudiant();
				
				etu.setNumEt(rset.getInt("NUM_ET"));
				etu.setNomEt(rset.getString("NOM_ET"));
				etu.setPrenomEt(rset.getString("PRENOM_ET"));
				listeEtu.add(etu);
			}
			
			for(Etudiant e : listeEtu)
			{
				System.out.println(e.toString());
			}
			
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
