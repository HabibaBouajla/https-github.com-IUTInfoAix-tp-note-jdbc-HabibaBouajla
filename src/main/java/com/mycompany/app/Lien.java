package com.mycompany.app;

public class Lien 
{
	//Donn�es membres
	private Module module;
	private Etudiant etudiant;
	private Notation note;

	public Notation getNote() {return note;}
	public Module getModule() {return module;}
	public Etudiant getEtudiant() {return etudiant;}
	
	public void setNotation(Notation note) {this.note = note;}
	@Override
	public String toString() {
		return "Lien [module=" + module + ", etudiant=" + etudiant + ", note=" + note + "]";
	}
	//Fonctions
	public Lien(Module module, Etudiant etudiant) 
	{
		this.module = module;
		this.etudiant = etudiant;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((etudiant == null) ? 0 : etudiant.hashCode());
		result = prime * result + ((module == null) ? 0 : module.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lien other = (Lien) obj;
		if (etudiant == null) {
			if (other.etudiant != null)
				return false;
		} else if (!etudiant.equals(other.etudiant))
			return false;
		if (module == null) {
			if (other.module != null)
				return false;
		} else if (!module.equals(other.module))
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		return true;
	}

}
