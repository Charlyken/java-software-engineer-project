package com.esigelec.service;

import java.util.List;

import com.esigelec.dao.session.SessionDAO;
import com.esigelec.model.Session;

public class SessionService {

    private SessionDAO sessionDAO;

    public SessionService(SessionDAO sessionDAO) {
        this.sessionDAO = sessionDAO;
    }
    
    public Session creerSession(int capaciteMax, Long idCampagne, Long idDominante, java.sql.Date date, String heureDebut, String heureFin) {

        if (idCampagne == null || date == null || heureDebut == null || heureFin == null || capaciteMax <= 0 || idDominante == null) {
            throw new IllegalArgumentException("Tous les champs sont obligatoires et doivent être valides.");
        }
        if (!HoraireValidator.isHoraireValide(heureDebut, heureFin)) {
            throw new IllegalArgumentException("Les horaires doivent être de 08:00 à 12:00 ou de 14:00 à 18:00.");
        }
        Session session = new Session(capaciteMax, idCampagne, idDominante, date, heureDebut, heureFin);
        sessionDAO.createSession(session);
        return session;
    }

    public List<Session> getSessionsByCampagne(Long idCampagne){
        if (idCampagne == null) {
            throw new IllegalArgumentException("L'id de la campagne ne peut etre null");
        }
        return sessionDAO.getAllSessionsByCampagne(idCampagne);
    }

    public Session getSessionById(Long idSession){
        if (idSession == null) {
            throw new IllegalArgumentException("L'id de la session ne peut etre null");
        }
        return sessionDAO.getSessionById(idSession);
    }

    public void updateSession(Session session) {
        if (session == null || session.getId() == null) {
            throw new IllegalArgumentException("La session ou son id ne peut etre null.");
        }
        if (session.getCampagne() == null || session.getDominante() == null || session.getDate() == null
                || session.getHeureDebut() == null || session.getHeureFin() == null || session.getCapaciteMax() <= 0) {
            throw new IllegalArgumentException("Tous les champs sont obligatoires et doivent etre valides.");
        }
        if (!HoraireValidator.isHoraireValide(session.getHeureDebut(), session.getHeureFin())) {
            throw new IllegalArgumentException("Les horaires doivent etre 08:30-12:30 ou 13:30-17:30.");
        }
        sessionDAO.updateSession(session);
    }
    
    public void deleteSession(Long idSession) {
        if (idSession == null) {
            throw new IllegalArgumentException("L'id de la session ne peut etre null.");
        }
        sessionDAO.deleteSession(idSession);
    }



    
}
