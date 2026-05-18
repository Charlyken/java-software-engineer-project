package com.esigelec.dao.campagne;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.esigelec.dao.DataBaseConnection;
import com.esigelec.model.Campagne;
import com.esigelec.model.EtatCampagne;

public class CampagneDAOImpl implements CampagneDAO{

    private static final String INSERT = "INSERT INTO CAMPAGNES (nom, date_debut, date_fin, nbre_choix, etat) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM CAMPAGNES";
    private static final String SELECT_BY_ID = "SELECT * FROM CAMPAGNES WHERE id = ?";
    private static final String UPDATE_ETAT = "UPDATE CAMPAGNES SET etat = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM CAMPAGNES WHERE id = ?";
    private static final String UPDATE = "UPDATE CAMPAGNES SET nom = ?, date_debut = ?, date_fin = ?, nbre_choix = ?, etat = ? WHERE id = ?";

    @Override
   public void create (Campagne campagne){
        if (campagne == null) {
            return;
        }
        try (Connection con = DataBaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT)) {
            ps.setString(1, campagne.getNom());
            ps.setDate(2, java.sql.Date.valueOf(campagne.getDateDebut()));
            ps.setDate(3, java.sql.Date.valueOf(campagne.getDateFin()));
            ps.setInt(4, campagne.getNbreChoix());
            ps.setString(5, campagne.getEtat().name());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la création de la campagne : " + e.getMessage());
        }
    }

    @Override
    public List<Campagne> findAll(){
        List<Campagne> campagnes = new ArrayList<>();
        try (Connection con = DataBaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Campagne campagne = new Campagne();
                campagne.setId(rs.getLong("id"));
                campagne.setNom(rs.getString("nom"));
                campagne.setDateDebut(rs.getDate("date_debut").toLocalDate());
                campagne.setDateFin(rs.getDate("date_fin").toLocalDate());
                campagne.setNbreChoix(rs.getInt("nbre_choix"));
                campagne.setEtat(EtatCampagne.valueOf(rs.getString("etat")));
                campagnes.add(campagne);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des campagnes : " + e.getMessage());
        }
        return campagnes;
    }

    @Override
    public Campagne findById(Long id){
        if (id == null) {
            return null;
        }

        try (Connection con = DataBaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_BY_ID)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Campagne campagne = new Campagne();
                    campagne.setId(rs.getLong("id"));
                    campagne.setNom(rs.getString("nom"));
                    campagne.setDateDebut(rs.getDate("date_debut").toLocalDate());
                    campagne.setDateFin(rs.getDate("date_fin").toLocalDate());
                    campagne.setNbreChoix(rs.getInt("nbre_choix"));
                    campagne.setEtat(EtatCampagne.valueOf(rs.getString("etat")));
                    return campagne;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void updateEtat(Long idCampagne, EtatCampagne etat){
        if (idCampagne == null || etat == null) {
            return;
        }
        try (Connection con = DataBaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_ETAT)) {
            ps.setString(1, etat.name());
            ps.setLong(2, idCampagne);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la mise à jour de l'état de la campagne : " + e.getMessage());
        }

    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            return;
        }
        try (Connection con = DataBaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression de la campagne : " + e.getMessage());
        }
    }  
    
    @Override
    public void update(Campagne campagne){
        if (campagne == null || campagne.getId() == null) {
            return;
        }

        try (Connection con = DataBaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE)) {
            ps.setString(1, campagne.getNom());
            ps.setDate(2, java.sql.Date.valueOf(campagne.getDateDebut()));
            ps.setDate(3, java.sql.Date.valueOf(campagne.getDateFin()));
            ps.setInt(4, campagne.getNbreChoix());
            ps.setString(5, campagne.getEtat().name());
            ps.setLong(6, campagne.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la mise à jour de la campagne : " + e.getMessage());
        }
    }
}
