package com.esigelec.dao.session;

import java.util.List;

import com.esigelec.model.Session;

public interface SessionDAO {
    
    void createSession(Session session);
    List<Session> getAllSessionsByCampagne(Long idCampagne);
    Session getSessionById(Long id);
    void deleteSession(Long id);
    void updateSession(Session session);
}
