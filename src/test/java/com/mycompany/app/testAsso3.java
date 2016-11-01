package com.mycompany.app;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class testAsso3 {

	 static final String req1 = "SELECT ANNEE, ET.NUM_ET, P.NUM_PROF, PRENOM_PROF, NOM_PROF, M.CODE, LIBELLE "
             				  + "FROM ENSEIGNT E JOIN ETUDIANT ET ON E.NUM_ET = ET.NUM_ET "
             				  + "JOIN MODULE M ON M.CODE = E.CODE "
             				  + "JOIN PROF P ON P.NUM_PROF = E.NUM_PROF";
	 
	public static void main(String[] args) throws SQLException 
	{
		Connection myConnection = ConnexionUnique.getInstance().getConnexion();
		try 
		{
			Statement stmt = myConnection.createStatement();
			
			ResultSet rset = stmt.executeQuery(req1);
			
			ArrayList<Enseignement> listeEnseignement = new ArrayList<>();
			
			while (rset.next())
			{
				Enseignement ens = new Enseignement();
                Etudiant etudiant = new Etudiant();
                Module mod = new Module();
                Prof prof = new Prof();

                etudiant.setNumEt(rset.getInt("ET.NUM_ET"));
                etudiant.setAnnee(rset.getInt("ANNEE"));
                mod.setCode(rset.getString("CODE"));
                mod.setLibelle(rset.getString("LIBELLE"));
                prof.setNumProf(rset.getInt("NUM_PROF"));
                prof.setPrenomProf(rset.getString("PRENOM_PROF"));
                prof.setNomProf(rset.getString("NOM_PROF"));

                ens.setEtudiant(etudiant);
                ens.setModule(mod);
                ens.setProf(prof);

                etudiant.addEnseignement(ens);
                prof.addEnseignement(ens);

                listeEnseignement.add(ens);
			}
			
			Set<Enseignement> ensPremAnnee = new HashSet<Enseignement>();
            for(Enseignement ens : listeEnseignement)
            {
                if(ens.getEtudiant().getAnnee() == 1) ensPremAnnee.add(ens);
            }
            for(Enseignement ens : ensPremAnnee) {
                System.out.println("Module : " + ens.getModule().getCode() + " (" + ens.getModule().getLibelle()
                        + "), Prof n° " + ens.getProf().getNumProf() + " (" + ens.getProf().getPrenomProf() + " " + ens.getProf().getNomProf() +")");
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
