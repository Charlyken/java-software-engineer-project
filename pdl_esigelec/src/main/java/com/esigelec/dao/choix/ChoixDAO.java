package com.esigelec.dao.choix;

import java.util.List;
import com.esigelec.model.Choix;

public interface ChoixDAO {
    void createChoix(Choix choix);
    List<Choix> getChoixByEtudiantAndCampagne(Long idEtudiant, Long idCampagne);
    Choix getChoixById(Long idChoix);
    void updateChoix(Choix choix);
    void deleteChoix(Long idChoix);
    boolean hasConflitHoraire(Long idEtudiant, Long idSession);
}
