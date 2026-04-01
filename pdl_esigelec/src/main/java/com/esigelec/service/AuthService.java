package com.esigelec.service;

import com.esigelec.dao.utilisateur.UtilisateurDAO;
import com.esigelec.model.Utilisateur;

public class AuthService {
    private UtilisateurDAO utilisateurDAO;

    public AuthService(UtilisateurDAO utilisateurDAO) {
        this.utilisateurDAO = utilisateurDAO;
    }

    public Utilisateur login (String email, String mdp) throws Exception{

        Utilisateur utilisateur = utilisateurDAO.findByEmail(email);
        if(utilisateur == null){
            throw new Exception("Le utilisateur n'existe pas");
        }

        if(!utilisateur.getMotDePasse().equals(mdp)){
            throw new Exception("Les identifiants sont Incorrectes");
        }
        return  utilisateur;
    }
}
