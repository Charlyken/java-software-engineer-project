package com.esigelec.dao.session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.esigelec.dao.DataBaseConnection;
import com.esigelec.model.Session;

public class SessionDAOImpl implements SessionDAO {
    private static final String INSERT = "INSERT INTO SESSIONS"
                                            +"(date_session, heure_debut, heure_fin, capacite_max, id_campagne, id_dominante) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_SESSIONS = "SELECT * FROM SESSIONS WHERE id_campagne = ?";      
    private static final String SELECT_BY_ID = "SELECT * FROM SESSIONS WHERE id_session = ?";
    private static final String DELETE_BY_ID = "DELETE FROM SESSIONS WHERE id_session = ?";
    private static final String UPDATE = "UPDATE SESSIONS SET date_session = ?, heure_debut = ?, heure_fin = ?, capacite_max = ?, id_campagne = ?, id_dominante = ? WHERE id_session = ?";

    /**
     * Creation d'une session
     * @param session 
     */                                        
    @Override
    public void createSession(Session session) {
        if (session == null) {
            return;
        }
        try(Connection con = DataBaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(INSERT)){
                ps.setDate(1, session.getDate());
                ps.setString(2, session.getHeureDebut());
                ps.setString(3, session.getHeureFin());
                ps.setInt(4, session.getCapaciteMax());
                ps.setLong(5, session.getCampagne());
                ps.setLong(6, session.getDominante());

                ps.executeUpdate();

            }catch(SQLException e){
                System.err.println(e.getMessage());
                throw new RuntimeException("Erreur lors de la création de la Session : "+e);
            }
        
    }

    @Override
    public List<Session> getAllSessionsByCampagne(Long idCampagne) {
        if (idCampagne == null) {
            throw new RuntimeException("L'id de la campagne ne peut etre null");
        }
        List<Session> sessions = new ArrayList<>();
        try(Connection con = DataBaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(SELECT_SESSIONS)){
                ps.setLong(1, idCampagne);

                try(ResultSet rs = ps.executeQuery()){
                    while (rs.next()) {
                       Session session = new Session();
                       session.setId(rs.getLong("id_session"));
                       session.setCapaciteMax(rs.getInt("capacite_max"));
                       session.setCampagne(rs.getLong("id_campagne"));
                       session.setDominante(rs.getLong("id_dominante"));
                       session.setDate(rs.getDate("date_session"));
                       session.setHeureDebut(rs.getString("heure_debut")); 
                       session.setHeureFin(rs.getString("heure_fin"));
                       sessions.add(session);
                    }
                    return sessions;
                }
            }catch(SQLException e){
                System.err.println(e.getMessage());
                throw new RuntimeException("Erreur lors de la recherche des sessions par Campagne: "+e);
            }
    }

    @Override
    public Session getSessionById(Long idSession) {
        if (idSession == null ) {
            throw new RuntimeException("L'id de la session ne peut être null");
        }

        try (Connection con = DataBaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_BY_ID)) {
                ps.setLong(1, idSession);

                try(ResultSet rs = ps.executeQuery()){
                    if (rs.next()) {
                        Session session = new Session();
                        session.setId(rs.getLong("id_session"));
                        session.setDate(rs.getDate("date_session"));
                        session.setHeureDebut(rs.getString("heure_debut"));
                        session.setHeureFin(rs.getString("heure_fin"));
                        session.setCapaciteMax(rs.getInt("capacite_max"));
                        session.setCampagne(rs.getLong("id_campagne"));
                        session.setDominante(rs.getLong("id_dominante"));
                        return session;
                    }
                }
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("Erreur lors de la recherche de la session: "+e);
        }
        return null;
    }

    @Override
    public void deleteSession(Long id) {
        if (id == null) {
            throw new RuntimeException("L'id d'une session ne peut etre null");
        }

        try (Connection con = DataBaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_BY_ID)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
           System.err.println(e.getMessage());
           throw new RuntimeException("Erreur lors de la suppression de la session : "+e);
        }
        
    }

    @Override
    public void updateSession(Session session) {
       if (session == null || session.getId() == null) {
           throw new RuntimeException("La session ou son id ne peut etre null");
       }

       try (Connection con = DataBaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(UPDATE)) {
                ps.setDate(1, session.getDate());
                ps.setString(2, session.getHeureDebut());
                ps.setString(3, session.getHeureFin());
                ps.setInt(4, session.getCapaciteMax());
                ps.setLong(5, session.getCampagne());
                ps.setLong(6, session.getDominante());
                ps.setLong(7, session.getId());

                ps.executeUpdate();
       } catch (SQLException e) {
           System.err.println(e.getMessage());
           throw new RuntimeException("Erreur lors de la mise à jour de la session : "+e);
       }
        
    }
    
}
