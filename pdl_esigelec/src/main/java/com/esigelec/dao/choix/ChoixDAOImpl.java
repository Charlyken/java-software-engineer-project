package com.esigelec.dao.choix;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.esigelec.dao.DataBaseConnection;
import com.esigelec.model.Choix;

public class ChoixDAOImpl implements ChoixDAO {

    @Override
    public void createChoix(Choix choix) {
        String query = "INSERT INTO CHOIX (id_utilisateur, id_session, id_campagne, date_saisie, priorite) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setLong(1, choix.getIdEtudiant());
            pstmt.setLong(2, choix.getIdSession());
            pstmt.setLong(3, choix.getIdCampagne());
            pstmt.setDate(4, choix.getDateSaisie());
            pstmt.setInt(5, choix.getPriorite());
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Choix> getChoixByEtudiantAndCampagne(Long idEtudiant, Long idCampagne) {
        List<Choix> choixList = new ArrayList<>();
        String query = "SELECT * FROM CHOIX WHERE id_utilisateur = ? AND id_campagne = ? ORDER BY priorite ASC";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setLong(1, idEtudiant);
            pstmt.setLong(2, idCampagne);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Choix choix = new Choix(
                        rs.getLong("id_choix"),
                        rs.getLong("id_utilisateur"),
                        rs.getLong("id_session"),
                        rs.getLong("id_campagne"),
                        rs.getDate("date_saisie"),
                        rs.getInt("priorite")
                );
                choixList.add(choix);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return choixList;
    }

    @Override
    public List<Choix> getChoixByCampagne(Long idCampagne) {
        List<Choix> choixList = new ArrayList<>();
        String query = "SELECT * FROM CHOIX WHERE id_campagne = ? ORDER BY id_utilisateur ASC, priorite ASC, date_saisie ASC";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setLong(1, idCampagne);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Choix choix = new Choix(
                        rs.getLong("id_choix"),
                        rs.getLong("id_utilisateur"),
                        rs.getLong("id_session"),
                        rs.getLong("id_campagne"),
                        rs.getDate("date_saisie"),
                        rs.getInt("priorite")
                );
                choixList.add(choix);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return choixList;
    }

    @Override
    public Choix getChoixById(Long idChoix) {
        String query = "SELECT * FROM CHOIX WHERE id_choix = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setLong(1, idChoix);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Choix(
                        rs.getLong("id_choix"),
                        rs.getLong("id_utilisateur"),
                        rs.getLong("id_session"),
                        rs.getLong("id_campagne"),
                        rs.getDate("date_saisie"),
                        rs.getInt("priorite")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateChoix(Choix choix) {
        String query = "UPDATE CHOIX SET priorite = ?, date_saisie = ?, id_session = ? WHERE id_choix = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, choix.getPriorite());
            pstmt.setDate(2, choix.getDateSaisie());
            pstmt.setLong(3, choix.getIdSession());
            pstmt.setLong(4, choix.getId());
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteChoix(Long idChoix) {
        String query = "DELETE FROM CHOIX WHERE id_choix = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setLong(1, idChoix);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean hasConflitHoraire(Long idEtudiant, Long idSession) {
        String query = """
                SELECT COUNT(*) FROM CHOIX c
                JOIN SESSIONS s1 ON c.id_session = s1.id_session
                JOIN SESSIONS s2 ON s2.id_session = ?
                WHERE c.id_utilisateur = ?
                AND s1.date_session = s2.date_session
                AND s1.heure_debut = s2.heure_debut
                """;
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setLong(1, idSession);
            pstmt.setLong(2, idEtudiant);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
