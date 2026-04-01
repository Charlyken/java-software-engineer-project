package com.esigelec.dao.utilisateur;

import com.esigelec.dao.DataBaseConnection;
import com.esigelec.model.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.esigelec.model.Role;

/**
 * Implémentation du DAO de l'entité Utilisateur
 * @author Julien Keuni
 */
public class UtilisateurDAOImpl implements  UtilisateurDAO {

    private static final String FIND_BY_EMAIL = "SELECT * FROM UTILISATEURS WHERE email = ?";

    @Override
    public Utilisateur findByEmail(String email) {
        Utilisateur user = null;
        
        try {
            Connection con = DataBaseConnection.getConnection();

            try (PreparedStatement ps = con.prepareStatement(FIND_BY_EMAIL)) {
                ps.setString(1, email);
                
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        user = new Utilisateur();
                        user.setId(rs.getLong("id_utilisateur"));
                        user.setNom(rs.getString("nom"));
                        user.setPrenom(rs.getString("prenom"));
                        user.setEmail(rs.getString("email"));
                        user.setMotDePasse(rs.getString("mot_de_passe"));

                        if ( rs.getString("role") != null) {
                            user.setRole(Role.valueOf(rs.getString("role").toUpperCase()));
                        }
                    }
                }
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
