package com.esigelec.dao.inscription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.esigelec.dao.DataBaseConnection;
import com.esigelec.model.Inscription;

public class InscriptionDAOImpl implements InscriptionDAO {

    @Override
    public void createInscription(Inscription inscription) {
        String query = "INSERT INTO INSCRIPTIONS (id_utilisateur, id_session, id_campagne, date_inscription) VALUES (?, ?, ?, ?)";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setLong(1, inscription.getIdEtudiant());
            pstmt.setLong(2, inscription.getIdSession());
            pstmt.setLong(3, inscription.getIdCampagne());
            pstmt.setDate(4, inscription.getDateInscription());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Inscription> getInscriptionsByEtudiantAndCampagne(Long idEtudiant, Long idCampagne) {
        List<Inscription> inscriptions = new ArrayList<>();
        String query = "SELECT * FROM INSCRIPTIONS WHERE id_utilisateur = ? AND id_campagne = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setLong(1, idEtudiant);
            pstmt.setLong(2, idCampagne);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Inscription inscription = new Inscription(
                        rs.getLong("id_inscription"),
                        rs.getLong("id_utilisateur"),
                        rs.getLong("id_session"),
                        rs.getLong("id_campagne"),
                        rs.getDate("date_inscription")
                );
                inscriptions.add(inscription);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inscriptions;
    }

    @Override
    public List<Inscription> getInscriptionsByCampagne(Long idCampagne) {
        List<Inscription> inscriptions = new ArrayList<>();
        String query = "SELECT * FROM INSCRIPTIONS WHERE id_campagne = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setLong(1, idCampagne);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Inscription inscription = new Inscription(
                        rs.getLong("id_inscription"),
                        rs.getLong("id_utilisateur"),
                        rs.getLong("id_session"),
                        rs.getLong("id_campagne"),
                        rs.getDate("date_inscription")
                );
                inscriptions.add(inscription);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inscriptions;
    }

    @Override
    public int countInscriptionsBySession(Long idSession) {
        String query = "SELECT COUNT(*) FROM INSCRIPTIONS WHERE id_session = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setLong(1, idSession);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
