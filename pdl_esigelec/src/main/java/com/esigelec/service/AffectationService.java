package com.esigelec.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.esigelec.dao.campagne.CampagneDAOImpl;
import com.esigelec.dao.choix.ChoixDAO;
import com.esigelec.dao.choix.ChoixDAOImpl;
import com.esigelec.dao.inscription.InscriptionDAO;
import com.esigelec.dao.inscription.InscriptionDAOImpl;
import com.esigelec.dao.session.SessionDAO;
import com.esigelec.dao.session.SessionDAOImpl;
import com.esigelec.model.Choix;
import com.esigelec.model.Inscription;
import com.esigelec.model.Session;

public class AffectationService {

    private final ChoixDAO choixDAO;
    private final SessionDAO sessionDAO;
    private final InscriptionDAO inscriptionDAO;
    private final CampagneService campagneService;

    public AffectationService() {
        this.choixDAO = new ChoixDAOImpl();
        this.sessionDAO = new SessionDAOImpl();
        this.inscriptionDAO = new InscriptionDAOImpl();
        this.campagneService = new CampagneService(new CampagneDAOImpl());
    }

    public int lancerAffectation(Long idCampagne) {
        if (idCampagne == null) {
            throw new IllegalArgumentException("L'id de la campagne ne peut pas être nul.");
        }

        List<Session> sessions = sessionDAO.getAllSessionsByCampagne(idCampagne);
        if (sessions.isEmpty()) {
            throw new IllegalStateException("Aucune session disponible pour cette campagne.");
        }

        List<Choix> choix = choixDAO.getChoixByCampagne(idCampagne);
        if (choix.isEmpty()) {
            throw new IllegalStateException("Aucun choix trouvé pour cette campagne.");
        }

        Map<Long, Integer> capacitesMax = new HashMap<>();
        Map<Long, Integer> capacitesOccupees = new HashMap<>();
        for (Session s : sessions) {
            capacitesMax.put(s.getId(), s.getCapaciteMax());
            capacitesOccupees.put(s.getId(), inscriptionDAO.countInscriptionsBySession(s.getId()));
        }

        Map<Long, List<Choix>> choixParEtudiant = new LinkedHashMap<>();
        for (Choix c : choix) {
            choixParEtudiant.computeIfAbsent(c.getIdEtudiant(), k -> new java.util.ArrayList<>()).add(c);
        }

        int affectes = 0;
        for (Map.Entry<Long, List<Choix>> entry : choixParEtudiant.entrySet()) {
            for (Choix c : entry.getValue()) {
                Long idSession = c.getIdSession();
                int capaciteMax = capacitesMax.getOrDefault(idSession, 0);
                int occupee = capacitesOccupees.getOrDefault(idSession, 0);

                if (occupee < capaciteMax) {
                    Inscription inscription = new Inscription(
                            c.getIdEtudiant(),
                            idSession,
                            idCampagne,
                            Date.valueOf(LocalDate.now())
                    );
                    inscriptionDAO.createInscription(inscription);
                    capacitesOccupees.put(idSession, occupee + 1);
                    affectes++;
                    break;
                }
            }
        }

        try {
            campagneService.validerCampagne(idCampagne);
        } catch (Exception ignored) {
        }

        return affectes;
    }
}
