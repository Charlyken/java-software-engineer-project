package com.esigelec.model;

public class Admin extends Utilisateur{

    public Admin() {}

    public Admin(String nom, String prenom, String email, String motDePasse) {
        super(nom, prenom, email, motDePasse, Role.ADMIN);
    }

}
