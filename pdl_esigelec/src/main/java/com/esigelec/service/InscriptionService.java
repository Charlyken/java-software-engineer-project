package com.esigelec.service;

import java.util.List;

import com.esigelec.dao.inscription.InscriptionDAO;
import com.esigelec.dao.inscription.InscriptionDAOImpl;
import com.esigelec.model.Inscription;

public class InscriptionService {

    private final InscriptionDAO inscriptionDAO;

    public InscriptionService() {
        this.inscriptionDAO = new InscriptionDAOImpl();
    }

    public List<Inscription> getInscriptionsByEtudiantAndCampagne(Long idEtudiant, Long idCampagne) {
        return inscriptionDAO.getInscriptionsByEtudiantAndCampagne(idEtudiant, idCampagne);
    }

    public List<Inscription> getInscriptionsByCampagne(Long idCampagne) {
        return inscriptionDAO.getInscriptionsByCampagne(idCampagne);
    }

    public int countInscriptionsBySession(Long idSession) {
        return inscriptionDAO.countInscriptionsBySession(idSession);
    }
}
