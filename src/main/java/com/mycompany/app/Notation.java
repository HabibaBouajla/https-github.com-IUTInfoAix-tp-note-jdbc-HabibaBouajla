package com.mycompany.app;

public class Notation 
{
    private float moyCC;
    private float moyTest;
    private Etudiant etudiant;
    private Module module;

    public Notation(float moyCC, float moyTest) 
    {
        this.moyCC = moyCC;
        this.moyTest = moyTest;
    }

    public Notation() {}

    public float getMoyCC() 
    {
        return moyCC;
    }


    
    @Override
	public String toString() {
		return "Notation [moyCC=" + moyCC + ", moyTest=" + moyTest + ", etudiant=" + etudiant + ", module=" + module
				+ "]";
	}

	public float getMoyTest() {return moyTest;}
    public Etudiant getEtudiant() {return etudiant;}
    public Module getModule() {return module;}
    
    public void setMoyCC(float moyCC) {this.moyCC = moyCC;}
    public void setMoyTest(float moyTest) {this.moyTest = moyTest;}
    public void setEtudiant(Etudiant etudiant) {this.etudiant = etudiant;}
    public void setModule(Module module) {this.module = module;} 
}
