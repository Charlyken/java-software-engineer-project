package com.esigelec.dao.session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.esigelec.dao.DataBaseConnection;
import com.esigelec.model.Session;

public class SessionDAOImpl implements SessionDAO {
    private static final String INSERT = "INSERT INTO SESSIONS"
                                            +"(date, heure_debut, heure_fin, capacite_max, id_campagne, id_dominante) VALUES (?, ?, ?, ?, ?, ?)";
    
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
                ps.setLong(5, session.getCampagne().getId());
                ps.setLong(6, session.getDominante().getId());

                ps.executeUpdate();

            }catch(SQLException e){
                System.err.println(e.getMessage());
                throw new RuntimeException(e);
            }
        
    }

    @Override
    public List<Session> getAllSessions() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Session getSessionById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteSession(Long id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateSession(Session session) {
        // TODO Auto-generated method stub
        
    }
    
}
