package com.esigelec.dao.utilisateur;

import com.esigelec.model.Utilisateur;

public interface UtilisateurDAO {

    public Utilisateur findByEmail(String email);
}
