package com.esigelec.service;

import java.util.List;

import com.esigelec.dao.choix.ChoixDAO;
import com.esigelec.dao.choix.ChoixDAOImpl;
import com.esigelec.model.Choix;

public class ChoixService {
    
    private final ChoixDAO choixDAO;

    public ChoixService() {
        this.choixDAO = new ChoixDAOImpl();
    }

    public void ajouterChoix(Choix choix) throws Exception {
        if (detecterConflit(choix.getIdEtudiant(), choix.getIdSession())) {
            throw new Exception("Conflit d'horaire détecté : Vous avez déjà une session à la même date et heure.");
        }
        choixDAO.createChoix(choix);
    }

    public List<Choix> getChoixByEtudiantAndCampagne(Long idEtudiant, Long idCampagne) {
        return choixDAO.getChoixByEtudiantAndCampagne(idEtudiant, idCampagne);
    }

    public void modifierOrdre(Choix choix, int nouvellePriorite) {
        choix.setPriorite(nouvellePriorite);
        choixDAO.updateChoix(choix);
    }

    public void supprimerChoix(Long idChoix) {
        choixDAO.deleteChoix(idChoix);
    }

    public boolean detecterConflit(Long idEtudiant, Long idSession) {
        return choixDAO.hasConflitHoraire(idEtudiant, idSession);
    }

    // ProposerAlternatives could be built fetching available sessions via SessionDAO 
    // that don't conflict with existing ones. It will be implemented later in the view logic 
    // or by expanding this service with SessionDAO.
}
