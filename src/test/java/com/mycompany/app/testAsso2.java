package com.mycompany.app;

import java.sql.*;
import java.util.ArrayList;

public class testAsso2 {

	// La requete de test
	static final String req = " SELECT * " +
							  " FROM ETUDIANT ";      
	static final String req1 = " SELECT * " +
							   " FROM MODULE M1 JOIN MODULE M ON M1.CODEPERE = M.CODE " +
							   " JOIN PROF P ON M1.RESP = P.NUM_PROF";
	
	
	public static void main(String[] args) throws SQLException {
		// Objet materialisant la connexion a la base de donnees
		ArrayList <Etudiant> etudiants = new ArrayList<Etudiant>();
		ArrayList <Module> modules = new ArrayList<Module>();
		
		try(Connection connection = ConnexionUnique.getInstance().getConnection()) {
			System.out.println("Connecte\n");
			
			// Creation d'une instruction SQL
			Statement stmt = connection.createStatement();
			
			// Execution de la requete req
			System.out.println("Execution de la requete : " + req );
			ResultSet rset = stmt.executeQuery(req);
			
			// Stockage dans etudiants
			while (rset.next()){
				Etudiant etudiant = new Etudiant(rset.getInt("NUM_ET"),rset.getString("NOM_ET"),
												 rset.getString("PRENOM_ET"),rset.getString("CP_ET"),
												 rset.getString("VILLE_ET"),rset.getInt("ANNEE"),
												 rset.getInt("GROUPE"));
				
				etudiants.add(etudiant);
			}
			
			Module acsi = new Module();
			System.out.println("Execution de la requete : " + req1 );
			rset = stmt.executeQuery(req1);
			
			// Stockage dans modules
			while (rset.next()){
				Module module = new Module(rset.getString(1),rset.getString(2),
										   rset.getInt(3),rset.getInt(4),
										   rset.getInt(5),rset.getInt(6),
										   rset.getString(7),rset.getInt(8),
										   rset.getInt(9));

				module.setResponsables( new Prof (rset.getInt("NUM_PROF"),rset.getString("NOM_PROF"),
						 						 rset.getString("PRENOM_PROF"),rset.getString("ADR_PROF"),
						 						 rset.getString("CP_PROF"),rset.getString("VILLE_PROF"))); 
				
				
				module.setModPere(new Module(rset.getString(10),rset.getString(11),
										  rset.getInt(12),rset.getInt(13),
										  rset.getInt(14),rset.getInt(15),
										  rset.getString(16),rset.getInt(17),
										  rset.getInt(18))); 
		
				modules.add(module);
				
				if (module.getLibelle().equals("ACSI"))
					acsi = module;
			}
			
			AssociationNotation associationNotation = AssociationNotation.getInstance();

			for (Etudiant etu : etudiants){
				for (Module mod : modules){
					
					// Requete
					String req2 = " SELECT * " +
							   	  " FROM NOTATION " +
							   	  " WHERE CODE = " + mod.getCode() +
							   	  " AND NUM_ET = " + etu.getNumEt();
								
					// Execution de la requete req2
					System.out.println("Execution de la requete : " + req2 );
					rset = stmt.executeQuery(req2);
					
					// Stockage dans Notation
					while(rset.next()){
						Notation notation = new Notation();
						
						notation.setNumEt(etu.getNumEt());
						notation.setCode(mod.getCode());
						notation.setMoyCC(rset.getInt("MOY_CC"));
						notation.setMoyTest(rset.getInt("MOY_TEST"));
						
						// Affichage de la liste
						System.out.println(notation); // toString sous-entendu
						
						//Création de l'association
						associationNotation.creerLien(mod, etu, notation);
					}
				}
			}
			
			// Récupéraation des notes en ACSI
			for(Lien lien : associationNotation.getLiens(acsi)){
				System.out.println("Note de l'étudiant " + lien.getEtudiant().getNumEt() + " dans la matière " + lien.getModule().getCode());
				System.out.println(lien.getNotation().toString() + '\n');
			}
			
			
			// Fermeture de l'instruction (liberation des ressources)
			stmt.close();
			System.out.println("\nOk.\n");
		} catch (SQLException e) {
			e.printStackTrace();// Arggg!!!
			System.out.println(e.getMessage() + "\n");
		}
	}
}