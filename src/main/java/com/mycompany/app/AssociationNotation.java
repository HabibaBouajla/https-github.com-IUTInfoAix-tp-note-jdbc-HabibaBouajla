package com.mycompany.app;
import java.util.HashSet;
import java.util.Set;

public class AssociationNotation 
{
	//Données membres
	private Set<Lien> liens = new HashSet<Lien>();
	private static AssociationNotation instance = null;

	//Fonctions
	public void creerLien(Module mod, Etudiant etu, Notation note)
	{
		Lien lien = new Lien(mod, etu);
		lien.setNotation(note);
		liens.add(lien);
	}
	public void supprimerLien(Module mod, Etudiant etu)
	{
		for(Lien l : liens)
			if(l.getModule() == mod && l.getEtudiant() == etu)
				liens.remove(l);
	}
	public void supprimerLien(Lien lien)
	{
		liens.remove(lien);
	}
	
	public Lien getLien(Module module, Etudiant etudiant)
	{
		for(Lien l : liens)
			if(l.getModule() == module && l.getEtudiant() == etudiant)
				return l;
		return null;
	}
	public Set<Lien> getLiens(Etudiant etudiant)
	{
		Set<Lien> liensEtu = new HashSet<Lien>();
		for(Lien l : liens)
			if(l.getEtudiant() == etudiant)
				liensEtu.add(l);
		return liensEtu;
	}
	public Set<Lien> getLiens(Module module)
	{
		Set<Lien> liensMod = new HashSet<Lien>();
		for(Lien l : liens)
			if(l.getModule() == module)
				liensMod.add(l);
		return liensMod;
	}
	public Set<Lien> getLiens() {return liens;}
	
	public Set<Module> getModules(Etudiant etudiant)
	{
		Set<Module> modules = new HashSet<Module>();
		for(Lien l : liens )
			if(l.getEtudiant() == etudiant) modules.add(l.getModule());
		return modules;		
	}
	public Set<Etudiant> getEtudiants(Module module)
	{
		Set<Etudiant> etudiants = new HashSet<Etudiant>();
		for(Lien l : liens )
			if(l.getModule() == module) etudiants.add(l.getEtudiant());
		return etudiants;
	}
	
	public static AssociationNotation getInstance()
	{
		if(instance == null)
			instance = new AssociationNotation();
		return instance;
	}
}
