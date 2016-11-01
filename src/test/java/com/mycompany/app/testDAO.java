package com.mycompany.app;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class testDAO 
{
    public static void main (String[] args) throws SQLException {

        Connection conn = ConnexionUnique.getInstance().getConnexion();
        DAOEtudiantI DAOEt = DAOEtudiantI.getInstance();
        DAOModuleI DAOMod = DAOModuleI.getInstance();
        DAONotationI DAONote = DAONotationI.getInstance();
        DAOEt.setConnection(conn);
        DAOMod.setConnection(conn);
        DAONote.setConnection(conn);

        try {
        	@SuppressWarnings("unused")
			Statement stmt = ConnexionUnique.getInstance().getConnexion().createStatement();

        	ArrayList<Etudiant> ALEtudiant = new ArrayList<Etudiant>();
            ArrayList<Etudiant> etuAnnee2 = new ArrayList<Etudiant>();
            ALEtudiant = DAOEt.findAll();
            
            for(Etudiant e : ALEtudiant)
            {
            	if(e.getAnnee() == 2)
            	{
            		etuAnnee2.add(e);
            	}
            }
            
            ArrayList<Notation> ALNotes = new ArrayList<Notation>();
            ALNotes = DAONote.findAll();
            for(Notation n : ALNotes)
            {
            	if(n.getModule().getCode().equals("ACSI"))
            	{
            		System.out.print(n.getEtudiant().getPrenomEt() + " " 
            					   + n.getEtudiant().getNomEt() + " | MoyCC : " 
            					   + n.getMoyCC() + " + 1 = ");
            		n.setMoyCC(n.getMoyCC()+1);
            		System.out.print(n.getMoyCC() + "; MoyTest : " + n.getMoyTest() + " + 1 = ");
            		n.setMoyTest(n.getMoyTest()+1);
            		System.out.println(n.getMoyTest());	
            	}
            }
        }
        catch ( SQLException e ) {
            e.printStackTrace() ; // Arggg!!!
            System.out.println(e. getMessage() + "\n" ) ;
        }

        finally {
            if ( conn != null ) {
                conn.close();
            }
        }
    }

}