package com.esigelec.service;

import java.time.LocalDate;
import com.esigelec.dao.campagne.CampagneDAO;
import com.esigelec.model.*;

/**
 * @author Julien
 */
public class CampagneService {
    private CampagneDAO campagneDAO;

    public CampagneService(CampagneDAO campagneDAO) {
        this.campagneDAO = campagneDAO;
    }

    public Campagne createCampagne(String nom, LocalDate dateDebut, LocalDate dateFin, int nbreChoix) throws Exception {
        if (nom == null || nom.isEmpty() || dateDebut == null || dateFin == null || nbreChoix <= 0) {
            throw new IllegalArgumentException("Tous les champs sont obligatoires et doivent être valides.");
        }
        if (dateDebut.isAfter(dateFin)) {
            throw new IllegalArgumentException("La date de début doit être avant la date de fin.");
        }
        Campagne campagne = new Campagne(nom, dateDebut, dateFin, nbreChoix, EtatCampagne.EN_PREPARATION);
        campagneDAO.create(campagne);
        return campagne;
    }

    public void ouvrirCampagne(Long campagneId){
        try {
            changerEtat(campagneId, EtatCampagne.OUVERTE);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'ouverture de la campagne : " + e.getMessage(), e);
        }
    }

    public void fermerCampagne(Long campagneId){
        try {
            changerEtat(campagneId, EtatCampagne.FERMEE);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la fermeture de la campagne : " + e.getMessage(), e);
        }
    }

    public void validerCampagne(Long campagneId){
        try {
            changerEtat(campagneId, EtatCampagne.VALIDEE);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la validation de la campagne : " + e.getMessage(), e);
        }
    }

    public void archiverCampagne(Long campagneId){
        try {
            changerEtat(campagneId, EtatCampagne.ARCHIVEE);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'archivage de la campagne : " + e.getMessage(), e);
        }
    }   

    public void changerEtat(Long campagneId, EtatCampagne nouvelEtat) throws Exception {
        Campagne campagne = campagneDAO.findById(campagneId);
        if (campagne == null) {
            throw new IllegalArgumentException("Campagne non trouvée.");
        }
        if (nouvelEtat == null) {
            throw new IllegalArgumentException("Le nouvel état ne peut pas être null.");
        }

        EtatCampagne etatActuel = campagne.getEtat();
        boolean transitionValide = (etatActuel == EtatCampagne.EN_PREPARATION && nouvelEtat == EtatCampagne.OUVERTE) ||
                                  (etatActuel == EtatCampagne.OUVERTE && nouvelEtat == EtatCampagne.FERMEE) ||
                                  (etatActuel == EtatCampagne.FERMEE && nouvelEtat == EtatCampagne.VALIDEE) ||
                                  (etatActuel == EtatCampagne.VALIDEE && nouvelEtat == EtatCampagne.ARCHIVEE);
        if (!transitionValide) {
            throw new IllegalStateException("Transition d'état invalide : " + etatActuel + " -> " + nouvelEtat);
        }
        campagneDAO.updateEtat(campagneId, nouvelEtat);
    }
    public void deleteCampagne(Long campagneId) {
        try {
            campagneDAO.delete(campagneId);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la suppression de la campagne : " + e.getMessage(), e);
        }
    }

    public void updateCampagne(Campagne campagne){
        if (campagne == null || campagne.getId() == null) {
            throw new IllegalArgumentException("La campagne et son ID ne peuvent pas être nuls.");
        }
        if (campagne.getNom() == null || campagne.getNom().isEmpty() || campagne.getDateDebut() == null || campagne.getDateFin() == null || campagne.getNbreChoix() <= 0) {
            throw new IllegalArgumentException("Tous les champs sont obligatoires et doivent être valides.");
        }
        if (campagne.getDateDebut().isAfter(campagne.getDateFin())) {
            throw new IllegalArgumentException("La date de début doit être avant la date de fin.");
        }
        try {
            campagneDAO.update(campagne);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la mise à jour de la campagne : " + e.getMessage(), e);
        }
    }
    
    public java.util.List<Campagne> getAllCampagnes() {
        return campagneDAO.findAll();
    }
}
