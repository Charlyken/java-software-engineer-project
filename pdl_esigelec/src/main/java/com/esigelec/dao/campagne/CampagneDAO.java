package com.esigelec.dao.campagne;

import java.util.List;

import com.esigelec.model.Campagne;
import com.esigelec.model.EtatCampagne;

public interface CampagneDAO {
    void create (Campagne campagne);
    List<Campagne> findAll();
    Campagne findById(Long id);
    void updateEtat(Long idCampagne, EtatCampagne etat);
    void delete(Long id);
    void update(Campagne campagne);
}
