package com.mycompany.app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOModuleI implements DAOModule{
    private static DAOModuleI instance;
    private Connection conn;

    public Module insert(Module Mod){
        final String req = "INSERT INTO MODULE "
                + "VALUES (" + Mod.getCode() + ", "
                + Mod.getLibelle() + ", "
                + Mod.gethCoursPrev()+ ", "
                + Mod.gethCoursRea() + ", "
                + Mod.gethTpPrev() + ", "
                + Mod.gethTpRea() + ", "
                + Mod.getDiscipline() + ", "
                + Mod.getCoefTest() + ", "
                + Mod.getCoefCc() + ")";
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(req);
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return Mod;
    }

    public boolean delete(Module Mod){
        final String req = "DELETE INTO MODULE "
                + "WHERE CODE = " + Mod.getCode();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(req);
            stmt.close();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Module Mod){
        final String req = "UPDATE MODULE "
                         + "SET CODE = " + Mod.getCode()
                         + ", LIBELLE = " + Mod.getLibelle()
                         + ", H_COURS_PREV =  "+ Mod.gethCoursPrev()
                         + ", H_COURS_REA = " + Mod.gethCoursRea()
                         + ", H_TP_PREV = "+ Mod.gethTpPrev()
                         + ", H_TP_REA = "+ Mod.gethTpRea()
                         + ", DISCIPLINE = "+ Mod.getDiscipline()
                         + ", COEF_TEST = "+ Mod.getCoefTest()
                         + ", COEF_CC = "+ Mod.getCoefCc()
                         + " WHERE CODE = " +Mod.getCode();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(req);
            stmt.close();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Module getById(String code)
    {
    	ArrayList<Module> ALMod = this.findAll();
        Module module = new Module();
        for(Module m : ALMod)
        {
        	if(m.getCode().equals(code)) 
        	{
        		module = m;
        	}
        		
        }
        return module;
    }

    public ArrayList<Module> findAll(){
        final String req = "SELECT * "
        			     + "FROM MODULE";
        ArrayList<Module> ALModule = new ArrayList<>();
        
        try {
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(req);
            
            while(rset.next()){
                Module Mod = new Module(rset.getString("CODE"),
                                        rset.getString("LIBELLE"),
                                        rset.getInt("H_COURS_PREV"),
                                        rset.getInt("H_COURS_REA"),
                                        rset.getInt("H_TP_PREV"),
                                        rset.getInt("H_TP_REA"),
                                        rset.getString("DISCIPLINE"),
                                        rset.getInt("COEFF_TEST"),
                                        rset.getInt("COEFF_CC"),
                                        null,
                                        null);
                ALModule.add(Mod);
            }
            stmt.close();
            return ALModule;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return ALModule;
    }

    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    public static DAOModuleI getInstance(){
        if (instance == null)
            instance = new DAOModuleI();
        return instance;
    }
}