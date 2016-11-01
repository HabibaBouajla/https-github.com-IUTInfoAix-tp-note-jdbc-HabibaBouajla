package com.mycompany.app;

import java.sql.*;

public class ConnexionUnique
{
	//Données membre
	private Connection connexion = null;
	private static ConnexionUnique instance = null;
	static final String urlConn = "jdbc:mysql://mysql-habiba.alwaysdata.net:3306/habiba_testmaven";
	static final String loginConn = "habiba_mysql";
	static final String passConn = "mysql06";
	
	//Fonctions
	private ConnexionUnique() throws SQLException
	{
		connexion = DriverManager.getConnection(urlConn,loginConn,passConn);
	}
	public Connection getConnexion(){return connexion;}
	public static ConnexionUnique getInstance() throws SQLException
	{
		if(instance == null)
			instance = new ConnexionUnique();
		return instance;
	}		
}