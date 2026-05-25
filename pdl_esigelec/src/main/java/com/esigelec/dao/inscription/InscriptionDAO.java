package com.esigelec.dao.inscription;

import java.util.List;
import com.esigelec.model.Inscription;

public interface InscriptionDAO {
    void createInscription(Inscription inscription);
    List<Inscription> getInscriptionsByEtudiantAndCampagne(Long idEtudiant, Long idCampagne);
    List<Inscription> getInscriptionsByCampagne(Long idCampagne);
    int countInscriptionsBySession(Long idSession);
}
