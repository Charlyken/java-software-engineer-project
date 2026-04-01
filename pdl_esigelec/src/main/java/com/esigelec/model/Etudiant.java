package com.esigelec.model;

public class Etudiant extends Utilisateur{
    private String numEtudiant;
    private String promo;

    public Etudiant() {}

    public Etudiant(String nom, String prenom, String email, String motDePasse, String numEtudiant, String promo) {
        super(nom, prenom, email, motDePasse, Role.ETUDIANT);
        this.numEtudiant = numEtudiant;
        this.promo = promo;
    }

    public String getNumEtudiant() {
        return numEtudiant;
    }

    public void setNumEtudiant(String numEtudiant) {
        this.numEtudiant = numEtudiant;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }
}
